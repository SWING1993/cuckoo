package com.cuckoo.utils;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro 配置
 *
 Apache Shiro 核心通过 Filter 来实现，就好像SpringMvc 通过DispachServlet 来主控制一样。
 既然是使用 Filter 一般也就能猜到，是通过URL规则来进行过滤和权限校验，所以我们需要定义一系列关于URL的规则和访问权限。
 *
 */
//@Configuration
public class ShiroConfiguration {

//    @Bean
//    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
//        System.out.println("ShiroConfiguration.shirFilter()");
//
//        // 设置SecurityManager
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//
//        //拦截器
//        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
//
//        //配置退出过滤器,其中的具体退出代码shiro已经帮我们实现了
//        filterChainDefinitionMap.put("/logout","logout");
//
//        //过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 :这是一个坑呢，一不小心代码就不好使了;
//        //authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
//        filterChainDefinitionMap.put("/**","authc");
//
//        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
//        shiroFilterFactoryBean.setLoginUrl("/login");
//
//        //未授权
//        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
//
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//
//        return shiroFilterFactoryBean;
//
//    }
//
//    public SecurityManager securityManager(){
//        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
//        return securityManager;
//    }
}
