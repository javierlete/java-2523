package com.ipartek.formacion.uf2213.dtos;

import java.math.BigDecimal;

public record ProductoDTO(Long id, String nombre, BigDecimal precio, Integer cantidad) {

}
