package com.bcht.axletempmonitor.controller;

import com.bcht.axletempmonitor.annotation.MyLog;
import com.bcht.axletempmonitor.pojo.BchPermission;
import com.bcht.axletempmonitor.pojo.BchRoleinfo;
import com.bcht.axletempmonitor.pojo.BchUserinfo;
import com.bcht.axletempmonitor.service.IUserService;
import com.bcht.axletempmonitor.utils.MyStringUtils;
import com.bcht.axletempmonitor.utils.PwdEncryUtil;
import com.bcht.axletempmonitor.utils.ResultModel;
import com.bcht.axletempmonitor.utils.ResultStatus;
import com.bcht.axletempmonitor.vo.UserVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bcht.axletempmonitor.utils.OtherUtils.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    private static final Logger logger =LoggerFactory.getLogger(UserController.class);
    @Autowired
    private IUserService userServiceImpl;
    private Integer pageNo;
    private Integer pageSize;

    @PostMapping(value = "/getUserByName")
    //@RequiresPermissions("1")
    public BchUserinfo getUserByName(@RequestBody UserVo userVo){
      /* Session session = SecurityUtils.getSubject().getSession();
        System.out.println(session.getId());
        BchUserinfo currentUser = userServiceImpl.queryUserById(Integer.parseInt((String) session.getAttribute("currentUser")));
        if(currentUser!=null){
            System.out.println(currentUser.getUserid()+"-"+currentUser.getUsername());
        }*/
        return userServiceImpl.getUserByName(userVo.getUsername());
    }
    @GetMapping(value = "/getPermissionByRoleId/{roleid}")
    public BchRoleinfo getPermissionByRoleId(@PathVariable("roleid") Integer roleid){
        return userServiceImpl.getPermissionByRoleId(roleid);
    }

    //根据id查用户
    @RequestMapping(value = "/getUserById",method = RequestMethod.POST)
    public ResultModel getUserById(@RequestBody UserVo uv){
        BchUserinfo u=userServiceImpl.queryUserById(uv.getUserid());
        if(u==null){
            return ResultModel.ERROR(ResultStatus.USER_NOT_EXIST);
        }else {
            return ResultModel.OK(u);
        }

    }
    //个人信息修改
    @MyLog(value = "修改个人信息")
    @PostMapping(value="/updateUserinfo")
    public ResultModel updUserInfo(@RequestBody UserVo uv){
        Map<String, Object> map = new HashMap<String, Object>();
        if(uv.getUserid()==null){
            map.put("msg","参数userid必输");
            return ResultModel.OK(map);
        }
        if(!MyStringUtils.isEmpty(uv.getRealname()) && !MyStringUtils.validRealname(uv.getRealname())){
            return ResultModel.ERROR(ResultStatus.USERADD_REALNAME_CHECKED);
        }
        if(!MyStringUtils.isEmpty(uv.getDepartment()) && !MyStringUtils.validRealname(uv.getDepartment())){
            return ResultModel.ERROR(ResultStatus.USERADD_DEPARTMENT_CHECKED);
        }
        if(!MyStringUtils.isEmpty(uv.getSex()) && ("F".equals(uv.getSex()) || "M".equals(uv.getSex()))){
            return ResultModel.ERROR(ResultStatus.USER_SEX_CHECKED);
        }
        if(!MyStringUtils.isEmpty(uv.getMobilephone()) && !MyStringUtils.validRealname(uv.getMobilephone())){
            return ResultModel.ERROR(ResultStatus.USERADD_MOBILEPHNE_CHECKED);
        }
        int i=userServiceImpl.updUserInfo(uv);
        if(i>0){
            //修改成功
            map.put("msg","个人信息修改成功");
        }else{
            map.put("msg","个人信息修改失败");
        }
        return ResultModel.OK(map);
    }

    @MyLog(value = "修改密码")
    @PostMapping(value="/updatePassword")
    public ResultModel updPassword(@RequestBody UserVo u){
        Map<String, Object> map = new HashMap<String, Object>();
        String pwd=u.getPassword();//输入原密码
        String newpwd=u.getNewpassword();//新密码
        String repnewpwd=u.getRepeatnewpassword();//重复新密码
        if(MyStringUtils.isEmpty(pwd) && MyStringUtils.isEmpty(newpwd) && MyStringUtils.isEmpty(repnewpwd)){
            //没有修改
            return ResultModel.OK();
        }else if(MyStringUtils.isEmpty(pwd) || MyStringUtils.isEmpty(newpwd) || MyStringUtils.isEmpty(repnewpwd)){
            map.put("msg","新密码、确认新密码、当前用户密码不能为空");
            return ResultModel.OK(map);
        }else{
            if(u.getUserid()== null){
                map.put("msg","参数userid不能为空");
                return ResultModel.OK(map);
            }
        }
        if(!MyStringUtils.isEmpty(newpwd) && !newpwd.equals(repnewpwd) ){
            return ResultModel.ERROR(ResultStatus.USER_PASSWORD_DOUBLE_CHECKED);
        }else{
            //BchUserinfo currentUser = userServiceImpl.queryUserById(Integer.parseInt((String) SecurityUtils.getSubject().getSession().getAttribute("currentUser")));
            BchUserinfo currentUser=getCurrentUser();
            String originpwd=currentUser.getPassword();
           String entryPwd= String.valueOf(PwdEncryUtil.getEncryptionResult("MD5",pwd,null,1));
           String entryNewPwd= String.valueOf(PwdEncryUtil.getEncryptionResult("MD5",newpwd,null,1));
            if(!entryPwd.equals(originpwd)){
                return ResultModel.ERROR(ResultStatus.USER_ORIGIN_PASSWORD_CHECKED);
            }else{
                u.setPassword(entryPwd);
                u.setNewpassword(entryNewPwd);
                int i=userServiceImpl.updPassword(u);
                if(i>0){
                    map.put("msg","密码修改成功");
                }else{
                    map.put("msg","密码修改失败");
                }
                return ResultModel.OK(map);
            }
        }
    }

    //根据不同条件模糊查询用户列表
    @RequestMapping(value = "/fuzzyQueryUser",method = RequestMethod.POST)
    public ResultModel fuzzyQueryUser(@RequestBody UserVo uv){
        if(uv.getPageNo()==null){
            pageNo=1;
        }else{
            pageNo=uv.getPageNo();
        }
        if(uv.getPageSize()==null){
            pageSize=50;
        }else{
            pageSize=uv.getPageSize();
        }
        PageHelper.startPage(pageNo,pageSize);
        List<BchUserinfo> bchUserinfos = userServiceImpl.fuzzyQueryUser(uv);
        PageInfo<BchUserinfo> result = new PageInfo<>(bchUserinfos);
        Map<String,Object> map = pageHelperResult(result);
        return ResultModel.OK(map);
    }

    @MyLog(value = "添加用户")
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public ResultModel addUser(@RequestBody UserVo uv){
        ResultStatus resultStatus = validUserParam(uv,"add");
        if(resultStatus.getCode()== -1010 || resultStatus.getCode()== -1014 || resultStatus.getCode()== -1015 || resultStatus.getCode()== -1012 ||
                resultStatus.getCode()== -1011 || resultStatus.getCode()== -1013 || resultStatus.getCode()== -1016){
            return ResultModel.ERROR(resultStatus);
        }

        if(validOriginPwd(uv.getPassword()).getCode()== -1017 || validOriginPwd(uv.getPassword()).getCode()== -1100){
            return ResultModel.ERROR(resultStatus);
        }

        int i = userServiceImpl.insertUser(uv);
        Map<String,String> map=new HashMap<String, String>();
        if(i>0){
            map.put("msg","添加用户成功");
        }else{
            map.put("msg","添加用户失败");
        }
        return ResultModel.OK(map);

    }


    //更新用户信息
    @MyLog(value = "修改用户")
    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    public ResultModel updateUser(@RequestBody UserVo uv){
        ResultStatus resultStatus = validUserParam(uv,"upd");
       /* if(resultStatus.getCode()==200){
            //一项未修改
            return ResultModel.OK();
        }*/
        if(resultStatus.getCode()== -1014 || resultStatus.getCode()== -1015 || resultStatus.getCode()== -1012 || resultStatus.getCode()== -1011 ||
                resultStatus.getCode()== -1013 || resultStatus.getCode()== -1016 || resultStatus.getCode()== -1018){
            return ResultModel.ERROR(resultStatus);
        }

        if(validOriginPwd(uv.getPassword()).getCode()== -1017 ){
            return ResultModel.ERROR(ResultStatus.USER_ORIGIN_PASSWORD_CHECKED);
        }
        int i=userServiceImpl.updUser(uv);
        Map<String,String> map=new HashMap<String, String>();
        if(i>0){
            map.put("msg","修改用户成功");
        }else{
            map.put("msg","修改用户失败");
        }
        return ResultModel.OK(map);
    }

    //删除用户信息
    @MyLog(value = "删除用户")
    @RequestMapping(value = "/deleteUser",method = RequestMethod.POST)
    public ResultModel deleteUser(@RequestBody UserVo uv){
        Map<String,String> map=new HashMap<String, String>();
        if(uv.getUserid()==null && (uv.getSelectids()==null || uv.getSelectids().length<=0)){
            map.put("msg","请选则要删除的记录");
            return ResultModel.OK(map);
        }
        if(MyStringUtils.isEmpty(uv.getPassword())){
            return ResultModel.ERROR(ResultStatus.USER_PASSWORD_NOT_NULL);
        }else{
            if(validOriginPwd(uv.getPassword()).getCode()== -1017){
                return ResultModel.ERROR(ResultStatus.USER_ORIGIN_PASSWORD_CHECKED);
            }
        }
        int i=userServiceImpl.delUser(uv);
        if(i>0){
            map.put("msg","删除用户成功");
        }else{
            map.put("msg","删除用户失败");
        }
        return ResultModel.OK(map);
    }

    //获取所有角色
    @RequestMapping(value = "/role/getAllRole",method = RequestMethod.POST)
    public ResultModel getAllRole(){
        List<BchRoleinfo> bchRoleinfos = userServiceImpl.queryRole();
        return ResultModel.OK(bchRoleinfos);
    }

    //角色查询 分页
    @RequestMapping(value = "/role/getRolePage",method = RequestMethod.POST)
    public ResultModel getRolePage(@RequestBody UserVo uv){
        if(uv.getPageNo()==null){
            pageNo=1;
        }else{
            pageNo=uv.getPageNo();
        }
        if(uv.getPageSize()==null){
            pageSize=50;
        }else{
            pageSize=uv.getPageSize();
        }
        PageHelper.startPage(pageNo,pageSize);
        List<BchRoleinfo> bchRoleinfos = userServiceImpl.queryRole();
        PageInfo<BchRoleinfo> result = new PageInfo<>(bchRoleinfos);
        Map<String,Object> map = pageHelperResult(result);
        return ResultModel.OK(map);
    }

    //添加角色
    @MyLog(value = "添加角色")
    @RequestMapping(value = "/role/addRole",method = RequestMethod.POST)
    public ResultModel addRole(@RequestBody UserVo uv){
        ResultStatus resultStatus = validRoleParam(uv, "add");
        if(resultStatus.getCode()== -1020 || resultStatus.getCode()== -1021 || resultStatus.getCode()== -1022 || resultStatus.getCode()== -1023 ){
            return ResultModel.ERROR(resultStatus);
        }
        ResultStatus resultStatus1 = validOriginPwd(uv.getPassword());
        if(resultStatus1.getCode()== -1017 || resultStatus1.getCode()== -1018){
            return ResultModel.ERROR(resultStatus1);
        }
        int i = userServiceImpl.insertRole(uv);
        Map<String,String> map=new HashMap<String, String>();
        if(i>0){
            map.put("msg","添加角色成功");
        }else{
            map.put("msg","添加角色失败");
        }
        return ResultModel.OK(map);
    }

    @RequestMapping(value = "/role/getRoleById",method = RequestMethod.POST)
    public ResultModel getRoleById(@RequestBody UserVo uv){
        Map<String,String> map=new HashMap<String, String>();
        if(uv.getRoleid()== null){
            map.put("msg","roleid参数不能为空");
            return ResultModel.OK(map);
        }
        BchRoleinfo roleById = userServiceImpl.getRoleById(uv.getRoleid());
        return ResultModel.OK(roleById);
    }
    //修改角色
    @MyLog(value = "修改角色")
    @RequestMapping(value = "/role/updateRole",method = RequestMethod.POST)
    public ResultModel updateRole(@RequestBody UserVo uv){
        Map<String,String> map=new HashMap<String, String>();
        if(uv.getRoleid()== null){
            map.put("msg","roleid参数不能为空");
            return ResultModel.OK(map);
        }
        ResultStatus resultStatus = validRoleParam(uv, "upd");
        if(resultStatus.getCode()== -1024 ){
            //未修改一项
            return ResultModel.OK();
        }
        if(resultStatus.getCode()== -1022 || resultStatus.getCode()== -1023 || resultStatus.getCode()== -1018){
            return ResultModel.ERROR(resultStatus);
        }
        ResultStatus resultStatus1 = validOriginPwd(uv.getPassword());
        if(resultStatus1.getCode()== -1017 || resultStatus1.getCode()== -1018){
            return ResultModel.ERROR(resultStatus1);
        }
        int i = userServiceImpl.updRole(uv);

        if(i>0){
            map.put("msg","修改角色成功");
        }else{
            map.put("msg","修改角色失败");
        }
        return ResultModel.OK(map);
    }

    //删除角色
    @MyLog(value = "删除角色")
    @RequestMapping(value = "/role/deleteRole",method = RequestMethod.POST)
    public ResultModel deleteRole(@RequestBody UserVo uv){
        Map<String,String> map=new HashMap<String, String>();
        if(uv.getRoleid()== null){
            map.put("msg","roleid参数不能为空");
            return ResultModel.OK(map);
        }
        if(MyStringUtils.isEmpty(uv.getPassword())){
            return  ResultModel.ERROR(ResultStatus.PASSWORD_NOT_NULL);
        }else {
            ResultStatus resultStatus = validOriginPwd(uv.getPassword());
            if(resultStatus.getCode()== -1017 || resultStatus.getCode()== -1100){
                return ResultModel.ERROR(resultStatus);
            }
        }
        int i = userServiceImpl.delRole(uv);

        if(i>0){
            map.put("msg","删除角色成功");
        }else{
            map.put("msg","删除角色失败");
        }
        return ResultModel.OK(map);
    }

    //查所有权限
    @RequestMapping(value = "/role/getPermission",method = RequestMethod.POST)
    public ResultModel getPermission(){
        List<BchPermission> bchPermissions = userServiceImpl.queryPermission();
        return ResultModel.OK(bchPermissions);
    }
    /**
     * 添加、修改角色时参数校验
     */
    public ResultStatus validRoleParam(UserVo uv,String flag){
        String rolename = uv.getRolename();
        String roledesc = uv.getRoledesc();
        Integer[] permids = uv.getPermids();
        if("add".equals(flag)){
            if(MyStringUtils.isEmpty(rolename)){
                return ResultStatus.ROLEADD_ROLENAME_NOT_NULL;
            }
            if(permids==null || permids.length<=0){
                return ResultStatus.ROLEADD_ROLEPERMS_NOT_NULL;
            }
        }else if("upd".equals(flag)){
            if(MyStringUtils.isEmpty(rolename) && MyStringUtils.isEmpty(roledesc) && (permids==null || permids.length<=0) &&
                    (uv.getUpdpermids()==null || uv.getUpdpermids().length<=0)){
                //一项未修改
               return ResultStatus.UPDATE_ZERO_ITEM;
            }else{
                //判断当前用户密码
                if(MyStringUtils.isEmpty(uv.getPassword())){
                    return ResultStatus.USER_PASSWORD_NOT_NULL;
                }
            }
        }

        if(!MyStringUtils.isEmpty(rolename) && !MyStringUtils.validDepartment(rolename)){
            return ResultStatus.ROLEADD_ROLENAME_LENCHECKED;
        }

        if(!MyStringUtils.isEmpty(roledesc) && !MyStringUtils.validRoledesc(roledesc)){
            return ResultStatus.ROLEADD_ROLEDESC_LENCHECKED;
        }

        return ResultStatus.SUCCESS;
    }
    /**
     * 添加、修改用户信息时参数校验
     */
    public ResultStatus validUserParam(UserVo uv,String flag){
        String realname=uv.getRealname();
        String username = uv.getUsername();
        String pwd = uv.getPassword();
        String newpwd = uv.getNewpassword();
        String repeatpwd = uv.getRepeatnewpassword();
        if("add".equals(flag)){
            if(MyStringUtils.isEmpty(realname) || MyStringUtils.isEmpty(username) || uv.getRoleid()==null ||
                    MyStringUtils.isEmpty(pwd) || MyStringUtils.isEmpty(newpwd) || MyStringUtils.isEmpty(repeatpwd)){
                return ResultStatus.USERADD_PARAM_NOT_NULL;
            }
        }else if("upd".equals(flag)){
            if(MyStringUtils.isEmpty(uv.getRealname()) && MyStringUtils.isEmpty(uv.getUsername()) && MyStringUtils.isEmpty(uv.getMobilephone()) &&
                    MyStringUtils.isEmpty(uv.getNewpassword()) && MyStringUtils.isEmpty(uv.getRepeatnewpassword()) &&
                    MyStringUtils.isEmpty(uv.getDepartment()) && MyStringUtils.isEmpty(uv.getSex()) && uv.getRoleid()==null){
                //一项都未修改
               // return ResultStatus.SUCCESS;
            }else{
                //判断当前用户密码
                if(MyStringUtils.isEmpty(uv.getPassword())){
                    return ResultStatus.USER_PASSWORD_NOT_NULL;
                }
            }
        }
        if(!MyStringUtils.isEmpty(uv.getMobilephone()) && !MyStringUtils.validPhone(uv.getMobilephone())){
            return ResultStatus.USERADD_MOBILEPHNE_CHECKED;
        }
        if(!MyStringUtils.isEmpty(uv.getDepartment()) && !MyStringUtils.validDepartment(uv.getDepartment())){
            return ResultStatus.USERADD_DEPARTMENT_CHECKED;
        }
        if(!MyStringUtils.isEmpty(realname) && !MyStringUtils.validRealname(realname)){
            return ResultStatus.USERADD_REALNAME_CHECKED;
        }
        if(!MyStringUtils.isEmpty(username) && !MyStringUtils.validUsername(username)){
            return ResultStatus.USERADD_USERNAME_CHECKED;
        }
        if((!MyStringUtils.isEmpty(newpwd) && !MyStringUtils.validPassword(newpwd)) ||
                (!MyStringUtils.isEmpty(repeatpwd) && !MyStringUtils.validPassword(repeatpwd)) ||
                (!MyStringUtils.isEmpty(pwd) && !MyStringUtils.validPassword(pwd)) ){
            return ResultStatus.USERADD_PASSWORD_CHECKED;
        }
        if((!MyStringUtils.isEmpty(newpwd) && MyStringUtils.isEmpty(repeatpwd)) || (!MyStringUtils.isEmpty(repeatpwd) && MyStringUtils.isEmpty(newpwd))){
            //更新时newpwd或确认密码只有一个有值,另一个按空串处理
            if(newpwd!=null && !"".equals(newpwd)){
                return ResultStatus.USER_PASSWORD_DOUBLE_CHECKED;
            }
            if(repeatpwd!=null && !"".equals(repeatpwd)){
                return ResultStatus.USER_PASSWORD_DOUBLE_CHECKED;
            }
        }
        if((!MyStringUtils.isEmpty(newpwd) && !MyStringUtils.isEmpty(repeatpwd)) && !newpwd.equals(repeatpwd)){
            return ResultStatus.USER_PASSWORD_DOUBLE_CHECKED;
        }

        return ResultStatus.SUCCESS;
    }

   /* public ResultStatus validOriginPwd(String pwd){
        if(!MyStringUtils.isEmpty(pwd)){
            String entryPwd= String.valueOf(PwdEncryUtil.getEncryptionResult("MD5",pwd,null,1));
            BchUserinfo currentUser = (BchUserinfo) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
            if(currentUser!=null){
                logger.info("currentUser.getPassword()："+currentUser.getPassword());
                if(!pwd.equals(currentUser.getPassword())){
                    return ResultStatus.USER_ORIGIN_PASSWORD_CHECKED;
                }
            }
        }else{
            return ResultStatus.USER_PASSWORD_NOT_NULL;
        }
        return ResultStatus.SUCCESS;
    }*/

    /*@RequestMapping(value = "/getUser/{id}",method = RequestMethod.GET)
    public User1 getUserByID(@PathVariable("id") int id){
        logger.debug("进入UserController.getUserByID方法");
       System.out.println(userServiceImpl.selectUserByID(id).getId()+"-"+userServiceImpl.selectUserByID(id).getAge()+"-"+userServiceImpl.selectUserByID(id).getName());
        logger.info("dddssssssssssssss");
        return userServiceImpl.selectUserByID(id);
    }
    @RequestMapping(value = "/getUser1",method = RequestMethod.GET)
    public User1 getUserByID1(@RequestParam(value = "id",required = false,defaultValue = "1") int id){
        return userServiceImpl.selectUserByID(id);
    }

    @GetMapping(value="/getUserall")
    public List<User1> getUsers(){
        //System.out.println(1);
        return userServiceImpl.selectUsers();
    }
    @GetMapping(value="/getUserpage")
    public List<User1> getUsersWithPage(Integer pageNo, Integer pageSize){
        //System.out.println(1);
        PageHelper.startPage(pageNo,pageSize);
        return userServiceImpl.selectUsers();
    }

    @PostMapping(value = "/insert1")
    public int insertUser(User1 u){
        //System.out.println(111);
        return userServiceImpl.insertUser(u);
    }

    @PostMapping(value = "/insertmap")
    public int insertByMap(Map map){
        //System.out.println(111);
        return userServiceImpl.insertByMap(map);
    }

    @DeleteMapping(value="/deleteuid")
    public int deleteUserById(Integer id){
        return  userServiceImpl.deleteUserById(id);
    }

    @DeleteMapping(value="/deleteu")
    public int deleteUser(User1 u){
        return  userServiceImpl.deleteUser(u);
    }
    @PutMapping(value = "/updateu")
    public int updateUser(User1 u){
        return userServiceImpl.updateUser(u);
    }

    //测试拦截器
    @RequestMapping(value = "/getUser11",method = RequestMethod.GET)
    public List<User1> getUsers1(){
        return userServiceImpl.selectUsers();
    }

    //测试异常
    @GetMapping(value = "/testException")
    public String testException(){
        int i=1/0;
        return "sss";
    }*/


}
