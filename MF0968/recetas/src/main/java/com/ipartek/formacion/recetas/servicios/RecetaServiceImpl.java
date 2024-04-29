package com.ipartek.formacion.recetas.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Service
public class RecetaServiceImpl implements RecetaService {
	@Autowired
	private PlatoIngredienteRepository platoIngredienteRepository;
	
	private IngredienteRepository ingredienteRepository;
	
	private PlatoRepository platoRepository;
	
	private DificultadRepository dificultadRepository;
	
	private TipoCocinaRepository tipoCocinaRepository;
	
	public RecetaServiceImpl(PlatoRepository platoRepository, IngredienteRepository ingredienteRepository, DificultadRepository dificultadRepository, TipoCocinaRepository tipoCocinaRepository) {
		dificultadRepository.save(Dificultad.builder().nombre("Baja").puntuacion(3).build());
		dificultadRepository.save(Dificultad.builder().nombre("Media").puntuacion(5).build());
		dificultadRepository.save(Dificultad.builder().nombre("Alta").puntuacion(7).build());
		
		tipoCocinaRepository.save(TipoCocina.builder().nombre("Americana").build());
		tipoCocinaRepository.save(TipoCocina.builder().nombre("Española").build());
		tipoCocinaRepository.save(TipoCocina.builder().nombre("Francesa").build());
		tipoCocinaRepository.save(TipoCocina.builder().nombre("Italiana").build());
		
		ingredienteRepository.save(Ingrediente.builder().nombre("Patata").build());
		ingredienteRepository.save(Ingrediente.builder().nombre("Lechuga").build());
		ingredienteRepository.save(Ingrediente.builder().nombre("Tomate").build());
		
		var tipoCocina = TipoCocina.builder().id(4L).build();
		var dificultad = Dificultad.builder().id(1L).build();
		var plato = Plato.builder().nombre("Pizza").preparacion("Horno").dificultad(dificultad).tipoCocina(tipoCocina).build();

		platoRepository.save(plato);
		
		this.platoRepository = platoRepository;
		this.ingredienteRepository = ingredienteRepository;
		this.dificultadRepository = dificultadRepository;
		this.tipoCocinaRepository = tipoCocinaRepository;
	}
	
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

	@Override
	public Plato verPlato(Long id) {
		return platoRepository.findById(id).orElse(null);
	}

	@Override
	public Iterable<PlatoIngrediente> verIngredientesPlato(Long id) {
		return platoIngredienteRepository.findByPlatoId(id);
	}
}
