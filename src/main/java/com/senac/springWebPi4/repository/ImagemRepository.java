package com.senac.springWebPi4.repository;

import com.senac.springWebPi4.model.Imagem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ImagemRepository extends JpaRepository<Imagem, Long> {

    @Query(value = "SELECT * FROM IMAGEM WHERE fk_prod_id = ?1", nativeQuery = true)
    public List<Imagem> findByFk_prodId(Long id);

}
