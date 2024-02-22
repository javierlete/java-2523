package com.ipartek.formacion.uf2215.rest;

import java.util.List;

import com.ipartek.formacion.uf2214.accesodatos.UsuarioAccesoDatos;
import com.ipartek.formacion.uf2214.dtos.PostDTO;
import com.ipartek.formacion.uf2214.dtos.RolDTO;
import com.ipartek.formacion.uf2214.dtos.UsuarioDTO;
import com.ipartek.formacion.uf2214.entidades.Rol;
import com.ipartek.formacion.uf2214.entidades.Usuario;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/usuarios")
public class UsuarioRest {
	private static final long ROL_USUARIO_ID = 2L;

	@GET
	@Path("/{id}")
	public UsuarioDTO obtenerPorId(@PathParam("id") long id) {
		return UsuarioAccesoDatos.obtenerPorId(id);
	}
	
	@GET
	@Path("/buscar/nickName")
	public UsuarioDTO buscarPorNickName(@QueryParam("nickName") String nickName) {
		System.out.println(nickName);
		
		Usuario usuario = UsuarioAccesoDatos.buscarPorNickName(nickName);
		
		if(usuario == null) {
			throw new NotFoundException();
		}
		
		RolDTO rolDTO = new RolDTO(usuario.getRol().getId(), usuario.getRol().getNombre());
		
		return new UsuarioDTO(usuario.getId(), usuario.getNickName(), null, rolDTO);
	}
	
	@GET
	@Path("/{id}/seguidores")
	public List<UsuarioDTO> seguidores(@PathParam("id") Long id) {
		return UsuarioAccesoDatos.obtenerSeguidores(id);
	}

	@GET
	@Path("/{id}/seguidorde")
	public List<UsuarioDTO> seguidorDe(@PathParam("id") Long id) {
		return UsuarioAccesoDatos.obtenerSeguidorDe(id);
	}
	
	@GET
	@Path("/{id}/posts")
	public List<PostDTO> posts(@PathParam("id") Long id) {
		return UsuarioAccesoDatos.posts(id);
	}

	@GET
	@Path("/{id}/postsquelegustan")
	public List<PostDTO> postsQueLeGustan(@PathParam("id") Long id) {
		return UsuarioAccesoDatos.postsQueLeGustan(id);
	}
	
	@POST
	public Response insertar(Usuario usuario) {
		UsuarioAccesoDatos.insertar(usuario);
		
		return Response.status(Status.CREATED).entity(usuario).build();
	}
	
	@POST
	@Path("/registrar")
	public Response registrar(Usuario usuario) {
		usuario.setRol(new Rol(ROL_USUARIO_ID,  null));
		
		UsuarioAccesoDatos.insertar(usuario);
		
		return Response.status(Status.CREATED).entity(usuario).build();
	}
	
	@POST
	@Path("/{id}/sigue/{id_seguido}")
	public void sigue(@PathParam("id") long id, @PathParam("id_seguido") long idSeguido) {
		UsuarioAccesoDatos.agregarSeguidor(id, idSeguido);
	}
	
	@POST
	@Path("/{id}/bloquea/{id_bloqueado}")
	public void bloquea(@PathParam("id") long id, @PathParam("id_bloqueado") long idBloqueado) {
		UsuarioAccesoDatos.agregarBloqueado(id, idBloqueado);
	}
}
