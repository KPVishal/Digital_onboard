package com.bijlipay.repository;

import com.bijlipay.model.DocumentCancelReason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentCancelReasonRepository extends JpaRepository<DocumentCancelReason, Long> {

    @Query("select d from DocumentCancelReason d where d.reason =?1")
    DocumentCancelReason findReason(String reason);

    @Query("select d from DocumentCancelReason d where d.isActive =?1")
    List<DocumentCancelReason> findCancelReason(boolean isActive);
}
