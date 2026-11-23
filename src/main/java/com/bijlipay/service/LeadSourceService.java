package com.bijlipay.service;

import com.bijlipay.model.LeadSource;

import java.util.List;

public interface LeadSourceService {

    LeadSource createLeadSource(LeadSource leadSource);

    LeadSource updateLeadSource(LeadSource leadSource);

    List<LeadSource> getAllLeadSource();

    LeadSource deActivate(int sourceid, LeadSource leadSource);


    LeadSource getLeadSource(int leadSource);
}
