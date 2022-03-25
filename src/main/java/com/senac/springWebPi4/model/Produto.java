package com.senac.springWebPi4.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nomeProduto;
    private long qtd;
    private String status;
    private String descricao;
    

    @Override
    public String toString() {
        return "Produto{" + "id=" + id + ", nomeProduto=" + nomeProduto + ", qtd=" + qtd + ", status=" + status + ", descricao=" + descricao + '}';
    }
      
}
