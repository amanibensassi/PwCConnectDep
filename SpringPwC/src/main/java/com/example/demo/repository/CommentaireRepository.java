package com.example.demo.repository;

import com.example.demo.entity.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentaireRepository extends JpaRepository<Commentaire, Long> {
    @Query("select c from Commentaire c where c.publication.id = :id")
    List<Commentaire> findByPublicationId(@Param("id") Long id);

    @Query("select c from Commentaire c where c.publication.id = :id ORDER BY c.date DESC")
    List<Commentaire> find3ByPublicationId(@Param("id") Long id);


    @Query("select count(*) from Commentaire i where i.publication.id = :id")
    Long calculByPublicationId(@Param("id") Long id);
}
