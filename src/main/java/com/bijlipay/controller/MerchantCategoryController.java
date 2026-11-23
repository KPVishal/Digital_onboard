package com.bijlipay.controller;

import com.bijlipay.model.MerchantCategory;
import com.bijlipay.model.User;
import com.bijlipay.model.UserAuthentication;
import com.bijlipay.model.dto.MerchantCategoryAndCodeDto;
import com.bijlipay.repository.MerchantCategoryRepository;
import com.bijlipay.service.MerchantCategoryService;
import com.bijlipay.service.UserAuthenticationService;
import com.bijlipay.util.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import static com.bijlipay.config.Constants.IS_ACTIVE_TRUE;

@RestController
@RequestMapping("/admin/merchantCategory")
public class MerchantCategoryController extends BaseController{


    private static final Logger logger = LoggerFactory.getLogger(MDRAndRentalChargeController.class);
    @Autowired
    private MerchantCategoryService merchantCategoryService;
    @Autowired
    private UserAuthenticationService userAuthenticateService;


    /***
     * create merchant category
     * @param merchantCategoryAndCodeDto
     * @return
     */
    @PostMapping("/create-merchant-category")
    public ResponseEntity<?> createMerchantCategory(HttpServletRequest request,@RequestBody MerchantCategoryAndCodeDto merchantCategoryAndCodeDto) {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            boolean flag = merchantCategoryService.existsMerchantCategory(merchantCategoryAndCodeDto);
            if (flag) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.CONFLICT, "Merchant Category Already Exists"), HttpStatus.CONFLICT);
            }
            MerchantCategory result = merchantCategoryService.createMerchantCategory(merchantCategoryAndCodeDto);
            if (result == null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "Merchant Category not created"), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", result), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    /***
     * get all merchant category list
     * @param isActive
     * @return
     */
    @GetMapping(value = {"/merchant-category","/merchant-category/{active}"})
    public ResponseEntity<?> merchantCategoryList(HttpServletRequest request,@PathVariable("active")Optional<Boolean> isActive) {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            List<MerchantCategory> result = isActive.isPresent() ? merchantCategoryService.getAllMerchantCategory(isActive.get()) : merchantCategoryService.getAllMerchantCategory(IS_ACTIVE_TRUE);
            if (result.isEmpty()) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, "Merchant category List not Found"), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", result), HttpStatus.NOT_FOUND);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    /***
     * update Merchant category based on ID
     */
    @PutMapping(value ={"/merchant-category/{id}","/merchant-category/{id}/{active}"})
    public ResponseEntity<?> updateMerchantCategory(HttpServletRequest request,@Valid @RequestBody MerchantCategoryAndCodeDto merchantCategoryAndCodeDto, @ModelAttribute("id") MerchantCategory idMerchant, @PathVariable("active") Optional<Boolean> isActive, BindingResult bindingResult)throws URISyntaxException {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            if (bindingResult.hasErrors() || idMerchant == null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "error", bindingResult), HttpStatus.BAD_REQUEST);
            }
            Pair<Boolean, MerchantCategory> result = isActive.isPresent() ? merchantCategoryService.update(merchantCategoryAndCodeDto, idMerchant.getId(), isActive.get()) : merchantCategoryService.update(merchantCategoryAndCodeDto, idMerchant.getId(), null);
            if (result.getFirst() == false) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "Merchant category not updated"), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", result.getSecond()), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    /***
     * deActivate the merchant category
     * @param merchantCategory
     * @return
     */
    @DeleteMapping("/merchant-category/{id}")
    public ResponseEntity<?> deleteMerchantCategory(HttpServletRequest request,@ModelAttribute("id") MerchantCategory merchantCategory) {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            merchantCategoryService.deActivate(merchantCategory.getId());
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success"), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }
}
