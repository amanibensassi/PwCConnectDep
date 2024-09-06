package com.example.demo.repository;

import com.example.demo.entity.Chat;
import com.example.demo.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("select m from Message m where m.chat.id= :id")
    List<Message> getChatByChatId(@Param("id") Long id);

    @Query("select m from Message m where m.chat.id= :id order by m.date desc limit 1")
    Message getLastMessageByChatId(@Param("id") Long id);
}
