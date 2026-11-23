package com.bijlipay.controller;

import com.bijlipay.model.Region;
import com.bijlipay.model.User;
import com.bijlipay.model.UserAuthentication;
import com.bijlipay.service.RegionService;
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

@RestController
@RequestMapping("/admin/region")
public class RegionController extends BaseController {


    private static final Logger logger = LoggerFactory.getLogger(RegionGroupController.class);

    @Autowired
    private RegionService regionService;


    @Autowired
    private UserAuthenticationService userAuthenticateService;

    @PostMapping
    public ResponseEntity<?> saveRegion(HttpServletRequest request,@Valid @RequestBody Region region, BindingResult bindingResult) throws URISyntaxException {
        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
            logger.debug("REST request to saveWithPassword Region: {}", region);
            boolean flag = regionService.isExistsRegion(region.getRegionAreaName());
            if (flag) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.CONFLICT, "Region Already exist"), HttpStatus.CONFLICT);
            }
            Region result = regionService.save(region);

            if (bindingResult.hasErrors()) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "error", bindingResult), HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", result), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    //Get All Regions
    @GetMapping
    public ResponseEntity<?> getRegion(HttpServletRequest request) throws URISyntaxException {
        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
            List<Region> result = regionService.getList();
            logger.debug("REST request to get all Regions: {}", result);
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", result), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRegionById(HttpServletRequest request,@PathVariable("id") Integer id) throws URISyntaxException {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            Region region = regionService.getRegionById(id);
            if (region == null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "ID NOT FOUND"), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", region), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    //Update Region
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRegion(HttpServletRequest request,@PathVariable("id") int id, @Valid @RequestBody Region region, BindingResult bindingResult) throws URISyntaxException {
        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
            Region result = regionService.update(id, region);
            if (bindingResult.hasErrors() || result == null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "error"), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", result), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    //Delete Region

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRegion(HttpServletRequest request,@PathVariable("id") int id) throws URISyntaxException {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            Region deletedRegion = regionService.delete(id);
            if (deletedRegion != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Region deleted successfully!!!"), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "Region not deleted !!!"), HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }
}

