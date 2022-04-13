package com.senac.springWebPi4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginControle {
    
    @RequestMapping("/login")
    public String login(){
    return "login";
    }
}
