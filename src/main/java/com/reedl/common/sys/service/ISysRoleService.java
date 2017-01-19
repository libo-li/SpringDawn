package com.reedl.common.sys.service;

import com.reedl.common.sys.entity.SysRole;

import java.util.List;

/**
 * Created by Li Libo on 2016/12/22.
 */
public interface ISysRoleService {

    List<SysRole> getRoleListByResourceId(Long resourceId);

}
