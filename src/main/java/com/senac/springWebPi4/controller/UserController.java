package com.senac.springWebPi4.controller;

import com.senac.springWebPi4.Utils.Status;
import com.senac.springWebPi4.Utils.UtilsTipoUsuario;
import com.senac.springWebPi4.model.Produto;
import com.senac.springWebPi4.model.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.senac.springWebPi4.repository.UserRepository;
import java.util.Optional;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
//@RequestMapping("user")

public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/criarUsuario")
    public RedirectView createUser(User user) {
//        String encode = new BCryptPasswordEncoder().encode(user.getSenha());
//        user.setSenha(encode);
        userRepository.save(user);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/list");
        return redirectView;
    }

    @GetMapping("/list")
    public ModelAndView userList() {
        List<User> usuarios = userRepository.findAll();
        ModelAndView mv = new ModelAndView("user/userlist");
        mv.addObject("list", usuarios);
        return mv;
    }

    @GetMapping("/form")
    public ModelAndView userForm() {
        ModelAndView mv = new ModelAndView("user/userForm");
        mv.addObject("tipoUser", UtilsTipoUsuario.values());
        mv.addObject("status", Status.values());
        return mv;
    }

    @GetMapping("/userEdit/{id}")
    public ModelAndView userEdit(@PathVariable("id") Long id, User usuario) {
        Optional<User> optional = this.userRepository.findById(id);

        if (optional.isPresent()) {
            User user = optional.get();
            ModelAndView mv = new ModelAndView("user/userEdit");

            mv.addObject("user", user);
            mv.addObject("tipoUser", UtilsTipoUsuario.values());
            mv.addObject("status", Status.values());
            return mv;
        } else {
            return new ModelAndView("redrect:/list");
        }
    }

    @PostMapping("/editUser/{id}")
    public RedirectView editUser(@PathVariable long id, User user) {

        Optional<User> optional = this.userRepository.findById(id);

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/list");
        if (optional.isPresent()) {
            user.setSenha(optional.get().getSenha());
            this.userRepository.save(user);

            return redirectView;
        } else {
            return redirectView;
        }
    }

    @PostMapping("/findByName")
    public ModelAndView findByName(@RequestParam("nomepesquisa") String nomepesquisa) {
        List<User> user = null;
        if (nomepesquisa.equals("")) {
            user = this.userRepository.findAll();
        } else {
            user = this.userRepository.findByName(nomepesquisa);
        }
        ModelAndView mv = new ModelAndView("user/userlist");
        mv.addObject("list", user);
        return mv;
    }
    
        @PostMapping("usuario/alterarStatus")
    public void alterarStatus(Long id) {
        Optional<User> user = userRepository.findById(id);
        User usuario = user.get();

        if (usuario.getStatus().equals(Status.ATIVO.toString())) {
            usuario.setStatus(Status.INATIVO.toString());
        } else {
            usuario.setStatus(Status.ATIVO.toString());
        }
        userRepository.save(usuario);
    }
}
