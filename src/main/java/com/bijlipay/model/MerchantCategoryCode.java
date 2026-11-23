package com.bijlipay.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "merchant_category_code")
public class MerchantCategoryCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mcc_id")
    private Long mccId;

    @Column(name = "category_name",nullable = false)
    private String categoryName;

    @Column(name = "industry_name",nullable = false)
    private String industryName;

    @Column(name = "is_active",columnDefinition = "tinyint(1) default 1")
    private boolean isActive;

    @Column(name = "mcc_code",nullable = false,unique = true)
    private String mccCode;

    @Column(name = "is_restricted", columnDefinition = "tinyint(1) default 0")
    private boolean isRestricted;

    @Column(name = "is_fuel_mcc",columnDefinition = "tinyint(1) default 0")
    private boolean isFuelMcc;
    @Column(name = "is_small_restricted", columnDefinition = "tinyint(1) default 0")
    private boolean isSmallRestricted;
    @Column(name = "is_other_restricted", columnDefinition = "tinyint(1) default 0")
    private boolean isOtherRestricted;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    public Long getMccId() {
        return mccId;
    }

    public void setMccId(Long mccId) {
        this.mccId = mccId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getMccCode() {
        return mccCode;
    }

    public void setMccCode(String mccCode) {
        this.mccCode = mccCode;
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

    public boolean isRestricted() {
        return isRestricted;
    }

    public void setRestricted(boolean restricted) {
        isRestricted = restricted;
    }

    public boolean isFuelMcc() {
        return isFuelMcc;
    }

    public void setFuelMcc(boolean fuelMcc) {
        isFuelMcc = fuelMcc;
    }

    public boolean isSmallRestricted() {
        return isSmallRestricted;
    }

    public void setSmallRestricted(boolean smallRestricted) {
        isSmallRestricted = smallRestricted;
    }

    public boolean isOtherRestricted() {
        return isOtherRestricted;
    }

    public void setOtherRestricted(boolean otherRestricted) {
        isOtherRestricted = otherRestricted;
    }

    @Override
    public String toString() {
        return "MerchantCategoryCode{" +
                "mccId=" + mccId +
                ", categoryName='" + categoryName + '\'' +
                ", industryName='" + industryName + '\'' +
                ", isActive=" + isActive +
                ", mccCode='" + mccCode + '\'' +
                ", isRestricted=" + isRestricted +
                ", isFuelMcc=" + isFuelMcc +
                ", isSmallRestricted=" + isSmallRestricted +
                ", isOtherRestricted=" + isOtherRestricted +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
