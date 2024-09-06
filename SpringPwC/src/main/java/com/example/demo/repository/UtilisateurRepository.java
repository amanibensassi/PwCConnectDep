package com.example.demo.repository;


import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String mail);
   // Optional<User> findByPwdResetToken(String token);
    @Query("select u from User u where u.id_utilisateur != :id")
    List<User> getAllButOne(@Param("id") Long id);

}
