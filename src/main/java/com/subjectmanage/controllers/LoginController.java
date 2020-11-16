package com.subjectmanage.controllers;

import com.subjectmanage.beans.Student;
import com.subjectmanage.beans.group;
import com.subjectmanage.services.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private StudentServiceImpl studentService;


    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @PostMapping(value = "/userLogin")
    public String Login(@RequestParam("student_num") String student_num, @RequestParam("password") String password,
                          Model model, HttpSession session){

        Student student = studentService.selectLoginStudent(student_num,password);
        //业务调用：如果验证正确，返回一个登陆的用户对象，并存入session
        if(student!=null){
            session.setAttribute("loginUser",student);
            return "redirect:/index";
        }else{
            model.addAttribute("msg","用户名或密码错误！");
            return "login";
        }

    }
}
