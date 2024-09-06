package com.example.demo.repository;

import com.example.demo.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {



    @Query("select n from Notification n where n.Publication.Utilisateur.id_utilisateur = :id ")
    List<Notification> findNotifByUser(@Param("id") Long id);
    @Query("select n from Notification n where n.Publication.Utilisateur.id_utilisateur = :id order by n.dateEnvoi desc limit 5")
    List<Notification> findFirst5NotifByUser(@Param("id") Long id);

    @Query("select n from Notification n JOIN n.chat.emetteur e  where e.id_utilisateur!=n.Sender.id_utilisateur and e.id_utilisateur = :id ")
    List<Notification> findNotifChatByUser(@Param("id") Long id);

    @Query("select n from Notification n JOIN n.chat.emetteur e  where e.id_utilisateur!=n.Sender.id_utilisateur and e.id_utilisateur = :id  order by n.dateEnvoi desc limit 5")
    List<Notification> findFirst5NotifChatByUser(@Param("id") Long id);

    @Query("select count(*) from Notification i where i.Publication.Utilisateur.id_utilisateur = :idUser and i.vu = false ")
    public int countByUserReceiver(@Param("idUser") Long idUser);

    @Query("select count(*) from Notification i JOIN i.chat.emetteur e  where e.id_utilisateur!=i.Sender.id_utilisateur and  e.id_utilisateur = :idUser and i.vu = false ")
    public int countChatsByUserReceiver(@Param("idUser") Long idUser);
}
