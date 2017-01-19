package com.reedl.common.sys.mapper;

import com.reedl.common.sys.entity.SysRole;
import com.reedl.common.sys.mapper.sql.SysRoleSql;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * Created by Li Libo on 2016/12/22.
 */
@Mapper
public interface SysRoleMapper {

    @SelectProvider(type = SysRoleSql.class, method = "selectRoleListByResourceId")
    List<SysRole> selectRoleListByResourceId(Long resourceId);
}
