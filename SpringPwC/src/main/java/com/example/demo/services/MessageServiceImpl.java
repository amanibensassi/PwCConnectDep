package com.example.demo.services;

import com.example.demo.entity.Message;
import com.example.demo.repository.MessageRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {
    private MessageRepository messageRepository;
    @Override
    public List<Message> retrieveAllChatts() {
        return messageRepository.findAll();
    }

    @Override
    public Message retrieveChat(Long idChat) {
        return messageRepository.findById(idChat).get();
    }

    @Override
    public Message addChat(Message c) {
        c.setRead(false);
        c.setDate(new Date());
        return messageRepository.save(c);
    }

    @Override
    public Message updateChat(Message c) {
        c.setRead(true);
        return messageRepository.save(c);
    }

    @Override
    public void removeChat(Long idChat) {
      messageRepository.deleteById(idChat);
    }

    @Override
    public List<Message> retriveChatByidChat(Long id) {
        return messageRepository.getChatByChatId(id);
    }

    @Override
    public Message getLastMessageByChatId(Long id) {
        return messageRepository.getLastMessageByChatId(id);
    }
}
