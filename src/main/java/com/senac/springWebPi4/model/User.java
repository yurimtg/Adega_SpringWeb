package com.senac.springWebPi4.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String username;
    private String password;
    private Boolean enabled;
    
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;
    
    public User() {
	}

	public User(String name, String username, String password, Boolean enabled, TipoUsuario tipoUsuario) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.tipoUsuario = tipoUsuario;
	}

	public long getId() {
		return id;
	}
    
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Boolean getEnabled() {
		return enabled;
	}
	
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}
	
	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password + ", enabled="
				+ enabled + ", tipoUsuario=" + tipoUsuario + "]";
	}
	
}
