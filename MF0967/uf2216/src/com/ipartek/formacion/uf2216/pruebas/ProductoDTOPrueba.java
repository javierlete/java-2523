package com.ipartek.formacion.uf2216.pruebas;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.ipartek.formacion.uf2216.pojos.ProductoDTO;

public class ProductoDTOPrueba {
	public static void main(String[] args) {
		ProductoDTO dto = new ProductoDTO(1L, "Prueba", new BigDecimal("1234"), LocalDate.now()	, 5, true);
		ProductoDTO dto2 = new ProductoDTO(1L, "Prueba", new BigDecimal("1234"), LocalDate.now()	, 5, true);
		
		System.out.println(dto);
		System.out.println(dto == dto2);
		System.out.println(dto.equals(dto2));
		
		System.out.println(dto.nombre());
		System.out.println(dto.getDatosBasicos());
	}
}
