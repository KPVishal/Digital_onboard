package com.bijlipay.security;

import com.bijlipay.exception.ResourceNotFoundException;
import com.bijlipay.model.User;
import com.bijlipay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    public CustomUserDetailsService() {
    }
    @Transactional
    public UserDetails loadUserById(long id) {
        User user = (User)this.userRepository.findById(id).orElseThrow(() -> {
            return new ResourceNotFoundException("User", "id", id);
        });
        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = this.userRepository.findUsername(name);
        if (user == null) {
            new UsernameNotFoundException("User not found  : ");
        }

        return UserPrincipal.create(user);
    }
}*/
