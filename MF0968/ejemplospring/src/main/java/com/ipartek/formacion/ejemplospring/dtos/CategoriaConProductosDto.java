package com.ipartek.formacion.ejemplospring.dtos;

import java.util.Set;

public record CategoriaConProductosDto(Long id, String codigo, String nombre, String descripcion, Set<ProductoDto> productos) {

}
