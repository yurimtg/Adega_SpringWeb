package com.senac.springWebPi4.service;

import java.util.List;

import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.CPFValidator;

public class ClienteService {

	public boolean valida(String cpf) {
		
		CPFValidator cpfValidator = new CPFValidator();
		List<ValidationMessage> erros = cpfValidator.invalidMessagesFor(cpf);
		
		if(erros.size() > 0){ 
			return false; 
		}else{ 
			return true;
		}
		
	}
	
}
