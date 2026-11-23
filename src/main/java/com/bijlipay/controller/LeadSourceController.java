package com.bijlipay.controller;

import com.bijlipay.model.LeadSource;
import com.bijlipay.model.User;
import com.bijlipay.model.UserAuthentication;
import com.bijlipay.service.LeadSourceService;
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
@RequestMapping("/admin/leadSource")
public class LeadSourceController extends BaseController {

    @Autowired
    private LeadSourceService leadSourceService;

    private static final Logger log = LoggerFactory.getLogger(LeadSourceController.class);


    @Autowired
    private UserAuthenticationService userAuthenticateService;

    @PostMapping("/create-lead-source")
    public ResponseEntity<?> createLeadSource(HttpServletRequest request, @RequestBody LeadSource leadsource) {

        String authtoken = request.getHeader("Authorization");
        log.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        log.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
            log.info("create api----------------------");
            LeadSource createdLeadSource = leadSourceService.createLeadSource(leadsource);
            if (createdLeadSource != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", createdLeadSource), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "Lead Source not created!!!", null), HttpStatus.BAD_REQUEST);

        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping(value = {"/leadSourceList"})
    public ResponseEntity<?> getLeadSource(HttpServletRequest request) {

        String authtoken = request.getHeader("Authorization");
        log.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        log.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
            List<LeadSource> leadSources = leadSourceService.getAllLeadSource();
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", leadSources), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/delete-lead-source/{id}")
    public ResponseEntity<?> deleteLeadSource(HttpServletRequest request, @PathVariable("id") int sourceid, LeadSource leadSource) {

        String authtoken = request.getHeader("Authorization");
        log.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        log.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            LeadSource deactivated = leadSourceService.deActivate(sourceid, leadSource);
            if (deactivated != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Given Leadsouce has been deactivated successfully!!!", deactivated), HttpStatus.OK);
            }

            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "Given Leadsource does not exist!", null), HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }


}
