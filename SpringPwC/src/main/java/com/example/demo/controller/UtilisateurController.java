package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.services.UtilisateurService;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/utilisateur")
@CrossOrigin(origins = "*")
public class UtilisateurController {
    UtilisateurService userService;
    @GetMapping("/users")
    public List<User> getUsers() {
        List<User> listUsers = userService.retrieveAllUsers();
        return listUsers;
    }

    @GetMapping("/retrieve-user-by-mail/{mail}")
    public User retrieveUserByEmail(@PathVariable("mail") String mail) {
        User user = userService.retrieveUserByMail(mail);
        return user;
    }
    // http://localhost:8089/espritgather/user/retrieve-user/2
    @GetMapping("/retrieve-user/{user-id}")
    public User retrieveUser(@PathVariable("user-id") Long chId) {
        User user =userService.retrieveUser(chId);
        return user;
    }


    @GetMapping("/search-user/{name}")
    public List<User> retrieveUser(@PathVariable("name") String nom) {

        return userService.findUsersBynameContainingIgnoreCase(nom);
    }

    @PostMapping("/add-user")
    public User adduser(@RequestBody User u) {
        User user = userService.addUser(u);
        return user;
    }
    @DeleteMapping("/remove-user/{user-id}")
    public void removeUser(@PathVariable("user-id") Long chId) {
        userService.removeUser(chId);
    }
    @PutMapping("/modify-user")
    public User modifyUser(@RequestBody User u) {
        User user = userService.modifyUser(u);
        return user;
    }
}
