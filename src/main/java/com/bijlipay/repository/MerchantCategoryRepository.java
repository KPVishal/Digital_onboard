package com.bijlipay.repository;

import com.bijlipay.model.MerchantCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantCategoryRepository extends JpaRepository<MerchantCategory,Long> {

    @Query("SELECT COUNT(m) FROM MerchantCategory m WHERE m.merchantCategoryName = ?1")
    int existsMerchantCategory(String merchantCategoryName);

    @Query("SELECT m FROM MerchantCategory m WHERE m.isActive=:isActive")
    List<MerchantCategory> getMerchantCategory(@Param("isActive") Boolean isActive);

    @Query("SELECT m FROM MerchantCategory m WHERE m.id =?1")
    MerchantCategory fetchMerchantCategoryById(long merchantCategoryId);
}
