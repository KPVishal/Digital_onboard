package com.bijlipay.repository;

import com.bijlipay.model.LeadCancellationReason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeadCancellationReasonRepository extends JpaRepository<LeadCancellationReason,Long> {
    @Query("SELECT lc FROM LeadCancellationReason lc WHERE lc.reason LIKE ?1")
    LeadCancellationReason findByReason(String reason);
//    @Query("SELECT lc FROM LeadCancellationReason lc WHERE lc.isActive = ?1")
//    LeadCancellationReason getAllCancellationReasonList(boolean isActive);

    @Query("SELECT lc FROM LeadCancellationReason lc WHERE lc.isActive =?1")
    List<LeadCancellationReason> getAllCancellationReasonList(boolean isActive);
}
