package com.bijlipay.controller;

import com.bijlipay.model.LeadSourceMCCAccountTypeMapping;
import com.bijlipay.model.User;
import com.bijlipay.model.UserAuthentication;
import com.bijlipay.service.LeadSourceMCCAccountTypeMappingService;
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
@RequestMapping("/admin/leadSourceMcc")
public class LeadSourceMCCAccountTypeMappingController extends BaseController {

    @Autowired
    private LeadSourceMCCAccountTypeMappingService leadSourceMCCAccountTypeMappingService;

    @Autowired
    private UserAuthenticationService userAuthenticateService;

    private static final Logger log = LoggerFactory.getLogger(LeadSourceMCCAccountTypeMappingController.class);

    @PostMapping("/createLeadSourceMCCAccountTypeMapping")
    public ResponseEntity<?> createLeadSourceMCCAccountType(HttpServletRequest request, @RequestBody LeadSourceMCCAccountTypeMapping leadSourceMCCAccountTypeMapping) {

        String authtoken = request.getHeader("Authorization");
        log.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        log.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
            LeadSourceMCCAccountTypeMapping leadSourceMCCAccountTypeMappingDetail = leadSourceMCCAccountTypeMappingService.isLeadSourceMCCAccountTypeMappingExists(leadSourceMCCAccountTypeMapping);
            if (leadSourceMCCAccountTypeMappingDetail == null) {
                LeadSourceMCCAccountTypeMapping createdLeadSourceMCCAccountTypeMapping = leadSourceMCCAccountTypeMappingService.createLeadSourceMCCAccountTypeMapping(leadSourceMCCAccountTypeMapping);
                if (createdLeadSourceMCCAccountTypeMapping != null) {
                    return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", createdLeadSourceMCCAccountTypeMapping), HttpStatus.OK);
                }
                return new ResponseEntity<>(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, "mapping not created"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.CONFLICT, "Mapping created already"), HttpStatus.CONFLICT);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    /* List of Document Category mapping */
    @GetMapping({"/listLeadSourceMCCAccountTypeMapping", "/listLeadSourceMCCAccountTypeMapping/{id}"})
    public ResponseEntity<?> listLeadSourceMCCAccountTypeMapping(HttpServletRequest request, @PathVariable("id") Optional<Long> id) throws Exception {

        String authtoken = request.getHeader("Authorization");
        log.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        log.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            List<LeadSourceMCCAccountTypeMapping> leadSourceMCCAccountTypeMapping = null;
            if (id.isPresent()) {
                leadSourceMCCAccountTypeMapping = leadSourceMCCAccountTypeMappingService.getLeadSourceMCCAccountTypeMapping(id);
            } else {
                leadSourceMCCAccountTypeMapping = leadSourceMCCAccountTypeMappingService.listAllLeadSourceMCCAccountTypeMappings();
            }
            if (leadSourceMCCAccountTypeMapping != null && leadSourceMCCAccountTypeMapping.size() > 0) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", leadSourceMCCAccountTypeMapping), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, "Mapping list not found"), HttpStatus.NOT_FOUND);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    /* List of Document Category mapping */
    @GetMapping({"/getLeadSourceMCCAccountTypeMapping/{leadSourceId}/{mccId}"})
    public ResponseEntity<?> getLeadSourceMCCAccountTypeMapping(HttpServletRequest request, @PathVariable("leadSourceId") Optional<Integer> leadSourceId, @PathVariable("mccId") Optional<Long> mccId) throws Exception {
        String authtoken = request.getHeader("Authorization");
        log.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        log.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            LeadSourceMCCAccountTypeMapping leadSourceMCCAccountTypeMapping = null;
            if (leadSourceId.isPresent() && mccId.isPresent()) {
                leadSourceMCCAccountTypeMapping = leadSourceMCCAccountTypeMappingService.getLeadSourceMCCAccountTypeMapping(leadSourceId, mccId);
            }
            if (leadSourceMCCAccountTypeMapping != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", leadSourceMCCAccountTypeMapping), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, "Mapping list not found"), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

}
