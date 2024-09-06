package com.example.demo.services;



import com.example.demo.entity.User;

import java.util.List;

public interface UtilisateurService {
    public List<User> retrieveAllUsers();
    public User retrieveUser(Long idUser);
    public User addUser(User u);
    public void removeUser(Long idUser);
    public User modifyUser(User user);
    public User retrieveUserByMail(String mail);
    List<User> findUsersBynameContainingIgnoreCase(String name);


}
