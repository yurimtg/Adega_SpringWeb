package com.senac.springWebPi4.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table(name = "users")

public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id; 
//    @Column(nullable = false)
    private String nome;
    private String email;
    private String telefone;
    private String senha;
    private String tipoUsuario; // Admin, estoquista
    private String data; 
}
