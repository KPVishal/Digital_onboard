package com.bijlipay.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "rental_charge")
public class RentalCharge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_plan_id", columnDefinition = "BIGINT", nullable = false)
    private Long id;
    @ManyToOne(optional = true)
    @JoinColumn(name = "device_id",referencedColumnName = "device_id")
    private Device device;
    @ManyToOne(optional = true)
    @JoinColumn(name = "lead_source_id",referencedColumnName = "lead_source_id")
    private LeadSource leadSource;
    @ManyToOne(optional = true)
    @JoinColumn(name = "plan_id",referencedColumnName = "plan_id")
    private Plan plan;
    @Column(name = "is_active",columnDefinition = "tinyint(1) default 1")
    private boolean isActive;
    @Column(name = "monthly_fees")
    private double monthlyFees;
    @Column(name = "setup_fees")
    private double setupFees;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public LeadSource getLeadSource() {
        return leadSource;
    }

    public void setLeadSource(LeadSource leadSource) {
        this.leadSource = leadSource;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public double getMonthlyFees() {
        return monthlyFees;
    }

    public void setMonthlyFees(double monthlyFees) {
        this.monthlyFees = monthlyFees;
    }

    public double getSetupFees() {
        return setupFees;
    }

    public void setSetupFees(double setupFees) {
        this.setupFees = setupFees;
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
        return "RentalCharge{" +
                "id=" + id +
                ", device=" + device +
                ", leadSource=" + leadSource +
                ", plan=" + plan +
                ", isActive=" + isActive +
                ", monthlyFees=" + monthlyFees +
                ", setupFees=" + setupFees +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
