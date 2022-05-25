package com.senac.springWebPi4.controller;

import com.senac.springWebPi4.model.Carrinho;
import com.senac.springWebPi4.model.Cliente;
import com.senac.springWebPi4.model.Imagem;
import com.senac.springWebPi4.model.Produto;
import com.senac.springWebPi4.repository.CarrinhoRepository;
import com.senac.springWebPi4.repository.ProdutoRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@Scope("session")
public class CarrinhoController {

    public static List<Produto> carrinhoDeslogado = new ArrayList<Produto>();

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @GetMapping("cliente/carrinho")
    public ModelAndView carrinho() {

        List<Carrinho> carrinho = null;
        ModelAndView mv = new ModelAndView("carrinho/carrinho");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Cliente cli = new Cliente();

        if (!authentication.getPrincipal().equals("anonymousUser")) {
            cli = (Cliente) authentication.getPrincipal();
            carrinho = (List<Carrinho>) carrinhoRepository.findByCli(cli.getId());
            mv.addObject("carrinho", carrinho);
        }

        for (Produto item : carrinhoDeslogado) {
            Optional<Produto> prod = produtoRepository.findById(item.getId());
            item.setImagens(prod.get().getImagens());
        }

        return mv;
    }

    @GetMapping("adicionar/carrinho/{id}/{qtd}/{status}")
    public ModelAndView adicionarCarrinho(@PathVariable("id") Long id,
            @PathVariable("qtd") int qtd,
            @PathVariable("status") String status) {

        boolean aux = false;
        Optional<Produto> prod = produtoRepository.findById(id);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.getPrincipal().equals("anonymousUser")) {

            Cliente cli = (Cliente) authentication.getPrincipal();
            List<Carrinho> carrinho = (List<Carrinho>) carrinhoRepository.findByCli(cli.getId());

            for (Carrinho item : carrinho) {

                if (prod.get().getId() == item.getProd().getId() && status.equals("add")) {
                    item.setQtd(item.getQtd() + qtd);
                    item.setTotal(item.getPreco() * item.getQtd());
                    aux = true;
                } else if (prod.get().getId() == item.getProd().getId() && status.equals("att")) {
                    item.setQtd(qtd);
                    item.setTotal(item.getPreco() * item.getQtd());
                    aux = true;
                }
                carrinhoRepository.save(item);
            }
            if (!aux) {
                prod.get().setQtd(qtd);
                Carrinho novoItem = new Carrinho();
                novoItem.setProd(prod.get());
                novoItem.setCli(cli);
                novoItem.setPreco(prod.get().getValor());
                novoItem.setTotal(prod.get().getValor() * qtd);
                novoItem.setQtd(qtd);
                carrinhoRepository.save(novoItem);
            }
        } else {

            for (Produto produto : carrinhoDeslogado) {
                if (prod.get().getId() == produto.getId() && status.equals("add")) {
                    produto.setQtd(produto.getQtd() + qtd);
                    aux = true;
                } else if (prod.get().getId() == produto.getId() && status.equals("att")) {
                    produto.setQtd(qtd);
                    aux = true;
                }
            }
            if (!aux) {
                prod.get().setQtd(qtd);
                carrinhoDeslogado.add(prod.get());
            }
        }

        return new ModelAndView("redirect:" + "http://localhost:8080/cliente/carrinho");
    }

    @GetMapping("carrinho/limparCarrinho")
    public ModelAndView limparCarrinho() {
        carrinhoDeslogado.clear();
        return new ModelAndView("redirect:" + "http://localhost:8080/home");
    }

    @GetMapping("carrinho/dropItem/{id}")
    public ModelAndView dropItemCarrinho(@PathVariable("id") Long id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.getPrincipal().equals("anonymousUser")) {
            Cliente cli = (Cliente) authentication.getPrincipal();
            carrinhoRepository.deleteById(id);
        } else {
            
            for (int i = 0; i < carrinhoDeslogado.size(); i++) {
                if (carrinhoDeslogado.get(i).getId() == id) {
                    carrinhoDeslogado.remove(i);
                }
            }
        }

        return new ModelAndView("redirect:" + "http://localhost:8080/cliente/carrinho");
    }
}
