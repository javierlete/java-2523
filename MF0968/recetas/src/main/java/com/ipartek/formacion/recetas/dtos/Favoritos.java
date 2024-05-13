package com.ipartek.formacion.recetas.dtos;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.ipartek.formacion.recetas.entidades.Plato;

import lombok.Data;

@Component
@SessionScope
@Data
public class Favoritos {
	private Map<Long, Plato> platos = new HashMap<>();
}
