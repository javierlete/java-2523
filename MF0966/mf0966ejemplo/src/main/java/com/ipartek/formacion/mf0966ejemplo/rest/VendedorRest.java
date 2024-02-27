package com.ipartek.formacion.mf0966ejemplo.rest;

import java.util.ArrayList;

import com.ipartek.formacion.mf0966ejemplo.accesodatos.VendedorAccesoDatos;
import com.ipartek.formacion.mf0966ejemplo.dtos.VendedorDTO;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/vendedores")
public class VendedorRest {
	@GET
	public ArrayList<VendedorDTO> obtenerTodos() {
		return VendedorAccesoDatos.obtenerTodos();
	}
}
