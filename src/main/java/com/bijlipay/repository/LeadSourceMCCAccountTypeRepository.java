package com.bijlipay.repository;

import com.bijlipay.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeadSourceMCCAccountTypeRepository extends JpaRepository<LeadSourceMCCAccountTypeMapping, Long> {

    @Query("select l from LeadSourceMCCAccountTypeMapping l where l.leadSource=?1 and l.merchantCategoryCode=?2")
    LeadSourceMCCAccountTypeMapping fetchLeadSourceMCCAccountTypeMapping(LeadSource leadSource, MerchantCategoryCode merchantCategoryCode);

    @Query("select l from LeadSourceMCCAccountTypeMapping l where l.leadSourceMCCAccountTypeId=?1")
    List<LeadSourceMCCAccountTypeMapping> fetchSingleLeadSourceMCCAccountTypeMapping(Optional<Long> id);

    @Query("select l from LeadSourceMCCAccountTypeMapping l where l.leadSource.id=?1 and l.merchantCategoryCode.mccId=?2")
    LeadSourceMCCAccountTypeMapping getLeadSourceMCCAccountTypeMapping(Optional<Integer> leadSourceId,Optional<Long> mccId);
}
