package com.subjectmanage.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerIntercepter())
                .addPathPatterns("/**")
                .excludePathPatterns("/login/**","/images/**","/css/**","/js/**","/font/**","/lib/**","/api/**",
                        "/page/**","/lay/**","/admin/**");
        registry.addInterceptor(new AdminLoginIntercepter())
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/login/**","/images/**","/css/**","/js/**","/font/**","/lib/**","/api/**",
                        "/page/**","/lay/**");
        registry.addInterceptor(new StuIntercepter())
                .addPathPatterns("/file/stu/*");
        registry.addInterceptor(new TIntercepter())
                .addPathPatterns("/teach/*");
    }
}
