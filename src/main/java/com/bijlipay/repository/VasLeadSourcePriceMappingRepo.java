package com.bijlipay.repository;

import com.bijlipay.model.VasLeadSourcePriceMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VasLeadSourcePriceMappingRepo extends JpaRepository<VasLeadSourcePriceMapping,Integer> {

    @Query(value="Select v from VasLeadSourcePriceMapping v where v.VasLeadSourcePriceMappingId=?1")
    VasLeadSourcePriceMapping findVasLeadSourceById(int vasLeadSourcePriceMappingId);
}
