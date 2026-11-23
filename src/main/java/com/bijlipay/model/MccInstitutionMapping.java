package com.bijlipay.model;


import javax.persistence.*;

@Entity
@Table(name = "mcc_institution_mapping")
public class MccInstitutionMapping {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String mcc;
    private String mccDescription;
    @ManyToOne
    @JoinColumn(name = "small_merchant",referencedColumnName = "id")
    private OnboardInstance smallMerchant;

    @ManyToOne
    @JoinColumn(name = "other_merchant",referencedColumnName = "id")
    private OnboardInstance otherMerchant;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public String getMccDescription() {
        return mccDescription;
    }

    public void setMccDescription(String mccDescription) {
        this.mccDescription = mccDescription;
    }

    public OnboardInstance getSmallMerchant() {
        return smallMerchant;
    }

    public void setSmallMerchant(OnboardInstance smallMerchant) {
        this.smallMerchant = smallMerchant;
    }

    public OnboardInstance getOtherMerchant() {
        return otherMerchant;
    }

    public void setOtherMerchant(OnboardInstance otherMerchant) {
        this.otherMerchant = otherMerchant;
    }
}
