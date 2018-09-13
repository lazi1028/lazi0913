package com.bcht.axletempmonitor.controller;

import com.bcht.axletempmonitor.annotation.MyLog;
import com.bcht.axletempmonitor.pojo.BchUserinfo;
import com.bcht.axletempmonitor.service.IUserService;
import com.bcht.axletempmonitor.utils.MyStringUtils;
import com.bcht.axletempmonitor.utils.ResultModel;
import com.bcht.axletempmonitor.utils.ResultStatus;
import com.bcht.axletempmonitor.utils.VerifyCodeUtils;
import com.bcht.axletempmonitor.vo.UserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/userLogin")
public class LoginController {
    private static final Logger logger =LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private IUserService userServiceImpl;

    @MyLog(value = "用户登录")
    @PostMapping(value = "/login")
    public ResultModel login(@Valid UserVo userVo, BindingResult bindingResult) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(MyStringUtils.isEmpty(userVo.getUsername()) || MyStringUtils.isEmpty(userVo.getPassword()) || MyStringUtils.isEmpty(userVo.getVerifycode())){
            return ResultModel.ERROR(ResultStatus.USERNAME_AND_PASSWORD_NOT_NULL);
        }else {
            if(!MyStringUtils.validUsername(userVo.getUsername())){
                return ResultModel.ERROR(ResultStatus.USERADD_USERNAME_CHECKED);
            }
            if(!MyStringUtils.validUsername(userVo.getPassword())){
                return ResultModel.ERROR(ResultStatus.USERADD_PASSWORD_CHECKED);
            }
        }
        /*if (bindingResult.hasErrors()) {

            map.put("code",100);
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            return ResultModel.OK(map);
        }
        */
        Session session = SecurityUtils.getSubject().getSession();
        logger.info("LoginController-->>sessionid:"+session.getId());
        String verifyCode=userVo.getVerifycode();//页面输入验证码
        String code = (String) session.getAttribute("RANDOMVALIDATECODEKEY");//session中取验证码
        if(!verifyCode.equalsIgnoreCase(code)){
           /* logger.info("验证码错误！");
            map.put("msg","验证码错误");
            return ResultModel.OK(map);*/
            return ResultModel.ERROR(ResultStatus.VERTIFYCODE_WRONG_OR_OUTOFTIME);
        }

        String userName=userVo.getUsername();
        //获取页面输入信息 封装成 UsernamePasswordToken
        UsernamePasswordToken token=new UsernamePasswordToken(userVo.getUsername(),userVo.getPassword());
        //获取当前登录用户
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(token);
        }catch(UnknownAccountException uae){
            logger.info("对用户【" + userName +"】进行登录验证，验证未通过，未知账户！");
           /* map.put("code",106);
            map.put("msg","登陆验证未通过，未知用户");*/
            return ResultModel.ERROR(ResultStatus.USER_NOT_EXIST);
        }catch(AuthenticationException ae){
            logger.info("对用户【" + userName +"】进行登录验证，验证未通过，用户名或密码不正确");
           /* ae.printStackTrace();
            map.put("code",107);
            map.put("msg","登陆验证未通过，用户名或密码不正确");*/
            return ResultModel.ERROR(ResultStatus.USERNAME_OR_PASSWORD_ERROR);
        }
        if(currentUser.isAuthenticated()){
            logger.info("用户登录系统成功.................");
            //model.addAttribute("name",userName);
             //用户是否有1角色(管理员角色) currentUser.hasRole("1")  "1"权限：currentUser.isPermitted("1")
            //Session session = currentUser.getSession();
            //System.out.println(session.getId());
            //session.setTimeout(36000);//ms
            BchUserinfo user=userServiceImpl.getUserByName(userName);
            session.setAttribute("currentUser",String.valueOf(user.getUserid()));//当前用户ID存入session
            logger.info("sessionID:"+session.getId()+"--sessionExpire:"+session.getTimeout());
           // System.out.println(currentUser.hasRole("1")+"--"+currentUser.isPermitted("1"));

            map.put("token",session.getId());
            map.put("user",user);
            return ResultModel.OK(map); //登录成功后返回给前端的信息
        }else{
            token.clear();
            return ResultModel.OK(map);
        }
    }

    @PostMapping(value = "/logout")
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()){
            subject.logout();//或销毁session
            logger.info("用户退出系统...........");
        }
    }

    /**
     * 生成验证码
     */
    @RequestMapping(value = "/getVerify")
    public void getVerify(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("image/jpeg");//设置响应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            VerifyCodeUtils.getRandcode(request, response);//输出验证码图片方法
        } catch (Exception e) {
            logger.error("获取验证码失败>>>>   ", e);
        }
    }
}
