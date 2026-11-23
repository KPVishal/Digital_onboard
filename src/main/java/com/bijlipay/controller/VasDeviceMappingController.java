package com.bijlipay.controller;

import com.bijlipay.model.Device;
import com.bijlipay.model.User;
import com.bijlipay.model.UserAuthentication;
import com.bijlipay.model.VasDeviceMapping;
import com.bijlipay.service.UserAuthenticationService;
import com.bijlipay.service.VasDeviceMappingService;
import com.bijlipay.util.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/admin/vasDevice")
public class VasDeviceMappingController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(VasDeviceMappingController.class);

    @Autowired
    VasDeviceMappingService vasDeviceMappingService;


    @Autowired
    private UserAuthenticationService userAuthenticateService;


    @PostMapping("/createVasDeviceMapping")
    public ResponseEntity<?> createVasDeviceMapping(HttpServletRequest request, @Valid @RequestBody VasDeviceMapping vasDeviceMappingReq) throws URISyntaxException {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            logger.info("Creating vas mapping lists :----------: {}", vasDeviceMappingReq);
            VasDeviceMapping vasDeviceMapping = vasDeviceMappingService.createVasDeviceMapping(vasDeviceMappingReq);
            if (vasDeviceMapping != null)
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "vas device successfully created"), HttpStatus.OK);
            else
                return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "Creating Vas device Failed !"), HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/getVasDeviceMapping/{device}")
    public ResponseEntity<?> getVasInstanceMapping(HttpServletRequest request, @PathVariable("device") Device device) throws URISyntaxException {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            if (device != null) {
                logger.info("Getting vas mapping based on Device : ------- : {} ", device);
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", vasDeviceMappingService.getVasMappingByDevice(device)), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "The requested Vas Mapping Not Found !"), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }

    }


    @PutMapping("/updateVasDeviceMapping")
    public ResponseEntity<?> updateVasDeviceMapping(HttpServletRequest request, @Valid @RequestBody VasDeviceMapping vasDeviceMappingReq) throws URISyntaxException {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            logger.info("Updating vas mapping lists");
            VasDeviceMapping vasDeviceMapping = vasDeviceMappingService.updateVasDeviceMapping(vasDeviceMappingReq);
            if (vasDeviceMapping != null)
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success"), HttpStatus.OK);
            else
                return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "Updating Vas Failed !"), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

}
