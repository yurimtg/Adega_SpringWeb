
package com.senac.springWebPi4.repository;

import com.senac.springWebPi4.model.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<Usuario, Long>{
    
 @Query("select p from Usuario p where p.nome like ?1%")   
List<Usuario> findByName(String nome);

@Query("select p from Usuario p where p.email like ?1%")   
Usuario findByEmail(String email);
    
}