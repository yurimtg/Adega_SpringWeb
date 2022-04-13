package com.senac.springWebPi4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringWebPi4 {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebPi4.class, args);
                System.out.println(new BCryptPasswordEncoder().encode("dani123"));
	}

}
