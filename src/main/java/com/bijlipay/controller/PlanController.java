package com.bijlipay.controller;

import com.bijlipay.model.Plan;
import com.bijlipay.model.User;
import com.bijlipay.model.UserAuthentication;
import com.bijlipay.service.PlanService;
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
@RequestMapping("/admin/plan")
public class PlanController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(PlanController.class);
    @Autowired
    private UserAuthenticationService userAuthenticateService;


    @Autowired
    private PlanService planService;

    @GetMapping("/test")
    public ResponseEntity<?> test() throws Exception {
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success"), HttpStatus.OK);
    }

    /* To create a plan for Rental charges */
    @PostMapping("/createPlan")
    public ResponseEntity<?> createPlan(HttpServletRequest request, @RequestBody Plan plan) throws Exception {
        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            logger.info("API Start -- : {}", plan);
            Plan checkPlan = planService.isPlanExists(plan);
            if (checkPlan != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.CONFLICT, "Plan already created"), HttpStatus.CONFLICT);
            }
            Plan planData = planService.createPlan(plan);
            if (planData != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", planData), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, "plan not created"), HttpStatus.INTERNAL_SERVER_ERROR);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    /* Get list of Plan details
     *  1(Active) - active plan details
     */
    @GetMapping({"/planList", "/planList/{active}"})
    public ResponseEntity<?> getPlanData(HttpServletRequest request,@PathVariable("active") Optional<Boolean> active) throws Exception {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            List<Plan> planDetails = null;
            if (active.isPresent()) {
                planDetails = planService.getActivePlan(active.get());
            } else {
                planDetails = planService.listOfPlan();
            }
            if (planDetails != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", planDetails), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, "plan list not found"), HttpStatus.NOT_FOUND);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    /* Deactivate the plan */
    @DeleteMapping("/deletePlan/{id}")
    public ResponseEntity<?> deletePlan(HttpServletRequest request,@PathVariable("id") long id) throws Exception {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
            Plan plan = planService.checkPlan(id);
            if (plan != null) {
                planService.deActivePlan(id);
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "plan deactivated"), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, "plan not found"), HttpStatus.NOT_FOUND);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }
}
