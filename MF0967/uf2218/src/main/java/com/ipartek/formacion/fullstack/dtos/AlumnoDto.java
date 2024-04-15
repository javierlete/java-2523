package com.ipartek.formacion.fullstack.dtos;

import java.time.LocalDate;

public record AlumnoDto(Long id, String nombre, String apellidos, LocalDate fechaNacimiento) {

}
