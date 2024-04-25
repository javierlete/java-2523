package com.ipartek.formacion.ejemplospring;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ipartek.formacion.ejemplospring.entidades.Producto;
import com.ipartek.formacion.ejemplospring.servicios.AdminNegocio;
import com.ipartek.formacion.ejemplospring.servicios.UsuarioNegocio;

@SpringBootApplication
public class EjemplospringApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EjemplospringApplication.class, args);
	}

	@Autowired
	private AdminNegocio adminNegocio;
	
	@Autowired
	private UsuarioNegocio usuarioNegocio;
	
	@Override
	public void run(String... args) throws Exception {
		adminNegocio.insertarProducto(Producto.builder().nombre("Prueba").precio(new BigDecimal(1234)).build());
		
		for(var p: usuarioNegocio.obtenerProductos()) {
			System.out.println(p);
		}
	}

//	@Autowired
//	private ProductoRepository repo;
//	
//	@Override
//	public void run(String... args) throws Exception {
//		repo.save(Producto.builder().nombre("Prueba").precio(new BigDecimal(1234)).build());
//		
//		for(var p: repo.findAll()) {
//			System.out.println(p);
//		}
//		
//		for(var p: repo.findByNombreContains("ru")) {
//			System.out.println(p);
//		}
//		
//		for(var p: repo.findByPrecioBetween(new BigDecimal(1), new BigDecimal(2000))) {
//			System.out.println(p);
//		}
//		
//	}
}
