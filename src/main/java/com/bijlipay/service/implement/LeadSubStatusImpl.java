package com.bijlipay.service.implement;

import com.bijlipay.model.LeadStatus;
import com.bijlipay.model.LeadSubStatus;
import com.bijlipay.repository.LeadStatusRepository;
import com.bijlipay.repository.LeadSubStatusRepo;
import com.bijlipay.service.LeadSubStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeadSubStatusImpl implements LeadSubStatusService {

    @Autowired
    private LeadSubStatusRepo subStatusRepo;

    @Autowired
    private LeadStatusRepository leadStatusRepository;

    @Override
    public LeadSubStatus saveLeadSubStatus(LeadSubStatus leadSubStatus) {
        LeadStatus leadStatus=leadStatusRepository.fetchLeadStatusById(3);
        leadSubStatus.setLeadStatus(leadStatus);
        return subStatusRepo.save(leadSubStatus);
    }

    @Override
    public List<LeadSubStatus> getAllSubStatus() {
        return subStatusRepo.getAll();
    }
}
