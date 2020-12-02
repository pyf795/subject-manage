package com.subjectmanage.controllers;


import com.subjectmanage.beans.*;
import com.subjectmanage.services.*;
import com.subjectmanage.utils.LayuiTableData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    private TopicServiceImpl topicService;

    @Autowired
    private GroupServiceImpl groupService;

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private ScoreServiceImpl scoreService;

    @Autowired
    private FileServiceImpl fileService;

    @RequestMapping("/teach/toTopicList")
    public String toteachTopicList(){
        return "teachtopic";
    }

    @RequestMapping("/teach/addTopicPage")
    public String toaddTopicPage(){
        return "teach-addtopic";
    }

    @RequestMapping("/toTopicList")
    public String toTopicList(){
        return "stutopic";
    }

    @RequestMapping("/chosenTopic")//只有学生有
    public String chosenTopic(){
        return "chosen-topic";
    }

    @RequestMapping("/topicScore")//只有学生有
    public String topicScore(){
        return "stuscore";
    }


    @RequestMapping("/getTopicList")
    @ResponseBody
    public LayuiTableData topicList(@RequestParam int page,@RequestParam int limit,Model model){

        //处理分页开始
        int startIndex = (page-1)*limit;
        List<Topic> topicList = topicService.selectAll(startIndex,limit); //分页数据
        int count = topicService.getTotal();  //数据总数
        LayuiTableData layuiTableData = LayuiTableData.layData(count,topicList);//转换成前端需要的数据格式
        return layuiTableData;
    }


    @RequestMapping("/details")
    public String getTopicDetails(@RequestParam int topic_id,Model model){
        Topic topic = topicService.getTopicWithGroupById(topic_id);
        model.addAttribute("topic_id",topic.getTopic_id());
        model.addAttribute("headline",topic.getHeadline());
        model.addAttribute("release_time",topic.getRelease_time());
        model.addAttribute("teacher",topic.getTeacher());
        model.addAttribute("groupList",topic.getGroupList());
        model.addAttribute("type",topic.getType());
        model.addAttribute("content",topic.getContent());
        model.addAttribute("grade",topic.getGrade());
        return "topic-details";
    }

    @RequestMapping("/chooseTopic")//只有学生有
    public String chooseTopic(@RequestParam int topic_id, @RequestParam int group_id, HttpSession session){

        String user_type = session.getAttribute("user_type").toString();
            if(!user_type.equals("学生")){
                session.setAttribute("tmsg","选择失败！没有权限");
            }
            Student loginUser =(Student) session.getAttribute("loginUser");

            Group group = groupService.getGroupById(group_id);
            if(group.getCurrent_numbers()+1<=group.getVolume()&&loginUser.getGroup_id()==0){
                loginUser.setGroup_id(group_id);
                studentService.updateStudent(loginUser);
                group.setCurrent_numbers(group.getCurrent_numbers()+1);
                groupService.updateGroup(group);
                session.setAttribute("tmsg","选择成功！课题情况请到系统里查看");
            }else if(loginUser.getGroup_id()!=0){
                session.setAttribute("tmsg","选择失败！你已经选择过课题了！");
            }
            else{
                session.setAttribute("tmsg","选择失败！人数已满请重新选择！");
            }

        return "redirect:/index#/topic/details?topic_id="+topic_id;
    }


    @RequestMapping(value="/downloadTopic")
    public void downloadTopic(HttpServletRequest req, Model model, HttpServletResponse response,@RequestParam int topic_id)
            throws ServletException, IOException {

        Topic topic = topicService.getTopicWithGroupById(topic_id);
        File file = fileService.getFileById(topic.getFile_id());
        String url = file.getFile_url();
        Path path = Paths.get("src/main/resources/static/",url);
        String filename=path.toString();
        String downFilename=filename.substring(filename.lastIndexOf("\\")+1);//要下载的文件名称
        response.setContentType("text/plain");
        response.setHeader("Location",downFilename);
        response.setHeader("Content-Disposition", "attachment; filename=" + downFilename);
        OutputStream outputStream = response.getOutputStream();
        InputStream inputStream = new FileInputStream( filename);
        byte[] buffer = new byte[1024];
        int i = -1;
        while ((i = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, i);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();

    }

    @RequestMapping("/searchTopics")
    @ResponseBody
    public LayuiTableData searchTopics(@RequestParam String topic_name,@RequestParam String grade,@RequestParam String type,@RequestParam String teacher_name,
                                       @RequestParam int page,@RequestParam int limit,
                                       Model model){
        int startIndex = (page-1)*limit;
        if(grade.equals("unselect")){
            grade = null;
        }
        List<Topic> topicList = topicService.searchTopics(topic_name,type,grade,teacher_name,startIndex,limit); //分页数据
        int count = topicService.getSearchTotal(topic_name,type,grade,teacher_name);  //数据总数
        LayuiTableData layuiTableData = LayuiTableData.layData(count,topicList);//转换成前端需要的数据格式
        return layuiTableData;
    }


    @RequestMapping("/getChosenTopic") //只有学生有
    @ResponseBody
    public LayuiTableData getChosenTopics(@RequestParam int page,@RequestParam int limit,HttpSession session){
       Student loginUser = (Student) session.getAttribute("loginUser");
       if(loginUser.getGroup_id()!=0){
           List<Group> groupList = groupService.getGroupWithTopic(loginUser.getGroup_id());
           int count = groupList.size();  //数据总数
           LayuiTableData layuiTableData = LayuiTableData.layData(count,groupList);//转换成前端需要的数据格式
           return layuiTableData;
       }

        return LayuiTableData.layData(0,null);
    }

    @RequestMapping("/getTopicScores") //只有学生有
    @ResponseBody
    public LayuiTableData getTopicScores(@RequestParam int page,@RequestParam int limit,HttpSession session){
        Student loginUser = (Student) session.getAttribute("loginUser");
        int student_id = loginUser.getStudent_id();
        List<Score> scoreList = scoreService.getScoreByStudent(student_id);
        int count = scoreList.size();
        return LayuiTableData.layData(count,scoreList);
    }




    @RequestMapping("/teach/getTopicList")
    @ResponseBody
    public LayuiTableData teachtopicList(@RequestParam int page,@RequestParam int limit,HttpSession session){

        Teacher loginUser = (Teacher)session.getAttribute("loginUser");
        //处理分页开始

        int startIndex = (page-1)*limit;
        List<Topic> topicList = topicService.selectTopicByTid(loginUser.getTeacher_id(),startIndex,limit);
        int count = topicService.getTotalByTid(loginUser.getTeacher_id());  //数据总数
        LayuiTableData layuiTableData = LayuiTableData.layData(count,topicList);//转换成前端需要的数据格式
        return layuiTableData;
    }

    @RequestMapping("/teach/searchTopics")
    @ResponseBody
    public LayuiTableData teachsearchTopics(@RequestParam String topic_name,@RequestParam String grade,@RequestParam String type,
                                       @RequestParam int page,@RequestParam int limit,
                                       HttpSession session){
        Teacher loginUser = (Teacher)session.getAttribute("loginUser");
        int startIndex = (page-1)*limit;
        if(grade.equals("unselect")){
            grade = null;
        }
        List<Topic> topicList = topicService.searchTeachTopics(topic_name,type,grade,loginUser.getTeacher_id(),startIndex,limit); //分页数据
        int count = topicService.getTeachSearchTotal(topic_name,type,grade,loginUser.getTeacher_id());  //数据总数
        LayuiTableData layuiTableData = LayuiTableData.layData(count,topicList);//转换成前端需要的数据格式
        return layuiTableData;
    }

    @RequestMapping("/teach/deleteTopic")
    @ResponseBody
    public String teachdeleteTopic(HttpSession session,@RequestParam int topic_id){
        int i = topicService.deleteTopic(topic_id);
        if(i>0){
            return "success";
        }
        return "false";
    }


    @RequestMapping("/teach/editTopic")
    public String teachEditTopic(@RequestParam int topic_id,Model model){
        Topic topic = topicService.getTopicWithGroupById(topic_id);
        model.addAttribute("topic_id",topic.getTopic_id());
        model.addAttribute("headline",topic.getHeadline());
        model.addAttribute("groupList",topic.getGroupList());
        model.addAttribute("type",topic.getType());
        model.addAttribute("content",topic.getContent());
        model.addAttribute("grade",topic.getGrade());
        return "teach-edittopic";
    }

    @RequestMapping("/teach/edit")
    public String EditTopic(@RequestParam int topic_id,@RequestParam String headline,@RequestParam String grade,
                            @RequestParam String type, @RequestParam String content,
                            Integer group0,Integer group1,Integer group2,Integer group3,
                            Integer volume0,Integer volume1,Integer volume2,Integer volume3,
                            HttpSession session){
        Topic topic = topicService.getTopicWithGroupById(topic_id);
        topic.setHeadline(headline);
        topic.setContent(content);
        topic.setGrade(grade);
        topic.setType(type);
        topicService.updateTopic(topic);
        if(group0!=null){
            if(volume0>0){
                update(volume0,group0);
            }else {
                groupService.deleteGroup(group0);
            }
        }
        if(group1!=null){
            if(volume1>0){
                update(volume1,group1);
            }else {
                groupService.deleteGroup(group1);
            }
    }
        if(group2!=null){
            if(volume0>2){
                update(volume2,group2);
            }else {
                groupService.deleteGroup(group2);
            }
        }
        if(group3!=null){
            if(volume0>0){
                update(volume3,group3);
            }else {
                groupService.deleteGroup(group3);
            }
        }

        return "redirect:/index#/topic/teach/toTopicList";
    }


    private void update(Integer volume,int group_id){
        Group group = groupService.getGroupById(group_id);
        group.setVolume(volume);
        groupService.updateGroup(group);
    }

    @RequestMapping("/teach/add")
    public String addTopic(@RequestParam String headline,@RequestParam String grade,@RequestParam String type,
                           @RequestParam String content,Integer group0,Integer group1,Integer group2,Integer group3
                           ,HttpSession session){
        Teacher loginUser = (Teacher)session.getAttribute("loginUser");
        int teacher_id = loginUser.getTeacher_id();
        String teacher_name = loginUser.getTeacher_name();
        Date date=new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         Topic topic = new Topic();
         topic.setHeadline(headline);
         topic.setContent(content);
         topic.setTeacher_id(teacher_id);
         topic.setGrade(grade);
         topic.setTeacher_name(teacher_name);
         topic.setRelease_time(simpleDate.format(date));
         topic.setType(type);
         topicService.addTopic(topic);//添加topic


        if(group0!=null){
           add(group0,topic.getTopic_id());
        }
        if(group1!=null){
            add(group0,topic.getTopic_id());
        }
        if(group2!=null){
            add(group0,topic.getTopic_id());
        }
        if(group3!=null){
            add(group0,topic.getTopic_id());
        }


        return "redirect:/index#/topic/teach/toTopicList";
    }

    private void add(Integer volume,int topic_id){
        Group group = new Group();
        group.setCurrent_numbers(0);
        group.setTopic_id(topic_id);
        group.setVolume(volume);
        groupService.addGroup(group);
    }
}
