package com.ipartek.formacion.recetas.configuraciones;

import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.ipartek.formacion.recetas.dtos.Favoritos;

@ControllerAdvice
@Configuration
public class SesionAModelo {
	private Favoritos favoritos;
	
	public SesionAModelo(Favoritos favoritos) {
		this.favoritos = favoritos;
	}
	
	@ModelAttribute
	private void favoritos(Model modelo) {
		modelo.addAttribute(favoritos);
	}
}
