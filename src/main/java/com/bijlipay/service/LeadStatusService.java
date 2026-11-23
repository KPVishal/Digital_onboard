package com.bijlipay.service;

import com.bijlipay.model.Device;
import com.bijlipay.model.LeadStatus;

import java.util.List;

public interface LeadStatusService {

    LeadStatus isLeadStatusExists(LeadStatus leadStatus);

    LeadStatus createLeadStatus(LeadStatus leadStatus);

    List<LeadStatus> listAllLeadStatus();

    List<LeadStatus> listActiveLeadStatus(boolean is_active);

    LeadStatus getLeadStatus(long id);

}
