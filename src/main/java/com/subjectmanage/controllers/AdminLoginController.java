package com.subjectmanage.controllers;


import com.subjectmanage.beans.Admin;
import com.subjectmanage.beans.Student;
import com.subjectmanage.beans.Teacher;
import com.subjectmanage.services.AdminServiceImpl;
import com.subjectmanage.utils.CheckNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/admin/login")
public class AdminLoginController {

    @Autowired
    private AdminServiceImpl adminService;


    @RequestMapping("/toLogin")
    public String toLogin(){
        return "admin-login";
    }


    @RequestMapping(value = "/getCheckNumber", produces = {"text/html;charset=UTF-8"})
    public void getCheckNumber(HttpServletResponse response, HttpSession session ) throws IOException {
        //创建对象
        CheckNumber checkNumber = new CheckNumber();
        //获取图片对象
        BufferedImage image = checkNumber.getImage();
        //获得图片的文本内容
        String text = checkNumber.getText();
        // 将系统生成的文本内容保存到session中
        session.setAttribute("acheckNumber",text);
        //把图片返回前端
        ImageIO.write(image, "JPEG", response.getOutputStream());
    }

    @PostMapping(value = "/adminLogin")
    public String Login(@RequestParam("user_num") String user_num, @RequestParam("password") String password,
                        @RequestParam("captcha") String captcha,
                        boolean rememberMe,
                        Model model, HttpSession session,HttpServletRequest request,HttpServletResponse response){
        Object user = session.getAttribute("loginAdmin");
        if(user!=null){
            model.addAttribute("msg","你已经登陆过了！不要重复登陆");
            return "admin-login";
        }

        String checkNumber = (String) session.getAttribute("acheckNumber");
        if(checkNumber==null||!checkNumber.equals(captcha)) {
            model.addAttribute("msg", "验证码错误！请重新输入");
            return "admin-login";
        }

        Admin admin = null;
        admin = adminService.selectLoginAdmin(user_num,password);

        //业务调用：如果验证正确，返回一个登陆的用户对象，并存入session
        if(admin!=null){
            session.setAttribute("loginAdmin",admin);
            rememberMe(rememberMe,request,response,admin.getAdmin_num(),admin.getAdmin_password());
            return "redirect:/admin/index";
        }
        else{
            model.addAttribute("msg","用户名或密码错误！请重新输入");
            return "admin-login";
        }

    }

    public void rememberMe(boolean rememberMe,HttpServletRequest request,HttpServletResponse response,String username,String password){
        if (rememberMe){
            //记住用户
            Cookie nameCookie = new Cookie("adminname",username);
            Cookie pwdCookie = new Cookie("adminpwd",password);
            //3天
            nameCookie.setMaxAge(3*24*60*60);
            pwdCookie.setMaxAge(3*24*60*60);
            response.addCookie(nameCookie);
            response.addCookie(pwdCookie);
        }else{//清除cookie
            Cookie[] cookies = request.getCookies();
            for(Cookie cookie:cookies ){
                if (cookie.getName().equals("name")||cookie.getName().equals("pwd")) {
                    cookie.setValue(null);
                    cookie.setMaxAge(0);// 立即销毁cookie
                    System.out.println("被删除的cookie名字为:" + cookie.getName());
                    response.addCookie(cookie);
                }
            }
        }
    }

    @RequestMapping(value = "/initMenu")
    @ResponseBody
    public Map<String,Object> initMenu(HttpSession session, HttpServletRequest request){
        Map<String,Object> map=new HashMap<>();

            Student loginUser =(Student) session.getAttribute("loginUser");
            Map<String,Object>homeInfo=new HashMap<>();
            homeInfo.put("title","首页");
            homeInfo.put("icon","fa fa-home");
            homeInfo.put("href","/admin/toWelcome");
            map.put("homeInfo",homeInfo);
            Map<String,Object>logoInfo=new HashMap<>();
            logoInfo.put("title","综设管理系统");
            logoInfo.put("image","/images/logo.jpg");
            logoInfo.put("href","/index");
            map.put("logoInfo",logoInfo);
            List<Map<String,Object>> menuInfo=new ArrayList<>();//保证菜单的顺序
            List<Map<String,Object>>childInfo=new ArrayList<>();



            Map<String,Object>aInfo=new HashMap<>();
            aInfo.put("title","个人信息修改");
            aInfo.put("icon","fa fa-home");
            aInfo.put("href","/admin/userDetails");
            childInfo.add(aInfo);

            Map<String,Object>bInfo=new HashMap<>();
            bInfo.put("title","课题管理");
            bInfo.put("icon","fa fa-window-maximize");
            bInfo.put("href","/admin/topic/toTopicList");
            childInfo.add(bInfo);

            Map<String,Object>dInfo=new HashMap<>();
            dInfo.put("title","小组管理");
            dInfo.put("icon","fa fa-window-maximize");
            dInfo.put("href","");
            childInfo.add(dInfo);

            Map<String,Object>eInfo=new HashMap<>();
            eInfo.put("title","成绩管理");
            eInfo.put("icon","fa fa-window-maximize");
            eInfo.put("href","/admin/topic/scores");
            childInfo.add(eInfo);


        Map<String,Object>fInfo=new HashMap<>();
        fInfo.put("title","公告管理");
        fInfo.put("icon","fa fa-window-maximize");
        fInfo.put("href","/admin/announcement/toAnnouncementList");
        childInfo.add(fInfo);

        Map<String,Object>gInfo=new HashMap<>();
        gInfo.put("title","新闻管理");
        gInfo.put("icon","fa fa-window-maximize");
        gInfo.put("href","/admin/news/toNewsList");
        childInfo.add(gInfo);

        Map<String,Object>hInfo=new HashMap<>();
        hInfo.put("title","文档管理");
        hInfo.put("icon","fa fa-window-maximize");
        hInfo.put("href","/admin/file/toFileList");
        childInfo.add(hInfo);

            Map<String,Object>cInfo=new HashMap<>();
            cInfo.put("title","欢迎你");
            cInfo.put("icon","fa fa-address-book");
            cInfo.put("href","");
            cInfo.put("target","_self");
            cInfo.put("child",childInfo);

            menuInfo.add(cInfo);


            map.put("menuInfo",menuInfo);
            return map;


    }
}

