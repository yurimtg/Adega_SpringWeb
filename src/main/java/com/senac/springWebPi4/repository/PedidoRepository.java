package com.senac.springWebPi4.repository;

import com.senac.springWebPi4.model.Carrinho;
import com.senac.springWebPi4.model.Pedido;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PedidoRepository extends PagingAndSortingRepository<Pedido, Long> {

    @Query(value = "SELECT * FROM PEDIDO WHERE cliente_id = ?1 ORDER BY id Desc", nativeQuery = true)
    public List<Pedido> findByCli(long id);

    @Query(value = "SELECT * FROM PEDIDO ORDER BY id Desc", nativeQuery = true)
    @Override
    public Iterable<Pedido> findAll();

}
