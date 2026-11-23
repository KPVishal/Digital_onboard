package com.bijlipay.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "lead_source")
public class LeadSource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="lead_source_id")
    private int id;

    @Column(name="lead_source_name")
    private String leadSourceName;

    @Column(name="is_active",columnDefinition = "tinyint(1) default 1")
    private boolean isActive;

    @Column(name="is_sole_emp_to_be_collected",nullable = false)
    private boolean isSoleEmpToBeCollected;

    @Column(name = "bank_enable", nullable = false)
    private String bank_enable;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at",columnDefinition = "TIMESTAMP")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_at",columnDefinition = "TIMESTAMP")
    private Date updatedAt;

    public LeadSource(){

    }

    public LeadSource(int id, String leadSourceName, boolean isActive, boolean isSoleEmpToBeCollected, Date createdAt, Date updatedAt, String bank_enable) {
        this.id = id;
        this.leadSourceName = leadSourceName;
        this.isActive = isActive;
        this.isSoleEmpToBeCollected = isSoleEmpToBeCollected;
        this.bank_enable = bank_enable;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLeadSourceName() {
        return leadSourceName;
    }

    public void setLeadSourceName(String leadSourceName) {
        this.leadSourceName = leadSourceName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isSoleEmpToBeCollected() {
        return isSoleEmpToBeCollected;
    }

    public void setSoleEmpToBeCollected(boolean soleEmpToBeCollected) {
        isSoleEmpToBeCollected = soleEmpToBeCollected;
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

    public String getBank_enable() {
        return bank_enable;
    }

    public void setBank_enable(String bank_enable) {
        this.bank_enable = bank_enable;
    }

    @Override
    public String toString() {
        return "LeadSource{" +
                "id=" + id +
                ", leadSourceName='" + leadSourceName + '\'' +
                ", isActive=" + isActive +
                ", isSoleEmpToBeCollected=" + isSoleEmpToBeCollected +
                ", bank_enable='" + bank_enable + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
