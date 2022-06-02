package com.senac.springWebPi4.repository;

import com.senac.springWebPi4.model.Carrinho;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CarrinhoRepository extends PagingAndSortingRepository<Carrinho, Long> {

    @Query(value = "SELECT * FROM CARRINHO WHERE cliente_id = ?1", nativeQuery = true)
    public List<Carrinho> findByCli(long id);

}
