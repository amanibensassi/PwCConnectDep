package com.example.demo.services;

import com.example.demo.entity.Chat;

import java.util.List;

public interface ChatService {
    public List<Chat> retrieveAllChatts();

    public Chat retrieveChat(Long idChat);

    public Chat addChat(Chat c);

    public Chat updateChat(Chat c);

    public void removeChat(Long idChat);
    public Long findChatIdByExactUserIds(List<Long> userIds);

    public List<Chat> retriveChatByidUser(Long iduser);

}
