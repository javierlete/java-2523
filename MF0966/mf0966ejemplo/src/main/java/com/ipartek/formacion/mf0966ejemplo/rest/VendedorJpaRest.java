package com.ipartek.formacion.mf0966ejemplo.rest;

import java.util.List;

import com.ipartek.formacion.mf0966ejemplo.accesodatos.VendedorAccesoDatosJpa;
import com.ipartek.formacion.mf0966ejemplo.dtos.VendedorDTO;
import com.ipartek.formacion.mf0966ejemplo.entidades.Vendedor;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/jpa/vendedores")
public class VendedorJpaRest {
	@GET
	public List<VendedorDTO> obtenerTodos() {
		return VendedorAccesoDatosJpa.obtenerTodos();
	}
	
	@GET
	@Path("/{id}")
	public VendedorDTO obtenerPorId(@PathParam("id") Long id) {
		return VendedorAccesoDatosJpa.obtenerPorId(id);
	}
	
	@POST
	public Vendedor insertar(Vendedor vendedor) {
		return VendedorAccesoDatosJpa.insertar(vendedor);
	}
	
	@PUT
	@Path("/{id}")
	public Vendedor modificar(@PathParam("id") Long id, Vendedor vendedor) {
		return VendedorAccesoDatosJpa.modificar(id, vendedor);
	}
	
	@DELETE
	@Path("/{id}")
	public void borrar(@PathParam("id") Long id) {
		VendedorAccesoDatosJpa.borrar(id);
	}
	
}
