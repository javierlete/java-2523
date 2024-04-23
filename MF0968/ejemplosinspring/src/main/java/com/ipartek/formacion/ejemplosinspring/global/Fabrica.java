package com.ipartek.formacion.ejemplosinspring.global;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;

public class Fabrica {
	private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql");
	
	public static Object obtenerObjeto(String tipo) {
		switch(tipo) {
		case "validador": 
			return factory.getValidator();
		case "em":
			return emf.createEntityManager();
		default:
			throw new UnsupportedOperationException("NO SE CONOCE EL TIPO RECIBIDO: " + tipo);
		}
	}
}
