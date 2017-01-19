package com.reedl.common.sys.mapper;

import com.reedl.common.sys.entity.SysResource;
import com.reedl.common.sys.mapper.sql.SysResourceSql;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * Created by Li Libo on 2016/12/22.
 */
@Mapper
public interface SysResourceMapper {

    @SelectProvider(type = SysResourceSql.class, method = "selectResourceList")
    List<SysResource> selectResourceList();
}
