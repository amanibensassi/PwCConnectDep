package com.example.demo.controller;

import com.example.demo.entity.Message;
import com.example.demo.entity.Publication;
import com.example.demo.services.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/message")
public class MessageController {
    public final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/get-by-idChat/{idChat}")
    public List<Message> getAllByIdChat(@PathVariable("idChat") long id){
        return messageService.retriveChatByidChat(id);
    }

    @GetMapping("/getLastMessageByChatId/{idChat}")
    public Message getLastMessageByChatId(@PathVariable("idChat") long id){
        return messageService.getLastMessageByChatId(id);
    }

    @PostMapping("/add-message")
    public Message addMessages(@RequestBody Message m) {
        return messageService.addChat(m);
    }

    @PutMapping("/update-message")
    public Message updateMessages(@RequestBody Message m) {
        m=messageService.retrieveChat(m.getId());
        return messageService.updateChat(m);
    }



}
