package com.example.demo.controller;

import com.example.demo.entity.Notification;
import com.example.demo.services.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/notification")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/notification-by-user/{user-id}")
    public List<Notification> getNotification(@PathVariable("user-id") long userId) {
        return notificationService.getNotificationByUserId(userId);
    }

    @GetMapping("/notificationChat-by-user/{user-id}")
    public List<Notification> getChatNotification(@PathVariable("user-id") long userId) {
        return notificationService.getNotificationChatsByUserId(userId);
    }


    @GetMapping("/5notificationChat-by-user/{user-id}")
    public List<Notification> getFirst5ChatNotification(@PathVariable("user-id") long userId) {
        return notificationService.findFirst5NotifChatByUser(userId);
    }

    @GetMapping("/5notification-by-user/{user-id}")
    public List<Notification> getFirst5Notification(@PathVariable("user-id") long userId) {
        return notificationService.findFirst5NotifByUser(userId);
    }

    @PostMapping("/add-notification")
    public Notification addNotification(@RequestBody Notification u) {
        return notificationService.addNotification(u);
    }

    @PutMapping("/Modify-notification")
    public Notification modifyNotification(@RequestBody Notification u) {
        return notificationService.updateNotification(u);
    }

    @GetMapping("/Calcul-notification/{user-id}")
    public int calculNotification(@PathVariable("user-id") long userId) {
        return notificationService.calculNotificationByIduser(userId);
    }
    @GetMapping("/CalculChat-notification/{user-id}")
    public int calculChatNotification(@PathVariable("user-id") long userId) {
        return notificationService.calculNotificationChatsByIduser(userId);
    }
}
