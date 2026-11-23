package com.bijlipay.service;

import com.bijlipay.model.LeadSourceMCCAccountTypeMapping;

import java.util.List;
import java.util.Optional;

public interface LeadSourceMCCAccountTypeMappingService {

    LeadSourceMCCAccountTypeMapping isLeadSourceMCCAccountTypeMappingExists(LeadSourceMCCAccountTypeMapping createSourceMCCAccountTypeMapping);

    LeadSourceMCCAccountTypeMapping createLeadSourceMCCAccountTypeMapping(LeadSourceMCCAccountTypeMapping leadSourceMCCAccountTypeMapping);

    List<LeadSourceMCCAccountTypeMapping> listAllLeadSourceMCCAccountTypeMappings();

    List<LeadSourceMCCAccountTypeMapping> getLeadSourceMCCAccountTypeMapping(Optional<Long> id);

    LeadSourceMCCAccountTypeMapping getLeadSourceMCCAccountTypeMapping(Optional<Integer> leadSourceId, Optional<Long> mccId);

}
