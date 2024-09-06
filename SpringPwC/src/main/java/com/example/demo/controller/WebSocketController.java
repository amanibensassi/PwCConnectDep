package com.example.demo.controller;

import com.example.demo.dto.NotificationRL;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin("*")
@Controller
public class WebSocketController {


    @MessageMapping("/chat/{roomId}")
    @SendTo("/topic/{roomId}")
    public String notifyMessage(@DestinationVariable String roomId,@RequestBody NotificationRL notification) {
        System.out.println("We are on the server point");
        System.out.println("The room id is: " + roomId);
        new NotificationRL(notification.getChat(),notification.getSender(),notification.getContenu());
        return "Connection is succefull";
    }


}
