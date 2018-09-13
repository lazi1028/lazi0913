package com.bcht.axletempmonitor.utils;

import com.bcht.axletempmonitor.config.filter.ShiroSessionFilter;
import com.bcht.axletempmonitor.pojo.BchUserinfo;
import com.bcht.axletempmonitor.service.UserServiceImpl;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * 没有分类的工具类
 *
 */
@Component
public class OtherUtils {
    private static Logger logger = LoggerFactory.getLogger(ShiroSessionFilter.class);

    @Autowired
    private UserServiceImpl userServiceImpl;
    private static OtherUtils otherUtils;

    @PostConstruct
    public void init(){
        otherUtils=this;
        otherUtils.userServiceImpl=this.userServiceImpl;
    }

    //分页查询结果集
    public static Map<String,Object> pageHelperResult(PageInfo result){
        Map<String,Object> map=new HashMap<>();
        map.put("pageNo",result.getPageNum());//当前页
        map.put("pageSize",result.getPageSize());//每页显示数据条数
        map.put("currentSize",result.getSize());//实际当前页数据条数
        map.put("totals",result.getTotal());//总记录数
        map.put("pages",result.getPages());//总页数
        map.put("data",result.getList());//数据结果集
        return map;
    }

   public static BchUserinfo getCurrentUser(){
       BchUserinfo currentUser=null;
        if(SecurityUtils.getSubject().isAuthenticated()){
            Session session = SecurityUtils.getSubject().getSession();
            String userid = (String) session.getAttribute("currentUser");
           currentUser=otherUtils.userServiceImpl.queryUserById(Integer.parseInt(userid));
        }
        return currentUser;
    }

    public static ResultStatus validOriginPwd(String pwd){
        if(!MyStringUtils.isEmpty(pwd)){
            String entryPwd= String.valueOf(PwdEncryUtil.getEncryptionResult("MD5",pwd,null,1));
            BchUserinfo currentUser = (BchUserinfo) getCurrentUser();
            if(currentUser!=null){
                logger.info("currentUser.getPassword()："+currentUser.getPassword());
                if(!pwd.equals(currentUser.getPassword())){
                    return ResultStatus.USER_ORIGIN_PASSWORD_CHECKED;
                }
            }else{
                return ResultStatus.USER_NOT_LOGIN;//用户未登陆
            }
        }else{
            return ResultStatus.USER_PASSWORD_NOT_NULL;
        }
        return ResultStatus.SUCCESS;
    }
}
