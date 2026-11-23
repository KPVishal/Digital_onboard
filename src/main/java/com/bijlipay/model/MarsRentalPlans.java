package com.bijlipay.model;

import javax.persistence.*;

@Entity
@Table(name = "mars_rental_plans")
public class MarsRentalPlans {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;
//    @Column(name = "setupFee")
//    private double setupFee;

    @Column(name = "setup_fees")
    private String setup_fees;
    @Column(name = "monthly_fees")
    private double monthlyFees;

    @Column(name = "plan_frequency")
    private String planFrequency;

    @ManyToOne(optional = true)
    @JoinColumn(name = "lead_source_id",referencedColumnName = "lead_source_id")
    private LeadSource leadSource;

    @ManyToOne(optional = true)
    @JoinColumn(name = "device_id",referencedColumnName = "device_id")
    private Device device;

    @ManyToOne(optional = true)
    @JoinColumn(name = "plan_id",referencedColumnName = "plan_id")
    private Plan plan;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public double getSetupFee() {
//        return setupFee;
//    }
//
//    public void setSetupFee(double setupFee) {
//        this.setupFee = setupFee;
//    }

    public String getSetup_fees() {
        return setup_fees;
    }

    public void setSetup_fees(String setup_fees) {
        this.setup_fees = setup_fees;
    }

    public double getMonthlyFees() {
        return monthlyFees;
    }

    public void setMonthlyFees(double monthlyFees) {
        this.monthlyFees = monthlyFees;
    }

    public String getPlanFrequency() {
        return planFrequency;
    }

    public void setPlanFrequency(String planFrequency) {
        this.planFrequency = planFrequency;
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

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    @Override
    public String toString() {
        return "MarsRentalPlans{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
//                ", setupFee=" + setupFee +
                ", setup_fees='" + setup_fees + '\'' +
                ", monthlyFees=" + monthlyFees +
                ", planFrequency='" + planFrequency + '\'' +
                ", leadSource=" + leadSource +
                ", device=" + device +
                ", plan=" + plan +
                '}';
    }
}
