package com.ipartek.formacion.uf2216.pojos;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProductoDTO(Long id, String nombre, BigDecimal precio, LocalDate caducidad, Integer stock, Boolean disponible) {
	public ProductoDTO {
		System.out.println("En constructor principal");
	}
	
	public ProductoDTO(Long id, String nombre) {
		this(id, nombre, null, null, null, null);
	}

	public String getDatosBasicos() {
		return String.format("%s: %s", id, nombre);
	}
}
