package com.ipartek.formacion.uf2214.dtos;

import java.time.LocalDateTime;

public record PostDTO(Long id, String usuario, LocalDateTime fecha, String texto, Integer numeroLikes) {
	public PostDTO(Long id, LocalDateTime fecha, String texto, Integer numeroLikes) {
		this(id, null, fecha, texto, numeroLikes);
	}
}
