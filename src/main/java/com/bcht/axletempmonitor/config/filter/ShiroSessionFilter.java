package com.bcht.axletempmonitor.config.filter;

import com.bcht.axletempmonitor.utils.MyStringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  设置shiroSession过期时间
 */
public class ShiroSessionFilter implements Filter{
    private static Logger logger = LoggerFactory.getLogger(ShiroSessionFilter.class);

    public List<String> excludes = new ArrayList<String>();

    private long serverSessionTimeout = 600000L;//ms

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
       logger.info("ShiroSessionFilter::shiro session filter init.............");
        String temp = filterConfig.getInitParameter("excludes");
        if (temp != null) {
            String[] url = temp.split(",");
            for (int i = 0; url != null && i < url.length; i++) {
                excludes.add(url[i]);
            }
        }
        String timeout = filterConfig.getInitParameter("serverSessionTimeout");
        if(!MyStringUtils.isEmpty(timeout)){
            this.serverSessionTimeout = Long.parseLong(timeout.substring(0,timeout.length()-1))*1000;//ms
        }
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException,ServletException {
        /*if(logger.isDebugEnabled()){
            logger.debug("shiro session filter is open");
        }*/
        logger.info("ShioSessionFilter:->doFilter：》shiro session filter is open ");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if(handleExcludeURL(req, resp)){
            filterChain.doFilter(request, response);
            return;
        }

        Subject currentUser = SecurityUtils.getSubject();
        if(currentUser==null){

        }
        if(currentUser.isAuthenticated()){
            currentUser.getSession().setTimeout(serverSessionTimeout);
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    private boolean handleExcludeURL(HttpServletRequest request, HttpServletResponse response) {

        if (excludes == null || excludes.isEmpty()) {
            return false;
        }

        String url = request.getServletPath();
        for (String pattern : excludes) {
            Pattern p = Pattern.compile("^" + pattern);
            Matcher m = p.matcher(url);
            if (m.find()) {
                return true;
            }
        }

        return false;
    }




}
