package com.senac.springWebPi4.controller;

import com.senac.springWebPi4.Utils.UtilsTipoUsuario;
import com.senac.springWebPi4.model.TipoUsuario;
import com.senac.springWebPi4.model.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.senac.springWebPi4.repository.UserRepository;
import java.util.Optional;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("user")

public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    public RedirectView createUser(User user) {
        userRepository.save(user);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/list");
        return redirectView;
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
        return mv;
    }

    @GetMapping("/userEdit/{id}")
    public ModelAndView userEdit(@PathVariable("id") Long id, User usuario) {
        Optional<User> optional = this.userRepository.findById(id);

        if (optional.isPresent()) {
            User user = optional.get();
            ModelAndView mv = new ModelAndView("userEdit");

            mv.addObject("user", user);
            mv.addObject("tipoUser", UtilsTipoUsuario.values());
            System.out.println(user.getId());
            return mv;
        } else {
            return new ModelAndView("redrect:/list");
        }
    }

    @PostMapping("/editUser/{id}")
    public RedirectView editUser(@PathVariable long id, User user) {

        Optional<User> optional = this.userRepository.findById(id);

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/list");
        if (optional.isPresent()) {
            user.setPassword(optional.get().getPassword());
            this.userRepository.save(user);

            return redirectView;
        } else {
            return redirectView;
        }
    }

    @GetMapping("/deleteUser/{id}")
    public RedirectView deleteUser(@PathVariable Long id) {

        this.userRepository.deleteById(id);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/list");
        return redirectView;
    }

    @GetMapping("/home")
    public ModelAndView homePage() {
        ModelAndView mv = new ModelAndView("home");
        return mv;
    }

//    @PostMapping("/findByName")
//    public ModelAndView findByName(@RequestParam("nomepesquisa") String nomepesquisa) {
//        List<User> user = null;
//        if (nomepesquisa.equals("")) {
//            user = this.userRepository.findAll();
//        } else {
//            user = this.userRepository.findByName(nomepesquisa);
//        }
//        ModelAndView mv = new ModelAndView("userlist");
//        mv.addObject("list", user);
//        return mv;
//    }
}