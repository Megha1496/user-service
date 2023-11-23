package com.microservices.user.userservice.controller;
import com.microservices.user.userservice.payload.request.AuthRequest;
import com.microservices.user.userservice.payload.response.BaseResponse;
import com.microservices.user.userservice.payload.response.JwtResponse;
import com.microservices.user.userservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<BaseResponse> login(@RequestBody AuthRequest request) {
       JwtResponse response= authService.login(request);
        return ResponseEntity.ok(new BaseResponse<>("Getting token",true,response));
    }
    @PostMapping("/refresh-token")
    public ResponseEntity<BaseResponse> refreshToken(@RequestHeader("Authorization") String authorizationHeader) {
        ResponseEntity<BaseResponse> refresheToken=authService.refreshToken(authorizationHeader);
        return ResponseEntity.ok(new BaseResponse<>("Getting token",true,refresheToken));

    }
}


