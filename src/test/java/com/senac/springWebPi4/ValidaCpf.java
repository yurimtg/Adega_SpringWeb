package com.senac.springWebPi4;

import com.senac.springWebPi4.service.ClienteService;

public class ValidaCpf {

	public static void main(String[] args) {

		ClienteService clienteService = new ClienteService();
		
		String cpf = "465.627.588-31";
		boolean valido = clienteService.valida(cpf);
		
		System.out.println(valido);
	}

}
