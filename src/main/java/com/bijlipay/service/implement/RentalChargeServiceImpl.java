package com.bijlipay.service.implement;

import com.bijlipay.model.*;
import com.bijlipay.repository.*;
import com.bijlipay.service.RentalChargeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentalChargeServiceImpl implements RentalChargeService {
    private static final Logger logger = LoggerFactory.getLogger(RentalChargeServiceImpl.class);
    @Autowired
    private RentalChargeRepository rentalChargeRepository;
    @Autowired
    private LeadSourceRepo leadSourceRepo;
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private MarsRentalPlansRepository marsRentalPlansRepository;

    @Override
    public int existsRentalDetails(LeadSource leadSource, Device device, Plan plan) {
        List<RentalCharge> existsRental = rentalChargeRepository.existsRentalCharge(leadSource, device, plan);
        return existsRental.size();
    }

    @Override
    public Pair<Boolean, RentalCharge> createRentalCharge(RentalCharge rentalCharge, LeadSource leadSource, Device device, Plan plan) {

        rentalCharge.setActive(true);
        rentalCharge.setLeadSource(leadSourceRepo.getReferenceById(leadSource.getId()));
        rentalCharge.setDevice(deviceRepository.getReferenceById(device.getId()));
        rentalCharge.setPlan(planRepository.getReferenceById(plan.getId()));

        RentalCharge result = rentalChargeRepository.save(rentalCharge);
        if (result == null) {
            return Pair.of(false, null);
        }
        return Pair.of(true, result);
    }

    @Override
    public Pair<Boolean, RentalCharge> updateRentalCharge(Long id, RentalCharge rentalCharge) {
        RentalCharge fromDB = rentalChargeRepository.getReferenceById(id);
        if (fromDB != null) {
            fromDB.setActive(rentalCharge.isActive());
            fromDB.setDevice(rentalCharge.getDevice());
            fromDB.setLeadSource(rentalCharge.getLeadSource());
            fromDB.setPlan(rentalCharge.getPlan());
            fromDB.setMonthlyFees(rentalCharge.getMonthlyFees());
            fromDB.setSetupFees(rentalCharge.getSetupFees());

            RentalCharge result = rentalChargeRepository.save(fromDB);

            if (result == null) {
                return Pair.of(false, null);
            }
            return Pair.of(true, result);
        }
        return Pair.of(false, null);
    }

    @Override
    public Page<RentalCharge> getAllRentalChargeList(Optional<String> search, Boolean isActive, Pageable pageable) {
        if(search.isPresent()){
            return rentalChargeRepository.getAllRentalChargeWithSearchTerm(search.get(),isActive,pageable);
        }
        else{
            return rentalChargeRepository.getAllRentalChargeList(isActive,pageable);
        }
    }

    @Override
    public RentalCharge findByRentalChargeId(Long id) {
        return rentalChargeRepository.getReferenceById(id);
    }

    @Override
    public void deActivateRentalChargeDetails(RentalCharge charge) {
        charge.setActive(false);
        rentalChargeRepository.save(charge);
    }

    @Override
    public List<Device> getDeviceList(int sourceId) {
        return rentalChargeRepository.getDeviceList(sourceId);
    }

    @Override
    public List<Plan> getPlanList(int sourceId) {
        return rentalChargeRepository.getPlanList(sourceId);
    }
    @Override
    public List<RentalCharge> fetchRentalChargeListBasedOnLeadSourceAndDevice(LeadSource leadSource, Device device) {
        List<RentalCharge> rentalChargeList = rentalChargeRepository.getRentalChargeListBasedOnLeadSourceAndDevice(leadSource,device);
        return rentalChargeList;
    }

//    @Override
//    public int countRentalPlan(int leadSource, long deviceId, long plan) {
//        return rentalChargeRepository.fetchCount(leadSource,deviceId,plan);
//    }

    @Override
    public void save(List<RentalCharge> rentalPlans) {
        rentalChargeRepository.saveAll(rentalPlans);
    }

//    @Override
//    public int countByName(String name) {
//        return  rentalChargeRepository.findCount(name);
//    }


    @Override
    public void saveMarsRentalPlans(List<MarsRentalPlans> rentalPlans) {
        marsRentalPlansRepository.saveAll(rentalPlans);
    }

    @Override
    public Page<MarsRentalPlans> fetchAllRentalList(Optional<String> searchTerm, Pageable pageable) {
        Page<MarsRentalPlans> rentalPlans = null;
        if (searchTerm.isPresent()){
            rentalPlans = marsRentalPlansRepository.fetchAllRentalListWithSearchTerm(searchTerm.get(),pageable);
        }else {
            rentalPlans = marsRentalPlansRepository.fetchAllRentalListWithoutSearchTerm(pageable);
        }
        return rentalPlans;
    }

    @Override
    public List<Plan> getPlanDetails(int sourceId) {
        return marsRentalPlansRepository.getPlanList(sourceId);
    }

    @Override
    public List<Device> getDeviceDetails(int sourceId) {
        return marsRentalPlansRepository.getDeviceList(sourceId);
    }

    @Override
    public List<MarsRentalPlans> findByLeadSourceAndDevice(LeadSource leadSource1, Device device1) {
        return marsRentalPlansRepository.getRentalList(leadSource1,device1);
    }
}
