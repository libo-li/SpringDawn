package com.reedl.common.config;

import com.reedl.common.sys.security.CustomAccessDecisionManager;
import com.reedl.common.sys.security.CustomFilterSecurityInterceptor;
import com.reedl.common.sys.security.CustomInvocationSecurityMetadataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import javax.servlet.Filter;

/**
 * Created by Li Libo on 2016/11/29.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService customUserDetailsService;

    @Autowired
    private CustomAccessDecisionManager customAccessDecisionManager;

    @Autowired
    private CustomInvocationSecurityMetadataSource customInvocationSecurityMetadataSource;

    /**
     * 配置忽略静态资源
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .antMatchers("/static/**")
                .antMatchers("/favicon.ico");
    }

    /**
     * 配置拦截规则
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/main")
                .permitAll()
                .and()
            .logout()
                .permitAll()
                .and()
            .addFilterBefore(customFilter(), FilterSecurityInterceptor.class)//自定义拦截器
            .csrf().disable();//关闭csrf
    }

    /**
     * 自定义UserDetailsService
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {

        auth.userDetailsService(customUserDetailsService).passwordEncoder(
                new Md5PasswordEncoder());

    }

    /**
     * Bean配置
     *
     * <beans:bean id="myFilter" class="org.springframework.security.web.access.intercept.FilterSecurityInterceptor">
     *   <beans:property name="accessDecisionManager" ref="myAccessDecisionManagerBean" />
     *   <beans:property name="securityMetadataSource" ref="securityMetadataSource" />
     * </beans:bean>
     *
     * 自定义RBAC过滤器，必备两个属性
     * AccessDecisionManager 访问决策器 决策是否授权
     * SecurityMetadataSource 受保护的资源 定义资源url和所需权限的映射
     */
    @Bean(name = "customFilter")
    public Filter customFilter() throws Exception {
        CustomFilterSecurityInterceptor customFilter = new CustomFilterSecurityInterceptor();
        customFilter.setAccessDecisionManager(customAccessDecisionManager);
        customFilter.setSecurityMetadataSource(customInvocationSecurityMetadataSource);
        customFilter.setAuthenticationManager(authenticationManagerBean());
        return customFilter;
    }

}
