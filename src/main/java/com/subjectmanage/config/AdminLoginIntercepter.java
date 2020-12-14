package com.subjectmanage.config;



import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminLoginIntercepter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Object loginUser = request.getSession().getAttribute("loginAdmin");
        if(loginUser == null){
            request.setAttribute("msg","没有权限，请先登录");
            request.getRequestDispatcher("/admin/login/toLogin").forward(request,response);
            return false;
        }else{
            return true;
        }



    }
}

