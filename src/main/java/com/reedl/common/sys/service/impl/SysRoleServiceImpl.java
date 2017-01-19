package com.reedl.common.sys.service.impl;

import com.reedl.common.sys.entity.SysRole;
import com.reedl.common.sys.mapper.SysRoleMapper;
import com.reedl.common.sys.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Li Libo on 2016/12/22.
 */
@Service
public class SysRoleServiceImpl implements ISysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRole> getRoleListByResourceId(Long resourceId) {
        return sysRoleMapper.selectRoleListByResourceId(resourceId);
    }
}
