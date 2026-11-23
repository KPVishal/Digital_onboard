package com.bijlipay.service;

import com.bijlipay.model.MerchantCategoryCode;
import java.util.List;
import java.util.Optional;

public interface MerchantCategoryCodeService {

    MerchantCategoryCode createMerchantCategoryCode(MerchantCategoryCode merchantCategoryCode);

    List<MerchantCategoryCode> getAllMccList();

    List<MerchantCategoryCode> getRestrictedMccList(int action, Optional<String> search);

    MerchantCategoryCode findMccById(Long id);

    void deleteMccById(Long id);

    boolean existsMcc(String mccCode);

    MerchantCategoryCode updateMerchantCategoryCode(MerchantCategoryCode merchantCategoryCode);


    List<MerchantCategoryCode> getAllMccListSearch(String search);
}
