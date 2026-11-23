package com.bijlipay.controller;

import com.bijlipay.model.MerchantCategoryCode;
import com.bijlipay.model.User;
import com.bijlipay.model.UserAuthentication;
import com.bijlipay.service.MerchantCategoryCodeService;
import com.bijlipay.service.UserAuthenticationService;
import com.bijlipay.util.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/mcc")
public class MerchantCategoryCodeController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(MerchantCategoryCodeController.class);

    @Autowired
    private MerchantCategoryCodeService merchantCategoryCodeService;

    @Autowired
    private UserAuthenticationService userAuthenticateService;

    @PostMapping("/create-merchant-category-code")
    public ResponseEntity<?> createMCC(HttpServletRequest request, @Valid @RequestBody MerchantCategoryCode merchantCategoryCode, BindingResult bindingResult) throws URISyntaxException {
        //check whether the mcc code already exists or not

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            boolean flag = merchantCategoryCodeService.existsMcc(merchantCategoryCode.getMccCode());
            if (flag) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.CONFLICT, "Mcc Already Exists"), HttpStatus.CONFLICT);
            }
            MerchantCategoryCode mcc = merchantCategoryCodeService.createMerchantCategoryCode(merchantCategoryCode);
            if (bindingResult.hasErrors()) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "error", bindingResult), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "MCC Created Successfully", mcc), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/getAllMccList")
    public ResponseEntity<?> getAllMccList(HttpServletRequest request) {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
            List<MerchantCategoryCode> mccList = merchantCategoryCodeService.getAllMccList();
            if (mccList.size() > 0) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", mccList), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "error"), HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping(value = {"/getRestrictedMccList/{action}", "/getRestrictedMccList/{action}/{search}"})
    public ResponseEntity<?> getRestrictedMccList(HttpServletRequest request, @PathVariable("action") int action, @PathVariable("search") Optional<String> search) {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
            List<MerchantCategoryCode> mccList = merchantCategoryCodeService.getRestrictedMccList(action, search);
            if (mccList.size() > 0) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", mccList), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "error"), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }
    @GetMapping("/getAllMccList/{Search}")
    public ResponseEntity<?> getAllMccListSearch(HttpServletRequest request, @PathVariable("Search") String Search) {
        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
            List<MerchantCategoryCode> mccList = merchantCategoryCodeService.getAllMccListSearch(Search);
            if (mccList.size() > 0) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", mccList), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "error"), HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/getMccList/{id}")
    public ResponseEntity<?> getMccBasedOnId(HttpServletRequest request, @PathVariable("id") Long id) {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
            MerchantCategoryCode categoryCode = merchantCategoryCodeService.findMccById(id);
            if (categoryCode != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", categoryCode), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, "mcc Not Found"), HttpStatus.NOT_FOUND);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/deleteMcc/{id}")
    public ResponseEntity<?> deleteMccBasedOnId(HttpServletRequest request, @PathVariable("id") Long id) {
        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
            MerchantCategoryCode categoryCode = merchantCategoryCodeService.findMccById(id);
            if (categoryCode != null) {
                merchantCategoryCodeService.deleteMccById(id);
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Success"), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, "Mcc Not Found"), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }
}
