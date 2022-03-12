
package com.senac.springWebPi4.repository;

import com.senac.springWebPi4.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long>{
    
 @Query("select u from User u where u.id > :id")   
 public List<User> findAllMoreThan(@Param("id") Long id);
 public List<User> findByIdGreaterThan(Long id);
 public List<User> findByNomeIgnoreCase(String id);
    
}