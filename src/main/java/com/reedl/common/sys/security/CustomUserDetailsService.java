package com.reedl.common.sys.security;

import com.reedl.common.sys.entity.SysUser;
import com.reedl.common.sys.service.ISysUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 实现自定义用户验证信息
 * Created by Li Libo on 2016/12/5.
 */
//@Service
@Component("CustomUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    protected static Logger logger = Logger.getLogger("CustomUserDetailsService");

    @Autowired
    private ISysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("===>loadUserByUsername");
        User user = null;

        //获取DB中用户信息。
        SysUser sysUser = sysUserService.getUserByUsername(username);
        logger.debug("###"+sysUser.toString());

        if(sysUser!=null){
            //获取权限
            Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
            auths = sysUserService.getAuthsByUsername(username);
            logger.debug("###[auths信息]"+auths.toString());

            //User是UserDetails的实现。用DB中用户信息初始化user，实现自定义验证
            String password = sysUser.getPassword();
            boolean enabled = sysUser.isEnabled();
            boolean accountNonExpired = sysUser.isAccountNonExpired();
            boolean credentialsNonExpired = sysUser.isCredentialsNonExpired();
            boolean accountNonLocked = sysUser.isAccountNonLocked();
            user = new User(username,
                    password,
                    enabled,
                    accountNonExpired,
                    credentialsNonExpired,
                    accountNonLocked,
                    auths);
            logger.debug("登陆用户信息：username="+username);
        } else {
            logger.info("用户不存在：username="+username);
            throw new UsernameNotFoundException("user is not exited");
        }

        return user;
    }
}
