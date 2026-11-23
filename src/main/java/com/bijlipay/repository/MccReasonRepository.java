package com.bijlipay.repository;

import com.bijlipay.model.MccReason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MccReasonRepository extends JpaRepository<MccReason,Long> {

    @Query("select m from MccReason m where m.reason =?1")
    MccReason findByReason(String reason);

    @Query("select m from MccReason m where m.isActive =1")
    List<MccReason> getAllMccReasonList();

    @Query("select m from MccReason m where m.id =?1")
    MccReason findByMccId(Long id);
}
