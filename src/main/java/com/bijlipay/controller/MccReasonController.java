package com.bijlipay.controller;

import com.bijlipay.model.MccReason;
import com.bijlipay.model.User;
import com.bijlipay.model.UserAuthentication;
import com.bijlipay.service.DocumentService;
import com.bijlipay.service.UserAuthenticationService;
import com.bijlipay.util.ApiResponse;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestController
@RequestMapping("/admin/mccReason")
public class MccReasonController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(MccReasonController.class);

    @Autowired
    private UserAuthenticationService userAuthenticationService;

    @Autowired
    private DocumentService documentService;

    @PostMapping("create-mcc-reason")
    public ResponseEntity<?> createMccReason(@RequestBody MccReason mccReason, HttpServletRequest request) {
        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticationService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
            MccReason reason = documentService.createMccReason(mccReason);
            if (reason != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Success"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, "Already Data Found"), HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/get-mcc-reason")
    public ResponseEntity<?>getMccReason(HttpServletRequest request){
        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticationService.getToken(curuser.getId());
        if(authtoken.equals(userauth.getToken())) {
            List<MccReason> mccReasonList = null;
            mccReasonList = documentService.getMccReasonList();
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Success", mccReasonList), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new ApiResponse(UNAUTHORIZED, "UnAuthorized!!!", null), UNAUTHORIZED);
        }
    }

    @PutMapping("update-mcc-reason/{id}")
    public ResponseEntity<?>updateMccReasonList(@PathVariable("id") Long id,@RequestBody MccReason mccReason,HttpServletRequest request){
        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticationService.getToken(curuser.getId());
        if(authtoken.equals(userauth.getToken())) {
            MccReason reason = documentService.updateMccReasonList(id, mccReason);
            if (reason != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Success", reason), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, "Not Found"), HttpStatus.NOT_FOUND);
            }
        }else{
            return new ResponseEntity<>(new ApiResponse(UNAUTHORIZED, "UnAuthorized!!!", null), UNAUTHORIZED);
        }
    }
}
