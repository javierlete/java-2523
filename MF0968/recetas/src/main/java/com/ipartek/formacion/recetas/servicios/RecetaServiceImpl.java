package com.ipartek.formacion.recetas.servicios;

import org.springframework.beans.factory.annotation.Autowired;

import com.ipartek.formacion.recetas.entidades.Ingrediente;
import com.ipartek.formacion.recetas.entidades.Plato;
import com.ipartek.formacion.recetas.entidades.PlatoIngrediente;
import com.ipartek.formacion.recetas.repositorios.IngredienteRepository;
import com.ipartek.formacion.recetas.repositorios.PlatoIngredienteRepository;
import com.ipartek.formacion.recetas.repositorios.PlatoRepository;

public class RecetaServiceImpl implements RecetaService {

	@Autowired
	private IngredienteRepository ingredienteRepository;
	
	@Autowired
	private PlatoRepository platoRepository;
	
	@Autowired
	private PlatoIngredienteRepository platoIngredienteRepository;
	
	@Override
	public void anadirIngrediente(Ingrediente ingrediente) {
		ingredienteRepository.save(ingrediente);
	}

	@Override
	public void anadirPlato(Plato plato) {
		platoRepository.save(plato);
	}

	@Override
	public void anadirIngredienteAPlato(PlatoIngrediente platoIngrediente) {
		platoIngredienteRepository.save(platoIngrediente);
	}

	@Override
	public Iterable<Plato> listadoPlatos() {
		return platoRepository.findAll();
	}
}
