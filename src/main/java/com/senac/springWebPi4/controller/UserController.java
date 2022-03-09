package com.senac.springWebPi4.controller;

import com.senac.springWebPi4.model.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.senac.springWebPi4.repository.UserRepository;

@RestController
@RequestMapping("/user")

public class UserController {

    private List<User> users = new ArrayList<>();
    
    @Autowired
    private UserRepository userRepository;
    
    
    @GetMapping("/{id}")
    public User user(@PathVariable("id") Long id) {

        for (User user : users) {
          if (id == user.getId()){
              return user;
          }  
        }
        return null;
    }

    @PostMapping("/")
    public User user(@RequestBody User user) {
       return this.userRepository.save(user);
    }
    
@GetMapping("/list")
    public List <User> AllUser() {
    return users;
    }
}
