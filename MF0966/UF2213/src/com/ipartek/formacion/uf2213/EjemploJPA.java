package com.ipartek.formacion.uf2213;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.ipartek.formacion.uf2213.accesodatos.AccesoDatosJpa;
import com.ipartek.formacion.uf2213.entidades.Cliente;
import com.ipartek.formacion.uf2213.entidades.DetalleFactura;
import com.ipartek.formacion.uf2213.entidades.Factura;
import com.ipartek.formacion.uf2213.entidades.Producto;

public class EjemploJPA {

	public static void main(String[] args) {
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

		List<Cliente> clientes = AccesoDatosJpa.enTransaccion(em -> em
				.createQuery("select c from Cliente c left join fetch c.facturas f join fetch f.detalles d join fetch d.producto", Cliente.class).getResultList());
		
		for (Cliente cliente : clientes) {
			mostrarCliente(cliente);
		}
	}

	private static void mostrarCliente(Cliente cliente) {
		System.out.println("DATOS COMPLETOS DE CLIENTE");
		System.out.println("==========================");

		System.out.println(cliente);

		for (Factura f : cliente.getFacturas()) {
			System.out.println(f);

			for (DetalleFactura df : f.getDetalles()) {
				System.out.println(df);
			}
		}
	}

	@SuppressWarnings("unused")
	private static Cliente crearCliente() {
		Cliente javier = new Cliente(null, "12345678Z", null, "Javier", null, null);

		Producto producto1 = new Producto(null, "Producto1", new BigDecimal("11.11"), 10);
		Producto producto2 = new Producto(null, "Producto2", new BigDecimal("22.22"), 20);

		Factura factura = new Factura(null, "20240207", LocalDate.now(), javier);

		javier.getFacturas().add(factura);

		factura.getDetalles().add(new DetalleFactura(null, factura, producto1, 5));
		factura.getDetalles().add(new DetalleFactura(null, factura, producto2, 6));
		return javier;
	}
}
