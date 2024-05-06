package com.ipartek.formacion.recetas.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ipartek.formacion.recetas.entidades.Ingrediente;
import com.ipartek.formacion.recetas.entidades.Plato;
import com.ipartek.formacion.recetas.servicios.RecetaService;

@RestController
@RequestMapping("/api/recetas")
public class RecetaRest {
	@Autowired
	private RecetaService servicio;
	
	@GetMapping("/ingredientes")
	public Iterable<Ingrediente> ingredientes() {
		return servicio.listarIngredientes();
	}
	
	@GetMapping("/platos")
	public Iterable<Plato> platos() {
		return servicio.listadoPlatos();
	}
	
	@GetMapping("/platos/{id}")
	public Plato platoPorId(@PathVariable Long id) {
		return servicio.verPlato(id);
	}
	
	@PostMapping("/ingredientes")
	public Ingrediente postIngrediente(@RequestBody Ingrediente ingrediente) {
		servicio.anadirIngrediente(ingrediente);
		return ingrediente;
	}
}
