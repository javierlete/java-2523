package com.ipartek.formacion.recetas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class RecetasApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(RecetasApplication.class, args);
	}

	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println(encoder.encode("contra"));
		System.out.println(encoder.encode("perezoso"));
		
		System.out.println(encoder.matches("perezoso", "$2a$10$vYE6rf3TVQ4rVxDoaz8cvO8bSCyG0tOSroqDEUAF5plkhUn/pk1my"));
	}
	
	

}
