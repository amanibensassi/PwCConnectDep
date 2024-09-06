package com.example.demo.services;

import com.example.demo.entity.Notification;
import com.example.demo.enumeration.Type;
import com.example.demo.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;
    @Override
    public List<Notification> getNotificationByUserId(Long userId) {
        return (List<Notification>) notificationRepository.findNotifByUser(userId);
    }

    @Override
    public List<Notification> getNotificationChatsByUserId(Long userId) {
        return notificationRepository.findNotifChatByUser(userId);
    }

    @Override
    public Notification addNotification(Notification notification) {
        notification.setDateEnvoi(new Date());
        notification.setVu(false);

        return notificationRepository.save(notification);
    }

    @Override
    public Notification updateNotification(Notification notification) {
        notification.setVu(true);
        return notificationRepository.save(notification);
    }

    @Override
    public int calculNotificationByIduser(Long userId) {
        return notificationRepository.countByUserReceiver(userId);
    }

    @Override
    public int calculNotificationChatsByIduser(Long userId) {
        return notificationRepository.countChatsByUserReceiver(userId);
    }

    @Override
    public List<Notification> findFirst5NotifChatByUser(Long userId) {
        return notificationRepository.findFirst5NotifChatByUser(userId);
    }

    @Override
    public List<Notification> findFirst5NotifByUser(Long userId) {
        return notificationRepository.findFirst5NotifByUser(userId);
    }
}
