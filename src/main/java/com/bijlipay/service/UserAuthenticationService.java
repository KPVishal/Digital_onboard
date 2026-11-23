
package com.bijlipay.service;

import com.bijlipay.model.User;
import com.bijlipay.model.UserAuthentication;

public interface UserAuthenticationService {

    UserAuthentication getUser(Long l);

    void insertdata(String jwtToken, User userData);

    void update(String jwtToken, Long long1);

    UserAuthentication getToken(Long id);

    void update(String token, User userDetails);

    void delete(Long id);
}

