package com.senac.springWebPi4.security;

import com.senac.springWebPi4.model.Cliente;
import com.senac.springWebPi4.model.Usuario;
import com.senac.springWebPi4.repository.ClienteRepository;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.senac.springWebPi4.repository.UserRepository;


@Repository
@Transactional
public class ImplementsUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository user;
        @Autowired
	private ClienteRepository cli;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Usuario usuario = user.findByEmail(login);
		Cliente cliente = cli.findByEmail(login);
		if(usuario != null){
			return usuario;
		}
                if(cliente != null){
                    return cliente;
                }
                else{
                    throw new UsernameNotFoundException("Usuario não encontrado!");
                }
	}

}
