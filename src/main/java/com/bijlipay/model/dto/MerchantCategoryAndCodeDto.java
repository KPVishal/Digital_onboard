package com.bijlipay.model.dto;

import com.bijlipay.model.MerchantCategory;
import com.bijlipay.model.MerchantCategoryCode;

import java.util.List;

public class MerchantCategoryAndCodeDto {

    private MerchantCategory merchantCategory;
    private List<MerchantCategoryCode> merchantCategoryCodes;

    public MerchantCategory getMerchantCategory() {
        return merchantCategory;
    }

    public void setMerchantCategory(MerchantCategory merchantCategory) {
        this.merchantCategory = merchantCategory;
    }

    public List<MerchantCategoryCode> getMerchantCategoryCodes() {
        return merchantCategoryCodes;
    }

    public void setMerchantCategoryCodes(List<MerchantCategoryCode> merchantCategoryCodes) {
        this.merchantCategoryCodes = merchantCategoryCodes;
    }
}
