package com.ipartek.formacion.uf2216.pojos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.TemporalAmount;
import java.util.Objects;

public class ProductoPerecedero extends Producto {

	private static final long serialVersionUID = 3261240510552464385L;

	private static final TemporalAmount TIEMPO_CADUCIDAD = Period.of(2,  0,  0);
	
	private LocalDate caducidad;
	
	public ProductoPerecedero(Long id, String nombre, BigDecimal precio, Integer stock, Boolean disponible,
			LocalDate caducidad) {
		super(id, nombre, precio, stock, disponible);
		setCaducidad(caducidad);
	}
	
	public ProductoPerecedero(String nombre, BigDecimal precio, Integer stock, Boolean disponible,
			LocalDate caducidad) {
		this(null, nombre, precio, stock, disponible, caducidad);
	}
	
	public ProductoPerecedero() {
		this(null, NOMBRE_POR_DEFECTO, BigDecimal.ZERO, stockMinimo, false, LocalDate.now().plus(TIEMPO_CADUCIDAD));
	}

	public LocalDate getCaducidad() {
		return caducidad;
	}

	public void setCaducidad(LocalDate caducidad) {
		if(caducidad == null || caducidad.isBefore(LocalDate.now())) {
			throw new RuntimeException("El producto no puede estar caducado y debe tener fecha de caducidad");
		}
		
		this.caducidad = caducidad;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(caducidad);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductoPerecedero other = (ProductoPerecedero) obj;
		return Objects.equals(caducidad, other.caducidad);
	}

	@Override
	public String toString() {
		return "ProductoPerecedero [caducidad=" + caducidad + ", getId()=" + getId() + ", getNombre()=" + getNombre()
				+ ", getPrecio()=" + getPrecio() + ", getStock()=" + getStock() + ", getDisponible()=" + getDisponible()
				+ "]" + super.toString();
	}
	
	
	
	
}
