package com.senac.springWebPi4.repository;

import com.senac.springWebPi4.model.Produto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.domain.Pageable;

public interface ProdutoRepository extends PagingAndSortingRepository<Produto, Long> {
    
    @Query(value = "SELECT * FROM PRODUTO WHERE nome_produto like %?1%", nativeQuery = true)
    Page<Produto> findByNomeProduto(String nome, Pageable page);
    
    List<Produto> findAll();

}
