package com.bijlipay.service.implement;

import com.bijlipay.config.Constants;
import com.bijlipay.model.*;
import com.bijlipay.model.marsDto.MarsMdrChargesDto;
import com.bijlipay.repository.*;
import com.bijlipay.service.MDRChargeService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Pair;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MDRChargeServiceImpl implements MDRChargeService {
    @Autowired
    private MDRChargeRepository mdrChargeRepository;
    @Autowired
    private LeadSourceRepo leadSourceRepo;
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private MerchantCategoryRepository merchantCategoryRepository;
    @Autowired
    private MarsMdrChargesRepository marsMdrChargesRepository;
    @Autowired
    private MarsRentalPlansRepository marsRentalPlansRepository;


    @Autowired
    private MerchantCategoryAndCodeMappingRepository merchantCategoryAndCodeMappingRepository;

    @Override
    public int existsMDRDetails(LeadSource leadSource, Device device, MerchantCategory merchantCategory, String mdrPlanName) {
        List<MDRCharge> existsMDRDetails = mdrChargeRepository.getMDRDetails(leadSource, device, merchantCategory, mdrPlanName);
        return existsMDRDetails.size();
    }

    @Override
    public Pair<Boolean, MDRCharge> saveMDRDetails(MDRCharge mdrCharge, LeadSource leadSource, Device device, MerchantCategory merchantCategory) {
        mdrCharge.setActive(true);
        mdrCharge.setLeadSource(leadSourceRepo.getReferenceById(leadSource.getId()));
        mdrCharge.setDevice(deviceRepository.getReferenceById(device.getId()));
        mdrCharge.setMerchantCategory(merchantCategoryRepository.getReferenceById(merchantCategory.getId()));

        MDRCharge result = mdrChargeRepository.save(mdrCharge);
        if (result == null) {

            return Pair.of(false, null);
        }
        return Pair.of(true, result);
    }

    @Override
    public Pair<Boolean, MDRCharge> updateMDRDetails(MDRCharge mdrCharge, Long mdrChargeId) {
        MDRCharge result = new MDRCharge();
        MDRCharge fromDB = mdrChargeRepository.getReferenceById(mdrChargeId);
        if (fromDB != null) {
            fromDB.setDebitLessThanAmount(mdrCharge.getDebitLessThanAmount());
            fromDB.setDebitGreaterThanAmount(mdrCharge.getDebitGreaterThanAmount());
            fromDB.setStdCC(mdrCharge.getStdCC());
            fromDB.setCorpCC(mdrCharge.getCorpCC());
            fromDB.setIntlCC(mdrCharge.getIntlCC());
            fromDB.setSuperPremiumCC(mdrCharge.getSuperPremiumCC());
            fromDB.setDevice(mdrCharge.getDevice());
            fromDB.setLeadSource(mdrCharge.getLeadSource());
            fromDB.setActive(mdrCharge.isActive());
            fromDB.setMdrPlanName(mdrCharge.getMdrPlanName());
            fromDB.setPremiumCC(mdrCharge.getPremiumCC());

            result = mdrChargeRepository.save(fromDB);

            return Pair.of(true, result);
        }
        return Pair.of(false, null);
    }

    @Override
    public Page<MDRCharge> getAllMDRCharges(Optional<String> search, Optional<Boolean> isActive, Pageable pageable) {
        if (search.isPresent()) {
            return mdrChargeRepository.getAllMDRChargesWithSearchKeyword(search, isActive, pageable);
        } else {
            return mdrChargeRepository.getAllMDRCharges(isActive, pageable);
        }
    }

    @Override
    public void deActivateMDRChargeDetails(MDRCharge charge) {
        charge.setActive(false);
        mdrChargeRepository.save(charge);
    }

    @Override
    public MDRCharge findByMDRChargeId(Long id) {
        return mdrChargeRepository.getReferenceById(id);
    }

    @Override
    public List<MDRCharge> fetchMdrChargeListBasedOnLeadSourceAndDevice(LeadSource leadSource, Device device) {
        List<MDRCharge> mdrChargeList = mdrChargeRepository.fetchMdrListBasedOnLeadSourceAndDevice(leadSource, device);
        return mdrChargeList;
    }

    //    @Override
//    public int countByLeadSourceDeviceMerchantCategory(int leadSource, long device_id, long merchantCategory) {
//        return mdrChargeRepository.getMdrChargeCount(leadSource,device_id,merchantCategory);
//    }
    @Override
    public void save(List<MarsMdrCharges> mdrChargeList) {
        marsMdrChargesRepository.saveAll(mdrChargeList);
    }

    @Override
    public int countByName(String name) {
        return marsMdrChargesRepository.findCountByName(name);
    }

    @Override
    public int countByRentalName(String name) {
        return marsRentalPlansRepository.findCountByName(name);
    }

    @Override
    public List<MarsMdrCharges> findByLeadSourceAndDevice(LeadSource leadSource1, Device device1, MerchantCategoryCode mcc) {

        MerchantCategory category = merchantCategoryAndCodeMappingRepository.findMerchantCategoryByMcc(mcc);

        List<MarsMdrChargesDto> chargesDtoList = new ArrayList<>();
        List<MarsMdrCharges> marsMdrCharges = marsMdrChargesRepository.findByLeadSourceAndDevice(leadSource1, device1, category);
//        marsMdrCharges.forEach(l ->{
//            JSONParser parser = new JSONParser();
////            MarsMdrCharges charges = new MarsMdrCharges();
//            MarsMdrChargesDto charges = new MarsMdrChargesDto();
//            charges.setId(l.getId());
//            charges.setCode(l.getCode());
//            charges.setName(l.getName());
//            charges.setDevice(l.getDevice());
//            charges.setLeadSource(l.getLeadSource());
//            charges.setMerchantCategory(l.getMerchantCategory());
//            charges.setActive(l.isActive());
//            try {
//                charges.setCommercialOrCorporate((JSONObject) parser.parse(l.getCommercialOrCorporate()));
//                charges.setDomesticDebitUpTo2000((JSONObject) parser.parse(l.getDomesticDebitUpTo2000()));
//                charges.setDomesticDebitAbove2000((JSONObject) parser.parse(l.getDomesticDebitAbove2000()));
//                charges.setInternationalDebitCard((JSONObject) parser.parse(l.getInternationalDebitCard()));
//                charges.setInternationalCreditCard((JSONObject) parser.parse(l.getInternationalCreditCard()));
//                charges.setSuperPremiumOrSignature((JSONObject) parser.parse(l.getSuperPremiumOrSignature()));
//                charges.setStandardOrClassic((JSONObject) parser.parse(l.getStandardOrClassic()));
//                charges.setPremiumOrPlatinum((JSONObject) parser.parse(l.getPremiumOrPlatinum()));
//                charges.setOnus((JSONObject) parser.parse(l.getOnus()));
//                charges.setDiners((JSONObject) parser.parse(l.getDiners()));
//                charges.setCashAtPos((JSONObject) parser.parse(l.getCashAtPos()));
//                charges.setMasterPass((JSONObject) parser.parse(l.getMasterPass()));
//
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//            chargesDtoList.add(charges);
//        });
        return marsMdrCharges;
    }

    @Override
    public List<MarsMdrCharges> findByLeadSourceAndDevice1(LeadSource leadSource1, Device device1, MerchantCategoryCode mcc, int type) {
        System.out.println("leadSource " + leadSource1.getId() + " " + "Device " + device1.getId() + " " + "MCC " + mcc.getMccCode() + " " + "Type " + type);

        String merchant;
        if (type == Constants.small) {
            merchant = "Small";
        } else {
            merchant = "Other";
        }
        MerchantCategory category = merchantCategoryAndCodeMappingRepository.findMerchantCategoryByMcc(mcc);
        List<MarsMdrChargesDto> chargesDtoList = new ArrayList<>();
        List<MarsMdrCharges> marsMdrCharges = marsMdrChargesRepository.findByLeadSourceAndDevice1(leadSource1, device1, category, merchant);
//        marsMdrCharges.forEach(l ->{
//            JSONParser parser = new JSONParser();
////            MarsMdrCharges charges = new MarsMdrCharges();
//            MarsMdrChargesDto charges = new MarsMdrChargesDto();
//            charges.setId(l.getId());
//            charges.setCode(l.getCode());
//            charges.setName(l.getName());
//            charges.setDevice(l.getDevice());
//            charges.setLeadSource(l.getLeadSource());
//            charges.setMerchantCategory(l.getMerchantCategory());
//            charges.setActive(l.isActive());
//            try {
//                charges.setCommercialOrCorporate((JSONObject) parser.parse(l.getCommercialOrCorporate()));
//                charges.setDomesticDebitUpTo2000((JSONObject) parser.parse(l.getDomesticDebitUpTo2000()));
//                charges.setDomesticDebitAbove2000((JSONObject) parser.parse(l.getDomesticDebitAbove2000()));
//                charges.setInternationalDebitCard((JSONObject) parser.parse(l.getInternationalDebitCard()));
//                charges.setInternationalCreditCard((JSONObject) parser.parse(l.getInternationalCreditCard()));
//                charges.setSuperPremiumOrSignature((JSONObject) parser.parse(l.getSuperPremiumOrSignature()));
//                charges.setStandardOrClassic((JSONObject) parser.parse(l.getStandardOrClassic()));
//                charges.setPremiumOrPlatinum((JSONObject) parser.parse(l.getPremiumOrPlatinum()));
//                charges.setOnus((JSONObject) parser.parse(l.getOnus()));
//                charges.setDiners((JSONObject) parser.parse(l.getDiners()));
//                charges.setCashAtPos((JSONObject) parser.parse(l.getCashAtPos()));
//                charges.setMasterPass((JSONObject) parser.parse(l.getMasterPass()));
//
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//            chargesDtoList.add(charges);
//        });
        return marsMdrCharges;
    }


    @Override
    public Page<MarsMdrChargesDto> fetchAllMdrList(Optional<String> searchTerm, Pageable pageable) {
        List<MarsMdrChargesDto> chargesDtoList = new ArrayList<>();
        Page<MarsMdrCharges> marsMdrCharges = null;
        if (searchTerm.isPresent()) {
            marsMdrCharges = marsMdrChargesRepository.fetchMdrListWithSearchTerm(searchTerm.get(), pageable);
        } else {
            marsMdrCharges = marsMdrChargesRepository.fetchMdrListWithoutSearchTerm(pageable);
        }

        marsMdrCharges.forEach(l -> {
            JSONParser parser = new JSONParser();
//            MarsMdrCharges charges = new MarsMdrCharges();
            MarsMdrChargesDto charges = new MarsMdrChargesDto();
            charges.setCode(l.getCode());
            charges.setName(l.getName());
            charges.setDevice(l.getDevice());
            charges.setLeadSource(l.getLeadSource());
            charges.setMerchantCategory(l.getMerchantCategory());
            charges.setActive(l.isActive());
            try {
                charges.setCommercialOrCorporate((JSONObject) parser.parse(l.getCommercialOrCorporate()));
                charges.setDomesticDebitUpTo2000((JSONObject) parser.parse(l.getDomesticDebitUpTo2000()));
                charges.setDomesticDebitAbove2000((JSONObject) parser.parse(l.getDomesticDebitAbove2000()));
                charges.setInternationalDebitCard((JSONObject) parser.parse(l.getInternationalDebitCard()));
                charges.setInternationalCreditCard((JSONObject) parser.parse(l.getInternationalCreditCard()));
                charges.setSuperPremiumOrSignature((JSONObject) parser.parse(l.getSuperPremiumOrSignature()));
                charges.setStandardOrClassic((JSONObject) parser.parse(l.getStandardOrClassic()));
                charges.setPremiumOrPlatinum((JSONObject) parser.parse(l.getPremiumOrPlatinum()));
                charges.setOnus((JSONObject) parser.parse(l.getOnus()));
                charges.setDiners((JSONObject) parser.parse(l.getDiners()));
                charges.setCashAtPos((JSONObject) parser.parse(l.getCashAtPos()));
                charges.setMasterPass((JSONObject) parser.parse(l.getMasterPass()));

            } catch (ParseException e) {
                e.printStackTrace();
            }
            chargesDtoList.add(charges);
        });
        return new PageImpl<>(chargesDtoList, PageRequest.of(marsMdrCharges.getNumber(), marsMdrCharges.getSize()), marsMdrCharges.getTotalElements());
    }

    @Override
    public List<MDRCharge> fetchMdrChargeListBasedOnLeadSourceDeviceMcc(LeadSource leadSource, Device device, MerchantCategoryCode mcc) {
        List<MDRCharge> mdrChargeList = new ArrayList<>();
        MerchantCategory category = merchantCategoryAndCodeMappingRepository.findMerchantCategoryByMcc(mcc);
        if (category != null) {
            mdrChargeList = mdrChargeRepository.fetchMdrListBasedOnLeadSourceDevice(leadSource, device, category);
        }
        return mdrChargeList;
    }
}
