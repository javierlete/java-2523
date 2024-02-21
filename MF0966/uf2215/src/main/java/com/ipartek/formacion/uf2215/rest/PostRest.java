package com.ipartek.formacion.uf2215.rest;

import java.util.List;

import com.ipartek.formacion.uf2214.accesodatos.PostAccesoDatos;
import com.ipartek.formacion.uf2214.dtos.PostDTO;
import com.ipartek.formacion.uf2214.entidades.Post;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/posts")
public class PostRest {
	@GET
	public List<PostDTO> obtenerTodos() {
		return PostAccesoDatos.obtenerTodos();
	}
	
	@POST
	public Response enviarPost(Post post) {
		PostAccesoDatos.insertar(post);
		return Response.status(Status.CREATED).entity(post).build();
	}
}
