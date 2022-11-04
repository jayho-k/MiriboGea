package com.ssafy.backend.common.auth;

import com.ssafy.backend.entity.User;
import com.ssafy.backend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@Slf4j
public class AppUserDetailService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = userService.getUserByEmail(email);
        if(userOptional.isPresent()) {
            AppUserDetails appUserDetails = new AppUserDetails(userOptional.get());
            return appUserDetails;
        }
        return null;
    }
}

