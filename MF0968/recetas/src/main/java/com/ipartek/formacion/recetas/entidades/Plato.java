package com.ipartek.formacion.recetas.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "platos")
public class Plato {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@NotBlank
	@Size(min = 3, max = 100)
	private String nombre;
	
	@Lob
	@NotNull
	@NotBlank
	@Size(max = 5000)
	private String preparacion;
	
	@NotNull
	@ManyToOne
	private TipoCocina tipoCocina;
	
	@NotNull
	@ManyToOne
	private Dificultad dificultad;

}
