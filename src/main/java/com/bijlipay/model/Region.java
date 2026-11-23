package com.bijlipay.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="region")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private int id;

    @Column(name = "region_area_name")
    private String regionAreaName;

    @ManyToOne(optional = true)
    @JoinColumn(name = "region_group_id",referencedColumnName = "region_group_id")
    private RegionGroup regionGroup;

    @Column(name="is_active",columnDefinition = "tinyint(1) default 1")
    private boolean isActive;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name="created_at")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegionAreaName() {
        return regionAreaName;
    }

    public void setRegionAreaName(String regionAreaName) {
        this.regionAreaName = regionAreaName;
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


    @Override
    public String toString() {
        return "Region{" +
                "id=" + id +
                ", regionAreaName='" + regionAreaName + '\'' +
                ", regionGroup=" + regionGroup +
                ", isActive=" + isActive +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
