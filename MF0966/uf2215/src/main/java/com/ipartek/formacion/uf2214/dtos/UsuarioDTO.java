package com.ipartek.formacion.uf2214.dtos;

public record UsuarioDTO(Long id, String nickName, String password, RolDTO rol) {
	public UsuarioDTO(Long id, String nickName, String password, Long idRol, String nombreRol) {
		this(id, nickName, password, new RolDTO(idRol, nombreRol));
	}
}
