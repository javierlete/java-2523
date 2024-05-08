package com.ipartek.formacion.recetas.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ipartek.formacion.recetas.entidades.Dificultad;
import com.ipartek.formacion.recetas.entidades.Ingrediente;
import com.ipartek.formacion.recetas.entidades.Plato;
import com.ipartek.formacion.recetas.entidades.PlatoIngrediente;
import com.ipartek.formacion.recetas.entidades.TipoCocina;
import com.ipartek.formacion.recetas.repositorios.DificultadRepository;
import com.ipartek.formacion.recetas.repositorios.IngredienteRepository;
import com.ipartek.formacion.recetas.repositorios.PlatoIngredienteRepository;
import com.ipartek.formacion.recetas.repositorios.PlatoRepository;
import com.ipartek.formacion.recetas.repositorios.TipoCocinaRepository;

import lombok.extern.java.Log;

@Log
@Service
public class RecetaServiceImpl implements RecetaService {

	@Autowired
	private PlatoIngredienteRepository platoIngredienteRepository;
	
	@Autowired
	private IngredienteRepository ingredienteRepository;
	
	@Autowired
	private PlatoRepository platoRepository;
	
	@Autowired
	private DificultadRepository dificultadRepository;
	
	@Autowired
	private TipoCocinaRepository tipoCocinaRepository;
	
	@Override
	public Iterable<Dificultad> listarDificultades() {
		return dificultadRepository.findAll();
	}

	@Override
	public Iterable<TipoCocina> listarTiposCocina() {
		return tipoCocinaRepository.findAll();
	}

	@Override
	public Iterable<Ingrediente> listarIngredientes() {
		return ingredienteRepository.findAll();
	}

	@Override
	public void anadirIngrediente(Ingrediente ingrediente) {
		ingredienteRepository.save(ingrediente);
	}

	@Override
	public void anadirPlato(Plato plato) {
		plato.setId(null);
		platoRepository.save(plato);
		log.info("Se ha añadido el plato " + plato);
	}

	@Override
	public void modificarPlato(Plato plato) {
		platoRepository.save(plato);
	}

	@Override
	@Transactional
	public void borrarPlato(Long id) {
		if(!platoRepository.existsById(id)) {
			log.warning("Se ha intentado borrar un plato que no existe: " + id);
			throw new ServiciosException("No existe el plato a borrar");
		}
		
		platoIngredienteRepository.deleteByPlatoId(id);
		platoRepository.deleteById(id);
	}

	@Override
	public void anadirIngredienteAPlato(PlatoIngrediente platoIngrediente) {
		platoIngredienteRepository.save(platoIngrediente);
	}

	@Override
	public Iterable<Plato> listadoPlatos() {
		return platoRepository.findAll();
	}

	@Override
	public Plato verPlato(Long id) {
		return platoRepository.findById(id).orElse(null);
	}

	@Override
	public Iterable<PlatoIngrediente> verIngredientesPlato(Long id) {
		return platoIngredienteRepository.findByPlatoId(id);
	}
}
