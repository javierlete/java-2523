package com.ipartek.formacion.uf2213.entidades;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "CHAR(9)", nullable = false)
	private String dni;
	
	@Column(name = "dni_diferencial")
	private Integer dniDiferencial;
	
	@Column(length = 50, nullable = false)
	private String nombre;
	
	@Column(length = 100)
	private String apellidos;
	
	@Column(name = "fecha_nacimiento")
	private LocalDate fechaNacimiento;

	// PARA JPA/HIBERNATE
	public Cliente() {}
	
	public Cliente(Long id, String dni, Integer dniDiferencial, String nombre, String apellidos,
			LocalDate fechaNacimiento) {
		super();
		this.id = id;
		this.dni = dni;
		this.dniDiferencial = dniDiferencial;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Integer getDniDiferencial() {
		return dniDiferencial;
	}

	public void setDniDiferencial(Integer dniDiferencial) {
		this.dniDiferencial = dniDiferencial;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", dni=" + dni + ", dniDiferencial=" + dniDiferencial + ", nombre=" + nombre
				+ ", apellidos=" + apellidos + ", fechaNacimiento=" + fechaNacimiento + "]";
	}
}
