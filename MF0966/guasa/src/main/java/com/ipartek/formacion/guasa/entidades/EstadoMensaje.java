package com.ipartek.formacion.guasa.entidades;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "estados_mensaje", uniqueConstraints = {
		@UniqueConstraint(name = "UK_mensaje_icono", columnNames = { "icono" }) })
public class EstadoMensaje {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "icono", length = 50, nullable = false)
	private String icono;

	@Column(name = "descripcion", length = 2000)
	@Lob
	private String descripcion;

	public EstadoMensaje(Long id, String icono, String descripcion) {
		super();
		this.id = id;
		this.icono = icono;
		this.descripcion = descripcion;
	}

	public EstadoMensaje() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIcono() {
		return icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(descripcion, icono, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EstadoMensaje other = (EstadoMensaje) obj;
		return Objects.equals(descripcion, other.descripcion) && Objects.equals(icono, other.icono)
				&& Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "EstadoMensaje [id=" + id + ", icono=" + icono + ", descripcion=" + descripcion + "]";
	}

}
