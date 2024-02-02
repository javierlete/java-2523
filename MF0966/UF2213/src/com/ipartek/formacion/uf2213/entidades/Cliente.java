package com.ipartek.formacion.uf2213.entidades;

import java.time.LocalDate;

public record Cliente(Long id, String dni, Integer dniDiferencial, String nombre, String apellidos,
		LocalDate fechaNacimiento) {
}
