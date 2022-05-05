package com.senac.springWebPi4.controller;

import com.senac.springWebPi4.Utils.Status;
import com.senac.springWebPi4.Utils.UtilsTipoUsuario;
import com.senac.springWebPi4.model.Role;
import com.senac.springWebPi4.model.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.senac.springWebPi4.repository.UserRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    public RedirectView createUser(Usuario Usuario) {
        Role role = new Role();
        if (Usuario.getTipoUsuario().equals(UtilsTipoUsuario.ADMINISTRADOR.toString())) {
            role.setNomeRole("ROLE_ADMIN");
        } else {
            role.setNomeRole("ROLE_ESTOQUE");
        }

        List<Role> rol = new ArrayList();
        rol.add(role);
        Usuario.setRoles(rol);

        String encode = new BCryptPasswordEncoder().encode(Usuario.getSenha());
        Usuario.setSenha(encode);

        userRepository.save(Usuario);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/list");
        return redirectView;
    }

    @GetMapping("/list")
    public ModelAndView userList() {
        List<Usuario> usuarios = userRepository.findAll();
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
    public ModelAndView userEdit(@PathVariable("id") Long id, Usuario usuario) {
        Usuario user = this.userRepository.findId(id);

        if (user != null) {
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
    public RedirectView editUser(@PathVariable long id, Usuario user) {
        String encode = new BCryptPasswordEncoder().encode(user.getSenha());
        user.setSenha(encode);
        RedirectView redirectView = new RedirectView("/list");
        if (user != null) {
            this.userRepository.save(user);
            return redirectView;
        } else {
            return redirectView;
        }
    }

    @PostMapping("/findByName")
    public ModelAndView findByName(@RequestParam("nomepesquisa") String nomepesquisa) {
        List<Usuario> user = null;
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
        Usuario user = userRepository.findId(id);

        if (user.getStatus().equals(Status.ATIVO.toString())) {
            user.setStatus(Status.INATIVO.toString());
        } else {
            user.setStatus(Status.ATIVO.toString());
        }
        userRepository.save(user);
    }
}
