package com.ipartek.formacion.uf2214.accesodatos;

import static com.ipartek.formacion.uf2214.accesodatos.AccesoDatosJpa.enTransaccion;

import java.util.List;

import com.ipartek.formacion.uf2214.dtos.PostDTO;
import com.ipartek.formacion.uf2214.entidades.Post;

public class PostAccesoDatos {
	public static void insertar(Post post) {
		enTransaccion(em -> {
			em.persist(post);
			return null;
		});
	}

	public static List<PostDTO> obtenerTodos() {
		return enTransaccion(em -> em.createQuery(
				"select p.id, p.usuario.nickName, p.fecha, p.texto, size(p.gustaA) from Post p order by p.fecha desc",
				PostDTO.class).getResultList());
	}

	public static List<PostDTO> buscar(Long idUsuario) {
		return enTransaccion(em -> em.createQuery(
				"""
				select p.id, p.usuario.nickName, p.fecha, p.texto, size(p.gustaA)
				from Post p
				where 
					p.usuario.id not in 
					(select b.id from Usuario u join u.bloqueados b where u.id = :id)
					and
					p.usuario.id not in
					(select u.id from Usuario u join u.bloqueados b where b.id = :id)
				order by p.fecha desc
				""",
//				"""
//				select p.id, p.usuario.nickName, p.fecha, p.texto, size(p.gustaA)
//				from Post p
//				where 
//					not
//					(
//						p.usuario.id in 
//						(select b.id from Usuario u join u.bloqueados b where u.id = :id)
//						or
//						p.usuario.id in
//						(select u.id from Usuario u join u.bloqueados b where b.id = :id)
//					)
//				order by p.fecha desc
//				""",
				PostDTO.class).setParameter("id", idUsuario).getResultList());
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
		return enTransaccion(em -> em.createQuery(
				"select p from Post p join fetch p.usuario join fetch p.usuario.rol where p.usuario.id = :idUsuario order by p.fecha desc",
				Post.class).setParameter("idUsuario", idUsuario).getResultList());
	}
}
