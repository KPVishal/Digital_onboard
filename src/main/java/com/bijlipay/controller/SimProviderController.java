package com.bijlipay.controller;

import com.bijlipay.model.SimProvider;
import com.bijlipay.model.User;
import com.bijlipay.model.UserAuthentication;
import com.bijlipay.service.SimProviderService;
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
@RequestMapping("/admin/sim")
public class SimProviderController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(SimProviderController.class);

    @Autowired
    private SimProviderService simProviderService;

    @Autowired
    private UserAuthenticationService userAuthenticateService;

    @RequestMapping("/create-sim")
    public ResponseEntity<?> createSim(HttpServletRequest request, @RequestBody SimProvider simProvider) {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            SimProvider simProvider1 = simProviderService.saveSim(simProvider);
            if (simProvider1 != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Sim Created successfully!!", simProvider1), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "Sim not created!!", null), HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/get-all-sim")
    public ResponseEntity<?> getAllSim(HttpServletRequest request) {
        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {


            List<SimProvider> simlist = simProviderService.getAll();
            if (simlist.isEmpty()) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "List is Empty!!! SimProvider Not found", null), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "SimProvider Found", simlist), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/deactivate-sim/{id}")
    public ResponseEntity<?> deleteSim(HttpServletRequest request, @PathVariable("id") long simId) {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
            SimProvider deactivatedSim = simProviderService.deactivate(simId);
            if (deactivatedSim != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Sim Deactivated Successfully!!", deactivatedSim), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "Sim Not Deactivated!!", null), HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }


    @PutMapping("/update-sim-name/{id}")
    public ResponseEntity<?> updateSim(HttpServletRequest request, @PathVariable("id") long simId, @RequestBody SimProvider simProvider) {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            SimProvider updateSim = simProviderService.getSim(simId);
            SimProvider updatedSim = simProviderService.updateSim(updateSim, simProvider);
            if (updatedSim != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Sim updated Successfully!!", updatedSim), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "Sim not updated", null), HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

}
