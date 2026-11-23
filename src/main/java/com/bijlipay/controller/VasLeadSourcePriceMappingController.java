package com.bijlipay.controller;

import com.bijlipay.model.User;
import com.bijlipay.model.UserAuthentication;
import com.bijlipay.model.VasLeadSourcePriceMapping;
import com.bijlipay.service.UserAuthenticationService;
import com.bijlipay.service.VasLeadSourcePriceMappingService;
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
@RequestMapping("/admin/vasLeadSource")
public class VasLeadSourcePriceMappingController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(VasLeadSourcePriceMappingController.class);


    @Autowired
    private VasLeadSourcePriceMappingService vasLeadSourcePriceMappingService;

    @Autowired
    private UserAuthenticationService userAuthenticateService;

    @PostMapping("/create-vas-lead-source-price")
    public ResponseEntity<?> createVasleadSourcePriceMapping(HttpServletRequest request, @RequestBody VasLeadSourcePriceMapping vasLeadSourcePriceMapping) {
        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {


            VasLeadSourcePriceMapping saveddetails = vasLeadSourcePriceMappingService.createVasPrice(vasLeadSourcePriceMapping);
            if (saveddetails != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "VasLeadSourcePrice Created successfully!!!", saveddetails), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "VasLeadSourcePrice not Created!!!", null), HttpStatus.BAD_REQUEST);

        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/get-all-vas-lead-source-price")
    public ResponseEntity<?> getAllVasLeadSourcePriceMapping(HttpServletRequest request) {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
            List<VasLeadSourcePriceMapping> vasLeadsourcelist = vasLeadSourcePriceMappingService.getAllVasLeadSourcePrice();
            if (vasLeadsourcelist != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "VasLeadSourcePriceList found!!!", vasLeadsourcelist), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "List Not Found!!!", null), HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

/*    @DeleteMapping("/delete-vas-lead-source/{id}")
    public ResponseEntity<?> deleteVasLeadSourcePriceMapping(@PathVariable("id") int id){
        vasLeadSourcePriceMappingService.deactivate(id);


    }*/


}
