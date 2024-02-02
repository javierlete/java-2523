package com.ipartek.formacion.uf2213.entidades;

import java.math.BigDecimal;

public record Producto(Long id, String nombre, BigDecimal precio, Integer cantidad) {

}
