package com.subjectmanage.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.*;

@Controller
public class indexController {

    @RequestMapping(value = "/toindex")
    public String toIndex(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        String id = session.getId();
        model.addAttribute("msg",id);
        return "index";
    }


}
