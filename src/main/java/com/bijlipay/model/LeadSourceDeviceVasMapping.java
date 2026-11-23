package com.bijlipay.model;

import javax.persistence.*;

@Entity
@Table(name = "lead_source_device_vas_mapping")
public class LeadSourceDeviceVasMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne(optional = true)
    @JoinColumn(name = "vas", referencedColumnName = "vas_id")
    private Vas vas;
    @ManyToOne(optional = true)
    @JoinColumn(name = "lead_source", referencedColumnName = "lead_source_id")
    private LeadSource leadSource;
    @ManyToOne(optional = true)
    @JoinColumn(name = "device", referencedColumnName = "device_id")
    private Device device;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    @Override
    public String toString() {
        return "LeadSourceDeviceVasMapping{" +
                "id=" + id +
                ", vas=" + vas +
                ", leadSource=" + leadSource +
                ", device=" + device +
                '}';
    }
}
