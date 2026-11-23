package com.bijlipay.controller;

import com.bijlipay.model.User;
import com.bijlipay.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;


public class BaseController {

    private Logger log= LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private UserRepository userRepository;

    public User getSessionUser() {
        String user = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // log.info("The session user ===========user id  {}, user name {}",user.getId(),user.getName());
        return userRepository.findSessionUser(user);
    }
}
