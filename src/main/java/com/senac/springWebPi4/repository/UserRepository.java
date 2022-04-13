
package com.senac.springWebPi4.repository;

import com.senac.springWebPi4.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<User, Long>{
    
 @Query("select p from User p where p.nome like ?1%")   
List<User> findByName(String nome);

@Query("select p from User p where p.email like ?1%")   
User findByEmail(String email);
    
}