package com.bijlipay.service;

import com.bijlipay.model.DocumentCancelReason;
import com.bijlipay.model.LeadCancellationReason;

import java.util.List;
import java.util.Optional;

public interface LeadCancellationReasonService {
    LeadCancellationReason existsReason(LeadCancellationReason leadCancellationRemarks);
    LeadCancellationReason createLeadCancellationReason(LeadCancellationReason leadCancellationRemarks);
    List<LeadCancellationReason> getAllList(boolean isActive);
    LeadCancellationReason findCancelReasonById(Long id);
    void deActivateLeadCancelReason(LeadCancellationReason cancellationRemarks);
    DocumentCancelReason checkReason(DocumentCancelReason documentCancelReason);
    DocumentCancelReason createCancellationReason(DocumentCancelReason documentCancelReason);
    List<DocumentCancelReason> getCancelReason(boolean isActive);
}
