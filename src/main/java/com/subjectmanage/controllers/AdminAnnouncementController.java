package com.subjectmanage.controllers;

import com.subjectmanage.beans.Announcement;
import com.subjectmanage.services.AdminServiceImpl;
import com.subjectmanage.services.AnnouncementServiceImpl;
import com.subjectmanage.utils.LayuiTableData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/announcement/")
public class AdminAnnouncementController {

    @Autowired
    private AnnouncementServiceImpl announcementService;


    @RequestMapping("/toAnnouncementList")
    public String toAnnouncementList(){
        return "admin-announcement";
    }

    @RequestMapping("/add")
    public String add(){
        return "admin-addannouncement";
    }

    @RequestMapping("/edit")
    public String edit(Model model,@RequestParam int a_id){
        Announcement announcement = announcementService.selectAnnouncementById(a_id);
        model.addAttribute("a_id",a_id);
        model.addAttribute("a_title",announcement.getA_title());
        model.addAttribute("release_time",announcement.getRelease_time());
        model.addAttribute("a_content",announcement.getA_content());
        return "admin-editannouncement";
    }

    @RequestMapping("/getAnnouncementList")
    @ResponseBody
    public LayuiTableData getAnnouncementList(@RequestParam int page, @RequestParam int limit, HttpSession session){

        int startIndex = (page-1)*limit;
        int count = announcementService.getTotal();
        List<Announcement> announcementList = announcementService.selectAll(startIndex,limit);
        LayuiTableData layuiTableData = LayuiTableData.layData(count,announcementList);//转换成前端需要的数据格式
        return layuiTableData;
    }

    @RequestMapping("/searchAnnouncement")
    @ResponseBody
    public LayuiTableData searchAnnouncementList(@RequestParam int page, @RequestParam int limit,
                                         @RequestParam String a_title, @RequestParam String release_time,
                                         HttpSession session){


        if(release_time.equals("")){
            release_time = null;
        }
        int startIndex = (page-1)*limit;
        int count = announcementService.searchAnnouncementTotal(a_title, release_time);
        List<Announcement> announcementList = announcementService.searchAnnouncement(startIndex,limit,a_title,release_time);
        LayuiTableData layuiTableData = LayuiTableData.layData(count,announcementList);//转换成前端需要的数据格式
        return layuiTableData;
    }



    @RequestMapping("/addAnnouncement")
    public String addAnnouncement(@RequestParam String a_title, @RequestParam String a_content, @RequestParam String release_time){
        Announcement announcement = new Announcement();
        announcement.setA_content(a_content);
        announcement.setA_title(a_title);
        announcement.setRelease_time(release_time);
        announcementService.addAnnouncement(announcement);
        return "redirect:/admin/index#//admin/announcement/toAnnouncementList";
    }

    @RequestMapping("/editAnnouncement")
    public String editAnnouncement(@RequestParam int a_id,@RequestParam String a_title, @RequestParam String a_content, @RequestParam String release_time){
        Announcement announcement = announcementService.selectAnnouncementById(a_id);
        announcement.setA_content(a_content);
        announcement.setA_title(a_title);
        announcement.setRelease_time(release_time);
        announcementService.updateAnnouncement(announcement);
        return "redirect:/admin/index#//admin/announcement/toAnnouncementList";
    }

    @RequestMapping("/deleteAnnouncement")
    @ResponseBody
    public Map<String,Object> deleteAnnouncement(@RequestParam int a_id){

        Map<String,Object> map = new HashMap<>();
        if(announcementService.deleteAnnouncement(a_id)>0){
            map.put("result","success");
        }else{
            map.put("result","failed");
        }
        return map;
    }

    @RequestMapping("/batchdeleteAnnouncement")
    @ResponseBody
    public Map<String,Object> batchdeleteAnnouncement(@RequestBody int[] a_ids){

        Map<String,Object> map = new HashMap<>();
        if(announcementService.batchdeleteAnnouncement(a_ids)>0){
            map.put("result","success");
        }else{
            map.put("result","failed");
        }
        return map;
    }
}
