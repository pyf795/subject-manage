package com.subjectmanage.controllers;


import com.subjectmanage.beans.*;
import com.subjectmanage.services.AnnouncementServiceImpl;
import com.subjectmanage.services.GroupServiceImpl;
import com.subjectmanage.services.NewsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class adminIndexController {

    @Autowired
    private GroupServiceImpl groupService;

    @Autowired
    private AnnouncementServiceImpl announcementService;

    @Autowired
    private NewsServiceImpl newsService;


    @RequestMapping(value = "/toWelcome")
    public String toWelcome(){
        return "admin-welcome";
    }

    @RequestMapping(value = "/index")
    public String toIndex(Model model, HttpServletRequest request,HttpSession session){

            Admin loginUser =(Admin) session.getAttribute("loginAdmin");
            session.setAttribute("admin_name",loginUser.getAdmin_name());

        return "admin-index";
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
