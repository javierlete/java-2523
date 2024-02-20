package com.ipartek.formacion.uf2215.rest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.TreeMap;

import com.ipartek.formacion.uf2215.servlets.Producto;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/productos")
@Produces(MediaType.APPLICATION_JSON)
public class ProductosRest {
	private static TreeMap<Long, Producto> productos = new TreeMap<>();

	static {
		productos.put(1L, new Producto(1L, "Producto1", new BigDecimal(11), LocalDate.of(2000, 1, 1)));
		productos.put(2L, new Producto(2L, "Producto2", new BigDecimal(22), LocalDate.of(2001, 1, 1)));
		productos.put(3L, new Producto(3L, "Producto3", new BigDecimal(33), LocalDate.of(2002, 1, 1)));
		productos.put(4L, new Producto(4L, "Producto4", new BigDecimal(44), LocalDate.of(2003, 1, 1)));
	}

	@GET
	public Iterable<Producto> getProductos() {
		return productos.values();
	}
	
	@GET
	@Path("/{id}")
	public Producto getProducto(@PathParam("id") Long id) {
		Producto p = productos.get(id);
		
		if(p == null) {
			throw new NotFoundException();
		} else {
			return p;
		}
	}
	
	@POST
	public Response postProducto(Producto producto) {
		Long id = productos.size() > 0 ? productos.lastKey() + 1L : 1L;
		
		producto.setId(id);
		
		productos.put(id, producto);
		
		return Response.status(Status.CREATED).entity(producto).build();
	}
	
	@PUT
	@Path("/{id}")
	public Producto putProducto(Producto producto, @PathParam("id") Long id) {
		productos.put(id, producto);
		
		return producto;
	}
	
	@DELETE
	@Path("/{id}")
	public void deleteProducto(@PathParam("id") Long id) {
		productos.remove(id);
	}
}
