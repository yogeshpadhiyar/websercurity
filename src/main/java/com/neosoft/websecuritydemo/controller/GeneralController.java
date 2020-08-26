package com.neosoft.websecuritydemo.controller;

import com.neosoft.websecuritydemo.model.MyUserDetails;
import com.neosoft.websecuritydemo.services.MyUserDetailsServicesImpl;
import com.neosoft.websecuritydemo.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class GeneralController {

    private final AuthenticationManager authenticationManager;
    private final MyUserDetailsServicesImpl myUserDetailsServices;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public GeneralController(AuthenticationManager authenticationManager, MyUserDetailsServicesImpl myUserDetailsServices , JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.myUserDetailsServices = myUserDetailsServices;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @GetMapping(value = "/")
    public ModelAndView allUser(){
        System.out.println("all User...");
        return new ModelAndView("index");
    }

    @GetMapping(value = "/user")
    public ModelAndView user(){
        System.out.println("Only User...");
        return new ModelAndView("index");
    }
    @GetMapping(value = "/admin")
    public ModelAndView admin(){
        System.out.println("only Admin...");
        return new ModelAndView("index");
    }

    @PostMapping(value = "/authenticate")
        public String createAuthenticationToken(@RequestBody MyUserDetails myUserDetails){
        System.out.println("In authenticate...");
        try {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(myUserDetails.getUsername() , myUserDetails.getPassword()));
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Incorrect UserName or Password");
        }
        System.out.println("here1");
        UserDetails userDetails = myUserDetailsServices.loadUserByUsername(myUserDetails.getUsername());

        return jwtTokenUtil.generateToken(userDetails);

    }
}
