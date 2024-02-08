package com.ipartek.formacion.uf2213.accesodatos;

import java.util.LinkedHashSet;

import com.ipartek.formacion.uf2213.dtos.ClienteDTO;
import com.ipartek.formacion.uf2213.dtos.FacturaDTO;
import com.ipartek.formacion.uf2213.dtos.ProductoDTO;
import com.ipartek.formacion.uf2213.entidades.Cliente;
import com.ipartek.formacion.uf2213.entidades.DetalleFactura;
import com.ipartek.formacion.uf2213.entidades.Factura;

public class FacturaAccesoDatosJpa {

	public static LinkedHashSet<FacturaDTO> obtenerPorIdCliente(Long id) {
		return AccesoDatosJpa.enTransaccion(em -> {
			var c = em.find(Cliente.class, id);
			var facturasDTO = new LinkedHashSet<FacturaDTO>();
			
			for (Factura f : em.createQuery("select f from Factura f join f.cliente c where c.id=:id", Factura.class)
					.setParameter("id", id).getResultList()) {
				FacturaDTO facturaDTO = factura2DTO(f, c);
				
				facturasDTO.add(facturaDTO);
			}

			return facturasDTO;
		});
	}

	public static LinkedHashSet<FacturaDTO> obtenerPorIdClienteConProductos(long id) {
		return AccesoDatosJpa.enTransaccion(em -> {
			var c = em.find(Cliente.class, id);
			var facturasDTO = new LinkedHashSet<FacturaDTO>();
			
			for (Factura f : em.createQuery("select f from Factura f join f.cliente c where c.id=:id", Factura.class)
					.setParameter("id", id).getResultList()) {
				FacturaDTO facturaDTO = factura2DTO(f, c);
				
				for (DetalleFactura df : em.createQuery(
						"select df from DetalleFactura df join fetch df.producto join df.factura f where f.id=:id",
						DetalleFactura.class).setParameter("id", f.getId()).getResultList()) {
					
					facturaDTO.productos().add(detalleFactura2productoDTO(df));
				}

				facturasDTO.add(facturaDTO);
			}

			return facturasDTO;
		});
	}

	private static ProductoDTO detalleFactura2productoDTO(DetalleFactura df) {
		return new ProductoDTO(df.getProducto().getId(), df.getProducto().getNombre(), df.getProducto().getPrecio(),
				df.getCantidad());
	}

	private static FacturaDTO factura2DTO(Factura f, Cliente c) {
		return new FacturaDTO(f.getId(), f.getNumero(), f.getFecha(), cliente2DTO(c),
				new LinkedHashSet<ProductoDTO>());
	}

	private static ClienteDTO cliente2DTO(Cliente c) {
		return new ClienteDTO(c.getId(), c.getDni(), c.getDniDiferencial(), c.getNombre(), c.getApellidos(), c.getFechaNacimiento());
	}

}
