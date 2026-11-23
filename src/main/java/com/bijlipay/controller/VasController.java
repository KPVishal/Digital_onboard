package com.bijlipay.controller;

import com.bijlipay.model.User;
import com.bijlipay.model.UserAuthentication;
import com.bijlipay.model.Vas;
import com.bijlipay.service.UserAuthenticationService;
import com.bijlipay.service.VasService;
import com.bijlipay.util.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/admin/vas")
public class VasController extends BaseController {

    @Autowired
    private VasService vasService;

    @Autowired
    private UserAuthenticationService userAuthenticateService;

    private static final Logger logger = LoggerFactory.getLogger(VasController.class);

    @GetMapping("/test")
    public ResponseEntity<?> getTest() {
        logger.info("testing api===============");
        String test = "Testing GET api";
        return ResponseEntity.ok(test);
    }

    @PostMapping("/create-vas")
    public ResponseEntity<?> createVas(HttpServletRequest request, @RequestBody Vas vas) throws Exception {
        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            logger.info("creating vas==============");
            Vas newvas = vasService.createVas(vas);
            if (newvas != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Vas Created successfully!!!", newvas), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "Vas not created", null), HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/update-vas")
    public ResponseEntity<?> updateVas(HttpServletRequest request, @RequestBody Vas vasRequest) {
        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            logger.info("Update api==============================");
            Vas updatedVas = vasService.updateVas(vasRequest);
            if (updatedVas != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", updatedVas), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "Vas not updated!!", null), HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/get-vas/{vasId}")
    public ResponseEntity<?> getVas(HttpServletRequest request, @PathVariable("vasId") int vasId) throws URISyntaxException {
        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
            logger.info("Get vas mapping based on vasId : --------- :");
            Vas vas = vasService.getVasById(vasId);
            if (vas != null) return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", vas), HttpStatus.OK);
            else
                return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "The requested Vas Not Found !", null), HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }


    @GetMapping("/get-all-vas-lists")
    public ResponseEntity<?> getAllVasList(HttpServletRequest request) {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
            logger.info("Get all vas list============");
            List<Vas> vaslist = vasService.getAllVas();
            if (vaslist.isEmpty()) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "VasList is Empty", null), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", vaslist), HttpStatus.OK);

        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }


}
