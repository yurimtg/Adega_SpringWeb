package com.senac.springWebPi4.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nomeProduto;
    private long qtd;
    private String status;
    private String descricao;
    private String categoria;
    
    public Produto() {
	}

	public Produto(String nomeProduto, long qtd, String status, String descricao, String categoria) {
		this.nomeProduto = nomeProduto;
		this.qtd = qtd;
		this.status = status;
		this.descricao = descricao;
                this.categoria = categoria;
	}

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public long getQtd() {
		return qtd;
	}

	public void setQtd(long qtd) {
		this.qtd = qtd;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
    public String toString() {
        return "Produto{" + "id=" + id + ", nomeProduto=" + nomeProduto + ", qtd=" + qtd + ", status=" + status + ", descricao=" + descricao + "categoria="+ categoria+ '}';
    }
      
}
