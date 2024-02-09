package com.ipartek.formacion.uf2214.entidades;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class Post {
	private Long id;
	private LocalDate fecha;
	private Usuario usuario;
	private String texto;
	private Set<Usuario> gustaA;

	public Post() {
	}

	public Post(Long id, LocalDate fecha, Usuario usuario, String texto, Set<Usuario> gustaA) {
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

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
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
		return "Post [id=" + id + ", fecha=" + fecha + ", usuario=" + usuario + ", texto=" + texto + ", gustaA="
				+ gustaA + "]";
	}
}
