package com.bijlipay.service.implement;

//import com.bijlipay.model.DocumentCategoryMapping;
import com.bijlipay.model.LeadSource;
import com.bijlipay.model.LeadSourceMCCAccountTypeMapping;
import com.bijlipay.model.LeadStatus;
import com.bijlipay.repository.LeadSourceMCCAccountTypeRepository;
import com.bijlipay.service.LeadSourceMCCAccountTypeMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeadSourceMCCAccountTypeServiceImpl implements LeadSourceMCCAccountTypeMappingService {


    @Autowired
    private LeadSourceMCCAccountTypeRepository leadSourceMCCAccountTypeRepository;
    @Override
    public LeadSourceMCCAccountTypeMapping isLeadSourceMCCAccountTypeMappingExists(LeadSourceMCCAccountTypeMapping leadSourceMCCAccountTypeMapping) {
        LeadSourceMCCAccountTypeMapping leadSourceMCCAccountTypeMappingDetail = null;
        if(leadSourceMCCAccountTypeMapping.getLeadSource() != null && leadSourceMCCAccountTypeMapping.getMerchantCategoryCode() != null) {
            leadSourceMCCAccountTypeMappingDetail = leadSourceMCCAccountTypeRepository.fetchLeadSourceMCCAccountTypeMapping(leadSourceMCCAccountTypeMapping.getLeadSource(), leadSourceMCCAccountTypeMapping.getMerchantCategoryCode());
        }
        return leadSourceMCCAccountTypeMappingDetail;
    }

    @Override
    public LeadSourceMCCAccountTypeMapping createLeadSourceMCCAccountTypeMapping(LeadSourceMCCAccountTypeMapping leadSourceMCCAccountTypeMapping) {
        LeadSourceMCCAccountTypeMapping leadSourceMCCAccountTypeMappingDetail = leadSourceMCCAccountTypeRepository.save(leadSourceMCCAccountTypeMapping);
        if(leadSourceMCCAccountTypeMappingDetail !=null){
            return leadSourceMCCAccountTypeMappingDetail;
        }
        return null;
    }

    @Override
    public List<LeadSourceMCCAccountTypeMapping> listAllLeadSourceMCCAccountTypeMappings() {
        return leadSourceMCCAccountTypeRepository.findAll();
    }

    @Override
    public List<LeadSourceMCCAccountTypeMapping> getLeadSourceMCCAccountTypeMapping(Optional<Long> id) {
        return leadSourceMCCAccountTypeRepository.fetchSingleLeadSourceMCCAccountTypeMapping(id);
    }

    @Override
    public LeadSourceMCCAccountTypeMapping getLeadSourceMCCAccountTypeMapping(Optional<Integer> leadSourceId, Optional<Long> mccId) {
        return leadSourceMCCAccountTypeRepository.getLeadSourceMCCAccountTypeMapping(leadSourceId,mccId);
    }
}
