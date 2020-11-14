package com.subjectmanage.controllers;


import com.subjectmanage.beans.group;
import com.subjectmanage.beans.topic;
import com.subjectmanage.mapper.TopicMapper;
import com.subjectmanage.services.GroupService;
import com.subjectmanage.services.GroupServiceImpl;
import com.subjectmanage.services.TopicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.*;
import java.util.List;

@Controller
@RequestMapping
public class indexController {

    @Autowired
    private GroupServiceImpl groupService;


    @RequestMapping(value = "/toindex")
    public String toIndex(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        List<group> groupList = groupService.getGroupListByTopicId(1);
        session.setAttribute("groupList",groupList);
        return "index";
    }

    @RequestMapping(value = "/toform")
    public String toForm(Model model, HttpServletRequest request){
        return "form";
    }



}
