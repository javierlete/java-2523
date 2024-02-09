package com.ipartek.formacion.uf2214.entidades;

import java.util.Objects;
import java.util.Set;

public class Usuario {
	private Long id;
	private String nickName;
	private Rol rol;
	private Set<Post> posts;
	private Set<Usuario> seguidorDe;
	private Set<Usuario> bloqueados;
	private Set<Post> postsQueLeGustan;
	private Set<Post> postGuardados;
	private Set<Denuncia> denuncias;

	public Usuario() {
	}

	public Usuario(Long id, String nickName, Rol rol, Set<Post> posts, Set<Usuario> seguidorDe, Set<Usuario> bloqueados,
			Set<Post> postsQueLeGustan, Set<Post> postGuardados, Set<Denuncia> denuncias) {
		super();
		this.id = id;
		this.nickName = nickName;
		this.rol = rol;
		this.posts = posts;
		this.seguidorDe = seguidorDe;
		this.bloqueados = bloqueados;
		this.postsQueLeGustan = postsQueLeGustan;
		this.postGuardados = postGuardados;
		this.denuncias = denuncias;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

	public Set<Usuario> getSeguidorDe() {
		return seguidorDe;
	}

	public void setSeguidorDe(Set<Usuario> seguidorDe) {
		this.seguidorDe = seguidorDe;
	}

	public Set<Usuario> getBloqueados() {
		return bloqueados;
	}

	public void setBloqueados(Set<Usuario> bloqueados) {
		this.bloqueados = bloqueados;
	}

	public Set<Post> getPostsQueLeGustan() {
		return postsQueLeGustan;
	}

	public void setPostsQueLeGustan(Set<Post> postsQueLeGustan) {
		this.postsQueLeGustan = postsQueLeGustan;
	}

	public Set<Post> getPostGuardados() {
		return postGuardados;
	}

	public void setPostGuardados(Set<Post> postGuardados) {
		this.postGuardados = postGuardados;
	}

	public Set<Denuncia> getDenuncias() {
		return denuncias;
	}

	public void setDenuncias(Set<Denuncia> denuncias) {
		this.denuncias = denuncias;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bloqueados, denuncias, id, nickName, postGuardados, posts, postsQueLeGustan, rol,
				seguidorDe);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(bloqueados, other.bloqueados) && Objects.equals(denuncias, other.denuncias)
				&& Objects.equals(id, other.id) && Objects.equals(nickName, other.nickName)
				&& Objects.equals(postGuardados, other.postGuardados) && Objects.equals(posts, other.posts)
				&& Objects.equals(postsQueLeGustan, other.postsQueLeGustan) && Objects.equals(rol, other.rol)
				&& Objects.equals(seguidorDe, other.seguidorDe);
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nickName=" + nickName + ", rol=" + rol + ", posts=" + posts + ", seguidorDe="
				+ seguidorDe + ", bloqueados=" + bloqueados + ", postsQueLeGustan=" + postsQueLeGustan
				+ ", postGuardados=" + postGuardados + ", denuncias=" + denuncias + "]";
	}

}
