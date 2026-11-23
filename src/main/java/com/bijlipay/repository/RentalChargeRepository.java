package com.bijlipay.repository;

import com.bijlipay.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@Repository
public interface RentalChargeRepository extends JpaRepository<RentalCharge,Long> {
    @Query("SELECT rc FROM RentalCharge rc WHERE rc.leadSource = ?1 AND rc.device =?2 AND rc.plan = ?3")
    List<RentalCharge> existsRentalCharge(LeadSource leadSource, Device device, Plan plan);
    @Query("SELECT rc FROM RentalCharge rc WHERE rc.isActive =?2 AND rc.leadSource.isActive = 1 AND (rc.leadSource.leadSourceName LIKE %?1%" +
            "OR rc.device.deviceName LIKE %?1% OR rc.plan.planName LIKE %?1%)")
    Page<RentalCharge> getAllRentalChargeWithSearchTerm(String s, Boolean isActive, Pageable pageable);
    @Query("SELECT rc FROM RentalCharge rc WHERE rc.isActive = ?1 AND rc.leadSource.isActive = 1")
    Page<RentalCharge> getAllRentalChargeList(Boolean isActive, Pageable pageable);


    @Query("Select r.device from  RentalCharge r where r.leadSource.id=?1 and r.isActive=1 group by r.device" )
    List<Device> getDeviceList(int sourceId);

    @Query("Select r.plan from RentalCharge r where r.leadSource.id=?1 and r.isActive=1 group by r.plan")
    List<Plan> getPlanList(int sourceId);
    @Query("SELECT rc FROM RentalCharge rc WHERE rc.leadSource = ?1 AND rc.device =?2")
    List<RentalCharge> getRentalChargeListBasedOnLeadSourceAndDevice(LeadSource leadSource, Device device);


//    @Query("SELECT COUNT(rc) FROM RentalCharge rc WHERE rc.leadSource.id =?1 AND rc.device.id =?2 AND rc.plan.id =?1")
//    int fetchCount(int leadSource, long deviceId, long plan);
//
//    @Query("SELECT COUNT(rc) FROM RentalCharge rc WHERE rc.rentalPlanName =?1")
//    int findCount(String name);
}
