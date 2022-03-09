
package com.senac.springWebPi4.repository;

import com.senac.springWebPi4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
    
}
