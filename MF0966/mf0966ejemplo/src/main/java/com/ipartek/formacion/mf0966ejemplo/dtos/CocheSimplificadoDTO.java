package com.ipartek.formacion.mf0966ejemplo.dtos;

public record CocheSimplificadoDTO(Long id, String matricula, String marca, String modelo, VendedorDTO vendedor) {
	public CocheSimplificadoDTO(Long id, String matricula, String marca, String modelo, Long idVendedor, String nombreVendedor, String apellidosVendedor, String dniVendedor) {
		this(id, matricula, marca, modelo, new VendedorDTO(idVendedor, nombreVendedor, apellidosVendedor, dniVendedor));
	}
}
