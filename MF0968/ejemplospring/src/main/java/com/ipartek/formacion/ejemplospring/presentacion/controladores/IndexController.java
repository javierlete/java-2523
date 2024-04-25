package com.ipartek.formacion.ejemplospring.presentacion.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ipartek.formacion.ejemplospring.servicios.UsuarioNegocio;

@Controller
@RequestMapping("/")
public class IndexController {
	@Autowired
	private UsuarioNegocio negocio;
	
	@GetMapping
	@ResponseBody
	public String index() {
		return negocio.obtenerProductos().toString();
	}
}
