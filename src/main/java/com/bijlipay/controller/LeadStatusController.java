package com.bijlipay.controller;

import com.bijlipay.model.Device;
import com.bijlipay.model.LeadStatus;
import com.bijlipay.model.User;
import com.bijlipay.model.UserAuthentication;
import com.bijlipay.service.DeviceService;
import com.bijlipay.service.LeadStatusService;
import com.bijlipay.service.UserAuthenticationService;
import com.bijlipay.util.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/status")
public class LeadStatusController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(LeadStatusController.class);

    @Autowired
    private LeadStatusService leadStatusService;

    @Autowired
    private UserAuthenticationService userAuthenticateService;

    /*
        create new device type
     */
    @PostMapping("/createLeadStatus")
    public ResponseEntity<?> createLeadStatus(HttpServletRequest request,@RequestBody LeadStatus leadStatus) {
        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            logger.info("Create Device API Starts : ", leadStatus);

            LeadStatus leadStatusDetail = leadStatusService.isLeadStatusExists(leadStatus);

            if (leadStatusDetail != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.CONFLICT, "LeadStatus Already Created"), HttpStatus.CONFLICT);
            }

            LeadStatus createdLeadStatus = leadStatusService.createLeadStatus(leadStatus);

            if (createdLeadStatus != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.CREATED, "LeadStatus Created Successfully"), HttpStatus.CREATED);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, "LeadStatus not Created"), HttpStatus.INTERNAL_SERVER_ERROR);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    /*
        get active device list
     */

    @GetMapping({"/leadStatusList", "/leadStatusList/{is_active}"})
    public ResponseEntity<?> getActiveLeadStatusList(HttpServletRequest request,@PathVariable("is_active") Optional<Boolean> is_active) {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {


            List<LeadStatus> leadStatusList = null;

            if (is_active.isPresent()) {
                leadStatusList = leadStatusService.listActiveLeadStatus(is_active.get());
            } else {
                leadStatusList = leadStatusService.listAllLeadStatus();
            }
            if (leadStatusList != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", leadStatusList), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, "device list not found"), HttpStatus.NOT_FOUND);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping({"/getLeadStatus/{id}"})
    public ResponseEntity<?> getActiveLeadStatusList(HttpServletRequest request,@PathVariable("id") long id) {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
            LeadStatus leadStatus = leadStatusService.getLeadStatus(id);

            if (leadStatus != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", leadStatus), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, "leadStatus not found"), HttpStatus.NOT_FOUND);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("check-connection")
    public ResponseEntity<?> connection(){
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK,"success"),HttpStatus.OK);
    }

}
