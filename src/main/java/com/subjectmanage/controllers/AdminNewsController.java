package com.subjectmanage.controllers;

import com.subjectmanage.beans.Admin;
import com.subjectmanage.beans.News;
import com.subjectmanage.services.AdminServiceImpl;
import com.subjectmanage.services.NewsServiceImpl;
import com.subjectmanage.utils.LayuiTableData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/admin/news/")
public class AdminNewsController {

    @Autowired
    private AdminServiceImpl adminService;

    @Autowired
    private NewsServiceImpl newsService;

    @RequestMapping("/toNewsList")
    public String toNewsList(){
        return "admin-news";
    }

    @RequestMapping("/add")
    public String add(){
        return "admin-addnews";
    }

    @RequestMapping("/edit")
    public String edit(Model model,@RequestParam int news_id){
        News news = newsService.selectNewsById(news_id);
        model.addAttribute("news_id",news_id);
        model.addAttribute("news_title",news.getNews_title());
        model.addAttribute("news_date",news.getNews_date());
        model.addAttribute("news_url",news.getNews_url());
        return "admin-editnews";
    }

    @RequestMapping("/getNewsList")
    @ResponseBody
    public LayuiTableData getNewsList(@RequestParam int page, @RequestParam int limit, HttpSession session){

        int startIndex = (page-1)*limit;
        int count = newsService.getTotal();
        List<News> newsList = newsService.selectAll(startIndex,limit);
        LayuiTableData layuiTableData = LayuiTableData.layData(count,newsList);//转换成前端需要的数据格式
        return layuiTableData;
    }

    @RequestMapping("/searchNews")
    @ResponseBody
    public LayuiTableData searchNewsList(@RequestParam int page, @RequestParam int limit,
                                         @RequestParam String news_title, @RequestParam String news_date,
                                         HttpSession session){

        System.out.println(news_date);
        if(news_date.equals("")){
            news_date = null;
        }
        int startIndex = (page-1)*limit;
        int count = newsService.searchNewsTotal(news_title,news_date);
        List<News> newsList = newsService.searchNews(startIndex,limit,news_title,news_date);
        LayuiTableData layuiTableData = LayuiTableData.layData(count,newsList);//转换成前端需要的数据格式
        return layuiTableData;
    }



    @RequestMapping("/addNews")
    public String addNews(@RequestParam String news_title, @RequestParam String news_url, @RequestParam String news_date){
        News news = new News();
        news.setNews_date(news_date);
        news.setNews_title(news_title);
        news.setNews_url(news_url);
        newsService.addNews(news);
        return "redirect:/admin/index#//admin/news/toNewsList";
    }

    @RequestMapping("/editNews")
    public String editNews(@RequestParam int news_id,@RequestParam String news_title, @RequestParam String news_url, @RequestParam String news_date){
        News news = newsService.selectNewsById(news_id);
        news.setNews_date(news_date);
        news.setNews_title(news_title);
        news.setNews_url(news_url);
        newsService.updateNews(news);
        return "redirect:/admin/index#//admin/news/toNewsList";
    }

    @RequestMapping("/deleteNews")
    @ResponseBody
    public Map<String,Object> deleteNews(@RequestParam int news_id){

        Map<String,Object> map = new HashMap<>();
        if(newsService.deleteNews(news_id)>0){
            map.put("result","success");
        }else{
            map.put("result","failed");
        }
        return map;
    }

    @RequestMapping("/batchdeleteNews")
    @ResponseBody
    public Map<String,Object> batchdeleteNews(@RequestBody int[] news_ids){

        Map<String,Object> map = new HashMap<>();
        if(newsService.batchdeleteNews(news_ids)>0){
            map.put("result","success");
        }else{
            map.put("result","failed");
        }
        return map;
    }
}
