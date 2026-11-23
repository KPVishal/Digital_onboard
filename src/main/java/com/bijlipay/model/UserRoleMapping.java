package com.bijlipay.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_role_mapping")
public class UserRoleMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_role_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "hierarchy_id",referencedColumnName = "hierarchy_id")
    private Hierarchy hierarchy;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "region_id",referencedColumnName = "region_id")
    private Region region;

    @ManyToOne
    @JoinColumn(name = "reference_id",referencedColumnName = "user_id")
    private User reference;

    @ManyToOne
    @JoinColumn(name = "role_id",referencedColumnName = "role_id" )
    private Role role;

    @ManyToOne
    @JoinColumn(name = "region_group_id",referencedColumnName ="region_group_id" )
    private RegionGroup regionGroup;

    @Column(name = "is_active")
    private boolean isActive;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    public UserRoleMapping (){

    }

    public UserRoleMapping(long id, Hierarchy hierarchy, User user, Region region, User reference, Role role, RegionGroup regionGroup, boolean isActive, Date createdAt, Date updatedAt) {
        this.id = id;
        this.hierarchy = hierarchy;
        this.user = user;
        this.region = region;
        this.reference = reference;
        this.role = role;
        this.regionGroup = regionGroup;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Hierarchy getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(Hierarchy hierarchy) {
        this.hierarchy = hierarchy;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public RegionGroup getRegionGroup() {
        return regionGroup;
    }

    public void setRegionGroup(RegionGroup regionGroup) {
        this.regionGroup = regionGroup;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getReference() {
        return reference;
    }

    public void setReference(User reference) {
        this.reference = reference;
    }

    @Override
    public String toString() {
        return "UserRoleMapping{" +
                "id=" + id +
                ", hierarchy=" + hierarchy +
                ", region=" + region +
                ", role=" + role +
                ", regionGroup=" + regionGroup +
                ", isActive=" + isActive +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
