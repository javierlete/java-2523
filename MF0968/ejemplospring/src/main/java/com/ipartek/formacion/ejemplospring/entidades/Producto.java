package com.ipartek.formacion.ejemplospring.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "productos")
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@NotBlank(message = "El nombre debe ser rellenado")
	@Size(min = 3, max = 100)
	@Column(name = "nombre")
	@Builder.Default
	private String nombre = "Anónimo";
	
	@NotNull
	@Min(0)
	@Column(name = "precio")
	private BigDecimal precio;
	
	@Future
	@Column(name = "fecha_caducidad")
	private LocalDate fechaCaducidad;
	
	@Min(0)
	@Column(name = "stock")
	private Integer stock;
}
