package com.bijlipay.model.marsDto;

import com.bijlipay.model.Device;
import com.bijlipay.model.LeadSource;
import com.bijlipay.model.MerchantCategory;
import org.json.simple.JSONObject;

public class MarsMdrChargesDto {
    private long id;
    private String name;
    private String code;
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
    private JSONObject cashAtPos;
    private JSONObject masterPass;

    private LeadSource leadSource;
    private Device device;
    private MerchantCategory merchantCategory;
    private boolean isActive;

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

    public JSONObject getCashAtPos() {
        return cashAtPos;
    }

    public void setCashAtPos(JSONObject cashAtPos) {
        this.cashAtPos = cashAtPos;
    }

    public JSONObject getMasterPass() {
        return masterPass;
    }

    public void setMasterPass(JSONObject masterPass) {
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
}
