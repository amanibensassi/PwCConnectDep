package com.example.demo.controller;

import com.example.demo.entity.Chat;
import com.example.demo.entity.Commentaire;
import com.example.demo.services.ChatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }
    @GetMapping("/chats")
    public List<Chat> getChats() {
        return chatService.retrieveAllChatts();
    }
    @PostMapping("/chatsByListId")
    public Long getChatsByList(@RequestBody List<Long> listchatId) {
        return chatService.findChatIdByExactUserIds(listchatId);
    }

    @GetMapping("/chatsById/{chat-id}")
    public Chat getChats(@PathVariable("chat-id") long chatId) {
        return chatService.retrieveChat(chatId);
    }
    @GetMapping("/chatsByIdUser/{user-id}")
    public List<Chat> getChatsByUser(@PathVariable("user-id") long userId) {
        return chatService.retriveChatByidUser(userId);
    }

    @PostMapping("/add-chat")
    public Chat addpublication(@RequestBody Chat c) {
        return chatService.addChat(c);
    }

    @PutMapping("/edit-chat")
    public Chat updatepublication(@RequestBody Chat c) {
        return chatService.updateChat(c);
    }







}
