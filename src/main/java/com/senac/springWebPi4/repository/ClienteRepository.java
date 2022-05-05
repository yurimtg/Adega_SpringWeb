package com.senac.springWebPi4.repository;

import com.senac.springWebPi4.model.Cliente;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClienteRepository extends PagingAndSortingRepository <Cliente, String>{

    public Cliente findByEmail(String login);
    public Cliente findById(Long id);
    
    
}
