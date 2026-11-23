package com.bijlipay.service;

import com.bijlipay.model.*;
import com.bijlipay.model.marsDto.MarsMdrChargesDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Pair;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

public interface MDRChargeService {
    int existsMDRDetails(LeadSource leadSource, Device device, MerchantCategory merchantCategory, String mdrPlanName);
    Pair<Boolean, MDRCharge> saveMDRDetails(MDRCharge mdrCharge, LeadSource leadSource, Device device, MerchantCategory merchantCategory);
    Pair<Boolean, MDRCharge> updateMDRDetails(MDRCharge mdrCharge, Long mdrChargeId);
    Page<MDRCharge> getAllMDRCharges(Optional<String> search, Optional<Boolean> isActive, Pageable pageable);
    void deActivateMDRChargeDetails(MDRCharge charge);
    MDRCharge findByMDRChargeId(Long id);
    List<MDRCharge> fetchMdrChargeListBasedOnLeadSourceAndDevice(LeadSource leadSource, Device device);
//    int countByLeadSourceDeviceMerchantCategory(int leadSource,long device_id, long merchantCategory);
    void save(List<MarsMdrCharges> mdrChargeList);
    int countByName(String name);
    int countByRentalName(String name);
    List<MarsMdrCharges> findByLeadSourceAndDevice(LeadSource leadSource1, Device device1, MerchantCategoryCode mcc);
    List<MarsMdrCharges> findByLeadSourceAndDevice1(LeadSource leadSource1, Device device1, MerchantCategoryCode mcc,int type);
  Page<MarsMdrChargesDto> fetchAllMdrList(Optional<String> searchTerm, Pageable pageable);

  List<MDRCharge> fetchMdrChargeListBasedOnLeadSourceDeviceMcc(LeadSource leadSource, Device device, MerchantCategoryCode mcc);
}
