package com.reedl.common.sys.service.impl;

import com.reedl.common.sys.entity.SysUser;
import com.reedl.common.sys.mapper.SysUserMapper;
import com.reedl.common.sys.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Li Libo on 2016/12/9.
 */
@Service
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser getUserByUsername(String username) {
        return sysUserMapper.selectUserByUsername(username);
    }

    @Override
    public Collection<GrantedAuthority> getAuthsByUsername(String username) {

        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        List<String> roles = sysUserMapper.selectRolesByUsername(username);

        for(String role : roles){
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role);
            grantedAuthorities.add(grantedAuthority);
        }

        return grantedAuthorities;
    }

}
