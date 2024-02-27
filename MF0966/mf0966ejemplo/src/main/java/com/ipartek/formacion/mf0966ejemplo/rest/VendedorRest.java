package com.ipartek.formacion.mf0966ejemplo.rest;

import java.util.ArrayList;

import com.ipartek.formacion.mf0966ejemplo.accesodatos.VendedorAccesoDatos;
import com.ipartek.formacion.mf0966ejemplo.dtos.VendedorDTO;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/vendedores")
public class VendedorRest {
	@GET
	public ArrayList<VendedorDTO> obtenerTodos() {
		return VendedorAccesoDatos.obtenerTodos();
	}
	
	@GET
	@Path("/{id}")
	public VendedorDTO obtenerPorId(@PathParam("id") Long id) {
		return VendedorAccesoDatos.obtenerPorId(id);
	}
	
	@POST
	public VendedorDTO insertar(VendedorDTO vendedor) {
		return VendedorAccesoDatos.insertar(vendedor);
	}
	
	@PUT
	@Path("/{id}")
	public VendedorDTO modificar(@PathParam("id") Long id, VendedorDTO vendedor) {
		return VendedorAccesoDatos.modificar(id, vendedor);
	}
	
	@DELETE
	@Path("/{id}")
	public void borrar(@PathParam("id") Long id) {
		VendedorAccesoDatos.borrar(id);
	}
	
}
