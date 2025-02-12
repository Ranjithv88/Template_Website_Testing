package com.springBoot.Template.Controller;

import com.springBoot.Template.Model.Login;
import com.springBoot.Template.Model.Register;
import com.springBoot.Template.Service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// AuthenticationController For Authentication Process
@RestController
public class AuthenticationController {

    // Services Class Dependency
    @Autowired
    private AuthenticationService service;

    // Post Api for Register
    @PostMapping("/register")
    public ResponseEntity<String> register (@Valid @RequestBody Register data) {
        return service.register(data);
    }

    // Post Api for Login
    @PostMapping("/login")
    public ResponseEntity<?> loginApi ( @Valid @RequestBody Login data) {
        return service.login(data);
    }

    // Get Api for Logout
    @GetMapping("/user/logout")
    public ResponseEntity<String> logOut ( @RequestHeader("Authorization") String token ) {
        return service.logOut(token);
    }

}

