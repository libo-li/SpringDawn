package com.reedl.common.sys.mapper.sql;

import org.apache.ibatis.jdbc.SQL;

/**
 * Created by Li Libo on 2016/12/22.
 */
public class SysRoleSql {

    public String selectRoleListByResourceId(final Long resourceId) {
        return new SQL() {{
            SELECT("R.*");
            FROM("SYS_ROLE_RESOURCE R_RES");
            INNER_JOIN("SYS_RESOURCE RES ON RES.ID = R_RES.RESOURCE_ID");
            INNER_JOIN("SYS_ROLE R ON R.ID = R_RES.ROLE_ID");
            WHERE("RESOURCE.ID = #{resourceId}");
        }}.toString();
    }

}
