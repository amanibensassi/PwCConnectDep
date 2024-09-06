package com.example.demo.controller;

import com.example.demo.dto.SignupRequest;
import com.example.demo.services.Authservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/signup")
public class SignUpController {
    private final Authservice authService ;
    // public static String uploadDirectory = "C:/Users/Admin/angular/src/assets/images/";
   // public static String uploadDirectory = "C:/Users/ameni/OneDrive/Bureau/angular/src/assets/images/";

    @Autowired
    public SignUpController(Authservice authService) {
        this.authService = authService;
    }
    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<String> signupUser(@RequestParam("nom") String nom,
                                             @RequestParam("prenom") String prenom,
                                             @RequestParam("adresse") String adresse,
                                             @RequestParam("email") String email,
                                             @RequestParam("telephone") String telephone,
                                             @RequestParam("password") String password,
                                             @RequestParam("grad") String grad,
                                             @RequestParam("details") String details,
                                             @RequestParam MultipartFile picture)  {
SignupRequest signupRequest = new SignupRequest();
signupRequest.setNom(nom);
signupRequest.setPrenom(prenom);
signupRequest.setAdresse(adresse);
signupRequest.setEmail(email);
signupRequest.setTelephone(telephone);
signupRequest.setPassword(password);
signupRequest.setGrad(grad);
signupRequest.setDetails(details);
        boolean isUserCreated = authService.createUser(signupRequest, picture);
        if (isUserCreated) {



            return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\": \"User created successfully\"}");
        } else {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Failed to create user\"}");
        }

    }



}
