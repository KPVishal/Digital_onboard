package com.bijlipay.repository;

import com.bijlipay.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MarsMdrChargesRepository extends JpaRepository<MarsMdrCharges,Long> {
    @Query("SELECT COUNT(m) FROM MarsMdrCharges m WHERE m.name =?1")
    int findCountByName(String name);

    @Query("SELECT m FROM MarsMdrCharges m WHERE  m.leadSource =?1 AND m.device =?2 AND m.merchantCategory =?3")
    List<MarsMdrCharges> findByLeadSourceAndDevice(LeadSource leadSource1, Device device1, MerchantCategory mcc);
    @Query("SELECT m FROM MarsMdrCharges m WHERE  m.leadSource =?1 AND m.device =?2 AND m.merchantCategory =?3 AND m.type=?4")
    List<MarsMdrCharges> findByLeadSourceAndDevice1(LeadSource leadSource1, Device device1, MerchantCategory mcc, String type);

    @Query("SELECT m FROM MarsMdrCharges m WHERE  m.code LIKE ?1% OR m.name LIKE ?1%")
    Page<MarsMdrCharges> fetchMdrListWithSearchTerm(String searchTerm, Pageable pageable);
    @Query("SELECT m FROM MarsMdrCharges m")
    Page<MarsMdrCharges> fetchMdrListWithoutSearchTerm(Pageable pageable);
}
