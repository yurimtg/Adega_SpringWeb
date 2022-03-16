package com.senac.springWebPi4.controller;

import com.senac.springWebPi4.Utils.UtilsTipoUsuario;
import com.senac.springWebPi4.model.User;
import com.senac.springWebPi4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("user")

public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/home")
    public ModelAndView getHome() {
        ModelAndView mv = new ModelAndView("home");
        return mv;
    }

    @GetMapping("/{id}")
    public Optional<User> user(@PathVariable("id") Long id) {
        return this.userRepository.findById(id);
    }

    @PostMapping("/")
    public User user(@RequestBody User user) {
        return this.userRepository.save(user);
    }

    @GetMapping("/list/{id}")
    public List<User> listMoreThan(@PathVariable("id") Long id) {
        return this.userRepository.findByIdGreaterThan(id);
    }

    public User findByUserName(String username){
        return this.userRepository.findByUserName(username);
    }

    @GetMapping("/list")
    public ModelAndView userList() {
        List<User> usuarios = userRepository.findAll();
        ModelAndView mv = new ModelAndView("userlist");
        mv.addObject("list", usuarios);
        return mv;
    }

    @GetMapping("/form")
    public ModelAndView userForm() {
        ModelAndView mv = new ModelAndView("userForm");
        mv.addObject("tipoUser", UtilsTipoUsuario.values());
        return mv;
    }
}
