
package com.senac.springWebPi4.repository;

import com.senac.springWebPi4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.id > :id")
    List<User> findAllMoreThan(@Param("id") Long id);

    List<User> findByIdGreaterThan(Long id);

//    List<User> findByNomeIgnoreCase(String id);

//    @Query("select u from User u where u.username like :username")
//    User findByUserName(@Param("username") String username);

}