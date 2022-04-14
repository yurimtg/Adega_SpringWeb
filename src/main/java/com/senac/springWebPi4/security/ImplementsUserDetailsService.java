package com.senac.springWebPi4.security;

import com.senac.springWebPi4.model.User;
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
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		User usuario = user.findByEmail(login);
		
		if(usuario == null){
			throw new UsernameNotFoundException("Usuario não encontrado!");
		}
		return usuario;
	}

}
