package com.subjectmanage.controllers;


import com.subjectmanage.beans.Group;
import com.subjectmanage.beans.Student;
import com.subjectmanage.beans.Teacher;
import com.subjectmanage.services.FileService;
import com.subjectmanage.services.FileServiceImpl;
import com.subjectmanage.services.GroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileServiceImpl fileService;

    @Autowired
    private GroupServiceImpl groupService;

    @RequestMapping("/stu/fileuploadPage")
    public String tofileuploadPage(){
        return "stufile-upload";
    }

    @RequestMapping("/teach/fileuploadPage")
    public String toteachfileuploadPage(@RequestParam int topic_id,Model model){
        model.addAttribute("topic_id",topic_id);
        return "teachfile-upload";
    }

  /*  @RequestMapping("/stu/fileuploadPage")
    public String tofileuploadPage(Model model,@RequestParam int group_id){
        model.addAttribute("group_id",group_id);
        return "stufile-upload";
    }*/

    @PostMapping("/stu/uploadFile")
    public String uploadFile(HttpSession session,HttpServletRequest request, @RequestParam String type,
                             @RequestParam List<MultipartFile> uploadfile) {

        Student student =(Student)session.getAttribute("loginUser");
        int group_id = student.getGroup_id();
        Group group = groupService.getGroupById(group_id);
        if(fileService.uploadFile(group,type,student.getStudent_id(),uploadfile)){
            session.setAttribute("filemsg","文件上传成功！");
            return "redirect:/index#/topic/chosenTopic";
        }else{
            session.setAttribute("filemsg","上传文件失败！");
            return "redirect:/index#/topic/chosenTopic";
        }

    }

    @PostMapping("/teach/uploadFile")
    public String uploadMissionFile(HttpSession session,HttpServletRequest request, @RequestParam String fileType,
                             @RequestParam int topic_id,
                             @RequestParam List<MultipartFile> uploadfile) {

        Teacher teacher = (Teacher)session.getAttribute("loginUser");
        if(fileService.uploadMissionFile(fileType,teacher,topic_id,uploadfile)){
            session.setAttribute("filemsg","文件上传成功！");
            return "redirect:/index#/topic/teach/toTopicList";
        }else{
            session.setAttribute("filemsg","上传文件失败！");
            return "redirect:/index#/topic/teach/toTopicList";
        }

    }


}
