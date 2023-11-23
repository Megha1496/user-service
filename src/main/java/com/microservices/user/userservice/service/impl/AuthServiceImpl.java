package com.microservices.user.userservice.service.impl;

import com.microservices.user.userservice.controller.AuthController;
import com.microservices.user.userservice.model.User;
import com.microservices.user.userservice.payload.request.AuthRequest;
import com.microservices.user.userservice.payload.response.BaseResponse;
import com.microservices.user.userservice.payload.response.JwtResponse;
import com.microservices.user.userservice.repository.UserRepo;
import com.microservices.user.userservice.security.JwtHelper;
import com.microservices.user.userservice.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserRepo userRepo;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);
    @Override
    public JwtResponse login(AuthRequest request) {
        this.doAuthenticate(request.getPassword(), request.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUserName());
        String token = this.jwtHelper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder()
                .token(token)
                .userName(userDetails.getUsername()).build();
        return response;

    }

    @Override
    public ResponseEntity<BaseResponse> refreshToken(String authorizationHeader) {
        String token = authorizationHeader.substring(7);
        String username = jwtHelper.getUsernameFromToken(token);
        User user = userRepo.findByName(username);
        Date lastPasswordReset = user.getLastPasswordResetDate();
        if (jwtHelper.canTokenBeRefreshed(token, lastPasswordReset)) {
            String refreshedToken = jwtHelper.refreshToken(token);
            return ResponseEntity.ok(new BaseResponse<>("Token refreshed successfully", true, refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(new BaseResponse<>("Token cannot be refreshed", false,null));
        }
    }

    private void doAuthenticate(String email, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password!!");
        }
    }
}
