package com.senac.springWebPi4.controller;

import com.senac.springWebPi4.Utils.Status;
import com.senac.springWebPi4.model.Produto;
import com.senac.springWebPi4.repository.ProdutoRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RequestMapping("product")
public class ProdController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("produto/form")
    public ModelAndView userForm() {
        ModelAndView mv = new ModelAndView("produto/prodForm");
        mv.addObject("status", Status.values());
        return mv;
    }

    @PostMapping("/criarProduto")
    public String createProd(Produto prod) {
        produtoRepository.save(prod);
        return "produto/detalheProduto";
    }
    
      @GetMapping("produto/list")
    public ModelAndView userList() {
        Sort sort = Sort.by("id").descending();
        Pageable page = PageRequest.of(0,5,sort);
        Page <Produto> produtos = produtoRepository.findAll(page);
        int n = produtos.getTotalPages();
        ModelAndView mv = new ModelAndView("produto/prodList");
        mv.addObject("list", produtos);
        return mv;
    }
}
