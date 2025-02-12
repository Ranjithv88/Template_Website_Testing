package com.springBoot.Template.Service;

import com.springBoot.Template.Model.*;
import com.springBoot.Template.Model.Enum.Role;
import com.springBoot.Template.Repository.LogoutRepository;
import com.springBoot.Template.Repository.UserRepository;
import com.springBoot.Template.Security.BlockedList;
import com.springBoot.Template.Security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

// Authentication Service Class
@Service
public class AuthenticationService {

    // Repository Class Dependency
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LogoutRepository logoutRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private BlockedList blockedList;

    // Register Services Method
    public ResponseEntity<String> register(Register data) {
        try {
            boolean userNameExist = userRepository.existsByUserName(data.getUserName());
            if (!userNameExist) {
                // Create Cart object manually
                Cart cart = new Cart();
                cart.setUserName(data.getUserName());

                // Create User object manually
                User newUser = new User();
                newUser.setUserName(data.getUserName());
                newUser.setPassword(passwordEncoder.encode(data.getPassword()));
                newUser.setAge(data.getAge());
                newUser.setEmail(data.getEmail());
                newUser.setEmailStatus(false);
                newUser.setPhoneNumber(data.getPhoneNumber());
                newUser.setPhoneNumberStatus(false);
                newUser.setCreatedOn(new Date(System.currentTimeMillis()));
                newUser.setModifyingDate(new Date(System.currentTimeMillis()));
                newUser.setRole(Role.USER);
                newUser.setCart(cart);

                // Save the new user
                userRepository.save(newUser);

                return ResponseEntity.status(HttpStatus.CREATED).body("Registered Successfully...!");
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body("That UserName is taken, Try another...!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Went wrong, please try again later....!");
        }
    }

    // Login Services Method
    public ResponseEntity<?> login(Login data) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(data.getUserName(), data.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UserName and Password are invalid.");
        }
        Optional<User> userOpt = userRepository.findByUserName(data.getUserName());
        if (userOpt.isPresent()) {
            String token = jwtUtils.generateToken(new HashMap<>(), userOpt.get());
            if (token == null) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    // LogOut Services Method
    public ResponseEntity<String> logOut(String token) {
        blockedList.checked();
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean tokenExist = logoutRepository.existsByToken(token);
        if (!tokenExist) {
            // Create LogOut object manually
            LogOut logOut = new LogOut();
            logOut.setUserName(userName);
            logOut.setToken(token);
            logOut.setCreatedOn(new Date(System.currentTimeMillis()));

            // Save the logout record
            logoutRepository.save(logOut);

            return ResponseEntity.status(HttpStatus.CREATED).body("To add BlockList Token Successfully.......!");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Token Is Already Exists......!");
    }
}

