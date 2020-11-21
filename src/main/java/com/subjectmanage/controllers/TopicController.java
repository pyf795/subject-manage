package com.subjectmanage.controllers;


import com.subjectmanage.beans.Group;
import com.subjectmanage.beans.Student;
import com.subjectmanage.beans.Teacher;
import com.subjectmanage.beans.Topic;
import com.subjectmanage.services.GroupService;
import com.subjectmanage.services.GroupServiceImpl;
import com.subjectmanage.services.StudentServiceImpl;
import com.subjectmanage.services.TopicServiceImpl;
import com.subjectmanage.utils.LayuiTableData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("/toTopicList")
    public String toTopicList(){
        return "stutopic";
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
        model.addAttribute("gender",topic.getGender());
        return "topic-details";
    }

    @RequestMapping("/chooseTopic")
    public String chooseTopic(@RequestParam int topic_id, @RequestParam int group_id, HttpSession session){


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
        String url = topic.getTopic_url();
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
}
