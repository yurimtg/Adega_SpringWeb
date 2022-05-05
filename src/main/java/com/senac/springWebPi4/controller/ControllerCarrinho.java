package com.senac.springWebPi4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControllerCarrinho {
    @GetMapping("cliente/carrinho")
    public ModelAndView carrinho (){
    
        ModelAndView mv = new ModelAndView("carrinho/carrinho");
        return mv;
    }
}
