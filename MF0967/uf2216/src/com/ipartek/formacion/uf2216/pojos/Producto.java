package com.ipartek.formacion.uf2216.pojos;

import java.math.BigDecimal;
import java.util.Objects;

public class Producto {
	// CONSTANTES
	public static final String NOMBRE_POR_DEFECTO = "PRODUCTO SIN DEFINIR";
	
	// VARIABLES ESTÁTICAS ("DE CLASE")
	protected static Integer stockMinimo = 0; 
	
	// VARIABLES DE INSTANCIA
	private Long id;
	private String nombre;
	private BigDecimal precio;
	private Integer stock;
	private Boolean disponible;

	// CONSTRUCTORES
	public Producto(Long id, String nombre, BigDecimal precio, Integer stock, Boolean disponible) {
		setId(id);
		setNombre(nombre);
		setPrecio(precio);
		setStock(stock);
		setDisponible(disponible);
	}
	
	// Constructor de copia
	public Producto(Producto producto) {
		this(producto.getId(), producto.getNombre(), producto.getPrecio(), producto.getStock(), producto.getDisponible());
	}
	
	public Producto(String nombre, BigDecimal precio, Integer stock, Boolean disponible) {
		this(null, nombre, precio, stock, disponible);
	}

	public Producto() {
		this(null, NOMBRE_POR_DEFECTO, BigDecimal.ZERO, stockMinimo, false);
	}

	// GETTERS Y SETTERS (MÉTODOS DE ACCESO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		if (id != null && id < 0) {
			throw new RuntimeException("No se admiten valores de id menores que 0");
		}

		this.id = id;
	}
	
	public void setId(String id) {
		if(id == null || id.isBlank()) {
			this.id = null;
			return;
		}
		
		this.id = Long.valueOf(id);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (nombre == null || nombre.isBlank()) {
			throw new RuntimeException("El nombre del producto es obligatorio");
		}

		this.nombre = nombre.trim();
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		if (precio == null || precio.compareTo(BigDecimal.ZERO) < 0) {
			throw new RuntimeException("El precio es obligatorio y debe ser positivo");
		}

		this.precio = precio;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		if(stock == null || stock < stockMinimo) {
			throw new RuntimeException("El stock es obligatorio y no puede ser menor que " + stockMinimo);
		}
		
		this.stock = stock;
	}

	public Boolean getDisponible() {
		return disponible;
	}

	public void setDisponible(Boolean disponible) {
		if(disponible == null) {
			throw new RuntimeException("Es obligatorio definir si está disponible o no");
		}
		
		this.disponible = disponible;
	}

	// Métodos estáticos ("de clase")
	public static Integer getStockMinimo() {
		return stockMinimo;
	}

	public static void setStockMinimo(Integer stockMinimo) {
		Producto.stockMinimo = stockMinimo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(disponible, id, nombre, precio, stock);
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
		return Objects.equals(disponible, other.disponible) && Objects.equals(id, other.id)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(precio, other.precio)
				&& Objects.equals(stock, other.stock);
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", stock=" + stock + ", disponible="
				+ disponible + "]";
	}

	@Override
	public Producto clone() {
		return new Producto(id, nombre, precio, stock, disponible);
	}
	
	

}
