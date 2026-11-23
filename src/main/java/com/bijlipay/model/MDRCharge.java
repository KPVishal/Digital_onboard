package com.bijlipay.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "mdr_charge")
public class MDRCharge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mdr_plan_id")
    private Long id;
    @ManyToOne(optional = true)
    @JoinColumn(name = "device_id",referencedColumnName = "device_id")
    private Device device;
    @ManyToOne(optional = true)
    @JoinColumn(name = "lead_source",referencedColumnName = "lead_source_id")
    private LeadSource leadSource;
    @ManyToOne(optional = true)
    @JoinColumn(name = "merchant_category_id",referencedColumnName = "merchant_category_id")
    private MerchantCategory merchantCategory;
    @Column(name = "corp_cc")
    private double corpCC;
    @Column(name = "debit_greaterthan_amount")
    private double debitGreaterThanAmount;
    @Column(name = "debit_lessthan_amount")
    private double debitLessThanAmount;
    @Column(name = "intl_cc")
    private double intlCC;
    @Column(name = "super_premium_cc")
    private double superPremiumCC;
    @Column(name = "std_cc")
    private double stdCC;
    @Column(name = "premium_cc")
    private double premiumCC;
    @Column(name = "mdr_plan_name", nullable = false)
    private String mdrPlanName;
    @Column(name = "is_active",columnDefinition = "tinyint(1) default 1")
    private boolean isActive;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;
    public void updateValues(MDRCharge mdrCharge){
        this.debitLessThanAmount=mdrCharge.debitLessThanAmount;
        this.debitGreaterThanAmount=mdrCharge.debitGreaterThanAmount;
        this.stdCC=mdrCharge.stdCC;
        this.premiumCC=mdrCharge.premiumCC;
        this.corpCC=mdrCharge.corpCC;
        this.intlCC=mdrCharge.intlCC;
        this.superPremiumCC=mdrCharge.superPremiumCC;
        this.device=mdrCharge.device;
        this.mdrPlanName=mdrCharge.mdrPlanName;
    }


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

    public MerchantCategory getMerchantCategory() {
        return merchantCategory;
    }

    public void setMerchantCategory(MerchantCategory merchantCategory) {
        this.merchantCategory = merchantCategory;
    }

    public double getCorpCC() {
        return corpCC;
    }

    public void setCorpCC(double corpCC) {
        this.corpCC = corpCC;
    }

    public double getDebitGreaterThanAmount() {
        return debitGreaterThanAmount;
    }

    public void setDebitGreaterThanAmount(double debitGreaterThanAmount) {
        this.debitGreaterThanAmount = debitGreaterThanAmount;
    }

    public double getDebitLessThanAmount() {
        return debitLessThanAmount;
    }

    public void setDebitLessThanAmount(double debitLessThanAmount) {
        this.debitLessThanAmount = debitLessThanAmount;
    }

    public double getIntlCC() {
        return intlCC;
    }

    public void setIntlCC(double intlCC) {
        this.intlCC = intlCC;
    }

    public double getSuperPremiumCC() {
        return superPremiumCC;
    }

    public void setSuperPremiumCC(double superPremiumCC) {
        this.superPremiumCC = superPremiumCC;
    }

    public double getStdCC() {
        return stdCC;
    }

    public void setStdCC(double stdCC) {
        this.stdCC = stdCC;
    }

    public double getPremiumCC() {
        return premiumCC;
    }

    public void setPremiumCC(double premiumCC) {
        this.premiumCC = premiumCC;
    }

    public String getMdrPlanName() {
        return mdrPlanName;
    }

    public void setMdrPlanName(String mdrPlanName) {
        this.mdrPlanName = mdrPlanName;
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
        return "MDRCharge{" +
                "id=" + id +
                ", device=" + device +
                ", leadSource=" + leadSource +
                ", merchantCategory=" + merchantCategory +
                ", corpCC=" + corpCC +
                ", debitGreaterThanAmount=" + debitGreaterThanAmount +
                ", debitLessThanAmount=" + debitLessThanAmount +
                ", intlCC=" + intlCC +
                ", superPremiumCC=" + superPremiumCC +
                ", stdCC=" + stdCC +
                ", premiumCC=" + premiumCC +
                ", mdrPlanName='" + mdrPlanName + '\'' +
                ", isActive=" + isActive +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }


}
