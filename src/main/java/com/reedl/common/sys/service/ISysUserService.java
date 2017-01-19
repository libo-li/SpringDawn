package com.reedl.common.sys.service;

import com.reedl.common.sys.entity.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**s
 * Created by Li Libo on 2016/11/29.
 */
public interface ISysUserService {

    SysUser getUserByUsername(String username);

    Collection<GrantedAuthority> getAuthsByUsername(String username);

}
