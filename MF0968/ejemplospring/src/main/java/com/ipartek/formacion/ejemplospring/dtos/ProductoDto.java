package com.ipartek.formacion.ejemplospring.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProductoDto(Long id, String nombre, BigDecimal precio, LocalDate fechaCaducidad, Integer stock) {

}
