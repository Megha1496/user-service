package com.microservices.user.userservice.payload.request;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {

    @NotEmpty(message = "username must be minimum of 4 characters ")
    private String name;

    @NotEmpty(message = "email cannot be null or empty")
    @Email(message = "Email Address is not valid")
    private String email;

    @NotEmpty(message = "password is mandatory")
    private String password;

    @NotEmpty(message = "about can't be null or empty")
    private String about;
}
