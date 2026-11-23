package com.bijlipay.controller;

import com.bijlipay.model.MerchantType;
import com.bijlipay.model.User;
import com.bijlipay.model.UserAuthentication;
import com.bijlipay.service.MerchantTypeService;
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
@RequestMapping("/admin/merchantType")
public class MerchantTypeController extends BaseController {


    private static final Logger logger = LoggerFactory.getLogger(MerchantTypeController.class);

    @Autowired
    private UserAuthenticationService userAuthenticateService;

    @Autowired
    MerchantTypeService merchantTypeService;

    //=====================================CRUD Operations for Merchant Type=================================================
    @PostMapping("/create-merchant-type")
    public ResponseEntity<?> addMerchantType(HttpServletRequest request, @RequestBody MerchantType merchantType) {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
            MerchantType savedMerchantType = merchantTypeService.saveMerchantType(merchantType);
            if (savedMerchantType != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Merchant Type created successfully!!!", savedMerchantType), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "Merchant Type not created!!!", null), HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/getAll-merchantTypes")
    public ResponseEntity<?> getAllMerchantTypes(HttpServletRequest request) {
        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            List<MerchantType> merchantTypeList = merchantTypeService.getAll();
            if (merchantTypeList != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Success! Merchant Type Found!!", merchantTypeList), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "Merchant Type Not Found!!!", null), HttpStatus.BAD_REQUEST);

        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/merchantTypes/{id}")
    public ResponseEntity<?> updateMerchantType(HttpServletRequest request,@PathVariable("id") int merchanttypeid, @RequestBody MerchantType merchantType) {
        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            MerchantType merchantType1 = merchantTypeService.updateMerchantType(merchanttypeid, merchantType);
            if (merchantType1 != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Success! Merchant Type Updated!!", merchantType1), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "Merchant type Not updated!!", null), HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }


    @DeleteMapping("/delete-merchant-type/{id}")
    public ResponseEntity<?> deleteMerchantType(HttpServletRequest request,@PathVariable("id") int merchanttypeid, MerchantType merchantType) {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            MerchantType deleteMerchantType = merchantTypeService.getMerchantByid(merchanttypeid);
            if (deleteMerchantType != null) {
                merchantTypeService.deactivate(deleteMerchantType);
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Success! Merchant Type deactivated successfully!!", deleteMerchantType), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "Given Merchant Type Not Found!!", null), HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

}
