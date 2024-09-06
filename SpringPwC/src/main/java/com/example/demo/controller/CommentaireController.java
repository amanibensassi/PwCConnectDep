package com.example.demo.controller;

import com.example.demo.entity.Commentaire;
import com.example.demo.entity.Publication;
import com.example.demo.services.CommentaireService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/commentaire")
public class CommentaireController {
    private final CommentaireService commentaireService;

    public CommentaireController(CommentaireService commentaireService) {
        this.commentaireService = commentaireService;
    }
    @GetMapping("/commentaires")
    public List<Commentaire> getPublications() {
        return commentaireService.retrieveAllCommentaire();
    }
    @PostMapping("/add-commentaire")
    public Commentaire addpublication(@RequestBody Commentaire u) {
        return commentaireService.addCommentaire(u);
    }
    @GetMapping("/get-by-publication/{user-id}")
    public List<Commentaire> getPublicationsByuser(@PathVariable("user-id") long userId) {
        return commentaireService.retrieveCommentairesByidPub(userId);
    }

    @GetMapping("/get3-by-publication/{user-id}")
    public List<Commentaire> get3PublicationsByuser(@PathVariable("user-id") long userId) {
        return commentaireService.retrieve3CommentairesByidPub(userId);
    }

    @GetMapping("/calcul-commentaire/{pub-id}")
    public Long calculByPublicationId(@PathVariable("pub-id") long pubId) {
        return commentaireService.calculByPublicationId(pubId);
    }
}
