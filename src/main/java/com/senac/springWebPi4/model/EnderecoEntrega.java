package com.senac.springWebPi4.model;

import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

public class EnderecoEntrega {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String logradouro;
    private int numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String UF;
    private String CEP;
    
}
