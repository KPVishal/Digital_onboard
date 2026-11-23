package com.bijlipay.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "document_category")
public class DocumentCategory {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_category_id", nullable = false)
    private long id;

    @Column(name = "category_name", nullable = false, unique = true)
    private String name;

    @Column(name = "is_active", columnDefinition = "tinyint(1) default 1")
    private boolean isActive;

    @Column(name = "category_description", nullable = true)
    private String categoryDescription;

    @Column(name = "is_upload_any_one", nullable = true)
    private boolean isUploadAnyOne;

    @Column(name = "category_identify", nullable = true)
    private int categoryIdentify; // 1- LeadDocuments, 2- ImplementDocuments

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

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public boolean isUploadAnyOne() {
        return isUploadAnyOne;
    }

    public void setUploadAnyOne(boolean uploadAnyOne) {
        isUploadAnyOne = uploadAnyOne;
    }

    public int getCategoryIdentify() {
        return categoryIdentify;
    }

    public void setCategoryIdentify(int categoryIdentify) {
        this.categoryIdentify = categoryIdentify;
    }

    @Override
    public String toString() {
        return "DocumentCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isActive=" + isActive +
                ", categoryDescription='" + categoryDescription + '\'' +
                ", isUploadAnyOne=" + isUploadAnyOne +
                ", categoryIdentify=" + categoryIdentify +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
