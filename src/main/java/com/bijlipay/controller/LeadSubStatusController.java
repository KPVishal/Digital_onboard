package com.bijlipay.controller;


import com.bijlipay.model.LeadSubStatus;
import com.bijlipay.model.User;
import com.bijlipay.model.UserAuthentication;
import com.bijlipay.service.LeadSubStatusService;
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

@RestController
@RequestMapping("/admin/subStatus")
public class LeadSubStatusController extends BaseController {

    @Autowired
    private LeadSubStatusService leadSubStatusService;

    private static final Logger logger = LoggerFactory.getLogger(LeadSubStatusController.class);

    @Autowired
    private UserAuthenticationService userAuthenticateService;

    @PostMapping("/lead-sub-status")
    public ResponseEntity<?> saveLeadSubStatus(HttpServletRequest request, @RequestBody LeadSubStatus leadSubStatus) {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            LeadSubStatus savedleadsubstatus = leadSubStatusService.saveLeadSubStatus(leadSubStatus);
            if (leadSubStatus != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "LeadSubStatus created Successfully!!!", leadSubStatus), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "LeadSubStatus not created !!!", null), HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }


    @GetMapping("/getAll-lead-sub-status")
    public ResponseEntity<?> getAllLeadSubStatus(HttpServletRequest request) {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            List<LeadSubStatus> leadSubStatusList = leadSubStatusService.getAllSubStatus();
            if (leadSubStatusList.isEmpty()) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "LeadSubStatus Not found!!", null), HttpStatus.BAD_REQUEST);

            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "LeadSubStatus Found!!!", leadSubStatusList), HttpStatus.OK);

        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }


}
