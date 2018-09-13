package com.bcht.axletempmonitor.exception;

import com.bcht.axletempmonitor.utils.ResultStatus;
import org.apache.shiro.authz.UnauthenticatedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
@ ControllerAdvice(basePackages = "指定包名")//basePackages 拦截指定包的异常
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private String noPermssionReturn = "{\"code\": \""+String.valueOf(ResultStatus.USER_HAS_NO_PERMISSION.getCode())+"\",\"message\":\""+ResultStatus.USER_HAS_NO_PERMISSION.getMessage()+"\",\"content\":\"\" }";

    @ExceptionHandler(value = Exception.class)
    public void exceptionHandler(HttpServletRequest req, Exception e, HttpServletResponse response) throws IOException {
        if(e instanceof  UnauthenticatedException){
            System.out.println("认证授权失败");
            logger.error(e.getMessage());
        }/*else if(e instanceof UnauthorizedException){
            logger.info("没有该权限");
           *//* response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.println(noPermssionReturn);//输出json字符串
            out.flush();
            out.close();*//*
        }*/else{
            System.out.println("异常处理。。。。。。。。。。");
            e.printStackTrace();
        }

    }
}
