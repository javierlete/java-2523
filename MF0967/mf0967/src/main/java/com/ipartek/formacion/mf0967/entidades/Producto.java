package com.ipartek.formacion.mf0967.entidades;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Producto {
	@Min(0)
	private Long id;
	
	@NotNull
	@Size(min = 3)
	private String nombre;
	
	@NotNull
	@Min(0)
	private BigDecimal precio;
	
	@URL
	private String urlFoto;

	public Producto() {
	}

	public Producto(String nombre, BigDecimal precio) {
		this(null, nombre, precio, null);
	}
	
	public Producto(String nombre, BigDecimal precio, String urlFoto) {
		this(null, nombre, precio, urlFoto);
	}
	
	public Producto(Long id, String nombre, BigDecimal precio, String urlFoto) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.urlFoto = urlFoto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public String getUrlFoto() {
		return urlFoto;
	}

	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((precio == null) ? 0 : precio.hashCode());
		result = prime * result + ((urlFoto == null) ? 0 : urlFoto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (precio == null) {
			if (other.precio != null)
				return false;
		} else if (!precio.equals(other.precio))
			return false;
		if (urlFoto == null) {
			if (other.urlFoto != null)
				return false;
		} else if (!urlFoto.equals(other.urlFoto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", urlFoto=" + urlFoto + "]";
	}
}
