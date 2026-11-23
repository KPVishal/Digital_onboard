package com.bijlipay.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bank_ops_user")
public class BankOpsUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_ops_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "leadSource",referencedColumnName = "lead_source_id")
    private LeadSource leadSource;

    @ManyToOne
    @JoinColumn(name = "user",referencedColumnName = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "created_by",referencedColumnName ="user_id")
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "updated_by",referencedColumnName ="user_id")
    private User updatedBy;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    public BankOpsUser(){

    }

    public BankOpsUser(long id, LeadSource leadSource, User user, User createdBy, User updatedBy, Date createdAt, Date updatedAt) {
        this.id = id;
        this.leadSource = leadSource;
        this.user = user;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LeadSource getLeadSource() {
        return leadSource;
    }

    public void setLeadSource(LeadSource leadSource) {
        this.leadSource = leadSource;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(User updatedBy) {
        this.updatedBy = updatedBy;
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
        return "BankOpsUser{" +
                "id=" + id +
                ", leadSource=" + leadSource +
                ", user=" + user +
                ", createdBy=" + createdBy +
                ", updatedBy=" + updatedBy +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }


}
