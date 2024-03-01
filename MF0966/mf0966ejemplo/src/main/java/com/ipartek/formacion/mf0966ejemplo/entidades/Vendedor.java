package com.ipartek.formacion.mf0966ejemplo.entidades;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "vendedores", uniqueConstraints = {
		@UniqueConstraint(name = "dni_UNIQUE", columnNames = { "dni" }) })
public class Vendedor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "nombre", length=45, nullable=false)
	private String nombre;
	
	@Column(name = "apellidos", length=100)
	private String apellidos;
	
	@Column(name = "dni", nullable = false, columnDefinition = "CHAR(9)", length= 9)
	private String dni;
	
	@OneToMany(mappedBy = "vendedor")
	private Set<Coche> coches;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Set<Coche> getCoches() {
		return coches;
	}

	public void setCoches(Set<Coche> coches) {
		this.coches = coches;
	}
	
	
}
