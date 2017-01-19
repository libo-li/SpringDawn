package com.reedl.common.sys.mapper.sql;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;


/**
 * Created by Li Libo on 2016/12/9.
 */
public class SysUserSql {

    public String selectUserByUsername(final String username) {
        return new SQL() {{
            SELECT("U.*");
            FROM("SYS_USER U");
            WHERE("U.USERNAME = #{username}");
        }}.toString();
    }

    public String selectRolesByUsername(final String username) {
        return new SQL() {{
            SELECT("R.ROLENAME");
            FROM("SYS_USER_ROLE U_R");
            INNER_JOIN("SYS_USER U ON U.ID = U_R.USER_ID");
            INNER_JOIN("SYS_ROLE R ON R.ID = U_R.ROLE_ID");
            WHERE("U.USERNAME = #{username}");
        }}.toString();
    }

}
