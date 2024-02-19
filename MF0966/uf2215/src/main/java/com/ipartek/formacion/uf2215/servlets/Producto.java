package com.ipartek.formacion.uf2215.servlets;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Producto {
	private Long id;
	private String nombre;
	private BigDecimal precio;
	private LocalDate fechaDeCaducidad;

	public Producto() {
	}

	public Producto(Long id, String nombre, BigDecimal precio, LocalDate fechaDeCaducidad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.fechaDeCaducidad = fechaDeCaducidad;
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

	public LocalDate getFechaDeCaducidad() {
		return fechaDeCaducidad;
	}

	public void setFechaDeCaducidad(LocalDate fechaDeCaducidad) {
		this.fechaDeCaducidad = fechaDeCaducidad;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fechaDeCaducidad, id, nombre, precio);
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
		return Objects.equals(fechaDeCaducidad, other.fechaDeCaducidad) && Objects.equals(id, other.id)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(precio, other.precio);
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", fechaDeCaducidad="
				+ fechaDeCaducidad + "]";
	}
}