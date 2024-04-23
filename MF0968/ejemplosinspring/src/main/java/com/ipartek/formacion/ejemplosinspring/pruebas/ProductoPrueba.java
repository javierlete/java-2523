package com.ipartek.formacion.ejemplosinspring.pruebas;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.ipartek.formacion.ejemplosinspring.entidades.Producto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class ProductoPrueba {
	public static void main(String[] args) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql");
		
		Producto.ProductoBuilder builder = Producto.builder().precio(new BigDecimal(1234));

		builder.fechaCaducidad(LocalDate.now());

		Producto producto = builder.build();

		System.out.println(producto);
		
		System.out.println(validator.validate(producto));

		Producto p = Producto.builder().precio(BigDecimal.ONE).nombre("Prueba").build();

		System.out.println(p);
		
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction t = em.getTransaction();
		t.begin();
		
		em.persist(p);
		
		t.commit();
	}
}
