package com.senac.springWebPi4.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority {

    @Id
    private String nomeRole;

    @ManyToMany(mappedBy = "roles")
    private List<Usuario> usuarios;

    @ManyToMany(mappedBy = "roles")
    private List<Cliente> Clientes;
    

    public String getNomeRole() {
        return nomeRole;
    }

    public void setNomeRole(String nomeRole) {
        this.nomeRole = nomeRole;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Cliente> getClientes() {
        return Clientes;
    }

    public void setClientes(List<Cliente> Clientes) {
        this.Clientes = Clientes;
    }
    
    @Override
    public String getAuthority() {
        // TODO Auto-generated method stub
        return this.nomeRole;
    }

}
