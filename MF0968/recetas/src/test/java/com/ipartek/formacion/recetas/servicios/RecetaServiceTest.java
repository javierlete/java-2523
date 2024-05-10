package com.ipartek.formacion.recetas.servicios;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ipartek.formacion.recetas.entidades.Plato;

@SpringBootTest
class RecetaServiceTest {
	
	@Autowired
	private Plato platoPrimero;
	
	@Autowired
	private RecetaService servicio;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testListarDificultades() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testListarTiposCocina() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testListarIngredientes() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testAnadirIngrediente() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testAnadirPlato() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testModificarPlato() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testBorrarPlato() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testAnadirIngredienteAPlato() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testListadoPlatos() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testVerPlato() {
		var recibido = servicio.verPlato(1L);
		var esperado = platoPrimero;
		
		assertNotNull(recibido);
		assertEquals(esperado, recibido);
		
		recibido = servicio.verPlato(5L);
		
		assertNull(recibido);
		
		assertThrows(ServiciosException.class, () -> servicio.verPlato(null));
	}

	@Test
	void testVerIngredientesPlato() {
		fail("Not yet implemented"); // TODO
	}

}
