package com.senac.springWebPi4.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private String status; // ATIVO, INATIVO
    private String data; 

	public User() {
	}

	public User(String nome, String email, String telefone, String senha, String tipoUsuario, String status,
			String data) {
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.senha = senha;
		this.tipoUsuario = tipoUsuario;
		this.status = status;
		this.data = data;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
    
	 @Override
	    public String toString() {
	        return "User{" + "id=" + id + ", nome=" + nome + ", email=" + email + ", telefone=" + telefone + ", senha=" + senha + ", tipoUsuario=" + tipoUsuario + ", data=" + data + '}';
	 }
    
}
