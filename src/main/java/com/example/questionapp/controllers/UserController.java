package com.example.questionapp.controllers;

import com.example.questionapp.entities.User;
import com.example.questionapp.repos.UserRepository;
import com.example.questionapp.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User newUser){
        return userService.saveOneUser( newUser );
    }

    @GetMapping("/{userId}")
    public User getOneUser(@PathVariable Long userId){
        //TODO custom exception
        return userService.getOneUser( userId );

    }

    @PutMapping("/{userId}")
    public User updateOneUser(@PathVariable Long userId , @RequestBody User newUser){

        return userService.updateOneUser(userId , newUser);
    }

    @DeleteMapping("/{userId}")
    public void deleteOneUser(@PathVariable Long userId){
        userService.deleteOneUser(userId);
    }



}
