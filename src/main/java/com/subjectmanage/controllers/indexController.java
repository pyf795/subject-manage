package com.subjectmanage.controllers;


import com.subjectmanage.beans.Group;
import com.subjectmanage.beans.PageElement;
import com.subjectmanage.beans.Student;
import com.subjectmanage.beans.Teacher;
import com.subjectmanage.services.GroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.*;
import java.util.*;

@Controller
@RequestMapping
public class indexController {

    @Autowired
    private GroupServiceImpl groupService;




    @RequestMapping(value = "/index")
    public String toIndex(Model model, HttpServletRequest request,HttpSession session){
        Object user = session.getAttribute("loginUser");
        String user_type = (String)session.getAttribute("user_type");
        if(user_type!=null&&user_type.equals("学生")){
            Student loginUser =(Student) session.getAttribute("loginUser");
            session.setAttribute("user_name",loginUser.getStudent_name());
        }else if(user_type!=null&&user_type.equals("老师")){
            Teacher loginUser = (Teacher)session.getAttribute("loginUser");
            session.setAttribute("user_name",loginUser.getTeacher_name());
        }

        return "index";
    }





}
