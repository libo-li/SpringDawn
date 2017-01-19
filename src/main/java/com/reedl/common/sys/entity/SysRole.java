package com.reedl.common.sys.entity;

/**
 * Created by Li Libo on 2016/12/22.
 */
public class SysRole {

    private Long id;
    private String rolename;
    private String realname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }
}
