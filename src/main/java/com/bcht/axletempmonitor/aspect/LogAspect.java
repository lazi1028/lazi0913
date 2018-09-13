package com.bcht.axletempmonitor.aspect;

import com.bcht.axletempmonitor.annotation.MyLog;
import com.bcht.axletempmonitor.pojo.BchLogtrace;
import com.bcht.axletempmonitor.pojo.BchUserinfo;
import com.bcht.axletempmonitor.service.ILogService;
import com.bcht.axletempmonitor.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

import static com.bcht.axletempmonitor.utils.OtherUtils.getCurrentUser;


/**
 *  日志切面类
 */
@Component
@Aspect
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Autowired
    ILogService logServiceImpl;
    @Autowired
    IUserService userServiceImpl;

    //定义切入点 在注解位置切入代码
    @Pointcut("@annotation(com.bcht.axletempmonitor.annotation.MyLog)")
    public void logPointCut(){}

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        logger.info("Aspect arround() start......");
        long beginTime = System.currentTimeMillis();
        Object result = pjp.proceed();
        long endTime = System.currentTimeMillis();
        if(SecurityUtils.getSubject().isAuthenticated()){
            saveLog(pjp);
        }
        return result;
    }

    public void saveLog(ProceedingJoinPoint joinPoint){
        BchLogtrace logtrace=new BchLogtrace();
        //从切面连接点处通过反射获取连接点处方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();
        //获取操作
        MyLog myLog = method.getAnnotation(MyLog.class);
        if(myLog!=null){
            String value = myLog.value();
            logtrace.setOperadetails(value);//设置操作内容
        }

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //String className = joinPoint.getTarget().getClass().getName();//获取请求的类名
        //String methodName = method.getName();//请求方法名
        logtrace.setOperafunc(request.getRequestURI());//设置请求url
        logtrace.setRequestip(request.getRemoteAddr());

        BchUserinfo currentUser=getCurrentUser();
        logtrace.setOperator(currentUser.getUsername());//设置操作人

        logServiceImpl.insertLog(logtrace);
    }

    //切入通知 正常返回后执行
   /* @AfterReturning("logPointCut()")
    public void saveLog(JoinPoint joinPoint){
        logger.info("切面执行saveLog()..............");
        if(SecurityUtils.getSubject().isAuthenticated()){
            BchLogtrace logtrace=new BchLogtrace();
            //从切面连接点处通过反射获取连接点处方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            //获取切入点所在的方法
            Method method = signature.getMethod();

            //获取操作
            MyLog myLog = method.getAnnotation(MyLog.class);
            if(myLog!=null){
                String value = myLog.value();
                logtrace.setOperadetails(value);//设置操作内容
            }

            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            //String className = joinPoint.getTarget().getClass().getName();//获取请求的类名
            //String methodName = method.getName();//请求方法名
            logtrace.setOperafunc(request.getRequestURI());//设置请求url
            logtrace.setRequestip(request.getRemoteAddr());

            BchUserinfo currentUser=getCurrentUser();
            *//*String userid = (String) item;//自定义选择().getSession().getAttribute("currentUser");
            BchUserinfo currentUser = userServiceImpl.queryUserById(Integer.parseInt(userid));*//*
            if(currentUser==null){
                return;
            }else {
                logtrace.setOperator(currentUser.getUsername());//设置操作人
            }
            logServiceImpl.insertLog(logtrace);
        }
    }*/
}
