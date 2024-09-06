package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.services.LoginServiceImpl;
import com.example.demo.utils.JWTUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class LoginController {
    private  final AuthenticationManager authenticationManager;
    private final LoginServiceImpl loginService;
    private  final JWTUtils jwtUtil ;
    @Autowired
    public LoginController(AuthenticationManager authenticationManager, LoginServiceImpl loginService, JWTUtils jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.loginService = loginService;
        this.jwtUtil = jwtUtil;
    }
    @PostMapping
    public ResponseEntity<LoginResponse>login(@RequestBody LoginRequest loginRequest){
        try{
            authenticationManager.authenticate(

                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword())
            );
        }catch (AuthenticationException e){
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        UserDetails userDetails;
        System.out.println("user details" );
        try {
            userDetails = loginService.loadUserByUsername(loginRequest.getEmail());
        } catch (UsernameNotFoundException e) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        String jwt = jwtUtil.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(new LoginResponse(jwt));
    }

 /*   @PutMapping("/forget-password")
    public ResponseEntity<?> forgetPassword(@RequestParam String mail){
        return new ResponseEntity<>(loginService.forgetPassword(mail),HttpStatus.OK);
    }*/
}
