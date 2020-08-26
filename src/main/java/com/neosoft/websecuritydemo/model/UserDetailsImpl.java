package com.neosoft.websecuritydemo.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {
    private String username;
    private String password;
    private boolean enable;
    private List<GrantedAuthority> authorityList;

    public UserDetailsImpl(MyUserDetails myUserDetails) {
        this.username= myUserDetails.getUsername();
        this.password=myUserDetails.getPassword();
        this.enable = myUserDetails.isEnable();
        this.authorityList = Arrays.asList(new SimpleGrantedAuthority(myUserDetails.getRole()));

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorityList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }
}
