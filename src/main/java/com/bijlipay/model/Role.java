package com.bijlipay.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private int roleId;

    @Column(name = "role", nullable = false, unique = true)
    private String role;

    @Column(name = "role_color")
    private String roleColor;

    @Column(name = "is_active", columnDefinition = "tinyint(1) default 1")
    private boolean isActive;
    @Column(name = "role_level", columnDefinition = "int(11) default 0")
    private int roleLevel;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    @JsonIgnore
    private Date updatedAt;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    @JsonIgnore
    private Date createdAt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "hierarchy_id", referencedColumnName = "hierarchy_id")
    private Hierarchy hierarchy;

    @Transient
    private String hierarchyRoleLevel;


    @Transient
    private Object predecessor;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRoleColor() {
        return roleColor;
    }

    public void setRoleColor(String roleColor) {
        this.roleColor = roleColor;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(int roleLevel) {
        this.roleLevel = roleLevel;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Hierarchy getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(Hierarchy hierarchy) {
        this.hierarchy = hierarchy;
    }

    public String getHierarchyRoleLevel() {
        return getHierarchy().getHierarchyCode() + "_" + getRoleLevel();
    }

    public void setHierarchyRoleLevel(String hierarchyRoleLevel) {
        this.hierarchyRoleLevel = hierarchyRoleLevel;
    }

    public Object getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(Object predecessor) {
        this.predecessor = predecessor;
    }


    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", role='" + role + '\'' +
                ", roleColor='" + roleColor + '\'' +
                ", isActive=" + isActive +
                ", roleLevel=" + roleLevel +
                ", updatedAt=" + updatedAt +
                ", createdAt=" + createdAt +
                ", hierarchy=" + hierarchy +
                ", hierarchyRoleLevel='" + hierarchyRoleLevel + '\'' +
                ", predecessor=" + predecessor +
                '}';
    }
}
