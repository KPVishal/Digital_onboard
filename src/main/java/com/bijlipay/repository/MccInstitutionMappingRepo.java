package com.bijlipay.repository;

import com.bijlipay.model.MccInstitutionMapping;
import com.bijlipay.model.OnboardInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MccInstitutionMappingRepo extends JpaRepository<MccInstitutionMapping,Long> {

    @Query("select m.otherMerchant from MccInstitutionMapping m where m.mcc =?1 and m.otherMerchant is not null")
    OnboardInstance findInstance(String mcc);

    @Query("select m.smallMerchant from MccInstitutionMapping m where m.mcc =?1 and m.smallMerchant is not null")
    OnboardInstance findInstanceSmall(String mcc);
}
