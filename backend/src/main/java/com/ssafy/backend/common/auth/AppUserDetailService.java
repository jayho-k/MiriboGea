package com.ssafy.backend.common.auth;

import com.ssafy.backend.entity.User;
import com.ssafy.backend.repository.UserRepository;
import com.ssafy.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AppUserDetailService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = userService.getUserByEmail(email);

        if(userOptional.isPresent()) {
            AppUserDetails appUserDetails = new AppUserDetails(userOptional.get());
            appUserDetails.setRole(userOptional.get().getRole());
            appUserDetails.setRole(String.valueOf(new SimpleGrantedAuthority(userOptional.get().getRole())));

//            appUserDetails.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(userOptional.get().getRole())));
            return appUserDetails;
        }
        return null;
    }
}

