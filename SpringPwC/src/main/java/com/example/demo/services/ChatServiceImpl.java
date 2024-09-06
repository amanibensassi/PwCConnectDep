package com.example.demo.services;

import com.example.demo.entity.Chat;
import com.example.demo.repository.ChatRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final ChatRepository chatRepository;
    @Override
    public List<Chat> retrieveAllChatts() {
        return chatRepository.findAll();
    }

    @Override
    public Chat retrieveChat(Long idChat) {
        return chatRepository.findById(idChat).get();
    }

    @Override
    public Chat addChat(Chat c) {
         c.setVisibility(false);
        return chatRepository.save(c);
    }

    @Override
    public Chat updateChat(Chat c) {
        c.setVisibility(true);
        return chatRepository.save(c);
    }

    @Override
    public void removeChat(Long idChat) {
      chatRepository.deleteById(idChat);
    }

    @Override
    public Long findChatIdByExactUserIds(List<Long> userIds) {
        Long chatId = chatRepository.findChatIdByExactUserIds(userIds,userIds.size());
        return chatId != null ? chatId : 0L;
    }

    @Override
    public List<Chat> retriveChatByidUser(Long iduser) {
        return chatRepository.findByEmetteurId(iduser);
    }
}
