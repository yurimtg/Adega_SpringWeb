package com.senac.springWebPi4.controller;

import com.senac.springWebPi4.model.Imagem;
import com.senac.springWebPi4.model.Produto;
import com.senac.springWebPi4.repository.ImagemRepository;
import com.senac.springWebPi4.repository.ProdutoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    private final String caminhoImg = "/Users/y.oliveira.ignacio/Desktop/Adega_SpringWeb/Adega_SpringWeb/src/main/resources/static/imagem/";

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ImagemRepository imagemRepository;

    @GetMapping("/home")
    public ModelAndView homePage() {
        ModelAndView mv = new ModelAndView("/home");
        List <Produto> prod =  (List <Produto>) produtoRepository.findAll();
        mv.addObject("produto", prod);

        return mv;
    }
}
