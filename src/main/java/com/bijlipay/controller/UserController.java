package com.bijlipay.controller;

import com.bijlipay.config.Constants;
import com.bijlipay.model.*;
import com.bijlipay.model.dto.request.UserRequest;
import com.bijlipay.model.dto.response.UserRoleMappingResponse;
import com.bijlipay.repository.*;
import com.bijlipay.service.*;
import com.bijlipay.util.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.bijlipay.config.Constants.BANK_OPS;
import static com.bijlipay.config.Constants.DUMMY_PASSWORD;

@RequestMapping("/admin/user")
@RestController
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private HierarchyService hierarchyService;

    @Autowired
    private UserRoleMappingRepository userRoleMappingRepository;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private HierarchyRepository hierarchyRepository;

    @Autowired
    private RegionGroupRepository regionGroupRepository;

    @Autowired
    private UserAuthenticationService userAuthenticateService;


    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Transactional
    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody UserRequest userRequest) {
        User user = new User();
        user.setPassword(userRequest.getPassword());
        user.setUserAddress(userRequest.getUserAddress());
        user.setState(userRequest.getState());
        user.setPincode(userRequest.getPincode());
        user.setCity(userRequest.getCity());
        user.setName(userRequest.getName());
        user.setEmployeeID(userRequest.getEmployeeID());
        user.setContactNumber(userRequest.getContactNumber());
        user.setAlternateContactNumber(userRequest.getAlternateContactNumber());
        user.setEmail(userRequest.getEmail());
        user.setDeviceToken(userRequest.getDeviceToken());
        user.setActive(true);

        UserRoleMapping userRoleMapping = new UserRoleMapping();

        Role role = roleRepository.findById(userRequest.getRole());
        logger.info("Role   {}", role);
        userRoleMapping.setRole(role);
        Region region = regionRepository.findRegionById(userRequest.getRegion());
        logger.info("Region   {}", region);
        if (region != null) {
            userRoleMapping.setRegion(region);

        }
        Hierarchy hierarchy = hierarchyRepository.fetchHierarchyById(userRequest.getHierarchy());
        logger.info("Hierarchy     {} ", hierarchy);
        userRoleMapping.setHierarchy(hierarchy);
        RegionGroup regionGroup = regionGroupRepository.findRegionById(userRequest.getRegionGroup());
        if (regionGroup != null) {
            logger.info("Region Group    {}", regionGroup);
            userRoleMapping.setRegionGroup(regionGroup);
        }

        userRoleMapping.setUser(user);
        userRepository.save(user);
        userRoleMappingRepository.save(userRoleMapping);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Success", user), HttpStatus.OK);
    }


    /*----------Getting the user by ID----------------*/

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getUserById(HttpServletRequest request,@PathVariable("id") long userId) {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
            User user = userService.getUser(userId);
            if (user != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "User is available", user), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "User Not found!!", null), HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    /*---------Getting all active users------------*/

    @GetMapping("/active")
    public ResponseEntity<?> getActiveUsers(HttpServletRequest request) {
        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
            List<User> userList = userService.getActiveList();
            if (userList.isEmpty()) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "User list not found!!!", null), HttpStatus.BAD_REQUEST);
            }
            List<UserRoleMappingResponse> responses = userService.convertUserRoleMappingResp(userList);
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Active users are Available", responses), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null),HttpStatus.UNAUTHORIZED);
        }
    }

    /*-----------Getting all inactive users----------*/

    @GetMapping("/in-active")
    public ResponseEntity<?> getInActiveUsers(HttpServletRequest request) {
        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
            List<User> inActiveList = userService.getInActiveList();
            if (inActiveList.isEmpty()) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "User lits not found", null), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Inactive List found!!", inActiveList), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    /* Getting all the users with respect to role and region */

    @GetMapping("/get/{rid}/{reg-id}")
    public ResponseEntity<?> getUsersListByRoleAndRegion(HttpServletRequest request, @PathVariable("rid") Role role, @PathVariable("reg-id") Region region) {
        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
            Role role1 = roleService.getByPredecessor(role);
            List<User> users = userService.findAllUserByRole(role1, region);
            List<UserRoleMappingResponse> responses = userService.convertUserRoleMappingResp(users);
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Success", responses), HttpStatus.OK);
        } else {

        return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
    }
    }

   /* @GetMapping("/profile")
    public ResponseEntity getUserProfile() {

        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        List<UserRoleMappingResponse> userRoleMappingResponses = userService.convertUserRoleMappingResponse(Arrays.asList(curuser));
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success",userRoleMappingResponses.get(0)), HttpStatus.OK);
    }*/

    @GetMapping("/profile")
    public ResponseEntity getUserProfile(HttpServletRequest request) {
       String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
       if (authtoken.equals(userauth.getToken())) {
            List<UserRoleMappingResponse> userRoleMappingResponses = userService.convertUserRoleMappingResponse(Arrays.asList(curuser));
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", userRoleMappingResponses.get(0)), HttpStatus.OK);
       } else {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/logout/{id}")
    public ResponseEntity<?> logout(@PathVariable("id") long userId) {
        logger.info("user=========={}", userId);
        userAuthenticateService.delete(userId);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Logout successfully", null), HttpStatus.OK);
    }
}
