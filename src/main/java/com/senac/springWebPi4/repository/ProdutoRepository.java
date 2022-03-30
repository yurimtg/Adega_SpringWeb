package com.senac.springWebPi4.repository;

import com.senac.springWebPi4.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.domain.Pageable;

public interface ProdutoRepository extends PagingAndSortingRepository<Produto, Long> {
    
    @Query(value = "SELECT * FROM PRODUTO WHERE nome_produto like %?1%", nativeQuery = true)

//    @Query("select p from produto p where p.nomeProduto like :nome%")
    Page<Produto> findByNomeProduto(String nome, Pageable page);

}
