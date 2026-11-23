package com.bijlipay.controller;


import com.bijlipay.config.Constants;
import com.bijlipay.config.WebConfig;
import com.bijlipay.model.*;
import com.bijlipay.model.marsDto.MarsMdrChargesDto;
import com.bijlipay.repository.*;
import com.bijlipay.service.*;
import com.bijlipay.util.ApiResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Pair;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@RestController
@RequestMapping("/admin/mdrRental")
public class MDRAndRentalChargeController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(MDRAndRentalChargeController.class);
    @Value("${rental.plan.sync}")
    private String rentalPlanSyncUrl;

    @Value("${mdr.charges.sync}")
    private String mdrChargesSyncUrl;
    @Autowired
    private MDRChargeService mdrChargeService;
    @Autowired
    private WebConfig webConfig;
    @Autowired
    private RentalChargeService rentalChargeService;

    @Autowired
    private UserAuthenticationService userAuthenticateService;

    @Autowired
    private LeadSourceService leadSourceService;
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private MerchantCategoryService merchantCategoryService;

    @Autowired
    private PlanService planService;
    @Autowired
    private MccInstitutionMappingRepo mccInstitutionMappingRepo;
    @Autowired
    private LeadInformationRepo leadInformationRepo;
    @Autowired
    private MerchantCategoryCodeRepository merchantCategoryCodeRepository;
    @Autowired
    private OnboardInstanseRepo onboardInstanseRepo;
    /************************************  MDR CHARGE  ************************************/

    /**
     * CREATE MDR DETAILS
     */
    @PostMapping("/add-mdr-details/{sourceId}/{deviceId}/{mCategoryId}")
    public ResponseEntity<?> addMDRDetails(HttpServletRequest request, @PathVariable("sourceId") LeadSource leadSource, @PathVariable("deviceId") Device device, @PathVariable("mCategoryId") MerchantCategory merchantCategory, @RequestBody MDRCharge mdrCharge) {
        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            int mdrDetails = mdrChargeService.existsMDRDetails(leadSource, device, merchantCategory, mdrCharge.getMdrPlanName());
            if (mdrDetails > 0) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "Record Already Exists"), HttpStatus.BAD_REQUEST);
            } else {
                Pair<Boolean, MDRCharge> result = mdrChargeService.saveMDRDetails(mdrCharge, leadSource, device, merchantCategory);
                if (result.getFirst() == false) {
                    return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "error"), HttpStatus.BAD_REQUEST);
                }
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", result.getSecond()), HttpStatus.OK);
            }
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * UPDATE MDRCharge
     */
    @PutMapping("/update-mdr-details/{id}")
    public ResponseEntity<?> updateMDRDetails(HttpServletRequest request, @RequestBody MDRCharge mdrCharge, @PathVariable("id") Long mdrChargeId) {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            Pair<Boolean, MDRCharge> result = mdrChargeService.updateMDRDetails(mdrCharge, mdrChargeId);
            if (result.getFirst() == false) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "MDR Details Not Updated !"), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "MDR Details Successfully Updated", result.getSecond()), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * GET ALL the List OF MDR Details
     */
    @GetMapping(value = {"/mdr-charge-list/{active}"})
    public ResponseEntity<?> getAllMDRDetails(HttpServletRequest request, @RequestParam Map<String, String> requestParams,
                                              @PathVariable("active") Optional<Boolean> isActive,
                                              @RequestParam("searchTerm") Optional<String> search, Pageable pageable) {
        pageable = webConfig.resolvePageable(requestParams, pageable);
        Page<MDRCharge> mdrCharges = mdrChargeService.getAllMDRCharges(search, isActive, pageable);


        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
            if (mdrCharges.isEmpty()) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, "No data found"), HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Success", mdrCharges), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Get MDRCharge List Based On LeadSource And Device
     *
     * @param leadSource
     * @param device
     * @return
     */
    @GetMapping("/mdr-charge-details/{leadSource}/{device}")
    public ResponseEntity<?> getMdrDetailsBasedOnLeadSourceAndDevice(HttpServletRequest request, @PathVariable("leadSource") LeadSource leadSource, @PathVariable("device") Device device) {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            List<MDRCharge> mdrChargeList = mdrChargeService.fetchMdrChargeListBasedOnLeadSourceAndDevice(leadSource, device);
            if (mdrChargeList.isEmpty()) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "No Data available"), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", mdrChargeList), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Get Mdr Charge based on LeadSource,Device,Mcc
     */
    @GetMapping("mdr-charge/{leadSource}/{device}/{mcc}")
    public ResponseEntity<?> mdrCharge(HttpServletRequest request, @PathVariable("leadSource") LeadSource leadSource, @PathVariable("device") Device device, @PathVariable("mcc") MerchantCategoryCode mcc) {
        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
            List<MDRCharge> mdrChargeList = mdrChargeService.fetchMdrChargeListBasedOnLeadSourceDeviceMcc(leadSource, device, mcc);
            if (mdrChargeList.isEmpty()) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "No Data available"), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", mdrChargeList), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }


    /**
     * DEACTIVATE MDR CHARGE
     */
    @DeleteMapping("/deactivate-mdr-charge/{id}")
    public ResponseEntity<?> deActivateMDRCharge(HttpServletRequest request, @PathVariable("id") Long id) {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {


            MDRCharge charge = mdrChargeService.findByMDRChargeId(id);
            if (charge == null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, "MDR Details Not Found"), HttpStatus.NOT_FOUND);
            }
            mdrChargeService.deActivateMDRChargeDetails(charge);
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "successfully deactivated"), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    /*******************************************  RENTAL CHARGE   ************************************************/

    /**
     * CREATE RENTAL CHARGE
     */
    @PostMapping("/add-rental-charges/{sourceId}/{deviceId}/{planId}")
    public ResponseEntity<?> createRentalCharges(HttpServletRequest request, @PathVariable("sourceId") LeadSource leadSource, @PathVariable("deviceId") Device device, @PathVariable("planId") Plan plan, @RequestBody RentalCharge rentalCharge) {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            int rentalDetails = rentalChargeService.existsRentalDetails(leadSource, device, plan);
            if (rentalDetails > 0) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "Record Already Exists"), HttpStatus.BAD_REQUEST);
            }
            Pair<Boolean, RentalCharge> result = rentalChargeService.createRentalCharge(rentalCharge, leadSource, device, plan);
            if (result.getFirst() == false) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "Rental Charges not created"), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Success", result.getSecond()), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * UPDATE RENTAL CHARGE
     */
    @PutMapping("/update-rental-charges/{id}")
    public ResponseEntity<?> updateRentalCharge(HttpServletRequest request, @PathVariable("id") Long id, @RequestBody RentalCharge rentalCharge) {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            Pair<Boolean, RentalCharge> result = rentalChargeService.updateRentalCharge(id, rentalCharge);
            if (result.getFirst() == false) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "Rental Charge Details Not Updated"), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Rental Charge Updated Successfully", result.getSecond()), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * GET ALL THE LIST OF RENTAL CHARGE
     */
    @GetMapping("/rental-charge-list/{active}")
    public ResponseEntity<?> getAllRentalChargeList(HttpServletRequest request, @PathVariable("active") Optional<Boolean> isActive,
                                                    @RequestParam Map<String, String> requestParams,
                                                    @RequestParam("searchTerm") Optional<String> search, Pageable pageable) {
        pageable = webConfig.resolvePageable(requestParams, pageable);
        Page<RentalCharge> result = rentalChargeService.getAllRentalChargeList(search, isActive.get(), pageable);

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            if (result.isEmpty()) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "No Data Found"), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Success", result), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Get RentalCharge list based on leadSource And device
     *
     * @param leadSource
     * @param device
     * @return
     */
    @GetMapping("/rental-charge-details/{leadSource}/{device}")
    public ResponseEntity<?> getRentalChargeDetails(HttpServletRequest request, @PathVariable("leadSource") LeadSource leadSource, @PathVariable("device") Device device) {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            List<RentalCharge> rentalChargeList = rentalChargeService.fetchRentalChargeListBasedOnLeadSourceAndDevice(leadSource, device);
            logger.info("rentalChargeList -- : {}", rentalChargeList);
            if (rentalChargeList.size() > 0) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", rentalChargeList), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "No data Found"), HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * DEACTIVATE RENTAL CHARGE
     */
    @DeleteMapping("/deactivate-rental-charge/{id}")
    public ResponseEntity<?> deActivateRentalCharge(HttpServletRequest request, @PathVariable("id") Long id) {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
            RentalCharge charge = rentalChargeService.findByRentalChargeId(id);
            if (charge == null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, "Rental Details Not Found"), HttpStatus.NOT_FOUND);
            }
            rentalChargeService.deActivateRentalChargeDetails(charge);
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "successfully deactivated"), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }


    /**
     * GET DEVICE LIST BASED ON LEAD SOURCE
     */

//    @GetMapping("/get-device-list-based-on-lead-source/{source-id}")
//    public ResponseEntity<?> getDeviceListBasedOnLeadSource(HttpServletRequest request,@PathVariable("source-id") int sourceId) {
//
//
//        String authtoken = request.getHeader("Authorization");
//        logger.info("authtoken==={}", authtoken);
//        User curuser = getSessionUser();
//        logger.info("Session User {}", curuser);
//        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
//        if (authtoken.equals(userauth.getToken())) {
//            List<Device> deviceList = rentalChargeService.getDeviceList(sourceId);
//            if (deviceList.isEmpty()) {
//                return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "Device List not found !!!", null), HttpStatus.BAD_REQUEST);
//            }
//            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Device list found!!!", deviceList), HttpStatus.OK);
//        } else {
//
//            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
//        }
//    }
    @GetMapping("/get-device-list-based-on-lead-source/{source-id}")
    public ResponseEntity<?> getDeviceListBasedOnLeadSource(HttpServletRequest request, @PathVariable("source-id") int sourceId) {


        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
            List<Device> deviceList = rentalChargeService.getDeviceDetails(sourceId);
            if (deviceList.isEmpty()) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "Device List not found !!!", null), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Device list found!!!", deviceList), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * GET PLAN LIST BASED ON LEAD SOURCE
     */

//    @GetMapping("/get-plan-list-based-on-lead-source/{source-id}")
//    public ResponseEntity<?> getPlanListBasedOnLeadSource(HttpServletRequest request,@PathVariable("source-id") int sourceId) {
//
//        String authtoken = request.getHeader("Authorization");
//        logger.info("authtoken==={}", authtoken);
//        User curuser = getSessionUser();
//        logger.info("Session User {}", curuser);
//        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
//        if (authtoken.equals(userauth.getToken())) {
//
//            List<Plan> planList = rentalChargeService.getPlanList(sourceId);
//            if (planList.isEmpty()) {
//                return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "Plan List not found!!!", null), HttpStatus.BAD_REQUEST);
//            }
//            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Plan List found successfully!!!", planList), HttpStatus.OK);
//        } else {
//
//            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
//        }
//    }
    @GetMapping("/get-plan-list-based-on-lead-source/{source-id}")
    public ResponseEntity<?> getPlanListBasedOnLeadSource(HttpServletRequest request, @PathVariable("source-id") int sourceId) {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            List<Plan> planList = rentalChargeService.getPlanDetails(sourceId);
            if (planList.isEmpty()) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "Plan List not found!!!", null), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Plan List found successfully!!!", planList), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }


    /**
     * Action 1 - Small
     * Action 2 - Other
     *
     * @return
     */

    @GetMapping("mdr-charge-mars-sync/{action}")
    public ResponseEntity<?> mdrCharge(@PathVariable("action") int action) {
        String url = null;
        if (action == Constants.small) {
            url = mdrChargesSyncUrl + "?institutionCode=101&type=Small";
        } else if (action == Constants.other) {
            url = mdrChargesSyncUrl + "?institutionCode=101&type=Other";
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("NII", "101");

        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<DTO> result = new RestTemplate().exchange(url, HttpMethod.GET, entity, DTO.class);

//        ResponseEntity<String> result = new RestTemplate().exchange(url, HttpMethod.GET, entity, String.class);
//        String[] items = new String[]{result.getBody()};

        List<MarsMdrCharges> mdrChargeList = new ArrayList<>();
        MarsData[] items = result.getBody().getItems();
        JSONArray jsonArray = new JSONArray(items);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("items", jsonArray);
        logger.info("json--{}", jsonObject.toString());

        Arrays.stream(items).forEach(l -> {
//            JSONObject jsonObject = new JSONObject(l);
//            logger.info("json--{}",jsonObject);

            boolean update = mdrChargeService.countByName(l.getName()) > 0;
            if (!update) {
                String commercialOrCorporate = l.getCommercialOrCorporate().toJSONString();
                String domesticDebitUpTo2000 = l.getDomesticDebitUpTo2000().toJSONString();
                logger.info("domestic 1-------------- :{}", domesticDebitUpTo2000);
                String domesticDebitAbove2000 = l.getDomesticDebitAbove2000().toString();
                logger.info("domestic 2---------- : {}", domesticDebitAbove2000);
                String internationalDebitCard = l.getInternationalDebitCard().toJSONString();
                String internationalCreditCard = l.getInternationalCreditCard().toJSONString();
                String superPremiumOrSignature = l.getSuperPremiumOrSignature().toJSONString();
                String standardOrClassic = l.getStandardOrClassic().toJSONString();
                String premiumOrPlatinum = l.getPremiumOrPlatinum().toJSONString();
                String onus = l.getOnus().toJSONString();
                String diners = l.getDiners().toJSONString();
                String masterPass = l.getMasterPass().toJSONString();
                String cashAtPos = l.getCashAtPos().toJSONString();
                String upiCreditAbove2000 = l.getUpiCreditAbove2000().toJSONString();
                logger.info("-------------upi-------------------------{}", upiCreditAbove2000);
                String upiCreditUpto2000 = l.getUpiCreditUpto2000().toJSONString();
                String upiAbove2000 = l.getUpiAbove2000().toJSONString();
                String upi_Upto_2000 = l.getUpiUpto2000().toJSONString();
                logger.info("-------------amex-------------------------{}", l.getAmexDomestic().toJSONString());

                String amexDomestic = l.getAmexDomestic().toJSONString();
                String amexInternational = l.getAmexInternational().toJSONString();
                MarsMdrCharges charges = new MarsMdrCharges();
                try {
                    charges.setLeadSource(l.getLeadSource() == null ? null : leadSourceService.getLeadSource(Integer.parseInt(l.getLeadSource())));
                    charges.setMerchantCategory(l.getMerchantCategoryId() == null ? null : merchantCategoryService.fetchMerchantCategory(Long.parseLong(l.getMerchantCategoryId())));
                    charges.setDevice(l.getDeviceId() == null ? null : deviceService.getDevice(Long.parseLong(l.getDeviceId())));
                    logger.info("leadSource---------- : {} MerchantCategory-------------- :{} device------------: {}" + l.getLeadSource(), l.getMerchantCategoryId(), l.getDeviceId());
                } catch (NumberFormatException e) {
                    logger.info("Error: " + e.getMessage());
                }
                charges.setCommercialOrCorporate(commercialOrCorporate);
                charges.setDomesticDebitUpTo2000(domesticDebitUpTo2000);
                charges.setDomesticDebitAbove2000(domesticDebitAbove2000);
                charges.setInternationalDebitCard(internationalDebitCard);
                charges.setInternationalCreditCard(internationalCreditCard);
                charges.setSuperPremiumOrSignature(superPremiumOrSignature);
                charges.setStandardOrClassic(standardOrClassic);
                charges.setPremiumOrPlatinum(premiumOrPlatinum);
                charges.setOnus(onus);
                charges.setDiners(diners);
                charges.setCashAtPos(masterPass);
                charges.setMasterPass(cashAtPos);
                charges.setName(l.getName() == null ? null : l.getName());
                charges.setCode(l.getCode());
                charges.setActive(true);
                charges.setType(action == Constants.small ? "Small" : "Other");
                charges.setUpiAbove2000(upiAbove2000);
                charges.setUpiUpto2000(upi_Upto_2000);
                charges.setUpiCreditUpto2000(upiCreditUpto2000);
                charges.setUpiCreditAbove2000(upiCreditAbove2000);
                charges.setAmexDomestic(amexDomestic);
                charges.setAmexInternational(amexInternational);
                mdrChargeList.add(charges);
            }
        });
        mdrChargeService.save(mdrChargeList);
//        return ResponseEntity.ok(result.getBody());
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success"), HttpStatus.OK);
    }

    @GetMapping(value = "/rentalPlanMarsSync")
    private ResponseEntity<?> rentalPlan() throws Exception {
        String url = rentalPlanSyncUrl + "?institutionCode=101";

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("NII", "101");

        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<DTO> result = new RestTemplate().exchange(url, HttpMethod.GET, entity, DTO.class);

        logger.info("rentalPlan body: {}", result);
        MarsData[] items = result.getBody().getItems();

        List<MarsRentalPlans> rentalPlans = new ArrayList<>();

        Arrays.stream(items).forEach(l -> {

            boolean update = mdrChargeService.countByRentalName(l.getName()) > 0;
            if (!update) {
                MarsRentalPlans plans = new MarsRentalPlans();

                plans.setLeadSource(l.getLeadSource() == null ? null : leadSourceService.getLeadSource(Integer.parseInt(l.getLeadSource())));
                plans.setDevice(l.getDeviceId() == null ? null : deviceService.getDevice(Long.parseLong(l.getDeviceId())));

                if (l.getPlanFrequency().equalsIgnoreCase(Constants.daily)) {
                    plans.setPlan(l.getPlanFrequency() == null ? null : planService.findPlanByName("Daily"));
                } else if (l.getPlanFrequency().equalsIgnoreCase(Constants.weekly)) {
                    plans.setPlan(l.getPlanFrequency() == null ? null : planService.findPlanByName("Weekly"));
                } else if (l.getPlanFrequency().equalsIgnoreCase(Constants.monthly)) {
                    plans.setPlan(l.getPlanFrequency() == null ? null : planService.findPlanByName("Monthly"));
                } else if (l.getPlanFrequency().equalsIgnoreCase(Constants.yearly)) {
                    plans.setPlan(l.getPlanFrequency() == null ? null : planService.findPlanByName("Yearly"));
                } else if (l.getPlanFrequency().equalsIgnoreCase(Constants.lifeTime)) {
                    plans.setPlan(l.getPlanFrequency() == null ? null : planService.findPlanByName("Life Time"));
                } else {
                    plans.setPlan(null);
                }
                plans.setCode(l.getCode());
                plans.setName(l.getName());
//                plans.setSetupFee(l.getSetupFees());
                plans.setMonthlyFees(l.getMonthlyFees());
                plans.setPlanFrequency(l.getPlanFrequency());
                plans.setSetup_fees(l.getSetup_fees());
                rentalPlans.add(plans);
            }
        });
        rentalChargeService.saveMarsRentalPlans(rentalPlans);
//        return ResponseEntity.ok(items);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success"), HttpStatus.OK);
    }

    @GetMapping("mars-mdr/{leadSource}/{device}/{mcc}")
    public ResponseEntity<?> marsMdr(@PathVariable("leadSource") int leadSource, @PathVariable("device") long device, @PathVariable("mcc") MerchantCategoryCode mcc) {
        LeadSource leadSource1 = leadSourceService.getLeadSource(leadSource);
        Device device1 = deviceService.getDevice(device);
//        List<MarsMdrChargesDto> marsMdrCharges = mdrChargeService.findByLeadSourceAndDevice(leadSource1,device1,mcc);
        List<MarsMdrCharges> marsMdrCharges = mdrChargeService.findByLeadSourceAndDevice(leadSource1, device1, mcc);

        if (marsMdrCharges.isEmpty()) {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, "data not found"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", marsMdrCharges), HttpStatus.OK);
    }

    @GetMapping("mars-mdr/{leadSource}/{device}/{mcc}/{type}")
    public ResponseEntity<?> marsMdr(@PathVariable("leadSource") int leadSource, @PathVariable("device") long device, @PathVariable("mcc") MerchantCategoryCode mcc, @PathVariable("type") int type) {
        LeadSource leadSource1 = leadSourceService.getLeadSource(leadSource);
        Device device1 = deviceService.getDevice(device);
//        List<MarsMdrChargesDto> marsMdrCharges = mdrChargeService.findByLeadSourceAndDevice(leadSource1,device1,mcc);
        List<MarsMdrCharges> marsMdrCharges = mdrChargeService.findByLeadSourceAndDevice1(leadSource1, device1, mcc, type);

        if (marsMdrCharges.isEmpty()) {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, "data not found"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", marsMdrCharges), HttpStatus.OK);
    }

    @GetMapping("mars-rental/{leadSource}/{device}")
    public ResponseEntity<?> marsRental(@PathVariable("leadSource") int leadSource, @PathVariable("device") long device) {
        LeadSource leadSource1 = leadSourceService.getLeadSource(leadSource);
        Device device1 = deviceService.getDevice(device);
        List<MarsRentalPlans> marsRentalPlans = rentalChargeService.findByLeadSourceAndDevice(leadSource1, device1);
        if (marsRentalPlans.isEmpty()) {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, "data not found"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", marsRentalPlans), HttpStatus.OK);
    }

    @GetMapping("get-mdr-details")
    public ResponseEntity<?> fetchMdrChargesList(@RequestParam Map<String, String> requestParams, @RequestParam("searchTerm") Optional<String> searchTerm, Pageable pageable) {
        Page<MarsMdrChargesDto> marsMdrChargesList = null;
        pageable = webConfig.resolvePageable(requestParams, pageable);
        marsMdrChargesList = mdrChargeService.fetchAllMdrList(searchTerm, pageable);
        if (marsMdrChargesList.isEmpty()) {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, "Data not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", marsMdrChargesList), HttpStatus.OK);
    }

    @GetMapping("get-rental-details")
    public ResponseEntity<?> fetchRentalChargesList(@RequestParam Map<String, String> requestParams, @RequestParam("searchTerm") Optional<String> searchTerm, Pageable pageable) {
        Page<MarsRentalPlans> rentalPlans = null;
        pageable = webConfig.resolvePageable(requestParams, pageable);
        rentalPlans = rentalChargeService.fetchAllRentalList(searchTerm, pageable);
        if (rentalPlans.isEmpty()) {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, "Data not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", rentalPlans), HttpStatus.OK);
    }

    @PostMapping("mdr-validate")
    public ResponseEntity<?> mdrValidate(@RequestBody MdrValidateDto mdrValidateDto) {
        if (!mdrValidateDto.getDevice().isEmpty()) {
            for (long device : mdrValidateDto.getDevice()) {
                MerchantCategoryCode merchantCategoryCode = merchantCategoryCodeRepository.findMccCode1(mdrValidateDto.getMcc());
                LeadSource leadSource1 = leadSourceService.getLeadSource(mdrValidateDto.getLeadSource());
                Device device1 = deviceService.getDevice(device);
                List<MarsMdrCharges> marsMdrCharges = mdrChargeService.findByLeadSourceAndDevice1(leadSource1, device1, merchantCategoryCode, mdrValidateDto.getType());
                if (marsMdrCharges.isEmpty()) {
                    return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, "MDR NOT FOUND FOR " + device1.getDeviceName()), HttpStatus.NOT_FOUND);
                }
                logger.info("MDR FOR " + device1.getDeviceName() + " TOTAL FETCH" + marsMdrCharges.size());
                List<MarsRentalPlans> marsRentalPlans = rentalChargeService.findByLeadSourceAndDevice(leadSource1, device1);
                if (marsRentalPlans.isEmpty()) {
                    return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, "RENTAL NOT FOUND FOR " + device1.getDeviceName()), HttpStatus.NOT_FOUND);
                }
                logger.info("RENTAL FOR " + device1.getDeviceName() + " TOTAL FETCH" + marsMdrCharges.size());
            }

            OnboardInstance onboardInstance;

            boolean isSmallMerchant = Constants.small==mdrValidateDto.getType();

            onboardInstance = isSmallMerchant
                    ? mccInstitutionMappingRepo.findInstanceSmall(mdrValidateDto.getMcc())
                    : mccInstitutionMappingRepo.findInstance(mdrValidateDto.getMcc());

            if (onboardInstance == null) {
                String message = isSmallMerchant
                        ? "Small Merchant not allowed for this MCC"
                        : "Other Merchant not allowed for this MCC";

                return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, message), HttpStatus.BAD_REQUEST);
            }
            LeadInformation leadInformation = leadInformationRepo.findLeadId(mdrValidateDto.getLeadId());
            if (leadInformation == null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "LEAD ID NOT FOUND"), HttpStatus.BAD_REQUEST);
            }
            leadInformation.setOnboardInstance(onboardInstance);
            leadInformationRepo.save(leadInformation);
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "SUCCESS"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "BAD DEVICE REQUEST"), HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/get-institution")
    public ResponseEntity<?> getInstitution(){
        List<OnboardInstance> onboardInstances=onboardInstanseRepo.findAll();
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK,"Success",onboardInstances),HttpStatus.OK);
    }
}
