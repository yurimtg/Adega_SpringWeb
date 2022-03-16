package com.senac.springWebPi4.controller;

import com.senac.springWebPi4.model.TipoUsuario;
import com.senac.springWebPi4.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @GetMapping()
    @RequestMapping("/home")
    public String hello() {
        return "home";
    }
}
