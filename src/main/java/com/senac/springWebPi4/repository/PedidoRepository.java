package com.senac.springWebPi4.repository;

import com.senac.springWebPi4.model.Pedido;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PedidoRepository extends PagingAndSortingRepository<Pedido, Long>{
    
}
