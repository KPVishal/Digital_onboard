package com.bijlipay.service;

import com.bijlipay.model.*;
import com.bijlipay.model.dto.request.UserRequest;
import com.bijlipay.model.dto.response.UserRoleMappingResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService  extends UserDetailsService{


    boolean isExist(UserRequest userRequest);

    User save(User user);

   void saveUserRoleMapping(List<UserRoleMapping> userRoleMappingList);

    User getUser(long userId);

    List<User> getActiveList();

    List<UserRoleMappingResponse> convertUserRoleMappingResp(List<User> userList);

    List<User> getInActiveList();

    List<User> findAllUserByRole(Role role1, Region region);

/*    void updateDeviceToken(String deviceToken, User sessionUser);

    User findByMailAndIsActive(String email, boolean isActive);*/

    User update(Long userId, UserRequest userRequest);

   // User get(long id);

    @Override
    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;

    List<UserRoleMapping> findAllRoleByUser(User user);

    User findByEmail(String email);

 List<UserRoleMappingResponse> convertUserRoleMappingResponse(List<User> users);



    //void saveBankOpsUser(List<BankOpsUser> bankOpsUsers);
}
