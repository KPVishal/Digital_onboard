package com.bijlipay.repository;

import com.bijlipay.model.LeadInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadInformationRepo extends JpaRepository<LeadInformation,Long> {

    @Query("select l from LeadInformation l where l.leadId =?1")
    LeadInformation findLeadId(long leadId);
}
