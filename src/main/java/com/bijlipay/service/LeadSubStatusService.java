package com.bijlipay.service;

import com.bijlipay.model.LeadSubStatus;

import java.util.List;

public interface LeadSubStatusService {

    LeadSubStatus saveLeadSubStatus(LeadSubStatus leadSubStatus);

    List<LeadSubStatus> getAllSubStatus();
}
