package com.subjectmanage.controllers;


import com.subjectmanage.beans.*;
import com.subjectmanage.services.AnnouncementServiceImpl;
import com.subjectmanage.services.GroupServiceImpl;
import com.subjectmanage.services.NewsService;
import com.subjectmanage.services.NewsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.*;
import java.util.*;

@Controller
@RequestMapping
public class indexController {

    @Autowired
    private GroupServiceImpl groupService;

    @Autowired
    private AnnouncementServiceImpl announcementService;

    @Autowired
    private NewsServiceImpl newsService;


    @RequestMapping(value = "/toWelcome")
    public String toWelcome(){
        return "welcome";
    }

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

    @RequestMapping(value = "/getAnnouncement")
    @ResponseBody
    public Map<String,Object> getAnnouncement(@RequestBody Map<String,Object> params){
        int startIndex =(int) params.get("startIndex");
        int pageSize =(int) params.get("pageSize");
        List<Announcement> announcementList = announcementService.selectAll(startIndex, pageSize);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("aList",announcementList);
        return map;
    }

    @RequestMapping(value = "/getAnnouncementTotal")
    @ResponseBody
    public Map<String,Object> getAnnouncementTotal(){
        int total = announcementService.getTotal();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("total",total);
        return map;
    }

    @RequestMapping(value = "/getNews")
    @ResponseBody
    public Map<String,Object> getNews(@RequestBody Map<String,Object> params){
        int startIndex =(int) params.get("startIndex");
        int pageSize =(int) params.get("pageSize");
        List<News> newsList = newsService.selectAll(startIndex,pageSize);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("newsList",newsList);
        return map;
    }

    @RequestMapping(value = "/getNewsTotal")
    @ResponseBody
    public Map<String,Object> getNewsTotal(){
        int total = newsService.getTotal();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("total",total);
        return map;
    }





}
