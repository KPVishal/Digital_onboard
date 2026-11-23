package com.bijlipay.repository;

import com.bijlipay.model.LeadStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeadStatusRepository extends JpaRepository<LeadStatus,Long> {

    @Query("select l from LeadStatus l where l.leadStatusName =?1")
    LeadStatus fetchByLeadStatusName(String leadStatusName);

    @Query("select l from LeadStatus l where l.isActive =?1")
    List<LeadStatus> fetchActiveLeadStatus(boolean active_flag);

    @Query("select l from LeadStatus l where l.leadStatusId=?1")
    LeadStatus fetchLeadStatusById(long id);

}
