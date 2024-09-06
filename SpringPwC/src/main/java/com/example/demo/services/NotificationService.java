package com.example.demo.services;

import com.example.demo.entity.Notification;

import java.util.List;

public interface NotificationService {
    public List<Notification> getNotificationByUserId(Long userId);
    public List<Notification> getNotificationChatsByUserId(Long userId);
    public Notification addNotification(Notification notification);
    public Notification updateNotification(Notification notification);
    public int calculNotificationByIduser(Long userId);
    public int calculNotificationChatsByIduser(Long userId);
    public List<Notification> findFirst5NotifChatByUser(Long userId);
    public List<Notification> findFirst5NotifByUser(Long userId);
}
