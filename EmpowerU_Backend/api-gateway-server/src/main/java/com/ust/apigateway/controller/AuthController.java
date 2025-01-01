package com.ust.apigateway.controller;

import com.ust.apigateway.dto.*;
import com.ust.apigateway.service.AuthenticationService;
import com.ust.apigateway.service.UserCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserCrudService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public LoginReq login(@RequestBody UserCredentials userCredentials){

        JwtToken jwtToken = authenticationService.authenticate(userCredentials);
        Long userId = userService.getUserIdByUserName(userCredentials.username());
        return new LoginReq(jwtToken.jwt(), userId);

    }

    @PostMapping("/validate")
    public void validateToken(@RequestParam String token){
        authenticationService.validateToken(token);
    }

    @PostMapping("/signup")
    public UserDtoResponse signup(@RequestBody SignupReq signupReq){
        return userService.saveUser(signupReq);
    }

    @PostMapping("/logout")
    public void logout(){
        authenticationService.logout();
    }

}
