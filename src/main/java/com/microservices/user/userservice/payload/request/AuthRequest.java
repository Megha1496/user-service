package com.microservices.user.userservice.payload.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    @NotEmpty(message = "username can't be null or empty")
    private String userName;

    @NotEmpty(message = "password can not be null or empty")
    @Min(value = 4)
    private String password;

}
