package com.example.demo.entity;

import com.example.demo.enumeration.Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn (name = "Sender")
    private User Sender;
    @ManyToOne
    @JoinColumn(name = "Publication")
    private Publication Publication;
    @ManyToOne
    @JoinColumn(name = "Chat")
    private Chat chat;


    private String contenu;
    private Date dateEnvoi;
    private boolean vu;

    @Enumerated(EnumType.STRING)
    private Type type;
}
