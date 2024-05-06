package com.ipartek.formacion.recetas.presentacion.controladores;

import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
@RequestMapping("/")
public class IndexController {
	@Autowired
	private RecetaService servicio;

	@GetMapping("ingredientes")
	public String listadoIngredientes(Model modelo) {
		modelo.addAttribute("ingredientes", servicio.listarIngredientes());
		return "ingredientes";
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
		modelo.addAttribute("niveles", servicio.listarDificultades());
		modelo.addAttribute("tipos", servicio.listarTiposCocina());

		return "plato";
	}

	@GetMapping("plato/{idPlato}")
	public String formularioPlatoConId(@PathVariable Long idPlato, Model modelo) {
		modelo.addAttribute("plato", servicio.verPlato(idPlato));
		modelo.addAttribute("niveles", servicio.listarDificultades());
		modelo.addAttribute("tipos", servicio.listarTiposCocina());

		return "plato";
	}

	@PostMapping("plato")
	public String postPlato(@Valid Plato plato, BindingResult bindingResult, Model modelo) {
		if (bindingResult.hasErrors()) {
			modelo.addAttribute("niveles", servicio.listarDificultades());
			modelo.addAttribute("tipos", servicio.listarTiposCocina());
			return "plato";
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

		return "platos";
	}

	@GetMapping("plato/{id}/ingredientes")
	public String formularioIngredientes(@PathVariable Long id, PlatoIngrediente platoIngrediente, Model modelo) {
		modelo.addAttribute("plato", servicio.verPlato(id));
		modelo.addAttribute("platoIngredientes", servicio.verIngredientesPlato(id));
		modelo.addAttribute("ingredientes", servicio.listarIngredientes());

		return "plato-ingredientes";
	}

	@PostMapping("plato/{idPlato}/ingredientes")
	public String postIngredientes(@PathVariable Long idPlato, @Valid PlatoIngrediente platoIngrediente,
			BindingResult bindingResult, Model modelo) {
		// TODO revisar el proceso

		System.out.println(platoIngrediente);

		var plato = Plato.builder().id(idPlato).build();
		platoIngrediente.setPlato(plato);

		System.out.println(platoIngrediente);

		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult);

			modelo.addAttribute("plato", servicio.verPlato(idPlato));
			modelo.addAttribute("platoIngredientes", servicio.verIngredientesPlato(idPlato));
			modelo.addAttribute("ingredientes", servicio.listarIngredientes());

			return "plato-ingredientes";
		}

		servicio.anadirIngredienteAPlato(platoIngrediente);

		System.out.println(platoIngrediente);

		return "redirect:/plato/" + idPlato + "/ingredientes";
	}

	@GetMapping("login")
	public String login() {
		return "login";
	}

}
