package com.example.demo.services;

import com.example.demo.entity.Commentaire;
import com.example.demo.repository.CommentaireRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CommentaireServiceImpl implements CommentaireService {
    private final CommentaireRepository commentaireRepository;
    @Override
    public List<Commentaire> retrieveAllCommentaire() {
        return commentaireRepository.findAll();
    }

    @Override
    public Commentaire addCommentaire(Commentaire u) {
        u.setDate(new Date());
        return commentaireRepository.save(u);
    }

    @Override
    public void removeCommentaire(Long idUser) {
commentaireRepository.deleteById(idUser);
    }

    @Override
    public Commentaire modifyCommentaire(Commentaire u) {
        return commentaireRepository.save(u);
    }

    @Override
    public List<Commentaire> retrieveCommentairesByidPub(Long idUser) {
        return commentaireRepository.findByPublicationId(idUser);
    }

    @Override
    public List<Commentaire> retrieve3CommentairesByidPub(Long idUser) {
        List<Commentaire> commentaires = commentaireRepository.find3ByPublicationId(idUser);
      int n =commentaires.size();
        System.out.println(" ");
        System.out.println(n);

          int i=3;
          if (n>3){
          while ((i!=n)){
              System.out.println("");
              System.out.println(i);
              commentaires.remove(i);
              n=commentaires.size();
              System.out.println("");
              System.out.println(n);
          }
}
        return commentaires;
    }

    @Override
    public Long calculByPublicationId(Long idPub) {
        return commentaireRepository.calculByPublicationId(idPub);
    }
}
