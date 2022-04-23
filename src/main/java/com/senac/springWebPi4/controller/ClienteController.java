
package com.senac.springWebPi4.controller;

import com.senac.springWebPi4.model.Cliente;
import com.senac.springWebPi4.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClienteController {
     @Autowired
    private ClienteRepository clienteRepository;
     
    @PostMapping("cadastroCliente")
    public ModelAndView userForm(Cliente cli) {
        
        ModelAndView mv = new ModelAndView("home");
        clienteRepository.save(cli);
        return mv;
    }

    
}
