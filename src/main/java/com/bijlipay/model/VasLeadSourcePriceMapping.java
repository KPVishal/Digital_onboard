package com.bijlipay.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="vas_lead_source_price_mapping")
public class VasLeadSourcePriceMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="vas_lead_source_price_mapping_id")
    private int VasLeadSourcePriceMappingId;

    @ManyToOne(optional = false)
    @JoinColumn(name="vas_id",referencedColumnName = "vas_id")
    private Vas vas;

    @ManyToOne(optional = false)
    @JoinColumn(name="lead_source_id",referencedColumnName = "lead_source_id")
    private LeadSource leadSource;

    @Column(name="price")
    private double price;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_at")
    private Date updatedAt;

    public VasLeadSourcePriceMapping(){

    }
    public VasLeadSourcePriceMapping(int vasLeadSourcePriceMappingId, Vas vas, LeadSource leadSource, double price, Date createdAt, Date updatedAt) {
        VasLeadSourcePriceMappingId = vasLeadSourcePriceMappingId;
        this.vas = vas;
        this.leadSource = leadSource;
        this.price = price;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getVasLeadSourcePriceMappingId() {
        return VasLeadSourcePriceMappingId;
    }

    public void setVasLeadSourcePriceMappingId(int vasLeadSourcePriceMappingId) {
        VasLeadSourcePriceMappingId = vasLeadSourcePriceMappingId;
    }

    public Vas getVas() {
        return vas;
    }

    public void setVas(Vas vas) {
        this.vas = vas;
    }

    public LeadSource getLeadSource() {
        return leadSource;
    }

    public void setLeadSource(LeadSource leadSource) {
        this.leadSource = leadSource;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
        return "VasLeadSourcePriceMapping{" +
                "VasLeadSourcePriceMappingId=" + VasLeadSourcePriceMappingId +
                ", vas=" + vas +
                ", leadSource=" + leadSource +
                ", price=" + price +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
