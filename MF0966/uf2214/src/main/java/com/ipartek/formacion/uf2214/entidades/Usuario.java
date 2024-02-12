package com.ipartek.formacion.uf2214.entidades;

import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "nick_name", nullable = false, length = 50)
	private String nickName;

	@ManyToOne
	@JoinColumn(name = "rol_id", nullable = false, foreignKey = @ForeignKey(name = "FK_usuario_rol"))
	private Rol rol;

	@OneToMany(mappedBy = "usuario")
	private Set<Post> posts;

	@ManyToMany
	@JoinTable(name = "usuarios_seguidores", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "seguidor_de_id"), foreignKey = @ForeignKey(name = "FK_usuario_sigue_a"), inverseForeignKey = @ForeignKey(name = "FK_usuario_seguido"))
	private Set<Usuario> seguidorDe;

	@ManyToMany
	@JoinTable(name = "usuarios_bloqueados", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "bloqueado_id"), foreignKey = @ForeignKey(name = "FK_usuario_bloquea_a"), inverseForeignKey = @ForeignKey(name = "FK_usuario_bloqueado"))
	private Set<Usuario> bloqueados;

	@ManyToMany
	@JoinTable(name = "posts_gustan", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "post_id"), foreignKey = @ForeignKey(name = "FK_usuario_gusta_post"), inverseForeignKey = @ForeignKey(name = "FK_usuario_post_gusta"))
	private Set<Post> postsQueLeGustan;

	@ManyToMany
	@JoinTable(name = "posts_guardados", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "post_id"), foreignKey = @ForeignKey(name = "FK_usuario_guarda_post"), inverseForeignKey = @ForeignKey(name = "FK_usuario_post_guarda"))
	private Set<Post> postGuardados;

	@OneToMany(mappedBy = "quien")
	private Set<Denuncia> denunciasRealizadas;

	public Usuario() {
	}

	public Usuario(Long id, String nickName, Rol rol, Set<Post> posts, Set<Usuario> seguidorDe, Set<Usuario> bloqueados,
			Set<Post> postsQueLeGustan, Set<Post> postGuardados, Set<Denuncia> denunciasRealizadas) {
		super();
		this.id = id;
		this.nickName = nickName;
		this.rol = rol;
		this.posts = posts;
		this.seguidorDe = seguidorDe;
		this.bloqueados = bloqueados;
		this.postsQueLeGustan = postsQueLeGustan;
		this.postGuardados = postGuardados;
		this.denunciasRealizadas = denunciasRealizadas;
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

	public Set<Denuncia> getDenunciasRealizadas() {
		return denunciasRealizadas;
	}

	public void setDenunciasRealizadas(Set<Denuncia> denunciasRealizadas) {
		this.denunciasRealizadas = denunciasRealizadas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bloqueados, denunciasRealizadas, id, nickName, postGuardados, posts, postsQueLeGustan, rol,
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
		return Objects.equals(bloqueados, other.bloqueados)
				&& Objects.equals(denunciasRealizadas, other.denunciasRealizadas) && Objects.equals(id, other.id)
				&& Objects.equals(nickName, other.nickName) && Objects.equals(postGuardados, other.postGuardados)
				&& Objects.equals(posts, other.posts) && Objects.equals(postsQueLeGustan, other.postsQueLeGustan)
				&& Objects.equals(rol, other.rol) && Objects.equals(seguidorDe, other.seguidorDe);
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nickName=" + nickName + ", rol=" + rol + ", posts=" + posts + ", seguidorDe="
				+ seguidorDe + ", bloqueados=" + bloqueados + ", postsQueLeGustan=" + postsQueLeGustan
				+ ", postGuardados=" + postGuardados + ", denunciasRealizadas=" + denunciasRealizadas + "]";
	}

}
