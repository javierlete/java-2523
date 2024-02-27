package com.ipartek.formacion.mf0966ejemplo.rest;

import java.util.ArrayList;

import com.ipartek.formacion.mf0966ejemplo.accesodatos.CocheAccesoDatos;
import com.ipartek.formacion.mf0966ejemplo.dtos.CocheDTO;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

@Path("/coches")
public class CocheRest {
	@GET
	public ArrayList<CocheDTO> obtenerTodos() {
		return CocheAccesoDatos.obtenerTodos();
	}
	
	@GET
	@Path("/buscar")
	public ArrayList<CocheDTO> obtenerTodos(@QueryParam("marca") String marca) {
		return CocheAccesoDatos.buscarPorMarca(marca);
	}
	
}
