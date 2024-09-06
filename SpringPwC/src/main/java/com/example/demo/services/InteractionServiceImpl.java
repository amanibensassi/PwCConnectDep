package com.example.demo.services;

import com.example.demo.entity.Interaction;
import com.example.demo.repository.InteractionRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class InteractionServiceImpl implements InteractionService {
    public final InteractionRepository interactionRepository;


    @Override
    public List<Interaction> retrieveAnyComboInteraction(Long idUser, long idInteraction) {
        return interactionRepository.findByUtilisateurandinteraction(idUser, idInteraction);
    }

    @Override
    public List<Interaction> retrieveAllInteractions() {
        return interactionRepository.findAll();
    }
    @Transactional
    @Override
    public Interaction addInteractionLike(Interaction u) {
        u.setDate(new Date());
        u.setType(true);
        if ((interactionRepository.findByUtilisateurandinteraction(u.getUtilisateur().getId_utilisateur(),u.getPublication().getId())).isEmpty()) {
            return interactionRepository.save(u);
        }else {
            interactionRepository.addLike(u.getUtilisateur().getId_utilisateur(), u.getPublication().getId());
            return u;
        }
    }
    @Transactional
    @Override
    public Interaction addInteractiondisLike(Interaction u) {
        u.setDate(new Date());
        u.setType(false);
        if ((interactionRepository.findByUtilisateurandinteraction(u.getUtilisateur().getId_utilisateur(),u.getPublication().getId())).isEmpty()) {
            return interactionRepository.save(u);
        }else {
            interactionRepository.addDislike(u.getUtilisateur().getId_utilisateur(), u.getPublication().getId());
            return u;
        }
    }

    @Override
    public void removeInteraction(Long idUser) {
      interactionRepository.deleteById(idUser);
    }

    @Override
    public Interaction modifyInteraction(Interaction u) {
        return interactionRepository.save(u);
    }

    @Override
    public List<Interaction> retrieveInteractionByidPub(Long idUser) {
        return interactionRepository.findByPublication(idUser);
    }

    @Override
    public List<Interaction> retrieveInteractionByidUser(Long idUser) {
        return interactionRepository.findByUtilisateur(idUser);
    }

    @Override
    public List<Interaction> retrieveInteractionByidUserandInt(Long idUser, long idInteraction) {
        return interactionRepository.findByUtilisateurandinteractionTrue(idUser, idInteraction);
    }



    @Override
    public Long calculByPublicationId(Long id) {
        return interactionRepository.calculByPublicationId(id);
    }
}
