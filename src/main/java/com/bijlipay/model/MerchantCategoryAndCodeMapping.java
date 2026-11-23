package com.bijlipay.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "merchant_category_and_code_mapping")
public class MerchantCategoryAndCodeMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mapping_id", nullable = false)
    private long id;

    @ManyToOne(optional = true)
    @JoinColumn(name = "merchant_category", referencedColumnName = "merchant_category_id", nullable = true)
    private MerchantCategory merchantCategory;

    @ManyToOne(optional = true)
    @JoinColumn(name = "merchant_category_code", referencedColumnName = "mcc_id", nullable = true)
    private MerchantCategoryCode merchantCategoryCode;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public MerchantCategory getMerchantCategory() {
        return merchantCategory;
    }

    public void setMerchantCategory(MerchantCategory merchantCategory) {
        this.merchantCategory = merchantCategory;
    }

    public MerchantCategoryCode getMerchantCategoryCode() {
        return merchantCategoryCode;
    }

    public void setMerchantCategoryCode(MerchantCategoryCode merchantCategoryCode) {
        this.merchantCategoryCode = merchantCategoryCode;
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
        return "MerchantCategoryAndCodeMapping{" +
                "id=" + id +
                ", merchantCategory=" + merchantCategory +
                ", merchantCategoryCode=" + merchantCategoryCode +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
