package com.bijlipay.model;

import com.bijlipay.model.marsDto.MdrChargesDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.json.simple.JSONObject;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MarsData {
    private String name;
    private String code;
    private double setupFees;
    private double monthlyFees;
    private String planFrequency;
    private String plan;
    private String setup_fees;

    //    private double domesticDebitUpTo2000;
//    private double domesticDebitAbove2000;
//    private double standardOrClassic;
//    private double premiumOrPlatinum;
//    private double superPremiumOrSignature;
//    private double commercialOrCorporate;
//    private double internationalDebitCard;
//    private double internationalCreditCard;
//    private double onus;
//    private double diners;
//    private double masterPass;
//    private double cashAtPos;

    private JSONObject domesticDebitUpTo2000;
    private JSONObject domesticDebitAbove2000;
    private JSONObject standardOrClassic;
    private JSONObject premiumOrPlatinum;
    private JSONObject superPremiumOrSignature;
    private JSONObject commercialOrCorporate;
    private JSONObject internationalDebitCard;
    private JSONObject internationalCreditCard;
    private JSONObject onus;
    private JSONObject diners;
    private JSONObject masterPass;
    private JSONObject cashAtPos;

    private String leadSource;
    private String deviceId;
    private String merchantCategoryId;
    private JSONObject upiCreditAbove2000;
    private JSONObject upiCreditUpto2000;
    private JSONObject upiAbove2000;
    private JSONObject upiUpto2000;
    private JSONObject amexDomestic;
    private JSONObject amexInternational;

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

//    public double getDomesticDebitUpTo2000() {
//        return domesticDebitUpTo2000;
//    }
//
//    public void setDomesticDebitUpTo2000(double domesticDebitUpTo2000) {
//        this.domesticDebitUpTo2000 = domesticDebitUpTo2000;
//    }
//
//    public double getDomesticDebitAbove2000() {
//        return domesticDebitAbove2000;
//    }
//
//    public void setDomesticDebitAbove2000(double domesticDebitAbove2000) {
//        this.domesticDebitAbove2000 = domesticDebitAbove2000;
//    }
//
//    public double getStandardOrClassic() {
//        return standardOrClassic;
//    }
//
//    public void setStandardOrClassic(double standardOrClassic) {
//        this.standardOrClassic = standardOrClassic;
//    }
//
//    public double getPremiumOrPlatinum() {
//        return premiumOrPlatinum;
//    }
//
//    public void setPremiumOrPlatinum(double premiumOrPlatinum) {
//        this.premiumOrPlatinum = premiumOrPlatinum;
//    }
//
//    public double getSuperPremiumOrSignature() {
//        return superPremiumOrSignature;
//    }
//
//    public void setSuperPremiumOrSignature(double superPremiumOrSignature) {
//        this.superPremiumOrSignature = superPremiumOrSignature;
//    }
//
//    public double getCommercialOrCorporate() {
//        return commercialOrCorporate;
//    }
//
//    public void setCommercialOrCorporate(double commercialOrCorporate) {
//        this.commercialOrCorporate = commercialOrCorporate;
//    }
//
//    public double getInternationalDebitCard() {
//        return internationalDebitCard;
//    }
//
//    public void setInternationalDebitCard(double internationalDebitCard) {
//        this.internationalDebitCard = internationalDebitCard;
//    }
//
//    public double getInternationalCreditCard() {
//        return internationalCreditCard;
//    }
//
//    public void setInternationalCreditCard(double internationalCreditCard) {
//        this.internationalCreditCard = internationalCreditCard;
//    }
//
//    public double getOnus() {
//        return onus;
//    }
//
//    public void setOnus(double onus) {
//        this.onus = onus;
//    }
//
//    public double getDiners() {
//        return diners;
//    }
//
//    public void setDiners(double diners) {
//        this.diners = diners;
//    }
//
//    public double getMasterPass() {
//        return masterPass;
//    }
//
//    public void setMasterPass(double masterPass) {
//        this.masterPass = masterPass;
//    }
//
//    public double getCashAtPos() {
//        return cashAtPos;
//    }
//
//    public void setCashAtPos(double cashAtPos) {
//        this.cashAtPos = cashAtPos;
//    }


    public JSONObject getDomesticDebitUpTo2000() {
        return domesticDebitUpTo2000;
    }

    public void setDomesticDebitUpTo2000(JSONObject domesticDebitUpTo2000) {
        this.domesticDebitUpTo2000 = domesticDebitUpTo2000;
    }

    public JSONObject getDomesticDebitAbove2000() {
        return domesticDebitAbove2000;
    }

    public void setDomesticDebitAbove2000(JSONObject domesticDebitAbove2000) {
        this.domesticDebitAbove2000 = domesticDebitAbove2000;
    }

    public JSONObject getStandardOrClassic() {
        return standardOrClassic;
    }

    public void setStandardOrClassic(JSONObject standardOrClassic) {
        this.standardOrClassic = standardOrClassic;
    }

    public JSONObject getPremiumOrPlatinum() {
        return premiumOrPlatinum;
    }

    public void setPremiumOrPlatinum(JSONObject premiumOrPlatinum) {
        this.premiumOrPlatinum = premiumOrPlatinum;
    }

    public JSONObject getSuperPremiumOrSignature() {
        return superPremiumOrSignature;
    }

    public void setSuperPremiumOrSignature(JSONObject superPremiumOrSignature) {
        this.superPremiumOrSignature = superPremiumOrSignature;
    }

    public JSONObject getCommercialOrCorporate() {
        return commercialOrCorporate;
    }

    public void setCommercialOrCorporate(JSONObject commercialOrCorporate) {
        this.commercialOrCorporate = commercialOrCorporate;
    }

    public JSONObject getInternationalDebitCard() {
        return internationalDebitCard;
    }

    public void setInternationalDebitCard(JSONObject internationalDebitCard) {
        this.internationalDebitCard = internationalDebitCard;
    }

    public JSONObject getInternationalCreditCard() {
        return internationalCreditCard;
    }

    public void setInternationalCreditCard(JSONObject internationalCreditCard) {
        this.internationalCreditCard = internationalCreditCard;
    }

    public JSONObject getOnus() {
        return onus;
    }

    public void setOnus(JSONObject onus) {
        this.onus = onus;
    }

    public JSONObject getDiners() {
        return diners;
    }

    public void setDiners(JSONObject diners) {
        this.diners = diners;
    }

    public JSONObject getMasterPass() {
        return masterPass;
    }

    public void setMasterPass(JSONObject masterPass) {
        this.masterPass = masterPass;
    }

    public JSONObject getCashAtPos() {
        return cashAtPos;
    }

    public void setCashAtPos(JSONObject cashAtPos) {
        this.cashAtPos = cashAtPos;
    }

    public String getLeadSource() {
        return leadSource;
    }

    public void setLeadSource(String leadSource) {
        this.leadSource = leadSource;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getMerchantCategoryId() {
        return merchantCategoryId;
    }

    public void setMerchantCategoryId(String merchantCategoryId) {
        this.merchantCategoryId = merchantCategoryId;
    }

    public double getSetupFees() {
        return setupFees;
    }

    public void setSetupFees(double setupFees) {
        this.setupFees = setupFees;
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

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getSetup_fees() {
        return setup_fees;
    }

    public void setSetup_fees(String setup_fees) {
        this.setup_fees = setup_fees;
    }

    public JSONObject getUpiCreditAbove2000() {
        return upiCreditAbove2000;
    }

    public void setUpiCreditAbove2000(JSONObject upiCreditAbove2000) {
        this.upiCreditAbove2000 = upiCreditAbove2000;
    }

    public JSONObject getUpiCreditUpto2000() {
        return upiCreditUpto2000;
    }

    public void setUpiCreditUpto2000(JSONObject upiCreditUpto2000) {
        this.upiCreditUpto2000 = upiCreditUpto2000;
    }

    public JSONObject getUpiAbove2000() {
        return upiAbove2000;
    }

    public void setUpiAbove2000(JSONObject upiAbove2000) {
        this.upiAbove2000 = upiAbove2000;
    }

    public JSONObject getUpiUpto2000() {
        return upiUpto2000;
    }

    public void setUpiUpto2000(JSONObject upiUpto2000) {
        this.upiUpto2000 = upiUpto2000;
    }

    public JSONObject getAmexDomestic() {
        return amexDomestic;
    }

    public void setAmexDomestic(JSONObject amexDomestic) {
        this.amexDomestic = amexDomestic;
    }

    public JSONObject getAmexInternational() {
        return amexInternational;
    }

    public void setAmexInternational(JSONObject amexInternational) {
        this.amexInternational = amexInternational;
    }
}
