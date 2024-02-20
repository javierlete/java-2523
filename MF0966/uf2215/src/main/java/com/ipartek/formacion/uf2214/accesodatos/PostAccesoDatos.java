package com.ipartek.formacion.uf2214.accesodatos;

import static com.ipartek.formacion.uf2214.accesodatos.AccesoDatosJpa.enTransaccion;

import java.util.List;

import com.ipartek.formacion.uf2214.entidades.Post;

public class PostAccesoDatos {
	public static void insertar(Post post) {
		enTransaccion(em -> {
			em.persist(post);
			return null;
		});
	}
	
	public static List<Post> obtenerTodos() {
		return enTransaccion(em -> em.createQuery("select p from Post p join fetch p.usuario join fetch p.usuario.rol order by p.fecha desc", Post.class).getResultList());
	}
	
	public static List<Post> obtenerTodos(int offset, int tamanoPagina) {
		return enTransaccion(em -> {
			var consulta = em.createQuery("select p from Post p order by p.fecha desc", Post.class);

			consulta.setFirstResult(offset);
			consulta.setMaxResults(tamanoPagina);
			
			return consulta.getResultList();
		});
	}
	
	public static List<Post> obtenerPorUsuario(long idUsuario) {
		return enTransaccion(em -> em.createQuery("select p from Post p join fetch p.usuario join fetch p.usuario.rol where p.usuario.id = :idUsuario order by p.fecha desc", Post.class).setParameter("idUsuario", idUsuario).getResultList());
	}
}
