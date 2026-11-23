package com.bijlipay.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="sim_provider")
public class SimProvider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sim_provider_id")
    private long simProviderId;

    @Column(name="network_name")
    private String networkName;

    @Column(name="is_active")
    private boolean isActive;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_at")
    private Date updatedAt;

    public SimProvider(){

    }

    public SimProvider(long simProviderId, String networkName, boolean isActive, Date createdAt, Date updatedAt) {
        this.simProviderId = simProviderId;
        this.networkName = networkName;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getSimProviderId() {
        return simProviderId;
    }

    public void setSimProviderId(long simProviderId) {
        this.simProviderId = simProviderId;
    }

    public String getNetworkName() {
        return networkName;
    }

    public void setNetworkName(String networkName) {
        this.networkName = networkName;
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
        return "SimProvider{" +
                "simProviderId=" + simProviderId +
                ", networkName='" + networkName + '\'' +
                ", isActive=" + isActive +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
