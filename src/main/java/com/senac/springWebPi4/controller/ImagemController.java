package com.senac.springWebPi4.controller;

import com.senac.springWebPi4.model.Imagem;
import com.senac.springWebPi4.model.Produto;
import com.senac.springWebPi4.repository.ImagemRepository;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ImagemController {

    private final String caminhoImg = "/Users/y.oliveira.ignacio/Desktop/Adega_SpringWeb/Adega_SpringWeb/src/main/resources/static/imagem/";

    @Autowired
    private ImagemRepository imagemRepository;

    @PostMapping("/salvarImagem")
    public RedirectView salvarImagem(long id, @RequestParam("file") MultipartFile arquivo) {
        
        Imagem img = new Imagem(caminhoImg, id);
        try {
            if (!arquivo.isEmpty()) {
                byte[] bytes = arquivo.getBytes();
                Path caminho = Paths.get(caminhoImg + String.valueOf(img.getFk_prodId()) + arquivo.getOriginalFilename());
                Files.write(caminho, bytes);
                img.setImg(String.valueOf(img.getFk_prodId()) + arquivo.getOriginalFilename());
                imagemRepository.save(img);
            } else {
//                Optional<Produto> produto = produtoRepository.findById(prod.getId());
//                if (produto.isPresent()) {
//                    Produto p = produto.get();
//                    prod.setImagem(p.getImagem());
            }
            
        } catch (Exception e) {
        }
        return new RedirectView("produto/detalhe/"+id);
    }

}
