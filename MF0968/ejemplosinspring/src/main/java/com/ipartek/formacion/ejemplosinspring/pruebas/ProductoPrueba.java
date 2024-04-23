package com.ipartek.formacion.ejemplosinspring.pruebas;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.ipartek.formacion.ejemplosinspring.entidades.Producto;

public class ProductoPrueba {
	public static void main(String[] args) {
		Producto.ProductoBuilder builder = Producto.builder().nombre("Producto prueba").precio(new BigDecimal(1234));
		
		builder.fechaCaducidad(LocalDate.now());
		
		Producto producto = builder.build();
		
		System.out.println(producto);
		
		Producto p = Producto.builder().precio(BigDecimal.ONE).nombre("Prueba").build();
		
		System.out.println(p);
	}
}
