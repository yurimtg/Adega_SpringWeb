package com.senac.springWebPi4.controller;

import com.senac.springWebPi4.Utils.UtilsTipoUsuario;
import com.senac.springWebPi4.model.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.senac.springWebPi4.repository.UserRepository;
import java.util.Optional;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("user")

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

    @GetMapping("/list/{id}")
    public List<User> listMoreThan(@PathVariable("id") Long id) {
        return this.userRepository.findByIdGreaterThan(id);
    }

//    @GetMapping("/ListName/{username}")
//    public List<User> findByName(@PathVariable("username") String username) {
//        return this.userRepository.findByNomeIgnoreCase(username);
//    }

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