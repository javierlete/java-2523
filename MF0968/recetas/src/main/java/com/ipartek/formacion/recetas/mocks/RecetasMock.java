package com.ipartek.formacion.recetas.mocks;

import java.math.BigDecimal;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ipartek.formacion.recetas.entidades.Dificultad;
import com.ipartek.formacion.recetas.entidades.Ingrediente;
import com.ipartek.formacion.recetas.entidades.Plato;
import com.ipartek.formacion.recetas.entidades.PlatoIngrediente;
import com.ipartek.formacion.recetas.entidades.TipoCocina;
import com.ipartek.formacion.recetas.entidades.Usuario;
import com.ipartek.formacion.recetas.repositorios.DificultadRepository;
import com.ipartek.formacion.recetas.repositorios.IngredienteRepository;
import com.ipartek.formacion.recetas.repositorios.PlatoIngredienteRepository;
import com.ipartek.formacion.recetas.repositorios.PlatoRepository;
import com.ipartek.formacion.recetas.repositorios.TipoCocinaRepository;
import com.ipartek.formacion.recetas.repositorios.UsuarioRepository;

@Configuration
public class RecetasMock {
	private Plato plato;
	
	@Bean
	Plato platoPrimero() {
		return plato;
	}
	
	public RecetasMock(PlatoIngredienteRepository platoIngredienteRepository, UsuarioRepository usuarioRepository, PlatoRepository platoRepository, IngredienteRepository ingredienteRepository, DificultadRepository dificultadRepository, TipoCocinaRepository tipoCocinaRepository) {
		var baja = Dificultad.builder().nombre("Baja").puntuacion(3).build();
		
		dificultadRepository.save(baja);
		dificultadRepository.save(Dificultad.builder().nombre("Media").puntuacion(5).build());
		dificultadRepository.save(Dificultad.builder().nombre("Alta").puntuacion(7).build());
		
		var italiana = TipoCocina.builder().nombre("Italiana").build();
		
		tipoCocinaRepository.save(TipoCocina.builder().nombre("Americana").build());
		tipoCocinaRepository.save(TipoCocina.builder().nombre("Española").build());
		tipoCocinaRepository.save(TipoCocina.builder().nombre("Francesa").build());
		tipoCocinaRepository.save(italiana);
		
		var tomate = Ingrediente.builder().nombre("Tomate").build();
		var jamon = Ingrediente.builder().nombre("Jamón").build();
		
		ingredienteRepository.save(Ingrediente.builder().nombre("Patata").build());
		ingredienteRepository.save(Ingrediente.builder().nombre("Lechuga").build());
		ingredienteRepository.save(tomate);
		ingredienteRepository.save(jamon);
		
		var tipoCocina = italiana;
		var dificultad = baja;
		var plato = Plato.builder().nombre("Pizza").preparacion("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Laborum dignissimos vero eligendi beatae adipisci non necessitatibus tempore consequuntur soluta asperiores ipsa eaque similique quia! Architecto consequatur a totam aut fuga!").dificultad(dificultad).tipoCocina(tipoCocina).build();

		platoRepository.save(plato);
		
		this.plato = plato;
		
		platoIngredienteRepository.save(PlatoIngrediente.builder().plato(plato).ingrediente(tomate).medida("rodajas").cantidad(new BigDecimal(5)).build());
		platoIngredienteRepository.save(PlatoIngrediente.builder().plato(plato).ingrediente(jamon).medida("láminas").cantidad(new BigDecimal(20)).build());
		
		usuarioRepository.save(Usuario.builder().nombre("Javier").email("javier@email.net").password("$2a$12$mof.u/4EIo58hR7On9DnPevyqBC7kb9FHzT.LN/BjF8xOqQVTP1NO").rol("ADMIN").build());
		usuarioRepository.save(Usuario.builder().nombre("Pepe").email("pepe@email.net").password("$2a$12$Dij9cgV3mXDQYtOo4nvQTOLaUz3URoe7DGjhBrqGa1fEEzkNVhBgq").rol("USER").build());
	}
}
