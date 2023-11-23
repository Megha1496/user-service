package com.microservices.user.userservice.service;

import com.microservices.user.userservice.payload.request.AuthRequest;
import com.microservices.user.userservice.payload.response.BaseResponse;
import com.microservices.user.userservice.payload.response.JwtResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    JwtResponse login(AuthRequest request);

    ResponseEntity<BaseResponse> refreshToken(String authorizationHeader);
}
