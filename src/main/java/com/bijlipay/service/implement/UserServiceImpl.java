package com.bijlipay.service.implement;

import com.bijlipay.model.*;
import com.bijlipay.model.dto.request.UserRequest;
import com.bijlipay.model.dto.response.UserRoleMappingResponse;
import com.bijlipay.repository.BankOpsUserRepository;
import com.bijlipay.repository.RoleRepository;
import com.bijlipay.repository.UserRepository;
import com.bijlipay.repository.UserRoleMappingRepository;
//import com.bijlipay.service.ServiceRequestClientsService;
import com.bijlipay.service.ServiceRequestClientsService;
import com.bijlipay.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.bijlipay.config.Constants.*;

@Service(value="userService")
public class UserServiceImpl implements UserService , UserDetailsService {

    private Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository rolerepository;

    @Autowired
    private UserRoleMappingRepository userRoleMappingRepository;

    @Autowired
    private BankOpsUserRepository bankOpsUserRepository;

   @Autowired
    private ServiceRequestClientsService serviceReqClientsService;

    @Override
    public boolean isExist(UserRequest userRequest) {
        return userRepository.countByEmailOrEmployeeID(userRequest.getEmail(),userRequest.getEmployeeID()) >0;
    }

    @Override
    public User save(User user) {
        user.setActive(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        return userRepository.save(user);
    }

    @Override
    public void saveUserRoleMapping(List<UserRoleMapping> userRoleMappingList) {
           userRoleMappingRepository.saveAll(userRoleMappingList);
    }

    @Override
    public User getUser(long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }

    @Override
    public List<User> getActiveList() {
        return userRepository.getActiveList();
    }

    @Override
    public List<UserRoleMappingResponse> convertUserRoleMappingResp(List<User> userList) {
          List<UserRoleMappingResponse> result=new ArrayList<>();
          userList.forEach(l->{
         List<UserRoleMapping> userRoles=this.findAllRolesByUser(l);
         UserRoleMappingResponse urole=new UserRoleMappingResponse();
         urole.setUserId(l.getId());
         urole.setUser(l);
         urole.setRegion(userRoles.size()>0?userRoles.get(0).getRegion():null);
         List<Role> roles=new ArrayList<>();
        userRoles.stream().forEach(role->{
         role.getRole().setPredecessor(role.getReference());
         roles.add(role.getRole());
           if (role.getRole().getHierarchyRoleLevel().equals(ROLE_HIERARCHY_BANK_OPS)){
               List<Long> bankOpsUser= bankOpsUserRepository.findByUser(l);
               urole.setBankOpsUser(bankOpsUser);
           }

        });
        urole.setRoles(roles);
        result.add(urole);
          });

        return result;
    }

    @Override
    public List<User> getInActiveList() {
        return userRepository.getInActiveList();
    }

    @Override
    public List<User> findAllUserByRole(Role role1, Region region) {
        if (role1.getRoleLevel()==HIERARCHY_BIJLIPAY_LEVEL|| role1.getRoleLevel()==HIERARCHY_NATIONAL_HEAD_LEVEL){
              userRoleMappingRepository.findAllByRole(role1);

        }
        return null;
    }

   /* @Override
    public void updateDeviceToken(String deviceToken, User sessionUser) {
        userRepository.updateDeviceToken(deviceToken,sessionUser.getId());
    }*/

   /* @Override
    public User findByMailAndIsActive(String email, boolean isActive) {
        return userRepository.findByMailAndActive(email,isActive);
    }*/

    @Override
    public User update(Long userId, UserRequest userRequest) {
        Optional<User> user= userRepository.findById(userId);
        if (user.isPresent()){
         user.get().setName(userRequest.getName());
         user.get().setEmail(userRequest.getEmail());
         user.get().setContactNumber(userRequest.getContactNumber());
         user.get().setCity(userRequest.getCity());
         user.get().setAlternateContactNumber(userRequest.getAlternateContactNumber());
         user.get().setEmployeeID(userRequest.getEmployeeID());
         user.get().setUserAddress(userRequest.getUserAddress());
         user.get().setPincode(userRequest.getPincode());
         user.get().setState(userRequest.getState());
         userRepository.save(user.get());
        }
        return null;
    }

  /*  @Override
    public User get(long id) {
        return userRepository.findByUserId(id);
    }*/

  /*  @Override
    public void saveBankOpsUser(List<BankOpsUser> bankOpsUsers) {
        bankOpsUserRepository.save(bankOpsUsers);
    }*/


    private List<UserRoleMapping> findAllRolesByUser(User user) {
         return userRoleMappingRepository.findAllByUser(user);
    }

  /*  @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException, NumberFormatException {
        User user = userRepository.findByEmail(email);
        if (user != null) user.setRoles(getAuthorities(user));
        return user;
    }*/


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        logger.info("UserName  {}",username);
        User user = findByUsername(username);
        logger.info("User  {}",user);
        if(user==null)
            throw new UsernameNotFoundException("User not exist");
        //read user(from db)
        return new org.springframework.security.core.userdetails.User(
                username,
                user.getPassword(),
                user.getRole().stream()
                        .map(role->new SimpleGrantedAuthority(role))
                        .collect(Collectors.toList()));

    }


    public User findByUsername(String username) {
        return userRepository.findUsername(username);
    }

    private Set<SimpleGrantedAuthority> getAuthorities(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        findAllRoleByUser(user).forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(String.format("ROLE_%s_%s", role.getHierarchy().getHierarchyCode(), role.getRole().getRoleLevel())));
        });
        return authorities;
    }

    @Override
    public List<UserRoleMapping> findAllRoleByUser(User user) {
        return userRoleMappingRepository.findAllByUser(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserRoleMappingResponse> convertUserRoleMappingResponse(List<User> users) {
        logger.info("User =======   {}",users);
        List<UserRoleMappingResponse> result = new ArrayList<>();
        users.forEach(l -> {

            logger.info("updated user ==========={}",l);
            List<UserRoleMapping> userRoles = this.findAllRoleByUser(l);
            logger.info("UserRoles   {}",userRoles);
            UserRoleMappingResponse userRl = new UserRoleMappingResponse();
            userRl.setUserId(l.getId());
            userRl.setUser(l);
           // userRl.setRegion(userRoles.get(0).getRegion() ==null ? null:userRoles.get(0).getRegion());
          //  logger.info("user role size=======    {}",userRoles.size());
            List<Role> roles = new ArrayList<>();
            userRoles.stream().forEach(rl -> {
                rl.getRole().setPredecessor(rl.getReference());
                roles.add(rl.getRole());
                userRl.setRegion(rl.getRegion());
                if (rl.getRole().getHierarchyRoleLevel().equals(ROLE_HIERARCHY_BANK_OPS)) {
                    List<Long> bankUser = bankOpsUserRepository.findByUser(l);
                    userRl.setBankOpsUser(bankUser);
                }
                if(rl.getRole().equals(ROLE_HIERARCHY_EXTERNAL_CLIENTS) || rl.getRole().equals(ROLE_HIERARCHY_INTERNAL_CLIENTS)){
                    ServiceRequestClients requestClients = serviceReqClientsService.getClientDetails(l);
                    logger.info(" service client details -- : {}", requestClients);
                    userRl.setServiceReqClients(requestClients);
                }
            });
            userRl.setRoles(roles);
            result.add(userRl);
        });
        return result;
    }




}
