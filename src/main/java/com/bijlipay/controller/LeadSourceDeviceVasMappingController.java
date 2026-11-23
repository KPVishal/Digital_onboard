package com.bijlipay.controller;

import com.bijlipay.model.*;
import com.bijlipay.model.dto.LeadSourceDeviceVasMappingDto;
import com.bijlipay.service.LeadSourceDeviceVasMappingService;
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
@RequestMapping("/admin/leadSourceDeviceVas")
public class LeadSourceDeviceVasMappingController extends BaseController {
    @Autowired
    private LeadSourceDeviceVasMappingService leadSourceDeviceVasMappingService;


    @Autowired
    private UserAuthenticationService userAuthenticateService;


    private static final Logger log = LoggerFactory.getLogger(LeadSourceDeviceVasMappingController.class);

    @GetMapping("/{leadsource}/{device}")
    public ResponseEntity<?> getAllLeadSourceDeviceList(HttpServletRequest request, @PathVariable("leadsource") LeadSource leadSource, @PathVariable("device") Device device) {
        String authtoken = request.getHeader("Authorization");
        log.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        log.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
            if (leadSource != null && device != null) {
                LeadSourceDeviceVasMappingDto result = leadSourceDeviceVasMappingService.findLeadSourceDeviceVasList(leadSource, device);
                if (result != null) {
                    return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", result), HttpStatus.OK);
                }
                return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, "No Data available for this leadSource And Device"), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "List not Found"), HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createLeadSourceDeviceVasMapping(HttpServletRequest request, @RequestBody LeadSourceDeviceVasMappingDto mappingDto) {
        String authtoken = request.getHeader("Authorization");
        log.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        log.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
            List<Vas> existsVasList = leadSourceDeviceVasMappingService.getAllVasListBasedOnLeadSourceAndDevice(mappingDto.getLeadSourceDeviceVasMapping().getLeadSource(), mappingDto.getLeadSourceDeviceVasMapping().getDevice());
            if (!existsVasList.isEmpty()) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.CONFLICT, "Already data exists"), HttpStatus.CONFLICT);
            }
            List<LeadSourceDeviceVasMapping> result = leadSourceDeviceVasMappingService.saveLeadSourceDeviceVasMappingList(mappingDto);
            if (result.size() > 0) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.CREATED, "LeadSourceDeviceVasMapping successfully created", result), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "error"), HttpStatus.BAD_REQUEST);
            }
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }

    }

    @PutMapping("/update")
    public ResponseEntity<?> updateLeadSourceDeviceVasMapping(HttpServletRequest request,@RequestBody LeadSourceDeviceVasMappingDto mappingDto) {
        String authtoken = request.getHeader("Authorization");
        log.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        log.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
            List<LeadSourceDeviceVasMapping> result = null;
            if (mappingDto != null) {
                result = leadSourceDeviceVasMappingService.getAllByLeadSourceAndDevice(mappingDto.getLeadSourceDeviceVasMapping().getLeadSource(), mappingDto.getLeadSourceDeviceVasMapping().getDevice());
                leadSourceDeviceVasMappingService.deleteExists(result);
                try {
                    result = leadSourceDeviceVasMappingService.saveLeadSourceDeviceVasMappingList(mappingDto);
                    if (result.size() > 0) {
                        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "successfully updated", result), HttpStatus.OK);
                    }
                    return new ResponseEntity<>(new ApiResponse(HttpStatus.CONFLICT, "Not updated"), HttpStatus.CONFLICT);
                } catch (Exception e) {
                    e.printStackTrace();
                    return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "error"), HttpStatus.BAD_REQUEST);
                }
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "Invalid Request Body"), HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/getAllList")
    public ResponseEntity<?> getAllList(HttpServletRequest request) {

        String authtoken = request.getHeader("Authorization");
        log.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        log.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
            List<LeadSourceDeviceVasMappingDto> result = leadSourceDeviceVasMappingService.getAllList();
            if (result.size() > 0) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Success", result), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, "No Data Found"), HttpStatus.NOT_FOUND);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }
}
