package com.example.demo.services;

import com.example.demo.dto.SignupRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UtilisateurRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
public class AuthserviceImpl implements Authservice{
    private final UtilisateurRepository userRepository;
    private final PasswordEncoder passwordEncoder;

   @Autowired
    public AuthserviceImpl(UtilisateurRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean createUser(SignupRequest signupRequest, MultipartFile picture) {
       try{
        User user = new User();
        BeanUtils.copyProperties(signupRequest,user);
        user.setBan(false);
        String hashPassword = passwordEncoder.encode(signupRequest.getPassword());
        user.setPassword(hashPassword);

        if (!picture.isEmpty()) {
            user.setPicture(picture.getBytes());
        }



        User createdUser = userRepository.save(user);
        return true;

   } catch (
    IOException e) {
        e.printStackTrace();
        return false;
    }}
}
