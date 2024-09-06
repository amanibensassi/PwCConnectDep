package com.example.demo.dto;

import com.example.demo.entity.Chat;
import com.example.demo.entity.Publication;
import com.example.demo.entity.User;
import com.example.demo.enumeration.Type;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
public class NotificationRL {

    private User sender;
    private Publication publication;
    private Chat chat;
    private String contenu;


    public NotificationRL(Chat chat, User sender, String contenu) {
        this.chat = chat;
        this.sender = sender;
        this.contenu = contenu;

    }
}
