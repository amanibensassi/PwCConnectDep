package com.example.demo.services;

import com.example.demo.entity.User;
import com.example.demo.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class LoginServiceImpl implements UserDetailsService {
    @Autowired
    private final UtilisateurRepository userRepository ;

    @Autowired
    public LoginServiceImpl(UtilisateurRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(mail)
                .orElseThrow(() -> new UsernameNotFoundException("Email not found" + mail));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), Collections.emptyList());


    }

   /* public String forgetPassword(String mail) {
        User user = userRepository.findByEmail(mail)
                .orElseThrow(() -> new UsernameNotFoundException("Email not found" + mail));
        return user.getPwd();
    }*/
}
