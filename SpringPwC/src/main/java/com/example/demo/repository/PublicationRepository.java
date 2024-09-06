package com.example.demo.repository;

import com.example.demo.entity.Publication;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {
   // Optional<Publication> findByUtilisateur(User utilisateur);

    @Query("SELECT p" +
            " FROM Publication p WHERE p.Utilisateur.id_utilisateur= :id")
    List<Publication> findAllByUtilisateur(@Param("id") Long id);

}
