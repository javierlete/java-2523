package com.ipartek.formacion.uf2213.dtos;

import java.time.LocalDate;

public record ClienteDTO(Long id, String dni, Integer dniDiferencial, String nombre, String apellidos,
		LocalDate fechaNacimiento) {
}
