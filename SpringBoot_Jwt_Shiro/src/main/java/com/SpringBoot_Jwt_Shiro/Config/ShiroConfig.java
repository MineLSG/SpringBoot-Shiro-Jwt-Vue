package com.SpringBoot_Jwt_Shiro.Config;

import com.SpringBoot_Jwt_Shiro.Realm.JwtRealm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author RenZetong
 */
@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        Map<String, Filter> filterMap = new HashMap<>();
        //使用自定义jwt过滤器
        filterMap.put("jwt", new JwtFilter());
        bean.setFilters(filterMap);
        bean.setSecurityManager(getDefaultWebSecurityManager());
        bean.setLoginUrl("/login");
        bean.setUnauthorizedUrl("/fail");

        //设置所有请求都通过jwt过滤器
        Map<String, String> beanMap = new HashMap<>();
        beanMap.put("/msg", "jwt");
        //设置不过滤登录页面 (登入页面)
        beanMap.put("/login", "anon");
        //设置不过滤登录页面 (登出页面)
        beanMap.put("/logout", "anon");
        bean.setFilterChainDefinitionMap(beanMap);
        return bean;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(getDefaultWebSecurityManager());
        return advisor;
    }

    /**
     * 开启aop注解支持
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultaPorxy = new DefaultAdvisorAutoProxyCreator();
        defaultaPorxy.setProxyTargetClass(true);
        return defaultaPorxy;
    }


    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(getJwtRealm());
        return securityManager;
    }

    @Bean
    public JwtRealm getJwtRealm() {
        return new JwtRealm();
    }
}
