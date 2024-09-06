package com.example.demo.services;

import com.example.demo.entity.Publication;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PublicationService {
    public List<Publication> retrieveAllPublications();
    public Publication retrievePublication(Long idUser);
    public Publication addUser(Publication u, MultipartFile picture);
    public void removePublication(Long idUser);
    public Publication modifyPublication(Publication u);
    public List<Publication> retrievePublicationByidUser(Long idUser);
}
