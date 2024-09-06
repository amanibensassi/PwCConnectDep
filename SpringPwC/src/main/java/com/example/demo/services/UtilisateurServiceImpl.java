package com.example.demo.services;

import com.example.demo.entity.User;
import com.example.demo.repository.UtilisateurRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class UtilisateurServiceImpl implements UtilisateurService {
    private UtilisateurRepository utilisateurRepository;
    @Override
    public List<User> retrieveAllUsers() {
        return utilisateurRepository.findAll();
    }

    @Override
    public User retrieveUser(Long idUser) {
        return utilisateurRepository.findById(idUser).get();
    }

    @Override
    public User addUser(User u) {
        return utilisateurRepository.save(u);
    }

    @Override
    public void removeUser(Long idUser) {
        utilisateurRepository.deleteById(idUser);
    }

    @Override
    public User modifyUser(User user) {
        return utilisateurRepository.save(user);
    }

    @Override
    public User retrieveUserByMail(String mail) {
        return utilisateurRepository.findByEmail(mail).orElse(null);
    }

    @Override
    public List<User> findUsersBynameContainingIgnoreCase(String name) {
        return null;
    }
}
