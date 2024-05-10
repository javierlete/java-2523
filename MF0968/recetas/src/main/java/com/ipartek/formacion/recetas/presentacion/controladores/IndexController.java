package com.ipartek.formacion.recetas.presentacion.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ipartek.formacion.recetas.entidades.Ingrediente;
import com.ipartek.formacion.recetas.entidades.Plato;
import com.ipartek.formacion.recetas.entidades.PlatoIngrediente;
import com.ipartek.formacion.recetas.servicios.RecetaService;

import jakarta.validation.Valid;
import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping("/")
public class IndexController {
	private static final String MODELO_NIVELES = "niveles";
	private static final String MODELO_TIPOS = "tipos";
	
	private static final String MODELO_PLATO = "plato";
	private static final String VISTA_PLATO = "plato";
	
	private static final String MODELO_INGREDIENTES = "ingredientes";
	private static final String VISTA_INGREDIENTES = "ingredientes";
	private static final String RUTA_INGREDIENTES = "ingredientes";
	
	private RecetaService servicio;
	
	public IndexController(RecetaService servicio) {
		this.servicio = servicio;
	}

	@GetMapping(RUTA_INGREDIENTES)
	public String listadoIngredientes(Model modelo) {
		modelo.addAttribute(MODELO_INGREDIENTES, servicio.listarIngredientes());
		return VISTA_INGREDIENTES;
	}

	@GetMapping("ingrediente")
	public String formularioIngrediente(Ingrediente ingrediente) {
		return "ingrediente";
	}

	@PostMapping("ingrediente")
	public String postIngrediente(@Valid Ingrediente ingrediente, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "ingrediente";
		}

		servicio.anadirIngrediente(ingrediente);

		return "redirect:/ingredientes";
	}

	@GetMapping("plato")
	public String formularioPlato(Plato plato, Model modelo) {
		modelo.addAttribute(MODELO_NIVELES, servicio.listarDificultades());
		modelo.addAttribute(MODELO_TIPOS, servicio.listarTiposCocina());

		return VISTA_PLATO;
	}

	@GetMapping("plato/{idPlato}")
	public String formularioPlatoConId(@PathVariable Long idPlato, Model modelo) {
		modelo.addAttribute(MODELO_PLATO, servicio.verPlato(idPlato));
		modelo.addAttribute(MODELO_NIVELES, servicio.listarDificultades());
		modelo.addAttribute(MODELO_TIPOS, servicio.listarTiposCocina());

		return VISTA_PLATO;
	}

	@PostMapping("plato")
	public String postPlato(@Valid Plato plato, BindingResult bindingResult, Model modelo) {
		if (bindingResult.hasErrors()) {
			modelo.addAttribute(MODELO_NIVELES, servicio.listarDificultades());
			modelo.addAttribute(MODELO_TIPOS, servicio.listarTiposCocina());
			return VISTA_PLATO;
		}

		if (plato.getId() == null) {
			servicio.anadirPlato(plato);
		} else {
			servicio.modificarPlato(plato);
		}

		return "redirect:/platos";
	}

	@GetMapping("platos/{id}/borrar")
	public String borrarPlato(@PathVariable Long id) {
		servicio.borrarPlato(id);
		
		return "redirect:/platos";
	}
	
	@GetMapping({ "/", "platos" })
	public String listadoPlatos(Model modelo) {
		modelo.addAttribute("platos", servicio.listadoPlatos());

		return VISTA_PLATO;
	}

	@GetMapping("plato/{id}/ingredientes")
	public String formularioIngredientes(@PathVariable Long id, PlatoIngrediente platoIngrediente, Model modelo) {
		modelo.addAttribute(MODELO_PLATO, servicio.verPlato(id));
		modelo.addAttribute("platoIngredientes", servicio.verIngredientesPlato(id));
		modelo.addAttribute(VISTA_INGREDIENTES, servicio.listarIngredientes());

		return "plato-ingredientes";
	}

	@PostMapping("plato/{idPlato}/ingredientes")
	public String postIngredientes(@PathVariable Long idPlato, @Valid PlatoIngrediente platoIngrediente,
			BindingResult bindingResult, Model modelo) {
		// TODO revisar el proceso

		log.fine(platoIngrediente.toString());

		var plato = Plato.builder().id(idPlato).build();
		platoIngrediente.setPlato(plato);

		log.fine(platoIngrediente.toString());

		if (bindingResult.hasErrors()) {
			log.fine(bindingResult.toString());

			modelo.addAttribute(MODELO_PLATO, servicio.verPlato(idPlato));
			modelo.addAttribute("platoIngredientes", servicio.verIngredientesPlato(idPlato));
			modelo.addAttribute(VISTA_INGREDIENTES, servicio.listarIngredientes());

			return "plato-ingredientes";
		}

		servicio.anadirIngredienteAPlato(platoIngrediente);

		log.fine(platoIngrediente.toString());

		return "redirect:/plato/" + idPlato + "/ingredientes";
	}

	@GetMapping("login")
	public String login() {
		return "login";
	}

}
