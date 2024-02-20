package com.ipartek.formacion.uf2214.entidades;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "posts")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "fecha", nullable = false)
	private LocalDateTime fecha;

	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false, foreignKey = @ForeignKey(name = "FK_post_usuario"))
	private Usuario usuario;

	@Column(name = "texto", length = 240, nullable = false)
	private String texto;

	@ManyToMany(mappedBy = "postsQueLeGustan")
	private Set<Usuario> gustaA;

	public Post() {
	}

	public Post(Usuario usuario, String texto) {
		super();
		this.usuario = usuario;
		this.texto = texto;
		this.fecha = LocalDateTime.now();
	}

	public Post(Long id, LocalDateTime fecha, Usuario usuario, String texto, Set<Usuario> gustaA) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.usuario = usuario;
		this.texto = texto;
		this.gustaA = gustaA;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Set<Usuario> getGustaA() {
		return gustaA;
	}

	public void setGustaA(Set<Usuario> gustaA) {
		this.gustaA = gustaA;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fecha, gustaA, id, texto, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		return Objects.equals(fecha, other.fecha) && Objects.equals(gustaA, other.gustaA)
				&& Objects.equals(id, other.id) && Objects.equals(texto, other.texto)
				&& Objects.equals(usuario, other.usuario);
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", fecha=" + fecha + ", usuario=" + usuario + ", texto=" + texto + "]";
	}
}
