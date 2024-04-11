package com.ipartek.formacion.fullstack.rest;

import static com.ipartek.formacion.fullstack.configuraciones.Globales.*;

import com.ipartek.formacion.fullstack.dtos.CursoDto;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/cursos")
public class CursoRest {
	@GET
	public Iterable<CursoDto> get() {
		return daoCurso.obtenerTodos(); 
	}
	
	@GET
	@Path("/{id}")
	public CursoDto getId(@PathParam(value = "id") Long id) {
		return daoCurso.obtenerPorId(id);
	}
	
	@POST
	public CursoDto post(CursoDto curso) {
		return daoCurso.insertar(curso);
	}
	
	@PUT
	@Path("/{id}")
	public CursoDto put(@PathParam(value = "id") Long id, CursoDto curso) {
		return daoCurso.modificar(new CursoDto(id, curso.nombre()));
	}
	
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam(value = "id") Long id) {
		daoCurso.borrar(id);
	}
}
