package com.ssafy.backend.common.auth;

import com.ssafy.backend.entity.User;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@ToString
public class AppUserDetails implements UserDetails {
    User appUser;
    boolean accountNonExpired;
    boolean accountNonLocked;
    boolean credentialNonExpired;
    boolean enabled = false;

    public AppUserDetails(User appUser) {
        super();
        this.appUser = appUser;
    }

    public User getAppUser() {
        return this.appUser;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.appUser.getEmail();
    }

    public Long getUserId() {
        return this.appUser.getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> authorities = new ArrayList<>();

        for(String role : this.appUser.getRole().split(",")){
            authorities.add(new SimpleGrantedAuthority(role));
        }
        log.info("role:{}",authorities.toString());
        return authorities;
    }


}
