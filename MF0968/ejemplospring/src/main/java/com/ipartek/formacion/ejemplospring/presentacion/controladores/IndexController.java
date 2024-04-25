package com.ipartek.formacion.ejemplospring.presentacion.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ipartek.formacion.ejemplospring.servicios.UsuarioNegocio;

@Controller
@RequestMapping("/")
public class IndexController {
	@Autowired
	private UsuarioNegocio negocio;
	
	@GetMapping
	public String index(Model modelo) {
		modelo.addAttribute("productos",negocio.obtenerProductos());
		return "index";
	}
}
