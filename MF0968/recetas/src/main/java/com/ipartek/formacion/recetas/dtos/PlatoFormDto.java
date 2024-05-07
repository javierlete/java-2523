package com.ipartek.formacion.recetas.dtos;

import com.ipartek.formacion.recetas.entidades.Dificultad;
import com.ipartek.formacion.recetas.entidades.TipoCocina;

public record PlatoFormDto(Iterable<Dificultad> dificultades, Iterable<TipoCocina> tiposCocina) {

}
