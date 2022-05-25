package com.senac.springWebPi4.controller;

import com.senac.springWebPi4.model.Carrinho;
import com.senac.springWebPi4.model.Cliente;
import com.senac.springWebPi4.model.Produto;
import com.senac.springWebPi4.repository.CarrinhoRepository;
import com.senac.springWebPi4.repository.ClienteRepository;
import com.senac.springWebPi4.repository.ProdutoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LoginControle {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/")
    public RedirectView logado() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
             Cliente cliente = (Cliente) authentication.getPrincipal();
        
  
        List<Produto> prod = CarrinhoController.carrinhoDeslogado;
        List<Carrinho> carrinho = carrinhoRepository.findByCli(cliente.getId());

        for (Produto itemDeslogado : prod) {
            boolean aux = false;
            for (Carrinho item : carrinho) {
                if (item.getProd().getId() == itemDeslogado.getId()) {
                    item.setProd(itemDeslogado);
                    item.setQtd(itemDeslogado.getQtd() + item.getQtd());
                    item.setPreco(itemDeslogado.getValor());
                    item.setTotal(itemDeslogado.getValor() * itemDeslogado.getQtd());
                    carrinhoRepository.save(item);
                    aux = true;
                }
            }
            if (!aux) {
                Carrinho novoItem = new Carrinho();
                novoItem.setCli(cliente);
                novoItem.setProd(itemDeslogado);
                novoItem.setQtd(itemDeslogado.getQtd());
                novoItem.setPreco(itemDeslogado.getValor());
                novoItem.setTotal(itemDeslogado.getValor() * itemDeslogado.getQtd());
                carrinhoRepository.save(novoItem);
            }
              
        }
            
        } catch (Exception e) {
            return new RedirectView("home");
        }
       return new RedirectView("carrinho/limparCarrinho");
    }

    @RequestMapping("/logout")
    public RedirectView logout() {
        return new RedirectView("carrinho/limparCarrinho");
    }

//    @RequestMapping("/teste")
//    public RedirectView teste() {
//        List<Carrinho> car = (List<Carrinho>) carrinhoRepository.findAll();
//        List<Produto> prod = (List<Produto>) produtoRepository.findAll();
//
//        return new RedirectView("/home");
//    }
}
