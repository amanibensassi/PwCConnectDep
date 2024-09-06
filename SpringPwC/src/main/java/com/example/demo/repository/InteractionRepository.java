package com.example.demo.repository;

import com.example.demo.entity.Interaction;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InteractionRepository extends JpaRepository<Interaction, Long> {
    @Query("select i from Interaction i where i.Utilisateur.id_utilisateur = :id")
    List<Interaction> findByUtilisateur(@Param("id") Long id);



    //hethiii lli tchouflek ken famma aayyy ligne fel bd
    @Query("select i from Interaction i where i.Utilisateur.id_utilisateur = :id and i.publication.id= :idint")
    List<Interaction> findByUtilisateurandinteraction(@Param("id") Long id, @Param("idint") Long idint);

    @Query("select i from Interaction i where i.Utilisateur.id_utilisateur = :id and i.publication.id= :idint and i.type=true")
    List<Interaction> findByUtilisateurandinteractionTrue(@Param("id") Long id, @Param("idint") Long idint);


    //hethi list taa les ints lkol aal pub wahda
    @Query("select i from Interaction i where i.publication.id = :id")
    List<Interaction> findByPublication(@Param("id") Long id);


    //hethi calcul aadi
    @Query("select count(*) from Interaction i where i.publication.id = :id and i.type= true")
    Long calculByPublicationId(@Param("id") Long id);
    @Modifying
    @Transactional
//modif en cas ou famma interaction lkaha ll user
    @Query(" update Interaction i set i.type = true where i.publication.id = :idint and i.Utilisateur.id_utilisateur = :id ")
    int addLike (@Param("id") Long id, @Param("idint") Long idint);
    @Modifying
    @Transactional
    @Query(" update Interaction i set i.type = false where i.publication.id = :idint and i.Utilisateur.id_utilisateur = :id ")
    int addDislike (@Param("id") Long id, @Param("idint") Long idint);
}
