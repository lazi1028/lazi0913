package com.bcht.axletempmonitor.config;

import com.bcht.axletempmonitor.pojo.BchPermission;
import com.bcht.axletempmonitor.pojo.BchRolePermission;
import com.bcht.axletempmonitor.pojo.BchRoleinfo;
import com.bcht.axletempmonitor.pojo.BchUserinfo;
import com.bcht.axletempmonitor.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

//import org.apache.shiro.cache.Cache;

/**
 * 身份认证与授权
 *  createdby lazi  2018/08/16
 */

public class MyShiroRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);
    @Autowired
    @Lazy
    IUserService userServiceImpl;
    /**
     * 身份认证  重写获取用户的方法
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.info("MyShiroRealm.doGetAuthenticationInfo()............");

        //获取用户的输入的账号.
        String username = (String)token.getPrincipal();
        logger.info("页面用户名+密码："+username+"---"+token.getCredentials());

        BchUserinfo u=userServiceImpl.getUserByName(username);
        if (u==null){
            throw  new UnknownAccountException();
        }else{
            //更新用户登录时间


        }

        Object principal = u.getUsername();//数据库用户名
        Object credentials = u.getPassword();//数据库用户密码
        logger.info("数据库用户名密码："+principal+"---"+credentials);
        String realmName = getName(); //当前realm对象的name，调用父类的getName()方法即可
        //credentialsSalt盐值
        // ByteSource credentialsSalt = ByteSource.Util.bytes(u.getId());//使用username作为盐值
        //暂时不用盐
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
        SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(u,credentials,realmName);
        return authenticationInfo;
    }

    /**
     * 授权
     * 链接配置了相应权限或者shiro标签才会执行此方法否则不会执行
     * 方法通过SimpleAuthorizationInfo进行角色的添加和权限的添加。
     *
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("MyShiroRealm.doGetAuthorizationInfo().............");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        BchUserinfo userinfo = (BchUserinfo) principalCollection.getPrimaryPrincipal();//查询当前登录用户实体
        //根据用户名查询数据库
        BchUserinfo user = userServiceImpl.getUserByName(userinfo.getUsername());
        if(user!=null){
            //获取当前登录 subject对象
            Subject currentUser = SecurityUtils.getSubject();
            //当前用户存入session中 key=userid  vlaue=principals
           // currentUser.getSession().setAttribute(String.valueOf(user.getUserid()),currentUser.getPrincipals());
            //给用户赋予角色
            authorizationInfo.addRole(String.valueOf(user.getUserRole().getRoleid()));
            //赋权限
            BchRoleinfo roleinfo = userServiceImpl.getPermissionByRoleId(user.getUserRole().getRoleid());
            if(roleinfo!=null && roleinfo.getRolePermissionList()!=null && roleinfo.getRolePermissionList().size()>0){
                for (BchRolePermission permsission : roleinfo.getRolePermissionList()){
                    BchPermission permissionInfo = userServiceImpl.getPermissionInfo(permsission.getPermid());
                    //eg: 权限字符串格式类似 user:addUser
                    authorizationInfo.addStringPermission(permissionInfo.getPermissions());
                }
            }

            System.out.println("授权："+authorizationInfo.getRoles()+"=="+authorizationInfo.getStringPermissions());
            return authorizationInfo;
        }
        return null;

    }

    /**
     * 清除所有用户授权信息缓存.
     */
    /*public void clearCachedAuthorizationInfo(String principal) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
        clearCachedAuthorizationInfo(principals);
    }*/

    /**
     * 清除所有用户授权信息缓存.
     */
    public void clearAllCachedAuthorizationInfo() {
       /* Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if (cache != null) {
            for (Object key : cache.keys()) {
                cache.remove(key);
            }
        }*/
    }
    /**
     *
     * @Title: clearAuthz
     * @Description: TODO 清除缓存的授权信息
     * @return void    返回类型
     */
    public void clearAuthz(){
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }


}
