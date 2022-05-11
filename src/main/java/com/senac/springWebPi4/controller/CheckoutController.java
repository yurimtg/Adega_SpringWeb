package com.senac.springWebPi4.controller;

import com.senac.springWebPi4.model.Carrinho;
import com.senac.springWebPi4.model.Cliente;
import com.senac.springWebPi4.model.EnderecoEntrega;
import com.senac.springWebPi4.model.ItemPedido;
import com.senac.springWebPi4.model.Pedido;
import com.senac.springWebPi4.repository.CarrinhoRepository;
import com.senac.springWebPi4.repository.ClienteRepository;
import com.senac.springWebPi4.repository.EnderecoEntregaRepository;
import com.senac.springWebPi4.repository.ItemPedidoRepository;
import com.senac.springWebPi4.repository.PedidoRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CheckoutController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoEntregaRepository enderecoRepository;

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @GetMapping("cliente/checkout")
    public ModelAndView checkout() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Cliente cli = (Cliente) authentication.getPrincipal();

        EnderecoEntrega endereco = enderecoRepository.findByIsEntregas(cli.getId());
        List<Carrinho> carrinho = carrinhoRepository.findByCli(cli.getId());

        ModelAndView mv = new ModelAndView("carrinho/checkout");
        mv.addObject("endereco", endereco);
        mv.addObject("carrinho", carrinho);

        return mv;
    }

    @PostMapping("confirmarCompra")
    public ModelAndView comfirmarCompra(String pagamento, double taxa) {    
        
        ModelAndView mv = new ModelAndView("carrinho/detalhePedido");
        Pedido pedido = new Pedido();
        
        
        double subTotal = 0;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Cliente cli = (Cliente) authentication.getPrincipal();
        
        List<Carrinho> carrinho = carrinhoRepository.findByCli(cli.getId());
        for (Carrinho item : carrinho) {
            subTotal += item.getTotal();          
        }
        
        pedido.setClientePedido(cli);
        pedido.setData(dtf.format(now));
        pedido.setPagamento(pagamento);
        pedido.setStatusPedido("Aguardando pagamento");
        pedido.setSubTotal(subTotal);
        pedido.setTaxa(taxa);
        pedido.setTotal(subTotal+taxa);
        
        pedidoRepository.save(pedido);
        
        
        
        for (Carrinho item : carrinho) {
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setItemProduto(item.getProd());
            itemPedido.setQuantidade(item.getQtd());
            itemPedido.setValor(item.getPreco());
            itemPedido.setTotal(item.getQtd()*item.getPreco());
            itemPedido.setPedido(pedido);
            itemPedidoRepository.save(itemPedido);
            carrinhoRepository.deleteById(item.getId());
        }
        
        mv.addObject("pedido",pedido);
        return mv;
    }
}
