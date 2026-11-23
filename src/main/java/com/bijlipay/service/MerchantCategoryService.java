package com.bijlipay.service;

import com.bijlipay.model.MerchantCategory;
import com.bijlipay.model.dto.MerchantCategoryAndCodeDto;
import org.springframework.data.util.Pair;

import java.util.List;

public interface MerchantCategoryService {
    boolean existsMerchantCategory(MerchantCategoryAndCodeDto merchantCategoryAndCodeDto);
    MerchantCategory createMerchantCategory(MerchantCategoryAndCodeDto merchantCategory);
    List<MerchantCategory> getAllMerchantCategory(Boolean isActive);
    Pair<Boolean, MerchantCategory> update(MerchantCategoryAndCodeDto merchantCategoryAndCodeDto, Long id, Boolean isActive);
    String deActivate(Long id);

    MerchantCategory fetchMerchantCategory(long merchantCategoryId);
}
