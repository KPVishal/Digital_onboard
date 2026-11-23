package com.bijlipay.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sub_document_type")
public class SubDocumentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub_document_type_id", nullable = false)
    private long subDocumentTypeId;

    @Column(name = "sub_document_type_name", nullable = false, unique = true)
    private String name;

    @Column(name = "is_active", columnDefinition = "tinyint(1) default 1")
    private boolean isActive;

    @Column(name = "is_signzy_verification_required", columnDefinition = "tinyint(1) default 0")
    private boolean isSignzyVerificationRequired;

    @ManyToOne
    @JoinColumn(name = "document_type", referencedColumnName = "document_type_id")
    private DocumentType documentType;

    @ManyToOne
    @JoinColumn(name = "document_category_id", referencedColumnName = "document_category_id")
    private DocumentCategory documentCategory;

    @ManyToOne
    @JoinColumn(name = "mcc_id", referencedColumnName = "mcc_id")
    private MerchantCategoryCode merchantCategoryCode;

    @Column(name = "mcc_category_indicator")
    private int mccCategoryIndicator;     // 0-basic  1-mcc 2-all mcc 3-implementation 4-mcc approval

    @Column(name = "is_mandatory")
    private boolean isMandatory;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    public long getSubDocumentTypeId() {
        return subDocumentTypeId;
    }

    public void setSubDocumentTypeId(long subDocumentTypeId) {
        this.subDocumentTypeId = subDocumentTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isSignzyVerificationRequired() {
        return isSignzyVerificationRequired;
    }

    public void setSignzyVerificationRequired(boolean signzyVerificationRequired) {
        isSignzyVerificationRequired = signzyVerificationRequired;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public DocumentCategory getDocumentCategory() {
        return documentCategory;
    }

    public void setDocumentCategory(DocumentCategory documentCategory) {
        this.documentCategory = documentCategory;
    }

    public MerchantCategoryCode getMerchantCategoryCode() {
        return merchantCategoryCode;
    }

    public void setMerchantCategoryCode(MerchantCategoryCode merchantCategoryCode) {
        this.merchantCategoryCode = merchantCategoryCode;
    }

    public int getMccCategoryIndicator() {
        return mccCategoryIndicator;
    }

    public void setMccCategoryIndicator(int mccCategoryIndicator) {
        this.mccCategoryIndicator = mccCategoryIndicator;
    }

    public boolean isMandatory() {
        return isMandatory;
    }

    public void setMandatory(boolean mandatory) {
        isMandatory = mandatory;
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
        return "SubDocumentType{" +
                "subDocumentTypeId=" + subDocumentTypeId +
                ", name='" + name + '\'' +
                ", isActive=" + isActive +
                ", isSignzyVerificationRequired=" + isSignzyVerificationRequired +
                ", documentType=" + documentType +
                ", documentCategory=" + documentCategory +
                ", merchantCategoryCode=" + merchantCategoryCode +
                ", mccCategoryIndicator=" + mccCategoryIndicator +
                ", isMandatory=" + isMandatory +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
