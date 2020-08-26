package com.neosoft.websecuritydemo.services;

import com.neosoft.websecuritydemo.model.MyUserDetails;
import com.neosoft.websecuritydemo.model.UserDetailsImpl;
import com.neosoft.websecuritydemo.repository.MyUserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsServicesImpl implements MyUserDetailsServices {
    private final MyUserDetailsRepository myUserDetailsRepository;

    @Autowired
    public MyUserDetailsServicesImpl(MyUserDetailsRepository myUserDetailsRepository) {
        this.myUserDetailsRepository=myUserDetailsRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUserDetails> userDetails =  myUserDetailsRepository.findByUsername(username);
        userDetails.orElseThrow(() -> new UsernameNotFoundException("-----User Not Fount!-----"));
        return userDetails.map(UserDetailsImpl::new).get();
    }


}
