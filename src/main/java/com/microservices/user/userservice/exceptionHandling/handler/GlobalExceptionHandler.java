package com.microservices.user.userservice.exceptionHandling.handler;

import com.microservices.user.userservice.exceptionHandling.ResourceNotFoundException;
import com.microservices.user.userservice.payload.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<BaseResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException resourceNotFoundException){
    return ResponseEntity.ok(new BaseResponse(resourceNotFoundException.getMessage(),false,null));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> methodNotValidException(MethodArgumentNotValidException ex){

        Map<String, String> resp= new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error->{
            String fieldName = ((FieldError) error).getField();
            String message= error.getDefaultMessage();
            resp.put(fieldName, message);
        });
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<BaseResponse> exceptionHandler() {
        return ResponseEntity.ok(new BaseResponse("Credentials Invalid !!",false,null));
    }
}
