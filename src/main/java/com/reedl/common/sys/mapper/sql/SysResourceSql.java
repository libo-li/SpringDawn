package com.reedl.common.sys.mapper.sql;

import org.apache.ibatis.jdbc.SQL;

/**
 * Created by Li Libo on 2016/12/22.
 */
public class SysResourceSql {

    public String selectResourceList() {
        return new SQL() {{
            SELECT("RES.*");
            FROM("SYS_RESOURCE RES");
        }}.toString();
    }
}
