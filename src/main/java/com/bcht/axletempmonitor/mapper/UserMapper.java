package com.bcht.axletempmonitor.mapper;

import com.bcht.axletempmonitor.pojo.BchPermission;
import com.bcht.axletempmonitor.pojo.BchRoleinfo;
import com.bcht.axletempmonitor.pojo.BchUserinfo;
import com.bcht.axletempmonitor.vo.UserVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    public BchUserinfo getUserByName(String username); //登陆验证

    public BchRoleinfo getPermissionByRoleId(Integer roleid);//获取权限id
    public BchPermission getPermissionInfo(Integer permid);//通过权限id查权限信息

    public  BchUserinfo queryUserById(Integer userid); //查看个人信息
   // public BchUserinfo queryUserById(UserVo uv);//查用户信息

    public int updUserInfo(UserVo u);//修改个人信息

    public String queryOriginPwd(Integer id);//查询原始密码
    public int updPassword(UserVo u); //修改密码

    public List<BchUserinfo> fuzzyQueryUser(UserVo uv);//模糊查询用户列表
    public int insertUser(UserVo uv);//添加用户
    public int insertUserRole(UserVo uv);//添加用户角色
    public int updUser(UserVo uv);//修改用户信息
    public int updUserRole(UserVo uv);//修改用户角色
    public int delUser(UserVo uv);//删除用户 单个/批量删除
    public int delUserRole(UserVo uv);

    public List<BchRoleinfo> queryRole();//查询所有角色

    public int insertRole(UserVo uv);//添加角色
    public int insertRolePerms(UserVo uv);//分配角色对应的权限

    public BchRoleinfo getRoleById(Integer roleid);
    public int updRole(UserVo uv);//角色修改
    //public int updRolePerms(UserVo uv);//修改角色对应的权限

    public int delRole(UserVo uv);//删除角色
    public int delRolePerms(UserVo uv);//删除角色权限

    public List<BchPermission> queryPermission();//查所有权限



    //#｛｝中内容与方法参数名一致
   /* @Select("select * from user1 where id= #{id}")
        @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "age",column="age")
         })
    public User1 queryUserById(int id);

    @Select("select id,name,age from user1")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "age",column="age")
    })
    public List<User1> queryUsers();

    @Insert("insert into user1(name,age) values(#{name},#{age})")
    public int addUser(@Param("name") String name, @Param("age") int age);

    @Insert("insert into user1(name,age) values(#{name,jdbcType=VARCHAR},#{age,jdbcType=INTEGER})")
    public int addByMap(Map<String, Object> map);

    @Update("update user1 set name=#{name},age=#{age} where id=#{id}")
    public int updUser(User1 user1);

    @Delete("delete from user1 where id=#{id}")
    public int delUserByid(Integer id);

    @Delete("delete from user1 where id=#{id}")
    public int delUser(User1 user1);*/


}
