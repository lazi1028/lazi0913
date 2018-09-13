package com.bcht.axletempmonitor.config;

import com.bcht.axletempmonitor.config.filter.AccessIsAllowFilter;
import com.bcht.axletempmonitor.config.filter.ShiroSessionFilter;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

//import org.crazycake.shiro.RedisManager;
//import org.crazycake.shiro.RedisSessionDAO;

/**
 * shiro配置
 * createdby lazi  2018/08/16
 *
 */
@Configuration
public class ShiroConfig {
    private static final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);

    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        logger.info("ShiroConfig.shirFilter().......................");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        shiroFilterFactoryBean.setLoginUrl("/userLogin/login");//登录url
        shiroFilterFactoryBean.setUnauthorizedUrl("/403"); //未授权界面;

        Map<String,Filter> fltMaps=new LinkedHashMap<>();
        fltMaps.put("access",new AccessIsAllowFilter());//自定义登录过滤
        //fltMaps.put("definePerm",new AuthorizedFilter());//自定义授权过滤
        shiroFilterFactoryBean.setFilters(fltMaps);

        //定义shiro过滤链 从上向下顺序执行 /**放在最为下边 authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
        filterChainDefinitionMap.put("/logout", "logout"); //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/js/**","anon");
        filterChainDefinitionMap.put("/css/**","anon");


        filterChainDefinitionMap.put("/userLogin/login", "anon");
        filterChainDefinitionMap.put("/userLogin/getVerify", "anon");
        //所有操作只有登陆才可以访问
        //filterChainDefinitionMap.put("/test/", "authc");
        filterChainDefinitionMap.put("/**", "access");
        //filterChainDefinitionMap.put("/**", "definePerm");


        //filterChainDefinitionMap.put("/add", "perms[权限添加]");
        logger.info("拦截器链："+filterChainDefinitionMap);


        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        logger.info("Shiro拦截器工厂类注入成功");
       // System.out.println("Shiro拦截器工厂类注入成功");
        return shiroFilterFactoryBean;

    }

    /**
     * 安全管理
     */
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());// 设置realm.
        securityManager.setCacheManager(cacheManager());//自定义缓存的实现 使用Redis
        securityManager.setSessionManager(sessionManager());// 自定义session管理 使用redis
        return securityManager;
    }

    /**
     * 身份认证 自定义实现
     */
    @Bean
    public MyShiroRealm myShiroRealm(){
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCacheManager(cacheManager());
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());//凭证匹配
        myShiroRealm.setCachingEnabled(true);//使用缓存
        myShiroRealm.setAuthenticationCachingEnabled(false);//认证
        myShiroRealm.setAuthorizationCachingEnabled(false);//授权
        return myShiroRealm;
    }

    /**
     * cacheManager 缓存 redis实现
     * 使用的是shiro-redis开源插件
     */
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }
    /**
     * 配置shiro redisManager
     * 使用的是shiro-redis开源插件
     *
     */
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost("127.0.0.1");
        redisManager.setPort(6379);
        redisManager.setExpire(120000);// 设置缓存过期时间
        redisManager.setTimeout(120000);
        //redisManager.setPassword(password);
        return redisManager;
    }
    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     * 使用的是shiro-redis开源插件
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    /**
     * Session Manager
     * 使用的是shiro-redis开源插件
     */
    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }

    /**
     * 凭证匹配器  也负责对页面密码进行编码后与数据库密码比较
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher  hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//使用MD5散列算法
        hashedCredentialsMatcher.setHashIterations(1);//散列次数，这里等于1次MD5
        //hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);  //散列后密码为16进制，要与生成密码时一致。false 表示Base64编码
        return hashedCredentialsMatcher;
    }
    /**
     *  开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)即可实现此功能
     *
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }
    /**
     * 开启aop注解支持
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


    /**
     * 解决shiro过滤器 执行Subject currentUser = SecurityUtils.getSubject()时 UnavailableSecurityManagerException
     * 不能和shiroSessionFilterRegistrationBean()合到一起写
     *
     */
    @Bean
    public FilterRegistrationBean delegatingFilterProxy(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        proxy.setTargetFilterLifecycle(true);
        proxy.setTargetBeanName("shirFilter");
        filterRegistrationBean.setFilter(proxy);
        return filterRegistrationBean;
    }

    @Value("${server.servlet.session.timeout}")
    private String serverSessionTimeout;

    /**
     * shiroSesion 过期时间过滤器
     * 保证shiroSession过期时间与application.properties中配置的session失效时间一致
     */
    @Bean
    public FilterRegistrationBean shiroSessionFilterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(shiroSessionFilter());
        filterRegistrationBean.setOrder(FilterRegistrationBean.LOWEST_PRECEDENCE);
        filterRegistrationBean.setEnabled(true);
        filterRegistrationBean.addUrlPatterns("/*");
        Map<String, String> initParameters = new HashMap<>();
        initParameters.put("serverSessionTimeout", serverSessionTimeout);
        initParameters.put("excludes", "/favicon.ico,/img/*,/js/*,/css/*");
        filterRegistrationBean.setInitParameters(initParameters);
        return filterRegistrationBean;
    }

    /**
     * 创建一个bean
     * @return
     */
    @Bean(name = "sessionFilter")
    public Filter shiroSessionFilter() {
            return new ShiroSessionFilter();
    }

    /**
     * shiro 生命周期管理
     *
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

}
