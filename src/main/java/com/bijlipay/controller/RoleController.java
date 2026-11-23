package com.bijlipay.controller;

import com.bijlipay.model.*;
import com.bijlipay.model.dto.request.RoleRequest;
import com.bijlipay.service.HierarchyService;
import com.bijlipay.service.RoleService;
import com.bijlipay.service.UserAuthenticationService;
import com.bijlipay.util.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/role")
public class RoleController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);


    @Autowired
    private RoleService roleService;

    @Autowired
    private HierarchyService hierarchyService;

    @Autowired
    private UserAuthenticationService userAuthenticateService;


    @PostMapping("/createRole")
    public ResponseEntity<?> createRole(HttpServletRequest request, @Valid @RequestBody RoleRequest roleRequest, BindingResult bindingResult) {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            Role role = roleService.getRoleByName(roleRequest.getRoleName());
            Hierarchy hierarchy = hierarchyService.fetchHierarchyById(roleRequest.getHierarchyId());
            Role roleDetail = null;
            if (role != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.CONFLICT, "Role already existed"), HttpStatus.CONFLICT);
            } else if (hierarchy == null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Hierarchy not found"), HttpStatus.OK);
            } else {
                roleDetail = new Role();
                roleDetail.setRole(roleRequest.getRoleName());
                roleDetail.setHierarchy(hierarchy);
                roleDetail.setRoleColor(roleRequest.getRoleColor());
                roleService.save(roleDetail);
            }
            if (bindingResult.hasErrors()) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "failure", bindingResult), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", roleDetail), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping({"/roleList", "/roleList/{is_active}"})
    public ResponseEntity<?> getActiveRoleList(HttpServletRequest request, @PathVariable("is_active") Optional<Boolean> is_active) {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            List<Role> roleList = null;

            if (is_active.isPresent()) {
                roleList = roleService.listActiveRoles(is_active.get());
            } else {
                roleList = roleService.listAllRoles();
            }
            if (roleList.size() > 0) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", roleList), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "role list not found"), HttpStatus.OK);
            }
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    /*---Get Roles by hierarchy---*/
    @GetMapping("/rolesByHierarchy/{id}")
    public ResponseEntity<?> getRolesByHierarchy(HttpServletRequest request, @PathVariable("id") int id) {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            List<Role> roles = roleService.findRoleByHierarchy(id);
            if (roles != null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", roles), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "Error"), HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }


    @PostMapping("/updateRole/{id}")
    public ResponseEntity<?> updateRole(HttpServletRequest request, @PathVariable("id") int id, @RequestBody RoleRequest role, BindingResult bindingResult) throws URISyntaxException {
        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {
            Role result = roleService.getRoleById(id);

            if (result == null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, "Role not found to update"), HttpStatus.NOT_FOUND);
            } else {
                result.setRole(role.getRoleName());
                result.setRoleColor(role.getRoleColor());
                roleService.save(result);
                if (bindingResult.hasErrors()) {
                    return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, "error"), HttpStatus.BAD_REQUEST);
                }
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "success", result), HttpStatus.OK);
            }

        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }

    /* Deactivate Role */
    @DeleteMapping("/deactivateRole/{id}")
    public ResponseEntity<?> deactivateRole(HttpServletRequest request, @PathVariable("id") int id) throws Exception {

        String authtoken = request.getHeader("Authorization");
        logger.info("authtoken==={}", authtoken);
        User curuser = getSessionUser();
        logger.info("Session User {}", curuser);
        UserAuthentication userauth = userAuthenticateService.getToken(curuser.getId());
        if (authtoken.equals(userauth.getToken())) {

            Role roleDetail = roleService.getRoleById(id);
            if (roleDetail == null) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Role not found"), HttpStatus.OK);
            }
            roleService.deactivateRole(id);
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "Role deactivated"), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED, "UnAuthorized!!!", null), HttpStatus.UNAUTHORIZED);
        }
    }
}
