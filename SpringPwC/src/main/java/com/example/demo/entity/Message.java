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
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   private String text;
   private Date date;
   private boolean read;

   @ManyToOne
   @JoinColumn(name = "chat")
   private Chat chat;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    private User emetteur;

}
