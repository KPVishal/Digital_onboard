package com.bijlipay.repository;

import com.bijlipay.model.LeadSubStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeadSubStatusRepo extends JpaRepository<LeadSubStatus,Integer> {

    @Query("Select lss from LeadSubStatus lss")
    List<LeadSubStatus> getAll();
}
