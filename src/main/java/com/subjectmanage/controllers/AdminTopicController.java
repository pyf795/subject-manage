package com.subjectmanage.controllers;


import com.subjectmanage.beans.*;
import com.subjectmanage.services.*;
import com.subjectmanage.utils.LayuiTableData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/topic")
public class AdminTopicController {

    @Autowired
    private TopicServiceImpl topicService;

    @Autowired
    private GroupServiceImpl groupService;

    @Autowired
    private TeacherServiceimpl teacherService;

    @Autowired
    private ScoreServiceImpl scoreService;

    @Autowired
    private FileServiceImpl fileService;

    @Autowired
    private IMailService mailService;

    @RequestMapping("/toTopicList")
    public String toadminTopicList(){
        return "admin-topic";
    }

    @RequestMapping("/addTopicPage")
    public String toaddTopicPage(){
        return "admin-addtopic";
    }

    @RequestMapping("/groupCheck")
    public String togroupCheck(){
        return "groupcheck";
    }

    @RequestMapping("/groupManage")
    public String togroupManage(){
        return "groupmanage";
    }

    @RequestMapping("/scores")//只有学生有
    public String topicScore(){
        return "admin-score";
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



    @RequestMapping("/deleteTopic")
    @ResponseBody
    public Map<String,Object>  admindeleteTopic(HttpSession session, @RequestParam int topic_id){
        int i=0;
        Topic topic = topicService.getTopicWithGroupById(topic_id);
        Map<String,Object> map = new HashMap<>();
        if(topic.getGroupList().size()>0){
            map.put("result","failed");
        }else{
            i = topicService.deleteTopic(topic_id);
            map.put("result","success");
        }
        return map;
    }


    @RequestMapping("/editTopic")
    public String adminEditTopic(@RequestParam int topic_id,Model model){
        Topic topic = topicService.getTopicWithGroupById(topic_id);
        model.addAttribute("topic_id",topic.getTopic_id());
        model.addAttribute("headline",topic.getHeadline());
        model.addAttribute("groupList",topic.getGroupList());
        model.addAttribute("type",topic.getType());
        model.addAttribute("content",topic.getContent());
        model.addAttribute("grade",topic.getGrade());
        return "admin-edittopic";
    }

    @RequestMapping("/edit")
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

        return "redirect:/admin/index#//admin/topic/toTopicList";
    }


    private void update(Integer volume,int group_id){
        Group group = groupService.getGroupById(group_id);
        group.setVolume(volume);
        groupService.updateGroup(group);
    }

    @RequestMapping("/add")
    public String addTopic(@RequestParam int teacher_id,@RequestParam String headline,@RequestParam String grade,@RequestParam String type,
                           @RequestParam String content,Integer group0,Integer group1,Integer group2,Integer group3
                           ,HttpSession session){
        Teacher teacher = teacherService.selectTeacher(teacher_id);
        Date date=new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         Topic topic = new Topic();
         topic.setHeadline(headline);
         topic.setContent(content);
         topic.setTeacher_id(teacher_id);
         topic.setGrade(grade);
         topic.setTeacher_name(teacher.getTeacher_name());
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


        return "redirect:/admin/index#//admin/topic/toTopicList";
    }

    private void add(Integer volume,int topic_id){
        Group group = new Group();
        group.setCurrent_numbers(0);
        group.setTopic_id(topic_id);
        group.setVolume(volume);
        groupService.addGroup(group);
    }


    @RequestMapping("/getTopicScores")
    @ResponseBody
    public LayuiTableData getTopicScores(@RequestParam int page,@RequestParam int limit,HttpSession session){
        int startIndex = (page-1)*limit;
        List<Score> scoreList = scoreService.selectAll(startIndex,limit);
        int count = scoreService.selectAllTotal();
        return LayuiTableData.layData(count,scoreList);
    }

}
