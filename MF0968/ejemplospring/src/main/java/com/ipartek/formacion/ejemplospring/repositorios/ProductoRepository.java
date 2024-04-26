package com.ipartek.formacion.ejemplospring.repositorios;

import java.math.BigDecimal;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.ipartek.formacion.ejemplospring.dtos.ProductoDto;
import com.ipartek.formacion.ejemplospring.entidades.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Long> {
	Set<Producto> findByNombreContains(String nombre);
	
	Set<Producto> findByPrecioBetween(BigDecimal minimo, BigDecimal maximo);
	
	Set<ProductoDto> findByCategoriaId(Long id);
}
