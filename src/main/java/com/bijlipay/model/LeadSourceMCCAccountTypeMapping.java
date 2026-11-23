package com.bijlipay.model;

import javax.persistence.*;

@Entity
@Table(name = "lead_source_mcc_account_type_mapping")
public class LeadSourceMCCAccountTypeMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lead_source_mcc_account_type_id")
    private long leadSourceMCCAccountTypeId;

    @ManyToOne (optional = false)
    @JoinColumn(name = "lead_source_id", referencedColumnName = "lead_source_id", nullable = false)
    private LeadSource leadSource;

    @ManyToOne (optional = false)
    @JoinColumn(name = "mcc_id", referencedColumnName = "mcc_id", nullable = false)
    private MerchantCategoryCode merchantCategoryCode;

    @Column(name = "account_type")
    private String accountType;

    public long getLeadSourceMCCAccountTypeId() {
        return leadSourceMCCAccountTypeId;
    }

    public void setLeadSourceMCCAccountTypeId(long leadSourceMCCAccountTypeId) {
        this.leadSourceMCCAccountTypeId = leadSourceMCCAccountTypeId;
    }

    public LeadSource getLeadSource() {
        return leadSource;
    }

    public void setLeadSource(LeadSource leadSource) {
        this.leadSource = leadSource;
    }

    public MerchantCategoryCode getMerchantCategoryCode() {
        return merchantCategoryCode;
    }

    public void setMerchantCategoryCode(MerchantCategoryCode merchantCategoryCode) {
        this.merchantCategoryCode = merchantCategoryCode;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        return "LeadSourceMCCAccountTypeMapping{" +
                "leadSourceMCCAccountTypeId=" + leadSourceMCCAccountTypeId +
                ", leadSource=" + leadSource +
                ", merchantCategoryCode=" + merchantCategoryCode +
                ", accountType='" + accountType + '\'' +
                '}';
    }
}
