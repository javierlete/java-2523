package com.ipartek.formacion.recetas.rest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ipartek.formacion.recetas.dtos.PlatoFormDto;
import com.ipartek.formacion.recetas.entidades.Ingrediente;
import com.ipartek.formacion.recetas.entidades.Plato;
import com.ipartek.formacion.recetas.servicios.RecetaService;

@RestController
@RequestMapping("/api/recetas")
public class RecetaRest {
	private RecetaService servicio;
	
	public RecetaRest(RecetaService servicio) {
		super();
		this.servicio = servicio;
	}

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
	
	@GetMapping("plato/form")
	public PlatoFormDto platoForm() {
		var dificultades = servicio.listarDificultades();
		var tiposCocina = servicio.listarTiposCocina();
		
		return new PlatoFormDto(dificultades, tiposCocina);
	}
	
	@PostMapping("plato")
	public Plato platoPost(@RequestBody Plato plato) {
		servicio.anadirPlato(plato);
		return plato;
	}
	
	@DeleteMapping("plato/{id}")
	public void platoBorrar(@PathVariable Long id) {
		servicio.borrarPlato(id);
	}
	
	@GetMapping("favoritos")
	public Iterable<Plato> favoritos() {
		return servicio.favoritos().getPlatos().values();
	}
	
	@PostMapping("favoritos/{id}")
	public Plato agregarFavorito(@PathVariable Long id) {
		return servicio.agregarFavorito(id);
	}
}
