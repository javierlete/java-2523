package com.ipartek.formacion.uf2214;

import com.ipartek.formacion.uf2214.accesodatos.DenunciaAccesoDatos;
import com.ipartek.formacion.uf2214.accesodatos.PostAccesoDatos;
import com.ipartek.formacion.uf2214.accesodatos.RolAccesoDatos;
import com.ipartek.formacion.uf2214.accesodatos.UsuarioAccesoDatos;
import com.ipartek.formacion.uf2214.entidades.Denuncia;
import com.ipartek.formacion.uf2214.entidades.Post;
import com.ipartek.formacion.uf2214.entidades.Rol;
import com.ipartek.formacion.uf2214.entidades.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class App {
	public static final Rol ADMIN = new Rol(null, "ADMIN");
	public static final Rol USER = new Rol(null, "USER");
	
	public static void main(String[] args) {
		// inicialización
		RolAccesoDatos.insertar(ADMIN);
		RolAccesoDatos.insertar(USER);
		
		Rol rolAdmin = RolAccesoDatos.obtenerPorId(1);
		Rol rolUser = RolAccesoDatos.obtenerPorId(2);
		
		// registro
		UsuarioAccesoDatos.insertar(new Usuario("javierlete", "contra", rolAdmin));
		UsuarioAccesoDatos.insertar(new Usuario("pepeperez", "pepillo", rolUser));
		UsuarioAccesoDatos.insertar(new Usuario("juan", "contra", rolUser));
		UsuarioAccesoDatos.insertar(new Usuario("pedro", "contra", rolUser));
		
		// loguearse
		Usuario noexiste = UsuarioAccesoDatos.buscarPorNickName("noexiste");
		
		System.out.println(noexiste);
		
		Usuario admin = UsuarioAccesoDatos.buscarPorNickName("javierlete");
		Usuario pepe = UsuarioAccesoDatos.buscarPorNickName("pepeperez");
		Usuario juan = UsuarioAccesoDatos.buscarPorNickName("juan");
		Usuario pedro = UsuarioAccesoDatos.buscarPorNickName("pedro");
		
		System.out.println(admin);
		System.out.println(pepe);
		
		// crearPost
		Post post = new Post(pepe, "Nuevo post en la aplicación");
		
		PostAccesoDatos.insertar(post);
		PostAccesoDatos.insertar(new Post(pepe, "Otro post"));
		PostAccesoDatos.insertar(new Post(admin, "No admitimos quejas"));
		
		// todosLosPost
		var posts = PostAccesoDatos.obtenerTodos();
		
		for(Post p: posts) {
			System.out.println(p);
		}
		
		// postsDeUnUsuario
		posts = PostAccesoDatos.obtenerPorUsuario(pepe.getId());
		
		for(Post p: posts) {
			System.out.println(p);
		}
		
		// seguirA
		UsuarioAccesoDatos.agregarSeguidor(pepe.getId(), admin.getId());
		UsuarioAccesoDatos.agregarSeguidor(juan.getId(), admin.getId());
		UsuarioAccesoDatos.agregarSeguidor(juan.getId(), pedro.getId());
		UsuarioAccesoDatos.agregarSeguidor(admin.getId(), pedro.getId());
		UsuarioAccesoDatos.agregarSeguidor(pepe.getId(), pedro.getId());
		
		// dejarDeSeguirA
		UsuarioAccesoDatos.quitarSeguidor(juan.getId(), pedro.getId());
		
		// seguidoresDeUnUsuario
		var seguidoresDeAdmin = UsuarioAccesoDatos.obtenerSeguidores(admin.getId());
		
		for(Usuario u: seguidoresDeAdmin) {
			System.out.println(u);
		}
		
		var seguidoresDePedro = UsuarioAccesoDatos.obtenerSeguidores(pedro.getId());
		
		for(Usuario u: seguidoresDePedro) {
			System.out.println(u);
		}
		
		// bloquear
		UsuarioAccesoDatos.agregarBloqueado(juan.getId(), pedro.getId());
		
		// denunciar
		DenunciaAccesoDatos.insertar(new Denuncia(null, juan, pedro, "Es un gilipollas", post));
	}
	
	public static void mainPruebaSinAccesoDatos(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.ipartek.formacion.uf2214.entidades");
		
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction t = em.getTransaction();
		
		t.begin();
		
		em.persist(ADMIN);
		em.persist(USER);
		
		t.commit();
	}
}
