package com.bijlipay.controller;

import com.bijlipay.model.DocumentCancelReason;
import com.bijlipay.model.LeadCancellationReason;
import com.bijlipay.model.User;
import com.bijlipay.model.UserAuthentication;
import com.bijlipay.service.LeadCancellationReasonService;
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
@RequestMapping("/admin/cancelReason")
public class LeadCancellationReasonController extends BaseController{
    @Autowired
    private LeadCancellationReasonService leadCancellationReasonService;

    private static final Logger log = LoggerFactory.getLogger(LeadCancellationReasonController.class);

    @Autowired
    private UserAuthenticationService userAuthenticateService;

    @PostMapping("/add-lead-cancellation-reason")
    public ResponseEntity<?> createLeadCancellationRemarks(HttpServletRequest request,@RequestBody LeadCancellationReason leadCancellationReason) {

        String authtoken = request.getHeader("Authorization");
        log.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        log.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            LeadCancellationReason reason = leadCancellationReasonService.existsReason(leadCancellationReason);
            if (reason != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "Reason Already Exists"), HttpStatus.BAD_REQUEST);
            }
            LeadCancellationReason result = leadCancellationReasonService.createLeadCancellationReason(leadCancellationReason);
            if (result == null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "Lead Cancellation Reason not created"), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "successfully created", result), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }
    @GetMapping("/get-lead-cancellation-reason/{active}")
    public ResponseEntity<?> getleadCancellationReason(HttpServletRequest request,@PathVariable("active")boolean isActive) {
        String authtoken = request.getHeader("Authorization");
        log.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        log.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
            List<LeadCancellationReason> reasonList = leadCancellationReasonService.getAllList(isActive);
            if (reasonList == null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "No Data Found"), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Success", reasonList), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }
    @DeleteMapping("/deactivate-lead-cancellation-reason/{id}")
    public ResponseEntity<?> deActivateLeadCancelReason(HttpServletRequest request,@PathVariable("id")Long id) {
        String authtoken = request.getHeader("Authorization");
        log.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        log.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
            LeadCancellationReason cancellationReason = leadCancellationReasonService.findCancelReasonById(id);
            if (cancellationReason == null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, "Id Not Found"), HttpStatus.NOT_FOUND);
            }
            leadCancellationReasonService.deActivateLeadCancelReason(cancellationReason);
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "successfully Deactivated"), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    /*
    Document cancel reason for Aggregator onboard - In ops
     */
    @GetMapping("/get-cancellation-reason/{active}")
    public ResponseEntity<?> getCancellationReason(HttpServletRequest request,@PathVariable("active")boolean isActive) {
        String authtoken = request.getHeader("Authorization");
        log.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        log.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
            List<DocumentCancelReason> reasonList = leadCancellationReasonService.getCancelReason(isActive);
            if (reasonList == null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "No Data Found"), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Success", reasonList), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    /* Create cancel reason remark */
    @PostMapping("/add-cancellation-reason")
    public ResponseEntity<?> createCancellationRemarks(HttpServletRequest request,@RequestBody DocumentCancelReason documentCancelReason) {

        String authtoken = request.getHeader("Authorization");
        log.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        log.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            DocumentCancelReason reason = leadCancellationReasonService.checkReason(documentCancelReason);
            if (reason != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "Reason Already Exists"), HttpStatus.BAD_REQUEST);
            }
            DocumentCancelReason result = leadCancellationReasonService.createCancellationReason(documentCancelReason);
            if (result == null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "Lead Cancellation Reason not created"), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "successfully created", result), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }
}
