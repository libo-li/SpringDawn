package com.reedl.common.sys.controller;

import com.reedl.common.sys.entity.SysUser;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Li Libo on 2016/11/24.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/{id}")
    public SysUser view(@PathVariable("id") Long id) {
        SysUser user = new SysUser();
        user.setId(id);
        user.setUsername("admin");
        return user;
    }

}