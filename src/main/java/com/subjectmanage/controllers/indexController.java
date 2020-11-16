package com.subjectmanage.controllers;


import com.subjectmanage.beans.group;
import com.subjectmanage.services.GroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.*;
import java.util.List;

@Controller
@RequestMapping
public class indexController {

    @Autowired
    private GroupServiceImpl groupService;


    @RequestMapping(value = "/index")
    public String toIndex(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        List<group> groupList = groupService.getGroupListByTopicId(1);
        session.setAttribute("groupList",groupList);
        request.setAttribute("msg","hello1122");
        return "index";
    }

    @RequestMapping(value = "/toform")
    public String toForm(Model model, HttpServletRequest request){
        return "form";
    }



}
