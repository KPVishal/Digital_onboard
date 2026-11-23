package com.bijlipay.controller;

import com.bijlipay.model.RegionGroup;
import com.bijlipay.model.User;
import com.bijlipay.model.UserAuthentication;
import com.bijlipay.service.RegionGroupService;
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

@RequestMapping("/admin/region-group")
@RestController
public class RegionGroupController  extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(RegionGroupController.class);

    @Autowired
    private RegionGroupService regionGroupService;

    @Autowired
    private UserAuthenticationService userAuthenticateService;


    @PostMapping("/save")
    public ResponseEntity<?> saveRegionGroup(HttpServletRequest request,@RequestBody RegionGroup regionGroup) {
        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            RegionGroup savedRegion = regionGroupService.createRegionGroup(regionGroup);
            if (savedRegion != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Region group created successfully", savedRegion), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "Region Group not created", null), HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/get-all-list")
    public ResponseEntity<?> getAllRegionGroup(HttpServletRequest request) {
        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            List<RegionGroup> regionList = regionGroupService.getAll();
            if (regionList.isEmpty()) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "Region group List is empty", null), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Region Group List found!!!", regionList), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/get-region-group/{id}")
    public ResponseEntity<?> getRegionGroupById(HttpServletRequest request,@PathVariable("id") int id) {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            RegionGroup selectedRegion = regionGroupService.getById(id);
            if (selectedRegion != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Region Group found", selectedRegion), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "Region group not found", null), HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/delete-region-group/{id}")
    public ResponseEntity<?> deleteRegionGroupById(HttpServletRequest request,@PathVariable("id") int id) {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            RegionGroup regionGroup = regionGroupService.delete(id);
            if (regionGroup != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Region Group deleted successfully!!!", regionGroup), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "Region Group not deleted!!!", null), HttpStatus.BAD_REQUEST);

        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }


}
