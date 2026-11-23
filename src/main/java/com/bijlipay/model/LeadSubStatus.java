package com.bijlipay.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="lead_sub_status")
public class LeadSubStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="lead_sub_status_id")
    private int leadSubStatusId;

    @Column(name="lead_sub_status_name")
    private String leadSubStatusName;

    @ManyToOne(optional = true)
    @JoinColumn(name = "lead_status_id",referencedColumnName = "lead_status_id")
    private LeadStatus leadStatus;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_at")
    private Date updatedAt;

    public LeadSubStatus(){

    }

    public LeadSubStatus(int leadSubStatusId, String leadSubStatusName, LeadStatus leadStatus, Date createdAt, Date updatedAt) {
        this.leadSubStatusId = leadSubStatusId;
        this.leadSubStatusName = leadSubStatusName;
        this.leadStatus = leadStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getLeadSubStatusId() {
        return leadSubStatusId;
    }

    public void setLeadSubStatusId(int leadSubStatusId) {
        this.leadSubStatusId = leadSubStatusId;
    }

    public String getLeadSubStatusName() {
        return leadSubStatusName;
    }

    public void setLeadSubStatusName(String leadSubStatusName) {
        this.leadSubStatusName = leadSubStatusName;
    }

    public LeadStatus getLeadStatus() {
        return leadStatus;
    }

    public void setLeadStatus(LeadStatus leadStatus) {
        this.leadStatus = leadStatus;
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
        return "LeadSubStatus{" +
                "leadSubStatusId=" + leadSubStatusId +
                ", leadSubStatusName='" + leadSubStatusName + '\'' +
                ", leadStatus=" + leadStatus +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
