package com.example.demo.controller;

import com.example.demo.entity.Publication;
import com.example.demo.entity.User;
import com.example.demo.repository.PublicationRepository;
import com.example.demo.services.PublicationService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/publication")

public class PublicationController {
   private final PublicationService publicationService;
   private final PublicationRepository publicationRepository;

    public PublicationController(PublicationService publicationService, PublicationRepository publicationRepository) {
        this.publicationService = publicationService;
        this.publicationRepository = publicationRepository;
    }
    @GetMapping("/publications")
    public List<Publication> getPublications() {
        return publicationService.retrieveAllPublications();
    }
    @GetMapping("/get-by-user/{user-id}")
    public List<Publication> getPublicationsByuser(@PathVariable("user-id") long userId) {
        return publicationService.retrievePublicationByidUser(userId);
    }
@GetMapping("/get-by-idpub/{idPub}")
public Publication getPubById(@PathVariable("idPub") long idPub){
        return publicationService.retrievePublication(idPub);
}

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public Publication addpublication(@RequestParam("corps") String corps,
                                      @RequestParam("picture") MultipartFile picture,
                                      @RequestParam("id-user") long id
   ) {
        User user = new User();
        user.setId_utilisateur(id);
        Publication u= new Publication();
        u.setCorps(corps);
        u.setUtilisateur(user);
        u.setDate(new Date());
        u.setMasquer(false);

        return publicationService.addUser(u,picture);
    }


    @PostMapping("/add-pub")
    public Publication addpublication(@RequestParam("corps") String corps,
                                      @RequestParam("id-user") long id
    ) {
        User user = new User();
        user.setId_utilisateur(id);
        Publication u= new Publication();
        u.setCorps(corps);
        u.setUtilisateur(user);
        u.setDate(new Date());
        u.setMasquer(false);

        return publicationRepository.save(u);
    }

    @DeleteMapping("/remove-publication/{publication-id}")
    public void removeUser(@PathVariable("publication-id") Long chId) {
        publicationService.removePublication(chId);
    }
    @PutMapping("/modify-publication")
    public Publication modifyUser(@RequestBody Publication u) {

        return publicationService.modifyPublication(u);
    }
}
