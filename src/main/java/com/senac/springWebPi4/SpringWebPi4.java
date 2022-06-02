package com.senac.springWebPi4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringWebPi4 {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebPi4.class, args);
                 String encode = new BCryptPasswordEncoder().encode("12345");
                 System.out.println(encode);
               
	}

}
