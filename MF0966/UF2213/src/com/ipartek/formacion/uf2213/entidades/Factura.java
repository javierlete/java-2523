package com.ipartek.formacion.uf2213.entidades;

import java.time.LocalDate;
import java.util.LinkedHashSet;

public record Factura(Long id, String numero, LocalDate fecha, Cliente cliente, LinkedHashSet<Producto> productos) {
}
