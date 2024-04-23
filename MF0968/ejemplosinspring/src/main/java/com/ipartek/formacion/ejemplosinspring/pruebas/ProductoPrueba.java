package com.ipartek.formacion.ejemplosinspring.pruebas;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.ipartek.formacion.ejemplosinspring.entidades.Producto;
import com.ipartek.formacion.ejemplosinspring.global.Fabrica;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.validation.Validator;

public class ProductoPrueba {
	public static void main(String[] args) {
		Producto.ProductoBuilder builder = Producto.builder().precio(new BigDecimal(1234));

		builder.fechaCaducidad(LocalDate.now());

		Producto producto = builder.build();

		System.out.println(producto);

		System.out.println(((Validator) Fabrica.obtenerObjeto("validador")).validate(producto));

		Producto p = Producto.builder().precio(BigDecimal.ONE).nombre("Prueba").build();

		System.out.println(p);

		EntityManager em = (EntityManager) Fabrica.obtenerObjeto("em");
		EntityTransaction t = em.getTransaction();
		t.begin();

		em.persist(p);

		t.commit();
	}
}
