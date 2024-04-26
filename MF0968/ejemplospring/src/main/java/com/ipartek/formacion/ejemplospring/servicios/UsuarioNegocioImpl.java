package com.ipartek.formacion.ejemplospring.servicios;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.ejemplospring.dtos.CategoriaConProductosDto;
import com.ipartek.formacion.ejemplospring.dtos.ProductoDto;
import com.ipartek.formacion.ejemplospring.entidades.Categoria;
import com.ipartek.formacion.ejemplospring.entidades.Producto;
import com.ipartek.formacion.ejemplospring.repositorios.CategoriaRepository;
import com.ipartek.formacion.ejemplospring.repositorios.ProductoRepository;

@Service
public class UsuarioNegocioImpl implements UsuarioNegocio {

	@Autowired
	private ProductoRepository repoProducto;

	@Autowired
	private CategoriaRepository repoCategoria;

	@Override
	public Iterable<Producto> obtenerProductos() {
		return repoProducto.findAll();
	}

	@Override
	public Iterable<Categoria> obtenerCategorias() {
		return repoCategoria.findAll();
	}

	@Override
	public CategoriaConProductosDto obtenerCategoriaPorId(Long id) {
		// SOLUCIÓN CON LAZY (hay que desactivar en configuración la línea spring.jpa.open-in-view=false o ponerle el valor true
		// return repoCategoria.findById(id).orElse(null);

		// SOLUCIÓN CON PRODUCTO CARTESIANO
		// return repoCategoria.obtenerCategoriaPorIdConProductos(id);

		// SOLUCIÓN CON "N+1" O MÁS BIEN DOS CONSULTAS A TRAVÉS DE DTOs
		Categoria c = repoCategoria.findById(id).orElse(null);
		Set<ProductoDto> productos = repoProducto.findByCategoriaId(id);

		CategoriaConProductosDto categoriaDto = new CategoriaConProductosDto(c.getId(), c.getCodigo(), c.getNombre(),
				c.getDescripcion(), productos);

		return categoriaDto;
	}
}
