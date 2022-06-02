package com.senac.springWebPi4.repository;

import com.senac.springWebPi4.model.ItemPedido;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ItemPedidoRepository extends PagingAndSortingRepository<ItemPedido, Long> {
    
}
