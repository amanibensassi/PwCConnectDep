package com.example.demo.services;


import com.example.demo.entity.Commentaire;

import java.util.List;

public interface CommentaireService {



    public List<Commentaire> retrieveAllCommentaire();
    public Commentaire addCommentaire(Commentaire u);
    public void removeCommentaire(Long idUser);
    public Commentaire modifyCommentaire(Commentaire u);
    public List<Commentaire> retrieveCommentairesByidPub(Long idUser);
    public List<Commentaire> retrieve3CommentairesByidPub(Long idUser);
    public Long calculByPublicationId(Long idPub);

}
