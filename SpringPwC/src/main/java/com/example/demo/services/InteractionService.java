package com.example.demo.services;

import com.example.demo.entity.Commentaire;
import com.example.demo.entity.Interaction;
import com.example.demo.entity.Publication;

import java.util.List;

public interface InteractionService {

    public List<Interaction> retrieveAllInteractions();
    public Interaction addInteractionLike(Interaction u);
    public Interaction addInteractiondisLike(Interaction u);
    public void removeInteraction(Long idUser);
    public Interaction modifyInteraction(Interaction u);
    public List<Interaction> retrieveInteractionByidPub(Long idUser);
    public List<Interaction> retrieveInteractionByidUser(Long idUser);
    public List<Interaction> retrieveInteractionByidUserandInt(Long idUser, long idInteraction);
    public List<Interaction> retrieveAnyComboInteraction(Long idUser, long idInteraction);
    public Long calculByPublicationId(Long id);

}
