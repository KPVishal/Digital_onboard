package com.bijlipay.service.implement;

import com.bijlipay.model.LeadStatus;
import com.bijlipay.repository.LeadStatusRepository;
import com.bijlipay.service.LeadStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeadStatusServiceImpl implements LeadStatusService {

    @Autowired
    private LeadStatusRepository leadStatusRepository;

    @Override
    public LeadStatus isLeadStatusExists(LeadStatus leadStatus){
        return leadStatusRepository.fetchByLeadStatusName(leadStatus.getLeadStatusName());
    }

    @Override
    public LeadStatus createLeadStatus(LeadStatus leadStatus) {
        leadStatus.setActive(true);
        return leadStatusRepository.save(leadStatus);
    }

    @Override
    public List<LeadStatus> listAllLeadStatus() {
        return leadStatusRepository.findAll();
    }

    @Override
    public List<LeadStatus> listActiveLeadStatus(boolean is_active) {
        return leadStatusRepository.fetchActiveLeadStatus(is_active);
    }

    @Override
    public LeadStatus getLeadStatus(long id) {
        return leadStatusRepository.fetchLeadStatusById(id);
    }

}
