package com.example.demo.services;

import com.example.demo.entity.Publication;
import com.example.demo.repository.PublicationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class PublicationServiceImpl implements PublicationService {
    private final PublicationRepository publicationRepository;
    @Override
    public List<Publication> retrieveAllPublications() {
        return publicationRepository.findAll();
    }

    @Override
    public Publication retrievePublication(Long idUser) {
        return publicationRepository.findById(idUser).orElse(null);
    }

    @Override
    public Publication addUser(Publication u, MultipartFile picture) {
     try{ if (!picture.isEmpty()) {
         u.setPicture(picture.getBytes());
     }
         return publicationRepository.save(u);

     }catch (
             IOException e) {
         e.printStackTrace();
         return null;
     }

    }

    @Override
    public void removePublication(Long idUser) {
        publicationRepository.deleteById(idUser);
    }

    @Override
    public Publication modifyPublication(Publication u) {
        return publicationRepository.save(u);
    }

    @Override
    public List<Publication> retrievePublicationByidUser(Long mail) {

        return publicationRepository.findAllByUtilisateur(mail);
    }
}
