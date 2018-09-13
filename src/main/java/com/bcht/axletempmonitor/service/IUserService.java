package com.bcht.axletempmonitor.service;

import com.bcht.axletempmonitor.pojo.BchPermission;
import com.bcht.axletempmonitor.pojo.BchRoleinfo;
import com.bcht.axletempmonitor.pojo.BchUserinfo;
import com.bcht.axletempmonitor.vo.UserVo;

import java.util.List;

public interface IUserService {

    public BchUserinfo getUserByName(String username) ;
    public BchRoleinfo getPermissionByRoleId(Integer roleid) ;
    public BchPermission getPermissionInfo(Integer permid);
    public  BchUserinfo queryUserById(Integer id); //查看个人信息
    public int updUserInfo(UserVo u);//修改个人信息
    public String queryOriginPwd(Integer id);//查询原始密码
    public int updPassword(UserVo u); //修改密码

    public List<BchUserinfo> fuzzyQueryUser(UserVo uv);
    public int insertUser(UserVo uv);
   // public int insertUserRole(UserVo uv);
    public int updUser(UserVo uv);
    //public int updUserRole(UserVo uv);
    public int delUser(UserVo uv);

    public List<BchRoleinfo> queryRole();
    public int insertRole(UserVo uv);//添加角色
    //public int insertRolePerms(UserVo uv);
    public BchRoleinfo getRoleById(Integer roleid);
    public int updRole(UserVo uv);//角色修改
    public int delRole(UserVo uv);//角色删除

    public List<BchPermission> queryPermission();
}
