package com.bijlipay.service.implement;

import com.bijlipay.model.DocumentCancelReason;
import com.bijlipay.model.LeadCancellationReason;
import com.bijlipay.repository.DocumentCancelReasonRepository;
import com.bijlipay.repository.LeadCancellationReasonRepository;
import com.bijlipay.service.LeadCancellationReasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeadCancellationReasonServiceImpl implements LeadCancellationReasonService {
    @Autowired
    private LeadCancellationReasonRepository leadCancellationReasonRepository;
    @Autowired
    private DocumentCancelReasonRepository documentCancelReasonRepository;
    @Override
    public LeadCancellationReason existsReason(LeadCancellationReason leadCancellationReason) {
        return leadCancellationReasonRepository.findByReason(leadCancellationReason.getReason());
    }

    @Override
    public LeadCancellationReason createLeadCancellationReason(LeadCancellationReason leadCancellationReason) {
        LeadCancellationReason reason=new LeadCancellationReason();
        if(leadCancellationReason!=null){
            reason.setReason(leadCancellationReason.getReason());
            reason.setActive(true);
        }
        return leadCancellationReasonRepository.save(reason);
    }

    @Override
    public List<LeadCancellationReason> getAllList(boolean isActive) {
        return leadCancellationReasonRepository.getAllCancellationReasonList(isActive);
    }

    @Override
    public LeadCancellationReason findCancelReasonById(Long id) {
        return leadCancellationReasonRepository.getReferenceById(id);
    }

    @Override
    public void deActivateLeadCancelReason(LeadCancellationReason cancellationReason) {
        cancellationReason.setActive(false);
        leadCancellationReasonRepository.save(cancellationReason);
    }

    @Override
    public DocumentCancelReason checkReason(DocumentCancelReason documentCancelReason) {
        return documentCancelReasonRepository.findReason(documentCancelReason.getReason());
    }

    @Override
    public DocumentCancelReason createCancellationReason(DocumentCancelReason documentCancelReason) {
        documentCancelReason.setActive(true);
        return documentCancelReasonRepository.save(documentCancelReason);
    }

    @Override
    public List<DocumentCancelReason> getCancelReason(boolean isActive) {
        return documentCancelReasonRepository.findCancelReason(isActive);
    }
}
