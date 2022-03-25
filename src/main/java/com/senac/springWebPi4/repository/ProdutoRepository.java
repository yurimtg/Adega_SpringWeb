package com.senac.springWebPi4.repository;

import com.senac.springWebPi4.model.Produto;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProdutoRepository extends PagingAndSortingRepository<Produto, Long> {

//    @Query("select p from Produto p order by p desc")

//    @Query("select p from produto p where p.nome like ?1%")
//    List<Produto> findByName(String nome);
}
