//package com.bijlipay.model;
//
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
//import javax.persistence.*;
//import java.util.Date;
//
//@Entity
//@Table(name = "document_category_mapping")
//public class DocumentCategoryMapping {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "document_category_mapping_id", nullable = false)
//    private long id;
//
//    @ManyToOne
//    @JoinColumn(name = "sub_document_type", referencedColumnName = "sub_document_type_id")
//    private SubDocumentType subDocumentType;
//
//    @ManyToOne
//    @JoinColumn(name = "document_category_id", referencedColumnName = "document_category_id")
//    private DocumentCategory documentCategory;
//
//    @ManyToOne
//    @JoinColumn(name = "mcc_id", referencedColumnName = "mcc_id")
//    private MerchantCategoryCode merchantCategoryCode;
//
//    @Column(name = "mcc_category_indicator")
//    private int mccCategoryIndicator;
//
//    @Column(name = "is_mandatory")
//    private boolean isMandatory;
//
//    @CreationTimestamp
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "created_at")
//    private Date createdAt;
//
//    @UpdateTimestamp
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "updated_at")
//    private Date updatedAt;
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public SubDocumentType getSubDocumentType() {
//        return subDocumentType;
//    }
//
//    public void setSubDocumentType(SubDocumentType subDocumentType) {
//        this.subDocumentType = subDocumentType;
//    }
//
//    public DocumentCategory getDocumentCategory() {
//        return documentCategory;
//    }
//
//    public void setDocumentCategory(DocumentCategory documentCategory) {
//        this.documentCategory = documentCategory;
//    }
//
//    public MerchantCategoryCode getMerchantCategoryCode() {
//        return merchantCategoryCode;
//    }
//
//    public void setMerchantCategoryCode(MerchantCategoryCode merchantCategoryCode) {
//        this.merchantCategoryCode = merchantCategoryCode;
//    }
//
//    public int getMccCategoryIndicator() {
//        return mccCategoryIndicator;
//    }
//
//    public void setMccCategoryIndicator(int mccCategoryIndicator) {
//        this.mccCategoryIndicator = mccCategoryIndicator;
//    }
//
//    public boolean isMandatory() {
//        return isMandatory;
//    }
//
//    public void setMandatory(boolean mandatory) {
//        isMandatory = mandatory;
//    }
//
//    public Date getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(Date createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public Date getUpdatedAt() {
//        return updatedAt;
//    }
//
//    public void setUpdatedAt(Date updatedAt) {
//        this.updatedAt = updatedAt;
//    }
//
//    @Override
//    public String toString() {
//        return "DocumentCategoryMapping{" +
//                "id=" + id +
//                ", subDocumentType=" + subDocumentType +
//                ", documentCategory=" + documentCategory +
//                ", merchantCategoryCode=" + merchantCategoryCode +
//                ", mccCategoryIndicator=" + mccCategoryIndicator +
//                ", isMandatory=" + isMandatory +
//                ", createdAt=" + createdAt +
//                ", updatedAt=" + updatedAt +
//                '}';
//    }
//}
