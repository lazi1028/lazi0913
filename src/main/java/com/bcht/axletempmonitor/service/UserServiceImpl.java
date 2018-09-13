package com.bcht.axletempmonitor.service;

import com.bcht.axletempmonitor.config.MyShiroRealm;
import com.bcht.axletempmonitor.mapper.UserMapper;
import com.bcht.axletempmonitor.pojo.BchPermission;
import com.bcht.axletempmonitor.pojo.BchRoleinfo;
import com.bcht.axletempmonitor.pojo.BchUserinfo;
import com.bcht.axletempmonitor.utils.MyStringUtils;
import com.bcht.axletempmonitor.utils.PwdEncryUtil;
import com.bcht.axletempmonitor.vo.UserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public BchUserinfo getUserByName(String username) {
        return userMapper.getUserByName(username);
    }
    @Override
    public BchRoleinfo getPermissionByRoleId(Integer roleid) {
        return userMapper.getPermissionByRoleId(roleid);
    }

    @Override
    public BchPermission getPermissionInfo(Integer permid) {
        return userMapper.getPermissionInfo(permid);
    }

    @Override
    public BchUserinfo queryUserById(Integer id) {
        return userMapper.queryUserById(id);
    }

    @Override
    public int updUserInfo(UserVo u) {
        return userMapper.updUserInfo(u);
    }

    @Override
    public int updPassword(UserVo u) {
        return userMapper.updPassword(u);
    }

    @Override
    public String queryOriginPwd(Integer id) {
        return userMapper.queryOriginPwd(id);
    }

    @Override
    public List<BchUserinfo> fuzzyQueryUser(UserVo uv) {
        return userMapper.fuzzyQueryUser(uv);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public int insertUser(UserVo uv) {
        if(MyStringUtils.isEmpty(uv.getSex())){
            uv.setSex("M");//无值默认 性别为男
        }
        String encryPwd = String.valueOf(PwdEncryUtil.getEncryptionResult("MD5", uv.getNewpassword(), null, 1));
        uv.setNewpassword(encryPwd);
        int i1=userMapper.insertUser(uv);//执行之后userid已经有值
        int i2=userMapper.insertUserRole(uv);
        return i1+i2;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public int updUser(UserVo uv) {
        int i1=0;
        int i2=0;
        if(MyStringUtils.isEmpty(uv.getRealname()) && MyStringUtils.isEmpty(uv.getUsername()) && MyStringUtils.isEmpty(uv.getMobilephone()) &&
                MyStringUtils.isEmpty(uv.getNewpassword()) && MyStringUtils.isEmpty(uv.getRepeatnewpassword()) &&
                MyStringUtils.isEmpty(uv.getDepartment()) && MyStringUtils.isEmpty(uv.getSex())){
            //只修改了角色
        }else {
            if(!MyStringUtils.isEmpty(uv.getNewpassword())){
                String encryPwd = String.valueOf(PwdEncryUtil.getEncryptionResult("MD5", uv.getNewpassword(), null, 1));
                uv.setNewpassword(encryPwd);
            }
           i1= userMapper.updUser(uv);
        }
        if(uv.getRoleid()!=null){
            i2=userMapper.updUserRole(uv);
        }
        return i1+i2;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public int delUser(UserVo uv) {
        return userMapper.delUser(uv);
    }

    @Override
    public List<BchRoleinfo> queryRole() {
        return userMapper.queryRole();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public int insertRole(UserVo uv) {
        int i1 = userMapper.insertRole(uv);
        int i2=0;
        if(uv.getPermids()!=null && uv.getPermids().length>0){
            i2=userMapper.insertRolePerms(uv);
        }
        return i1+i2;
    }

    @Override
    public BchRoleinfo getRoleById(Integer roleid) {
        return userMapper.getRoleById(roleid);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public int updRole(UserVo uv) {
        int i1=0;
        int i2=0;
        int i3=0;
        int i4=0;
        int i5=0;
        if(!MyStringUtils.isEmpty(uv.getRolename()) || !MyStringUtils.isEmpty(uv.getRoledesc())){
            i1=userMapper.updRole(uv);
        }
        if(uv.getUpdpermids()!=null && uv.getUpdpermids().length>0){
            //有修改权限分配
            i2=userMapper.delRolePerms(uv);//先删除原来的
            i4=userMapper.insertRolePerms(uv);//在添加修改后的
            //i2=userMapper.updRolePerms(uv);
        }
        if(uv.getPermids()!=null && uv.getPermids().length>0){
            //只是在原来基础上新增权限
            i3=userMapper.insertRolePerms(uv);
        }
        if(uv.getDelpermids()!=null && uv.getDelpermids().length>0){
            //只在原权限的基础上删除部分权限
            i5=userMapper.delRolePerms(uv);
        }
        //更新角色后清除权限缓存
        RealmSecurityManager rsm= (RealmSecurityManager) SecurityUtils.getSecurityManager();
        MyShiroRealm msr= (MyShiroRealm) rsm.getRealms().iterator().next();
        msr.clearAuthz();
        return i1+i2+i3+i4+i5;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public int delRole(UserVo uv) {
        return userMapper.delRole(uv);
    }

    @Override
    public List<BchPermission> queryPermission() {
        return userMapper.queryPermission();
    }
}
