package com.bcht.axletempmonitor.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@Configuration
public class WebMvcConfigurer implements org.springframework.web.servlet.config.annotation.WebMvcConfigurer {
    @Autowired
    private SessionInterceptor sessionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截器  addPathPatterns是拦截器路径
        registry.addInterceptor(sessionInterceptor).addPathPatterns("/getUser11/**");

    }
}
