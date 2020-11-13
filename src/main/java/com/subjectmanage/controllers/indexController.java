package com.subjectmanage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexController {

    @RequestMapping(value = "/toindex")
    public String toIndex(){
        return "index";
    }
}
