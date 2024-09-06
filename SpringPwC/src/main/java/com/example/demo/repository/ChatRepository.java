package com.example.demo.repository;

import com.example.demo.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {


    @Query("SELECT c FROM Chat c JOIN c.emetteur e WHERE e.id_utilisateur = :id")
    List<Chat> findByEmetteurId(@Param("id") Long userId);

    @Query("SELECT c.id FROM Chat c WHERE " +
            "(SELECT COUNT(u) FROM User u WHERE u.id_utilisateur IN :userIds AND u MEMBER OF c.emetteur) = :size " +
            "AND SIZE(c.emetteur) = :size")
    Long findChatIdByExactUserIds(@Param("userIds") List<Long> userIds, @Param("size") long size);

}
