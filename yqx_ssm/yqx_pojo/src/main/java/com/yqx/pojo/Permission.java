package com.yqx.pojo;

import java.util.List;

// 创建一张资源权限表
// 一个用户可以有多个权限 一个权限可以被多个用户所共享
public class Permission {

    private String id;              // 权限ID
    private String permissionName;  // 权限名称
    private String url; // 权限URL  一个权限可以被多个用户拥有
    private List<Role> roles;       // 权限所对应的用户

    @Override
    public String toString() {
        return "Permission{" +
                "id='" + id + '\'' +
                ", permissionName='" + permissionName + '\'' +
                ", url='" + url + '\'' +
                ", roles=" + roles +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
