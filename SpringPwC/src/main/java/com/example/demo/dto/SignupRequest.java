package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
@Getter
@Setter
public class SignupRequest {

    private String email ;
    private String nom;
    private String prenom;
    private String password;
    private String telephone;
    private String adresse;
    private String grad;
    private Boolean ban;
    private String details;



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}
