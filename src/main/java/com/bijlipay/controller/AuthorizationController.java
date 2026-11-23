/*
package com.bijlipay.controller;


import com.bijlipay.config.Constants;
import com.bijlipay.model.User;
import com.bijlipay.model.UserAnalytics;
import com.bijlipay.model.UserAuthentication;
import com.bijlipay.model.dto.request.LoginRequest;
import com.bijlipay.repository.*;

import com.bijlipay.security.hash.JwtUtil;
import com.bijlipay.service.*;
import com.bijlipay.util.ApiResponse;
import com.bijlipay.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;

@RequestMapping("/admin/authorization")
@RestController
public class AuthorizationController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private HierarchyService hierarchyService;

    @Autowired
    private HierarchyService mHierarchyService;

    @Autowired
    private LeadSourceService leadSourceService;

  */
/*  @Autowired
    private ServiceRequestClientsService serviceReqClientsService;*//*


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
    private UserAttemptsService userAttemptsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

 */
/*   @Autowired
    private AuthenticationManager authenticationManager;*//*



    */
/*@Autowired
    private UserAuthenticationService userAuthenticateService;*//*


*/
/*    @Autowired
    private UserAnalyticsService userAnalyticsService;*//*


   @Autowired
    private JwtUtil jwtGenerator;



    private Logger logger= LoggerFactory.getLogger(AuthorizationController.class);


  */
/*  @PostMapping("/login")
    public ResponseEntity<?> doLogin(@RequestBody LoginRequest loginRequest) {

        String token = null;
        User userData = userService.findByEmail(loginRequest.getEmail());
        if (userAttemptsService.getlockedAccount(loginRequest.getEmail())) {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.LOCKED, "Account Locked"), HttpStatus.LOCKED);
        }
         if (userData != null ) {
        if (!userData.isActive())
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "User not found!"), HttpStatus.BAD_REQUEST);

            Date loginAt = DateUtil.currentDate();
        //    final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
            UserAuthentication username = userAuthenticateService.getUser(userData.getId());
            userAttemptsService.resetFailAttempts(loginRequest.getEmail());

           // SecurityContextHolder.getContext().setAuthentication(authentication);
          //
             //  final String jwtToken = jwtGenerator.generateToken(authentication);

             token = jwtGenerator.generateToken(userData.getName());

             //token = jwtGenerator.generate(userData.getName());

            HashMap<String, Object> result = new HashMap<>();
            result.put("jwt", token);
            UserAnalytics whenLogsIn = new UserAnalytics(Constants.AnalyticsInterface.USER_LOGGED_IN, userData);
            userAnalyticsService.add(whenLogsIn);
            if (username == null) {
                userAuthenticateService.insertdata(token, userData);
            } else {
                username.getId();
                userAuthenticateService.update(token, username.getId());
            }
            return ResponseEntity.ok(result);


        }

            //throw new UserNotFoundException("Incorrect email-ID/plainText not found!");

        userAttemptsService.updateFailAttempts(loginRequest.getEmail());
        return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "Incorrect email-ID/plainText"), HttpStatus.UNAUTHORIZED);
        }*//*



}
*/
