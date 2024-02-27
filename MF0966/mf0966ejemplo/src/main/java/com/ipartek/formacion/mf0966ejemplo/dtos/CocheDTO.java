package com.ipartek.formacion.mf0966ejemplo.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CocheDTO(Long id, String matricula, String marca, String modelo, LocalDate fechaFabricacion,
		BigDecimal precio, Integer stock, VendedorDTO vendedor) {

}
