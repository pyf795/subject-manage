package com.subjectmanage.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TIntercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String user_type = request.getSession().getAttribute("user_type").toString();
        if(!user_type.equals("老师")){
            request.setAttribute("msg","没有权限，请先登录");
            request.getRequestDispatcher("/login/toLogin").forward(request,response);
            return false;
        }else{
            return true;
        }

    }
}
