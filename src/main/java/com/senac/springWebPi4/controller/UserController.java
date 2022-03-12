package com.senac.springWebPi4.controller;

import com.senac.springWebPi4.model.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.senac.springWebPi4.repository.UserRepository;
import java.util.Optional;

@RestController
@RequestMapping("/user")

public class UserController {


    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public Optional<User> user(@PathVariable("id") Long id) {
        return this.userRepository.findById(id);
    }

    @PostMapping("/")
    public User user(@RequestBody User user) {
        return this.userRepository.save(user);
    }

    @GetMapping("/list")
    public List<User> list() {
        return this.userRepository.findAll();
    }

    @GetMapping("/list/{id}")
    public List<User> listMoreThan(@PathVariable("id") Long id) {
        return this.userRepository.findByIdGreaterThan(id);
    }
    
    @GetMapping("/ListName/{nome}")
    public List<User> findByName(@PathVariable("nome") String nome) {
        return this.userRepository.findByNomeIgnoreCase(nome);
    }
}
