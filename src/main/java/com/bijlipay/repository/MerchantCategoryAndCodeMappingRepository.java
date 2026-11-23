package com.bijlipay.repository;

import com.bijlipay.model.MerchantCategory;
import com.bijlipay.model.MerchantCategoryAndCodeMapping;
import com.bijlipay.model.MerchantCategoryCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantCategoryAndCodeMappingRepository extends JpaRepository<MerchantCategoryAndCodeMapping, Long> {

    @Query("delete from MerchantCategoryAndCodeMapping m where m.merchantCategory =?1")
    void deleteMappingByCategory(MerchantCategory category);

    @Query("SELECT m.merchantCategory FROM MerchantCategoryAndCodeMapping m WHERE m.merchantCategoryCode =? 1")
    MerchantCategory findMerchantCategoryByMcc(MerchantCategoryCode mcc);
}
