package com.bijlipay.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "vas_device_mapping")
public class VasDeviceMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vas_device_mapping_id", columnDefinition = "BIGINT", nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "device_id",referencedColumnName = "device_id")
    private Device device;

    @ManyToOne
    @JoinColumn(name = "vas_id",referencedColumnName = "vas_id")
    private Vas vas;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_at")
    private Date updatedAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Vas getVas() {
        return vas;
    }

    public void setVas(Vas vas) {
        this.vas = vas;
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
        return "VasDeviceMapping{" +
                "id=" + id +
                ", device=" + device +
                ", vas=" + vas +
                '}';
    }
}