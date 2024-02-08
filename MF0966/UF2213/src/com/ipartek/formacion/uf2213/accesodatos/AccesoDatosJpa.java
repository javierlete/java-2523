package com.ipartek.formacion.uf2213.accesodatos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.function.Function;

import com.ipartek.formacion.uf2213.entidades.Cliente;
import com.ipartek.formacion.uf2213.entidades.DetalleFactura;
import com.ipartek.formacion.uf2213.entidades.Factura;
import com.ipartek.formacion.uf2213.entidades.Producto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class AccesoDatosJpa {
	private static EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("com.ipartek.formacion.uf2213.entidades");
			
	static {
		AccesoDatosJpa.enTransaccion(em -> {
			Producto producto1 = new Producto(null, "Producto1", new BigDecimal("11.11"), 10);
			Producto producto2 = new Producto(null, "Producto2", new BigDecimal("22.22"), 20);

			em.persist(producto1);
			em.persist(producto2);

			Cliente javier = new Cliente(null, "12345678Z", null, "Javier", null, null);

			em.persist(javier);

			Factura factura = new Factura(null, "20240207", LocalDate.now(), javier);

			factura.getDetalles().add(new DetalleFactura(null, factura, producto1, 5));
			factura.getDetalles().add(new DetalleFactura(null, factura, producto2, 6));

			em.persist(factura);

			return null;
		});
	}
	public static <T> T enTransaccion(Function<EntityManager, T> lambda) {
		T resultado;
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		try {
			transaction.begin();
			resultado = lambda.apply(entityManager);
			transaction.commit();
			
			return resultado;
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw e;
		} finally {
			entityManager.close();
		}
	}
	
	public static void abrirConexion() {}
	public static void cerrarConexion() {}
}
