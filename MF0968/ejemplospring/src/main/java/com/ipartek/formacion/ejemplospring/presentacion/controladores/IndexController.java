package com.ipartek.formacion.ejemplospring.presentacion.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ipartek.formacion.ejemplospring.entidades.Producto;
import com.ipartek.formacion.ejemplospring.servicios.AdminNegocio;
import com.ipartek.formacion.ejemplospring.servicios.UsuarioNegocio;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class IndexController {
	@Autowired
	private UsuarioNegocio negocio;
	
	@Autowired
	private AdminNegocio adminNegocio;
	
	@GetMapping
	public String index(Model modelo) {
		modelo.addAttribute("productos",negocio.obtenerProductos());
		return "index";
	}
	
	@GetMapping("categorias")
	@ResponseBody
	public String categorias(Model modelo) {
		//modelo.addAttribute("categorias", negocio.obtenerCategorias());
		
		return negocio.obtenerCategorias().toString();
	}
	
//	@GetMapping("admin")
//	public String admin(Model modelo) {
//		var producto = new Producto();
//		producto.setNombre("");
//		modelo.addAttribute(producto);
//		return "admin";
//	}

	@GetMapping("admin")
	public String admin(Producto producto) {
		producto.setNombre("");
		return "admin";
	}
	
	@PostMapping("admin")
	public String adminPost(@Valid Producto producto, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "admin";
		}
		
		adminNegocio.insertarProducto(producto);
		return "redirect:/";
	}
}
