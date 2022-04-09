package com.senac.springWebPi4.controller;

import com.senac.springWebPi4.Utils.Status;
import com.senac.springWebPi4.model.Imagem;
import com.senac.springWebPi4.model.Produto;
import com.senac.springWebPi4.repository.ImagemRepository;
import com.senac.springWebPi4.repository.ProdutoRepository;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RequestMapping("product")
public class ProdController {

    private final String caminhoImg = "/Users/y.oliveira.ignacio/Desktop/Adega_SpringWeb/Adega_SpringWeb/src/main/resources/static/imagem/";

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ImagemRepository imagemRepository;

    @GetMapping("produto/form")
    public ModelAndView userForm() {
        ModelAndView mv = new ModelAndView("produto/prodForm");
        Produto prod = new Produto();
        mv.addObject("prod", prod);
        mv.addObject("status", Status.values());
        return mv;
    }

    @PostMapping("/criarProduto")
    public ModelAndView createProd(Produto prod, @RequestParam("file") MultipartFile arquivo) {
        try {
            if (!arquivo.isEmpty()) {
                produtoRepository.save(prod);
                byte[] bytes = arquivo.getBytes();
                Path caminho = Paths.get(caminhoImg + String.valueOf(prod.getId()) + arquivo.getOriginalFilename());
                Files.write(caminho, bytes);
                prod.setImagem(String.valueOf(prod.getId()) + arquivo.getOriginalFilename());
            } else {
                Optional<Produto> produto = produtoRepository.findById(prod.getId());
                if (produto.isPresent()) {
                    Produto p = produto.get();
                    prod.setImagem(p.getImagem());
                }
            }
            produtoRepository.save(prod);

        } catch (Exception e) {
        }
        ModelAndView mv = new ModelAndView("produto/detalheProduto");
        mv.addObject("prod", prod);
        return mv;
    }

    @GetMapping("produto/list/{page}")
    public ModelAndView userList(@PathVariable("page") int pagina
    ) {
        Sort sort = Sort.by("id").descending();
        Pageable page = PageRequest.of(pagina, 10, sort);
        Page<Produto> produtos = produtoRepository.findAll(page);
        int nPage = produtos.getTotalPages();
        ModelAndView mv = new ModelAndView("produto/prodList");
        mv.addObject("list", produtos);
        mv.addObject("pagina", nPage);
        return mv;
    }

    @GetMapping("produto/findByName/{page}/{nomepesquisa}")
    public ModelAndView findByName(@PathVariable("nomepesquisa") String nomepesquisa,
            @PathVariable("page") int pagina) {

        Sort sort = Sort.by("id").descending();
        Pageable page = PageRequest.of(pagina, 10, sort);
        Page<Produto> produtos = null;

        produtos = produtoRepository.findByNomeProduto(nomepesquisa, page);

        int nPage = produtos.getTotalPages();
        ModelAndView mv = new ModelAndView("produto/prodList");
        mv.addObject("list", produtos);
        mv.addObject("pagina", nPage);
        return mv;
    }

//    @GetMapping("/imagem/{imagem}")
//    @ResponseBody
//    public byte[] retornaImagem(@PathVariable("imagem") String imagem) throws IOException {
//        File imagemArquivo = new File(caminhoImg + imagem);
//        if (imagem != null || imagem.trim().length() > 0) {
//            return Files.readAllBytes(imagemArquivo.toPath());
//        }
//        return null;
//    }
    @GetMapping("produto/detalhe/{id}")
    public ModelAndView detalheProd(@PathVariable("id") Long id) {

        Optional<Produto> prod = produtoRepository.findById(id);
        List<Imagem> img = imagemRepository.findByFk_prodId(id);

        if (prod.isPresent()) {
            Produto produto = prod.get();
            int tamanho = 0;
            for (Imagem imagem : img) {
                tamanho ++;
            }
            ModelAndView mv = new ModelAndView("produto/detalheProduto");
            mv.addObject("prod", produto);
            mv.addObject("imagem", img);
            mv.addObject("tamanho", tamanho);
            return mv;
        }
        return new ModelAndView("/home");
    }

    @GetMapping("produto/edit/{id}")
    public ModelAndView editProduto(@PathVariable("id") Long id
    ) {
        Optional<Produto> prod = produtoRepository.findById(id);
        if (prod.isPresent()) {
            Produto produto = prod.get();
            ModelAndView mv = new ModelAndView("produto/prodForm");
            mv.addObject("prod", produto);
            mv.addObject("status", Status.values());
            return mv;
        }
        return new ModelAndView("/home");
    }

}
