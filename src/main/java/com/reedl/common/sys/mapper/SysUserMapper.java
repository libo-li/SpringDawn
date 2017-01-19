package com.reedl.common.sys.mapper;

import com.reedl.common.sys.entity.SysUser;
import com.reedl.common.sys.mapper.sql.SysUserSql;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * Created by Li Libo on 2016/11/28.
 */
@Mapper
public interface SysUserMapper {

    @SelectProvider(type = SysUserSql.class, method = "selectUserByUsername")
    SysUser selectUserByUsername(String username);

    @SelectProvider(type = SysUserSql.class, method = "selectRolesByUsername")
    List<String> selectRolesByUsername(String username);

}
