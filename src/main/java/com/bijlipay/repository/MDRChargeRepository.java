package com.bijlipay.repository;

import com.bijlipay.model.Device;
import com.bijlipay.model.LeadSource;
import com.bijlipay.model.MDRCharge;
import com.bijlipay.model.MerchantCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MDRChargeRepository extends JpaRepository<MDRCharge, Long> {
    @Query("SELECT m FROM MDRCharge m WHERE m.leadSource=?1 AND m.device =?2 AND m.merchantCategory=?3 AND m.mdrPlanName=?4")
    List<MDRCharge> getMDRDetails(LeadSource leadSource, Device device, MerchantCategory merchantCategory, String mdrPlanName);
    @Query("SELECT m FROM MDRCharge m WHERE m.isActive = ?2 AND m.leadSource.isActive =1 AND (m.leadSource.leadSourceName LIKE %?1% " +
            "OR m.device.deviceName LIKE %?1% OR m.merchantCategory.merchantCategoryName LIKE %?1% OR m.merchantCategory.id LIKE %?1%)")
    Page<MDRCharge> getAllMDRChargesWithSearchKeyword(Optional<String> search, Optional<Boolean> isActive, Pageable pageable);
    @Query("SELECT m FROM MDRCharge m WHERE m.isActive = ?1 AND m.leadSource.isActive = 1")
    Page<MDRCharge> getAllMDRCharges(Optional<Boolean> isActive, Pageable pageable);
    @Query("SELECT m FROM MDRCharge m WHERE m.leadSource = ?1 AND m.device = ?2")
    List<MDRCharge> fetchMdrListBasedOnLeadSourceAndDevice(LeadSource leadSource, Device device);

    @Query("SELECT m FROM MDRCharge m WHERE m.leadSource =?1 AND m.device =?2 AND m.merchantCategory =?3")
    List<MDRCharge> fetchMdrListBasedOnLeadSourceDevice(LeadSource leadSource, Device device, MerchantCategory category);
//    @Query("SELECT COUNT(m) FROM MDRCharge m WHERE m.leadSource.id=?1 AND m.device.id =?2 AND m.merchantCategory.id =?3")
//    int getMdrChargeCount(int leadSource, long deviceId, long merchantCategory);

}
