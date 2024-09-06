package com.example.demo.services;

import com.example.demo.dto.SignupRequest;
import org.springframework.web.multipart.MultipartFile;

public interface Authservice {
    boolean createUser(SignupRequest signupRequest, MultipartFile multipartFile); ;
}
