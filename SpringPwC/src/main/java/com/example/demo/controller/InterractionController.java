package com.example.demo.controller;

import com.example.demo.entity.Commentaire;
import com.example.demo.entity.Interaction;
import com.example.demo.repository.CommentaireRepository;
import com.example.demo.repository.InteractionRepository;
import com.example.demo.services.InteractionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/interaction")
public class InterractionController {
    private final InteractionService interactionService;

    public InterractionController(InteractionService interactionService) {
        this.interactionService = interactionService;
    }
    @PostMapping("/add-interaction-like")
    public Interaction addInteractionLike(@RequestBody Interaction i) {
        return interactionService.addInteractionLike(i);
    }
    @PostMapping("/add-interaction-dislike")
    public Interaction addInteractionDislike(@RequestBody Interaction i) {
        return interactionService.addInteractiondisLike(i);
    }
    @GetMapping("/interactions-by-id/{id-pub}")
    public List<Interaction> getInteractionByIdPub(@PathVariable("id-pub") long id) {
        return interactionService.retrieveInteractionByidPub(id);
    }

    @GetMapping("/interactions-by-iduser-idint/{id-user}/{id-int}")
    public boolean getInteractionByUser(@PathVariable("id-user") long id, @PathVariable("id-int") long idint) {
        if (interactionService.retrieveInteractionByidUserandInt(id, idint).isEmpty()){return  false;}
        else return true;
    }
    @GetMapping("/Anyinteractions-by-iduser-idint/{id-user}/{id-int}")
    public boolean getAnyInteractionByUser(@PathVariable("id-user") long id, @PathVariable("id-int") long idint) {
        if (interactionService.retrieveInteractionByidUserandInt(id, idint).isEmpty()){return  false;}
        else return true;
    }

    @GetMapping("/interactions-by-iduser/{id-user}")
    public List<Interaction> getInteractionByUser(@PathVariable("id-user") long id) {
        return interactionService.retrieveInteractionByidUser(id);
    }

    @GetMapping("/calcul-interaction/{id-pub}")
    public Long calculInteractionByPub(@PathVariable("id-pub") long id) {
        return interactionService.calculByPublicationId(id);
    }


}
