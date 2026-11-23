package com.bijlipay.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "service_req_clients")
public class ServiceRequestClients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "name", nullable = true, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = true)
    private User userId;

    @ManyToOne
    @JoinColumn(name = "source_id", referencedColumnName = "lead_source_id", nullable = true)
    private LeadSource sourceId;

    @Column(name = "auth_method", nullable = true)
    private String authMethod;

    @Column(name = "auth_key", nullable = true)
    private String authKey;

    @Column(name = "client_type", nullable = true)
    private boolean clientType;

    @Column(name = "callback_url",nullable = true)
    private String callBackUrl;

    @Column(name = "auth_value", columnDefinition = "TEXT", nullable = true)
    private String authValue;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private Date createdDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_date")
    private Date updatedDate;

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

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public LeadSource getSourceId() {
        return sourceId;
    }

    public void setSourceId(LeadSource sourceId) {
        this.sourceId = sourceId;
    }

    public String getAuthMethod() {
        return authMethod;
    }

    public void setAuthMethod(String authMethod) {
        this.authMethod = authMethod;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public boolean isClientType() {
        return clientType;
    }

    public void setClientType(boolean clientType) {
        this.clientType = clientType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCallBackUrl() {
        return callBackUrl;
    }

    public void setCallBackUrl(String callBackUrl) {
        this.callBackUrl = callBackUrl;
    }

    public String getAuthValue() {
        return authValue;
    }

    public void setAuthValue(String authValue) {
        this.authValue = authValue;
    }

    @Override
    public String toString() {
        return "ServiceReqClients{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userId=" + userId +
                ", sourceId=" + sourceId +
                ", authMethod='" + authMethod + '\'' +
                ", authKey='" + authKey + '\'' +
                ", clientType=" + clientType +
                ", callBackUrl='" + callBackUrl + '\'' +
                ", authValue='" + authValue + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
