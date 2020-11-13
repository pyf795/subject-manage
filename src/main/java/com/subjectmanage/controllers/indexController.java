package com.subjectmanage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexController {

    @RequestMapping(value = "/toindex")
    public String toIndex(Model model){
        model.addAttribute("msg","这是接收的信息");
        return "index";
    }
}
