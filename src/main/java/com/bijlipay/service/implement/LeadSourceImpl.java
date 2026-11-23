package com.bijlipay.service.implement;

import com.bijlipay.model.LeadSource;
import com.bijlipay.repository.LeadSourceRepo;
import com.bijlipay.service.LeadSourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeadSourceImpl implements LeadSourceService {

    @Autowired
    private LeadSourceRepo leadSourceRepo;

    private static final Logger logger = LoggerFactory.getLogger(LeadSourceImpl.class);


    @Override
    public LeadSource createLeadSource(LeadSource leadSource) {
        leadSource.setLeadSourceName(leadSource.getLeadSourceName());
        leadSource.setActive(true);
        leadSource.setSoleEmpToBeCollected(true);
        return leadSourceRepo.save(leadSource);
    }

    @Override
    public LeadSource updateLeadSource(LeadSource leadSource) {
        LeadSource source = leadSourceRepo.getReferenceById(leadSource.getId());
        if (source != null) {
            source.setLeadSourceName(leadSource.getLeadSourceName());
            return leadSourceRepo.save(source);
        }
        return null;
    }

    @Override
    public List<LeadSource> getAllLeadSource() {
        return leadSourceRepo.getLeadSource();
    }

    @Override
    public LeadSource deActivate(int sourceid, LeadSource leadSource) {
        LeadSource leadsourcetobedeleted = leadSourceRepo.findLeadSourceById(sourceid);
        if (leadsourcetobedeleted != null) {
            leadsourcetobedeleted.setActive(false);
            return leadSourceRepo.save(leadsourcetobedeleted);
        }
        return null;
    }

    @Override
    public LeadSource getLeadSource(int leadSource) {
        return leadSourceRepo.fetchLeadSource(leadSource);
    }
}
