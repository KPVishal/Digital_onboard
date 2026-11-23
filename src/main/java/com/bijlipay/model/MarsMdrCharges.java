package com.bijlipay.model;

import org.apache.el.lang.FunctionMapperImpl;
import org.json.simple.JSONObject;
import org.springframework.cglib.core.internal.Function;

import javax.persistence.*;

@Entity
@Table(name = "mars_mdr_charges")
public class MarsMdrCharges {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;
    @Column(name = "code")
    private String code;
    @Column(name = "domestic_debit_upto_2000",columnDefinition = "JSON")
    private String domesticDebitUpTo2000;
    @Column(name = "domestic_debit_above_2000",columnDefinition = "JSON")
    private String domesticDebitAbove2000;
    @Column(name = "standard_or_classic",columnDefinition = "JSON")
    private String standardOrClassic;
    @Column(name = "premium_or_platinum", columnDefinition = "JSON")
    private String premiumOrPlatinum;
    @Column(name = "super_premium_or_signature", columnDefinition = "JSON")
    private String superPremiumOrSignature;
    @Column(name = "commercial_or_corporate",columnDefinition = "JSON")
    private String commercialOrCorporate;
    @Column(name = "international_debit_card",columnDefinition = "JSON")
    private String internationalDebitCard;
    @Column(name = "international_credit_card",columnDefinition = "JSON")
    private String internationalCreditCard;
    @Column(name = "onus",columnDefinition = "JSON")
    private String onus;
    @Column(name = "diners",columnDefinition = "JSON")
    private String diners;
    @Column(name = "cashAt_pos",columnDefinition = "JSON")
    private String cashAtPos;
    @Column(name = "master_pass",columnDefinition = "JSON")
    private String masterPass;
    @ManyToOne(optional = true)
    @JoinColumn(name = "lead_source",referencedColumnName = "lead_source_id")
    private LeadSource leadSource;
    @ManyToOne(optional = true)
    @JoinColumn(name = "device",referencedColumnName = "device_id")
    private Device device;
    @ManyToOne(optional = true)
    @JoinColumn(name = "merchant_category",referencedColumnName = "merchant_category_id")
    private MerchantCategory merchantCategory;
    @Column(name = "is_active",columnDefinition = "tinyint(1) default 1")
    private boolean isActive;
    @Column(name = "type")
    private String type;

    @Column(name = "upi_Credit_Above_2000",columnDefinition = "JSON")
    private String upiCreditAbove2000;
    @Column(name = "upi_Credit_Upto_2000",columnDefinition = "JSON")
    private String upiCreditUpto2000;
    @Column(name = "upi_Above_2000",columnDefinition = "JSON")
    private String upiAbove2000;
    @Column(name = "upi_Upto_2000",columnDefinition = "JSON")
    private String upiUpto2000;
    @Column(name = "amex_Domestic ",columnDefinition = "JSON")
    private String amexDomestic;
    @Column(name = "amex_International",columnDefinition = "JSON")
    private String amexInternational;


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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDomesticDebitUpTo2000() {
        return domesticDebitUpTo2000;
    }

    public void setDomesticDebitUpTo2000(String domesticDebitUpTo2000) {
        this.domesticDebitUpTo2000 = domesticDebitUpTo2000;
    }

    public String getDomesticDebitAbove2000() {
        return domesticDebitAbove2000;
    }

    public void setDomesticDebitAbove2000(String domesticDebitAbove2000) {
        this.domesticDebitAbove2000 = domesticDebitAbove2000;
    }

    public String getStandardOrClassic() {
        return standardOrClassic;
    }

    public void setStandardOrClassic(String standardOrClassic) {
        this.standardOrClassic = standardOrClassic;
    }

    public String getPremiumOrPlatinum() {
        return premiumOrPlatinum;
    }

    public void setPremiumOrPlatinum(String premiumOrPlatinum) {
        this.premiumOrPlatinum = premiumOrPlatinum;
    }

    public String getSuperPremiumOrSignature() {
        return superPremiumOrSignature;
    }

    public void setSuperPremiumOrSignature(String superPremiumOrSignature) {
        this.superPremiumOrSignature = superPremiumOrSignature;
    }

    public String getCommercialOrCorporate() {
        return commercialOrCorporate;
    }

    public void setCommercialOrCorporate(String commercialOrCorporate) {
        this.commercialOrCorporate = commercialOrCorporate;
    }

    public String getInternationalDebitCard() {
        return internationalDebitCard;
    }

    public void setInternationalDebitCard(String internationalDebitCard) {
        this.internationalDebitCard = internationalDebitCard;
    }

    public String getInternationalCreditCard() {
        return internationalCreditCard;
    }

    public void setInternationalCreditCard(String internationalCreditCard) {
        this.internationalCreditCard = internationalCreditCard;
    }

    public String getOnus() {
        return onus;
    }

    public void setOnus(String onus) {
        this.onus = onus;
    }

    public String getDiners() {
        return diners;
    }

    public void setDiners(String diners) {
        this.diners = diners;
    }

    public String getCashAtPos() {
        return cashAtPos;
    }

    public void setCashAtPos(String cashAtPos) {
        this.cashAtPos = cashAtPos;
    }

    public String getMasterPass() {
        return masterPass;
    }

    public void setMasterPass(String masterPass) {
        this.masterPass = masterPass;
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

    public MerchantCategory getMerchantCategory() {
        return merchantCategory;
    }

    public void setMerchantCategory(MerchantCategory merchantCategory) {
        this.merchantCategory = merchantCategory;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpiCreditAbove2000() {
        return upiCreditAbove2000;
    }

    public void setUpiCreditAbove2000(String upiCreditAbove2000) {
        this.upiCreditAbove2000 = upiCreditAbove2000;
    }

    public String getUpiCreditUpto2000() {
        return upiCreditUpto2000;
    }

    public void setUpiCreditUpto2000(String upiCreditUpto2000) {
        this.upiCreditUpto2000 = upiCreditUpto2000;
    }

    public String getUpiAbove2000() {
        return upiAbove2000;
    }

    public void setUpiAbove2000(String upiAbove2000) {
        this.upiAbove2000 = upiAbove2000;
    }

    public String getUpiUpto2000() {
        return upiUpto2000;
    }

    public void setUpiUpto2000(String upiUpto2000) {
        this.upiUpto2000 = upiUpto2000;
    }

    public String getAmexDomestic() {
        return amexDomestic;
    }

    public void setAmexDomestic(String amexDomestic) {
        this.amexDomestic = amexDomestic;
    }

    public String getAmexInternational() {
        return amexInternational;
    }

    public void setAmexInternational(String amexInternational) {
        this.amexInternational = amexInternational;
    }

    @Override
    public String toString() {
        return "MarsMdrCharges{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", domesticDebitUpTo2000='" + domesticDebitUpTo2000 + '\'' +
                ", domesticDebitAbove2000='" + domesticDebitAbove2000 + '\'' +
                ", standardOrClassic='" + standardOrClassic + '\'' +
                ", premiumOrPlatinum='" + premiumOrPlatinum + '\'' +
                ", superPremiumOrSignature='" + superPremiumOrSignature + '\'' +
                ", commercialOrCorporate='" + commercialOrCorporate + '\'' +
                ", internationalDebitCard='" + internationalDebitCard + '\'' +
                ", internationalCreditCard='" + internationalCreditCard + '\'' +
                ", onus='" + onus + '\'' +
                ", diners='" + diners + '\'' +
                ", cashAtPos='" + cashAtPos + '\'' +
                ", masterPass='" + masterPass + '\'' +
                ", leadSource=" + leadSource +
                ", device=" + device +
                ", merchantCategory=" + merchantCategory +
                ", isActive=" + isActive +
                ", type='" + type + '\'' +
                ", upiCreditAbove2000='" + upiCreditAbove2000 + '\'' +
                ", upiCreditUpto2000='" + upiCreditUpto2000 + '\'' +
                ", upiAbove2000='" + upiAbove2000 + '\'' +
                ", upiUpto2000='" + upiUpto2000 + '\'' +
                ", amexDomestic='" + amexDomestic + '\'' +
                ", amexInternational='" + amexInternational + '\'' +
                '}';
    }
}
