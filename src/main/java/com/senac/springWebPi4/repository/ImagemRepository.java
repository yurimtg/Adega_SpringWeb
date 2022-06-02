package com.senac.springWebPi4.repository;

import com.senac.springWebPi4.model.Imagem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ImagemRepository extends JpaRepository<Imagem, Long> {

}
