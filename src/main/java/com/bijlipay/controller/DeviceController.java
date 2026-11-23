package com.bijlipay.controller;

import com.bijlipay.model.Device;
import com.bijlipay.model.User;
import com.bijlipay.model.UserAuthentication;
import com.bijlipay.service.DeviceService;
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
@RequestMapping("/admin/device")
public class DeviceController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(DeviceController.class);

    @Autowired
    private DeviceService deviceService;


    @Autowired
    private UserAuthenticationService userAuthenticateService;

    /*
        create new device type
     */
    @PostMapping("/createDevice")
    public ResponseEntity<?> createDevice(HttpServletRequest request, @RequestBody Device device) {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            logger.info("Create Device API Starts : ", device);

            Device checkDevice = deviceService.isDeviceExists(device);

            if (checkDevice != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.CONFLICT, "Device Already Created"), HttpStatus.CONFLICT);
            }

            Device deviceData = deviceService.createDevice(device);

            if (deviceData != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.CREATED, "Device Created Successfully"), HttpStatus.CREATED);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Device not Created"), HttpStatus.INTERNAL_SERVER_ERROR);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    /*
        get active device list
     */

    @GetMapping({"/deviceList", "/deviceList/{is_active}"})
    public ResponseEntity<?> getActiveDeviceList(HttpServletRequest request, @PathVariable("is_active") Optional<Boolean> is_active) {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {


            List<Device> deviceList = null;

            if (is_active.isPresent()) {
                deviceList = deviceService.listActiveDevices(is_active.get());
            } else {
                deviceList = deviceService.listAllDevices();
            }
            if (deviceList != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", deviceList), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, "device list not found"), HttpStatus.NOT_FOUND);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/updateDevice/{id}")
    public ResponseEntity<?> deviceUpdate(HttpServletRequest request, @PathVariable("id") long id, @RequestBody Device device) {
        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
            logger.debug("Request to update device: {} ", device);

            Device deviceDetail = deviceService.getDevice(id);
            if (deviceDetail == null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, "device not found"), HttpStatus.NOT_FOUND);
            }
            Device updatedDevice = deviceService.updateDevice(id, device);
            if (updatedDevice != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "device successfully updated", device), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "error"), HttpStatus.BAD_REQUEST);

        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

        /* Deactivate Device */
        @DeleteMapping("/deactivateDevice/{id}")
        public ResponseEntity<?> deactivateDevice ( HttpServletRequest request,@PathVariable("id") long id) throws Exception {
            String authtoken = request.getHeader("Authorization");
            logger.info("authtoken==={}", authtoken);
            User curuser = getSessionUser();
            logger.info("Session User {}", curuser);
            UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
            if (authtoken.equals(userauth.getToken())) {
                Device deviceDetail = deviceService.getDevice(id);
                if (deviceDetail == null) {
                    return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, "Device not found"), HttpStatus.NOT_FOUND);
                }
                deviceService.deactivateDevice(id);
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Device deactivated"), HttpStatus.OK);
            } else {

                return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
            }
        }
    }
