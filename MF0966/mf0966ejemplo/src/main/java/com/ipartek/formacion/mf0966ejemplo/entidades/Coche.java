package com.ipartek.formacion.mf0966ejemplo.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "coches", uniqueConstraints = {
		@UniqueConstraint(name = "matricula_UNIQUE", columnNames = { "matricula" }) })
public class Coche {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "matricula", nullable = false, columnDefinition = "CHAR(7)")
	private String matricula;

	@Column(name = "marca", nullable = false, length = 45)
	private String marca;

	@Column(name = "modelo", nullable = false, length = 45)
	private String modelo;

	@Column(name = "fecha_fabricacion", nullable = false)
	private LocalDate fechaFabricacion;
	
	@Column(name = "precio", nullable=false)
	private BigDecimal precio;
	
	@Column(name = "stock", nullable = false)
	private Integer stock;

	@ManyToOne
	@JoinColumn(name = "vendedores_id", nullable = false, foreignKey = @ForeignKey(name = "fk_coches_vendedores"))
	private Vendedor vendedor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public LocalDate getFechaFabricacion() {
		return fechaFabricacion;
	}

	public void setFechaFabricacion(LocalDate fechaFabricacion) {
		this.fechaFabricacion = fechaFabricacion;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
	
}
