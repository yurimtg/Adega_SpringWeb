package com.senac.springWebPi4.controller;

import com.senac.springWebPi4.Utils.Status;
import com.senac.springWebPi4.Utils.UltisUF;
import com.senac.springWebPi4.model.Cliente;
import com.senac.springWebPi4.model.EnderecoEntrega;
import com.senac.springWebPi4.model.Pedido;
import com.senac.springWebPi4.model.Role;
import com.senac.springWebPi4.repository.ClienteRepository;
import com.senac.springWebPi4.repository.EnderecoEntregaRepository;
import com.senac.springWebPi4.repository.PedidoRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ClienteController{

    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private EnderecoEntregaRepository enderecoRepository;

    @PostMapping("cadastroCliente")
    public ModelAndView cadastrarCliente(Cliente cli, String chkEndereco) {

        ModelAndView mv = new ModelAndView("login");
        Role role = new Role();
        EnderecoEntrega endereco = new EnderecoEntrega();
        List<Cliente> validacao = (List<Cliente>) clienteRepository.findAll();

        for (Cliente cliente : validacao) {
            mv.setViewName("cliente/cadastroCli");
            mv.addObject("cliente", cli);
            if (cliente.getEmail().equals(cli.getEmail())) {
                mv.addObject("erroEmail", true);
                return mv;
            }
            if (cliente.getCPF().equals(cli.getCPF())) {
                mv.addObject("erroCPF", true);
                return mv;
            }


        }
        mv.setViewName("login");
        
        role.setNomeRole("ROLE_CLIENTE");
        List<Role> rol = new ArrayList();
        rol.add(role);
        cli.setRolesCLi(rol);
        String encode = new BCryptPasswordEncoder().encode(cli.getSenha());
        cli.setSenha(encode);
        cli.setStatus(Status.ATIVO.toString());
        clienteRepository.save(cli);
        Cliente cliente = clienteRepository.findByEmail(cli.getEmail());

        if (chkEndereco != null) {
            endereco.setBairro(cli.getBairro());
            endereco.setCEP(cli.getCEP());
            endereco.setCidade(cli.getCidade());
            endereco.setComplemento(cli.getComplemento());
            endereco.setLogradouro(cli.getLogradouro());
            endereco.setNumero(cli.getNumero());
            endereco.setUF(cli.getUF());
            endereco.setIsEntrega(true);
            endereco.setCliente(cliente);

            enderecoRepository.save(endereco);
        }

        mv.addObject("loginSuscesso", true);
        return mv;
    }

    @GetMapping("cliente/cadastro")
    public ModelAndView clienteFOrm() {
        Cliente cli = new Cliente();
        ModelAndView mv = new ModelAndView("cliente/cadastroCli");
        mv.addObject("uf", UltisUF.values());
        mv.addObject("cliente", cli);
        return mv;
    }

    @GetMapping("cliente/cadastroEnderecoEntrega")
    public ModelAndView cadastroEnderecoEntrega() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Cliente cli = new Cliente();
        if (authentication.isAuthenticated()) {
            cli = (Cliente) authentication.getPrincipal();
            System.out.println(cli.getEmail());
        }

        ModelAndView mv = new ModelAndView("cliente/cadastroCli");
        mv.addObject("cliente", cli);
        return mv;
    }

    @GetMapping("cliente/dados")
    public ModelAndView dadosCli() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Cliente cli =(Cliente) authentication.getPrincipal();      
        cli = clienteRepository.findById(cli.getId());
        
        List<EnderecoEntrega> end = cli.getEnderecos();
        
        ModelAndView mv = new ModelAndView("cliente/dadosCliente");
        mv.addObject("cliente", cli);
        mv.addObject("enderecos", end);
        return mv;
    }

    @GetMapping("cliente/editDadosPessoais")
    public ModelAndView editDadosCli() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Cliente cli = new Cliente();
        List<EnderecoEntrega> end = new ArrayList();

        cli = (Cliente) authentication.getPrincipal();
        clienteRepository.findByEmail(cli.getEmail());

        ModelAndView mv = new ModelAndView("cliente/editDadosPessoais");
        mv.addObject("cliente", cli);

        return mv;
    }

    @PostMapping("attDadosCli")
    public RedirectView atualizacaoDadosCli(Cliente cli) {
        ModelAndView mv = new ModelAndView("/home");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Cliente cliente = (Cliente) authentication.getPrincipal();

        cliente.setNome(cli.getNome());
        cliente.setGenero(cli.getGenero());
        cliente.setGenero(cli.getGenero());
        cliente.setDataNascimento(cli.getDataNascimento());

        if (!cli.getSenha().equals("")) {
            String encode = new BCryptPasswordEncoder().encode(cli.getSenha());
            cliente.setSenha(encode);
        }

        clienteRepository.save(cliente);
        return new RedirectView("cliente/dados");
    }

    @GetMapping("cliente/novoEndereco")
    public ModelAndView novoEndereco() {

        ModelAndView mv = new ModelAndView("cliente/cadastroEndereco");
        return mv;
    }

    @PostMapping("cadastroNovoEndereco")
    public RedirectView cadastroNovoEndereco(EnderecoEntrega end) {

        EnderecoEntrega endAtivo = new EnderecoEntrega();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Cliente cliente = (Cliente) authentication.getPrincipal();

        endAtivo = enderecoRepository.findByIsEntregas(cliente.getId());

        if (endAtivo != null) {
            end.setIsEntrega(false);
        } else {
            end.setIsEntrega(true);
        }
        end.setCliente(cliente);
        enderecoRepository.save(end);
        return new RedirectView("cliente/dados");
    }

    @GetMapping("cliente/mudarEndereco")
    public ModelAndView mudarEndereco() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Cliente cliente = (Cliente) authentication.getPrincipal();
        cliente = clienteRepository.findById(cliente.getId());


        ModelAndView mv = new ModelAndView("cliente/enderecosEntrega");
        mv.addObject("enderecos", cliente.getEnderecos());

        return mv;
    }

    @GetMapping("mudarSelecao/{id}")
    public ModelAndView userEdit(@PathVariable("id") Long id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Cliente cliente = (Cliente) authentication.getPrincipal();
        cliente = clienteRepository.findById(cliente.getId());

        for (EnderecoEntrega enderecoEntrega : cliente.getEnderecos()) {
            if (enderecoEntrega.getId() == id) {
                enderecoEntrega.setIsEntrega(true);
            } else {
                enderecoEntrega.setIsEntrega(false);
            }
            enderecoRepository.save(enderecoEntrega);
        }

        ModelAndView mv = new ModelAndView("cliente/enderecosEntrega");
        mv.addObject("enderecos", cliente.getEnderecos());

        return mv;
    }
    
    @GetMapping("cliente/pedido")
    public ModelAndView pedidoCli(){
        ModelAndView mv = new ModelAndView("cliente/pedido");
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Cliente cliente = (Cliente) authentication.getPrincipal();
        
        List<Pedido> pedido = pedidoRepository.findByCli(cliente.getId());
        
        mv.addObject("pedido", pedido);
        
        return mv;
    }

    @GetMapping("detalheDoPedido/{id}")
    public ModelAndView detalhePedido(@PathVariable("id") Long id) {    
        
        String [] status = {"aguardando pagamento","pagamento rejeitado","pagamento com sucesso","aguardando retirada","em transito", "entregue"};
        
        ModelAndView mv = new ModelAndView("cliente/detalhePedido");

        Optional<Pedido> pedido = pedidoRepository.findById(id);
        
        for (int i = 0; i < status.length; i++) {
            String aux;
            if(pedido.get().getStatusPedido().equals(status[i])){
             aux = status[0];
             status[0] = status[i];
             status[i] = aux;
         
            }
        }

        mv.addObject("pedido",pedido.get());
        mv.addObject("status",status);

        return mv;
    }

}
