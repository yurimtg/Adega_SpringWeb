package com.senac.springWebPi4.repository;

import com.senac.springWebPi4.model.EnderecoEntrega;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EnderecoEntregaRepository extends PagingAndSortingRepository<EnderecoEntrega, Long> {
    
    @Query(value = "SELECT * FROM endereco_entrega WHERE cliente_id like ?1 and is_entrega = true", nativeQuery = true)
    List<EnderecoEntrega> findByIsEntregas(Long id);

}
