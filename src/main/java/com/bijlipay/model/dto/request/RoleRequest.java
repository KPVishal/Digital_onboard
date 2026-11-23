package com.bijlipay.model.dto.request;

public class RoleRequest {

    private String roleName;

    private Integer hierarchyId;

    private String roleColor;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getHierarchyId() {
        return hierarchyId;
    }

    public void setHierarchyId(Integer hierarchyId) {
        this.hierarchyId = hierarchyId;
    }

    public String getRoleColor() {
        return roleColor;
    }

    public void setRoleColor(String roleColor) {
        this.roleColor = roleColor;
    }
}
