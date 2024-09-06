package com.example.demo.services;

import com.example.demo.entity.Chat;
import com.example.demo.entity.Message;

import java.util.List;

public interface MessageService {
    public List<Message> retrieveAllChatts();

    public Message retrieveChat(Long idChat);

    public Message addChat(Message c);

    public Message updateChat(Message c);

    public void removeChat(Long idChat);

    public List<Message> retriveChatByidChat(Long id);
    public Message getLastMessageByChatId(Long id);
}
