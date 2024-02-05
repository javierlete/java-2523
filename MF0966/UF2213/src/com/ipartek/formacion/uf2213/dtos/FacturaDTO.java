package com.ipartek.formacion.uf2213.dtos;

import java.time.LocalDate;
import java.util.LinkedHashSet;

public record FacturaDTO(Long id, String numero, LocalDate fecha, ClienteDTO cliente, LinkedHashSet<ProductoDTO> productos) {
}
