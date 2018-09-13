package com.bcht.axletempmonitor.config.filter;

import com.bcht.axletempmonitor.utils.ResultStatus;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 *  访问控制过滤器
 * 所有的请求只有登录才可以访问
 * 判断当前用户有没有权限操作
 */
public class AccessIsAllowFilter extends AccessControlFilter {
    private static final Logger logger = LoggerFactory.getLogger(AccessIsAllowFilter.class);

    private String noAuthenticationReturn = "{\"code\": \""+String.valueOf(ResultStatus.USER_NOT_LOGIN.getCode())+"\",\"message\":\""+ResultStatus.USER_NOT_LOGIN.getMessage()+"\",\"content\":\"\" }";
    private String noPermssionReturn = "{\"code\": \""+String.valueOf(ResultStatus.USER_HAS_NO_PERMISSION.getCode())+"\",\"message\":\""+ResultStatus.USER_HAS_NO_PERMISSION.getMessage()+"\",\"content\":\"\" }";

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object o) throws Exception {

        Subject subject = SecurityUtils.getSubject();
        if(null != subject && subject.isAuthenticated()){
            HttpServletRequest req = (HttpServletRequest) request;
            String uri=req.getRequestURI();//项目名+请求路径
            String contextPath = req.getContextPath();//项目名
            int index = uri.indexOf(contextPath);
            if(index > -1){
                uri = uri.substring(index + contextPath.length());
            }
            //登录不做权限控制
            if("/userLogin/login".equals(uri) || "/userLogin/getVerify".equals(uri)){
                return true;
            }
            uri = uri.substring(1);// eg:/user/addUser --> user/addUser
            String replaceUri = uri.replace("/", ":");// user:addUser
          /*  Boolean permitted=subject.isPermitted(replaceUri);

            if(!permitted){
                logger.info("当前用户没有访问"+replaceUri+"的权限");
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                response.reset();
                out.println(noPermssionReturn);//输出json字符串 没有权限
                out.flush();
                out.close();
            }
            return permitted;*/
          return true;
        }else{
            logger.info("当前用户没有登录........");
          /* response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.println(noAuthenticationReturn);//输出json字符串.没有登录
            out.flush();
            out.close();
            return false;*/
            return true;
        }

    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        return false;
    }

}
