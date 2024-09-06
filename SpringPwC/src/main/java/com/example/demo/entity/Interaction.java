package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Interaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean type;
    private Date date;
    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    private User Utilisateur;
    @ManyToOne
    @JoinColumn(name = "Publication")
    private Publication publication;

}
