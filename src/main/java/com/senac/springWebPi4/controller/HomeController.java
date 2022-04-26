package com.senac.springWebPi4.controller;

import com.senac.springWebPi4.model.Imagem;
import com.senac.springWebPi4.model.Produto;
import com.senac.springWebPi4.repository.ImagemRepository;
import com.senac.springWebPi4.repository.ProdutoRepository;
import java.util.ArrayList;
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
        List<Produto> prod = produtoRepository.findAll();
        List<Imagem> img = imagemRepository.findAll();
        List<Imagem> firstImg = new ArrayList();
        List<Imagem> imagens = new ArrayList();

        for (Produto produto : prod) {
            boolean achou = false;
            for (Imagem imagem : img) {
                if (produto.getId() == imagem.getFk_prodId() && achou == false) {
                    Imagem fImg = new Imagem();
                    fImg.setImg(imagem.getImg());
                    fImg.setFk_prodId(imagem.getFk_prodId());
                    fImg.setId(imagem.getId());
                    firstImg.add(fImg);
                    achou = true;
                }
            }
        }

        for (Imagem imagem : img) {
            boolean achou = false;
            for (Imagem fImg : firstImg) {
                if (imagem.getId() == fImg.getId()) {
                    achou = true;
                }
            }
            if (achou == false) {
                Imagem imgAux = new Imagem();
                imgAux.setImg(imagem.getImg());
                imgAux.setFk_prodId(imagem.getFk_prodId());
                imgAux.setId(imagem.getId());
                imagens.add(imgAux);
            }
        }

        mv.addObject("produto", prod);
        mv.addObject("imagem", img);
        mv.addObject("fistImg", firstImg);
        mv.addObject("outrasImg", imagens);

        return mv;
    }

    @GetMapping("/home/{nomepesquisa}")
    public ModelAndView homeFindByName(@PathVariable("nomepesquisa") String nomepesquisa) {
        ModelAndView mv = new ModelAndView("/home");
        List<Produto> prod = produtoRepository.findByNome(nomepesquisa);
        List<Imagem> img = imagemRepository.findAll();
        List<Imagem> firstImg = new ArrayList();
        List<Imagem> imagens = new ArrayList();

        for (Produto produto : prod) {
            boolean achou = false;
            for (Imagem imagem : img) {
                if (produto.getId() == imagem.getFk_prodId() && achou == false) {
                    Imagem fImg = new Imagem();
                    fImg.setImg(imagem.getImg());
                    fImg.setFk_prodId(imagem.getFk_prodId());
                    fImg.setId(imagem.getId());
                    firstImg.add(fImg);
                    achou = true;
                }
            }
        }

        for (Imagem imagem : img) {
            boolean achou = false;
            for (Imagem fImg : firstImg) {
                if (imagem.getId() == fImg.getId()) {
                    achou = true;
                }
            }
            if (achou == false) {
                Imagem imgAux = new Imagem();
                imgAux.setImg(imagem.getImg());
                imgAux.setFk_prodId(imagem.getFk_prodId());
                imgAux.setId(imagem.getId());
                imagens.add(imgAux);
            }
        }

        mv.addObject("produto", prod);
        mv.addObject("imagem", img);
        mv.addObject("fistImg", firstImg);
        mv.addObject("outrasImg", imagens);

        return mv;
    }

    @GetMapping("produto/detalheHome/{id}")
    public ModelAndView detalheProd(@PathVariable("id") Long id) {

        Optional<Produto> prod = produtoRepository.findById(id);
        List<Imagem> img = imagemRepository.findByFk_prodId(id);
        ModelAndView mv = new ModelAndView("produto/detalheProdutoHome");

        Produto produto = prod.get();
        int tamanho = 0;
        for (Imagem imagem : img) {
            tamanho++;
        }
        if (img.isEmpty()) {
            Imagem img1 = new Imagem();
            img1.setImg("semImagem.jpg");
            mv.addObject("imagem", img1);
            tamanho = 1;
        } else {
            mv.addObject("imagem", img);
        }

        mv.addObject("prod", produto);

        mv.addObject("tamanho", tamanho);
        return mv;
    }

}
