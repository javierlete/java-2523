package com.ipartek.formacion.mf0966ejemplo.rest;

import java.util.List;

import com.ipartek.formacion.mf0966ejemplo.accesodatos.CocheAccesoDatosJpa;
import com.ipartek.formacion.mf0966ejemplo.dtos.CocheSimplificadoDTO;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

@Path("/jpa/coches")
public class CocheJpaRest {
	@GET
	public List<CocheSimplificadoDTO> obtenerTodos() {
		return CocheAccesoDatosJpa.obtenerTodos();
	}
	
	@GET
	@Path("/buscar")
	public List<CocheSimplificadoDTO> obtenerTodos(@QueryParam("marca") String marca) {
		return CocheAccesoDatosJpa.buscarPorMarca(marca);
	}
	
}
