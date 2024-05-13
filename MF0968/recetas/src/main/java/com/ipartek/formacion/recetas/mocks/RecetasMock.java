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
		var espanola = TipoCocina.builder().nombre("Española").build();
		
		tipoCocinaRepository.save(TipoCocina.builder().nombre("Americana").build());
		tipoCocinaRepository.save(espanola);
		tipoCocinaRepository.save(TipoCocina.builder().nombre("Francesa").build());
		tipoCocinaRepository.save(italiana);
		
		var tomate = Ingrediente.builder().nombre("Tomate").build();
		var jamon = Ingrediente.builder().nombre("Jamón").build();
		var pollo = Ingrediente.builder().nombre("Pollo").build();
		
		ingredienteRepository.save(Ingrediente.builder().nombre("Patata").build());
		ingredienteRepository.save(Ingrediente.builder().nombre("Lechuga").build());
		ingredienteRepository.save(tomate);
		ingredienteRepository.save(jamon);
		ingredienteRepository.save(pollo);
		
		var pizza = Plato.builder().nombre("Pizza").preparacion("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Laborum dignissimos vero eligendi beatae adipisci non necessitatibus tempore consequuntur soluta asperiores ipsa eaque similique quia! Architecto consequatur a totam aut fuga!").dificultad(baja).tipoCocina(italiana).build();

		platoRepository.save(pizza);
		
		platoIngredienteRepository.save(PlatoIngrediente.builder().plato(pizza).ingrediente(tomate).medida("rodajas").cantidad(new BigDecimal(5)).build());
		platoIngredienteRepository.save(PlatoIngrediente.builder().plato(pizza).ingrediente(jamon).medida("láminas").cantidad(new BigDecimal(20)).build());
		
		this.plato = pizza;

		var polloAsado = Plato.builder().nombre("Pollo asado").preparacion("Lo metes en el horno").dificultad(baja).tipoCocina(espanola).build();
		
		platoRepository.save(polloAsado);
		
		platoIngredienteRepository.save(PlatoIngrediente.builder().plato(polloAsado).ingrediente(pollo).medida("unidad").cantidad(BigDecimal.ONE).build());
		
		usuarioRepository.save(Usuario.builder().nombre("Javier").email("javier@email.net").password("$2a$12$mof.u/4EIo58hR7On9DnPevyqBC7kb9FHzT.LN/BjF8xOqQVTP1NO").rol("ADMIN").build());
		usuarioRepository.save(Usuario.builder().nombre("Pepe").email("pepe@email.net").password("$2a$12$Dij9cgV3mXDQYtOo4nvQTOLaUz3URoe7DGjhBrqGa1fEEzkNVhBgq").rol("USER").build());
	}
}
