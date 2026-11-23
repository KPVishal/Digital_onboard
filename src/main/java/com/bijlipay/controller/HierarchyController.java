package com.bijlipay.controller;

import com.bijlipay.model.Hierarchy;
import com.bijlipay.model.LeadStatus;
import com.bijlipay.model.User;
import com.bijlipay.model.UserAuthentication;
import com.bijlipay.service.HierarchyService;
import com.bijlipay.service.UserAuthenticationService;
import com.bijlipay.util.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/hierarchy")
public class HierarchyController extends BaseController{

    private static final Logger log = LoggerFactory.getLogger(HierarchyController.class);

    @Autowired
    private HierarchyService hierarchyService;

    @Autowired
    private UserAuthenticationService userAuthenticateService;

    /*
    Create Hierarchy
    */
    @PostMapping("/createHierarchy")
    public ResponseEntity<?> saveHierarchy(HttpServletRequest request,@Valid @RequestBody Hierarchy hierarchy, BindingResult bindingResult) throws URISyntaxException {

        String authtoken = request.getHeader("Authorization");
        log.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        log.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            Hierarchy hierarchyDetail = hierarchyService.isHierarchyExists(hierarchy);
            if (hierarchyDetail != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.CONFLICT, "Hierarchy Already Exists"), HttpStatus.CONFLICT);
            }
            Hierarchy createdHierarchy = hierarchyService.save(hierarchy);

            if (bindingResult.hasErrors()) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "error", bindingResult), HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", createdHierarchy), HttpStatus.OK);

        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }


    /*
    Retrieve All Hierarchies
    */
    @GetMapping({"/listHierarchy","/listHierarchy/{isActive}"})
    public ResponseEntity<?> getAllHierarchy(HttpServletRequest request,@PathVariable("isActive") Optional<Boolean> isActive) {

        String authtoken = request.getHeader("Authorization");
        log.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        log.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
            List<Hierarchy> hierarchyList = null;

            if (isActive.isPresent()) {
                hierarchyList = hierarchyService.listActiveHierarchy(isActive.get());
            } else {
                hierarchyList = hierarchyService.listAllHierarchy();
            }
            if (hierarchyList != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", hierarchyList), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Hierarchy list not found"), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    /*
    Get Hierarchy by Id
    */
    @GetMapping({"/getHierarchy/{id}"})
    public ResponseEntity<?> getHierarchy(HttpServletRequest request,@PathVariable("id") int id) {

        String authtoken = request.getHeader("Authorization");
        log.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        log.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
            Hierarchy hierarchy = hierarchyService.fetchHierarchyById(id);

            if (hierarchy != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", hierarchy), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "leadStatus not found"), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    /*
    Update Hierarchy
    */
    @PutMapping("/updateHierarchy/{id}")
    public ResponseEntity<?> updateHierarchy(HttpServletRequest request,@PathVariable("id") int id, @RequestBody Hierarchy hierarchy, BindingResult bindingResult) throws URISyntaxException {
        String authtoken = request.getHeader("Authorization");
        log.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        log.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
            Hierarchy hierarchy1 = hierarchyService.fetchHierarchyById(id);
            if (hierarchy1 == null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Hierarchy not found to update"), HttpStatus.OK);
            } else {
                log.debug("REST request to update hierarchy: {} ", hierarchy);
                Hierarchy result = hierarchyService.update(id, hierarchy);

                if (bindingResult.hasErrors() || result == null) {
                    return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "error"), HttpStatus.BAD_REQUEST);
                }
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", result), HttpStatus.OK);
            }
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    /*
    Deactivate Hierarchy
    */
    @DeleteMapping("/deactivateHierarchy/{id}")
    public ResponseEntity<?> deleteHierarchy(HttpServletRequest request,@PathVariable("id") int id) throws URISyntaxException {
        String authtoken = request.getHeader("Authorization");
        log.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        log.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            log.debug("REST request to delete hierarchy");
            Hierarchy result = hierarchyService.fetchHierarchyById(id);
            if (result != null) {
                hierarchyService.deactivateHierarchy(id);
            } else {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Hierarchy not found"), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "deactivated successfully"), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }


}
