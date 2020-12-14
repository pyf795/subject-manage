package com.subjectmanage.controllers;

import com.subjectmanage.beans.Admin;
import com.subjectmanage.beans.Student;
import com.subjectmanage.beans.Teacher;
import com.subjectmanage.services.AdminServiceImpl;
import com.subjectmanage.services.StudentServiceImpl;
import com.subjectmanage.services.TeacherServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminServiceImpl adminService;

    @RequestMapping("/toPassword")
    public String toPassword(){
        return "admin-password";
    }

    @RequestMapping("/UpdatePassword")
    public String updatePassword(@RequestParam String old_password, @RequestParam String new_password,
                                 HttpSession session, Model model){



            Admin loginUser =(Admin) session.getAttribute("loginAdmin");
            if(loginUser.getAdmin_password().equals(old_password)){
                loginUser.setAdmin_password(new_password);
                adminService.updateAdmin(loginUser);
                model.addAttribute("msg","修改密码成功，请重新登录！");
                session.removeAttribute("loginAdmin");
                return "admin-login";
            }else {
                session.setAttribute("msg","旧密码错误！修改失败");
            }


        return "redirect:/admin/index#//admin/toPassword";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("loginAdmin");
        Enumeration em = session.getAttributeNames();  //得到session中所有的属性名
        while (em.hasMoreElements()) {
             session.removeAttribute(em.nextElement().toString()); //遍历删除session中的值
        }
        return "redirect:/admin/login/toLogin";
    }

    @RequestMapping("/userDetails")
    public String toUserDetails(HttpSession session,Model model){

            Admin loginUser =(Admin) session.getAttribute("loginAdmin");
            model.addAttribute("admin_num",loginUser.getAdmin_num());
            model.addAttribute("admin_name",loginUser.getAdmin_name());

        return "admin-details";
    }

    @RequestMapping("/userDetailsUpdate")
    public String toUserDetails(@RequestParam String user_num,@RequestParam String user_name,
                                HttpSession session, Model model) {
        Admin loginUser =(Admin) session.getAttribute("loginAdmin");
        if(loginUser!=null){
            loginUser.setAdmin_name(user_name);
            adminService.updateAdmin(loginUser);
            session.setAttribute("dmsg","修改成功！");
        }else{
            session.setAttribute("dmsg","修改失败！");
        }


        return "redirect:/admin/index#//admin/userDetails";
    }
}
