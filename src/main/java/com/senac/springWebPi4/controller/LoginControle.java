package com.senac.springWebPi4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LoginControle {

    @RequestMapping("/login")
    public String login() {
        return "login";
    }
    
        @RequestMapping("/")
    public RedirectView logado() {
        return new RedirectView("/home");
    }
}
