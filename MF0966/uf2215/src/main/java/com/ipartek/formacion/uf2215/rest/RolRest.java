package com.ipartek.formacion.uf2215.rest;

import java.util.List;

import com.ipartek.formacion.uf2214.accesodatos.RolAccesoDatos;
import com.ipartek.formacion.uf2214.entidades.Rol;

import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/roles")
public class RolRest {
	@GET
	public List<Rol> obtenerTodos() {
		return RolAccesoDatos.obtenerTodos();
	}
	
	@GET
	@Path("/{id}")
	public Rol obtenerPorId(@PathParam("id") Long id) {
		Rol rol = RolAccesoDatos.obtenerPorId(id);
		
		if(rol == null) {
			throw new NotFoundException();
		}
		
		return rol;
	}
	
	@POST
	public Response insertar(Rol rol) {
		try {
			RolAccesoDatos.insertar(rol);
			return Response.status(Status.CREATED).entity(rol).build();
		} catch (PersistenceException e) {
			return Response.status(Status.BAD_REQUEST).entity("No se admiten duplicados").build();
		}
	}
	
	@PUT
	@Path("/{id}")
	public Rol modificar(Rol rol, @PathParam("id") Long id) {
		return RolAccesoDatos.modificar(rol);
	}
	
	@DELETE
	@Path("/{id}")
	public void borrar(@PathParam("id") Long id) {
		RolAccesoDatos.borrar(id);
	}
	
}
