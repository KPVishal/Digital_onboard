package com.bijlipay.service;

import com.bijlipay.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Pair;

import java.util.List;
import java.util.Optional;

public interface RentalChargeService {
    int existsRentalDetails(LeadSource leadSource, Device device, Plan plan);
    Pair<Boolean, RentalCharge> createRentalCharge(RentalCharge rentalCharge, LeadSource leadSource, Device device, Plan plan);
    Pair<Boolean, RentalCharge> updateRentalCharge(Long id, RentalCharge rentalCharge);
    Page<RentalCharge> getAllRentalChargeList(Optional<String> search, Boolean isActive, Pageable pageable);
    RentalCharge findByRentalChargeId(Long id);
    void deActivateRentalChargeDetails(RentalCharge charge);

    List<Device> getDeviceList(int sourceId);

    List<Plan> getPlanList(int sourceId);
    List<RentalCharge> fetchRentalChargeListBasedOnLeadSourceAndDevice(LeadSource leadSource, Device device);

//    int countRentalPlan(int leadSource, long deviceId, long plan);

    void save(List<RentalCharge> rentalPlans);

    void saveMarsRentalPlans(List<MarsRentalPlans> rentalPlans);

    Page<MarsRentalPlans> fetchAllRentalList(Optional<String> searchTerm, Pageable pageable);

    List<Plan> getPlanDetails(int sourceId);

    List<Device> getDeviceDetails(int sourceId);

    List<MarsRentalPlans> findByLeadSourceAndDevice(LeadSource leadSource1, Device device1);

//    int countByName(String name);
}
