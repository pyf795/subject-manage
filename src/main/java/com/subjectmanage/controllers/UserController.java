package com.subjectmanage.controllers;

import com.subjectmanage.beans.Student;
import com.subjectmanage.beans.Teacher;
import com.subjectmanage.services.StudentServiceImpl;
import com.subjectmanage.services.TeacherServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private TeacherServiceimpl teacherService;

    @RequestMapping("/toPassword")
    public String toPassword(){
        return "user-password";
    }

    @RequestMapping("/UpdatePassword")
    public String updatePassword(@RequestParam String old_password, @RequestParam String new_password,
                                 HttpSession session, Model model){
        String user_type = (String)session.getAttribute("user_type");

        if(user_type!=null&&user_type.equals("学生")){
            Student loginUser =(Student) session.getAttribute("loginUser");
            if(loginUser.getPassword().equals(old_password)){
                loginUser.setPassword(new_password);
                studentService.updateStudent(loginUser);
                model.addAttribute("msg","修改密码成功，请重新登录！");
                session.removeAttribute("loginUser");
                return "login";
            }else {
                session.setAttribute("msg","旧密码错误！修改失败");
            }
        }else if(user_type!=null&&user_type.equals("老师")){
            Teacher loginUser = (Teacher)session.getAttribute("loginUser");
            if(loginUser.getPassword().equals(old_password)){
                loginUser.setPassword(new_password);
                teacherService.updateTeacher(loginUser);
                model.addAttribute("msg","修改密码成功，请重新登录！");
                session.removeAttribute("loginUser");
                return "login";
            }else {
                session.setAttribute("msg","旧密码错误！修改失败");
            }
        }

        return "redirect:/index#/toPassword";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("loginUser");
        return "redirect:/login/toLogin";
    }

    @RequestMapping("/userDetails")
    public String toUserDetails(HttpSession session,Model model){
        String user_type = (String)session.getAttribute("user_type");

        if(user_type!=null&&user_type.equals("学生")){
            Student loginUser =(Student) session.getAttribute("loginUser");
            model.addAttribute("user_num",loginUser.getStudent_num());
            model.addAttribute("user_phone",loginUser.getStudent_phone());
            model.addAttribute("user_email",loginUser.getStudent_email());
        }else if(user_type!=null&&user_type.equals("老师")){
            Teacher loginUser = (Teacher)session.getAttribute("loginUser");
            model.addAttribute("user_num",loginUser.getTeacher_num());
            model.addAttribute("user_phone",loginUser.getTeacher_phone());
            model.addAttribute("user_email",loginUser.getTeacher_email());
        }
        return "user-details";
    }

    @RequestMapping("/userDetailsUpdate")
    public String toUserDetails(@RequestParam String phone, @RequestParam String email,@RequestParam String user_num,
                                HttpSession session, Model model) {
        String user_type = (String)session.getAttribute("user_type");
        if(user_type!=null&&user_type.equals("学生")){
            Student loginUser =(Student) session.getAttribute("loginUser");
            loginUser.setStudent_phone(phone);
            loginUser.setStudent_email(email);
            studentService.updateStudent(loginUser);
            session.setAttribute("dmsg","修改成功！");
        }else if(user_type!=null&&user_type.equals("老师")){
            Teacher loginUser = (Teacher)session.getAttribute("loginUser");
            loginUser.setTeacher_phone(phone);
            loginUser.setTeacher_email(email);
            teacherService.updateTeacher(loginUser);
            session.setAttribute("dmsg","修改成功！");
        }else{
            session.setAttribute("dmsg","修改失败！");
        }


        return "redirect:/index#/userDetails";
    }
}
