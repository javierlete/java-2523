package com.ipartek.formacion.uf2215.rest;

import com.ipartek.formacion.uf2214.accesodatos.UsuarioAccesoDatos;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

@Path("/")
public class TuiterRest {
	@Path("/megusta")
	@POST
	public void meGusta(@QueryParam("usuario") Long idUsuario, @QueryParam("post") Long idPost) {
		UsuarioAccesoDatos.meGusta(idUsuario, idPost);
	}
}
