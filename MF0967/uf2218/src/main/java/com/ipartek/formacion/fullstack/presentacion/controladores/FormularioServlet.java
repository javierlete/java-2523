package com.ipartek.formacion.fullstack.presentacion.controladores;

import java.io.IOException;
import java.time.LocalDate;

import com.ipartek.formacion.fullstack.configuraciones.Globales;
import com.ipartek.formacion.fullstack.dtos.AlumnoDto;
import com.ipartek.formacion.fullstack.dtos.CursoDto;
import com.ipartek.formacion.fullstack.dtos.InscripcionDto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/formulario")
public class FormularioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sId = request.getParameter("id"); // names del formulario, o datos de la querystring ?id=valor
		String sIdAlumno = request.getParameter("id-alumno");

		if (sId != null) {
			Long id = Long.valueOf(sId);
			CursoDto curso = Globales.daoCurso.obtenerPorId(id);
			request.setAttribute("curso", curso);
		}

		if (sIdAlumno != null) {
			Long idAlumno = Long.valueOf(sIdAlumno);
			AlumnoDto alumno = Globales.daoAlumno.obtenerPorId(idAlumno);
			request.setAttribute("alumno", alumno);
		}

		request.getRequestDispatcher("/WEB-INF/vistas/formulario.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// RECOGER DATOS DE LA PETICIÓN
		String sIdAlumno = request.getParameter("id");
		String sIdCurso = request.getParameter("id-curso");
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String sFechaNacimiento = request.getParameter("fecha-nacimiento");

		// CONVERTIR SI ES NECESARIO
		Long idAlumno = null;

		if (!sIdAlumno.isBlank()) {
			idAlumno = Long.valueOf(sIdAlumno);
		}

		Long idCurso = sIdCurso.isBlank() ? null : Long.valueOf(sIdCurso);
		LocalDate fechaNacimiento = sFechaNacimiento.isBlank() ? null : LocalDate.parse(sFechaNacimiento);

		// EMPAQUETAR EN MODELO
		AlumnoDto alumno = new AlumnoDto(idAlumno, nombre, apellidos, fechaNacimiento);

		// LLAMAR A LÓGICA DE NEGOCIO
		if (idCurso != null) {
			// PREPARAR EL MODELO PARA LA SIGUIENTE VISTA
			var inscripcion = altaAlumnoCurso(alumno, idCurso);
			
			request.setAttribute("curso", inscripcion.curso());
			request.setAttribute("alumno", inscripcion.alumno());
			
			// SALTAMOS A LA SIGUIENTE VISTA
			request.getRequestDispatcher("/WEB-INF/vistas/bienvenida-curso.jsp").forward(request, response);
		} else {
			AlumnoDto alumnoConId;
			
			if(idAlumno != null) {
				alumnoConId = Globales.daoAlumno.modificar(alumno);
			} else {
				alumnoConId = Globales.daoAlumno.insertar(alumno);
			}
			
			request.setAttribute("alumno", alumnoConId);
			
			response.sendRedirect("cursos");
		}
	}

	private InscripcionDto altaAlumnoCurso(AlumnoDto alumno, Long idCurso) {
		CursoDto curso = Globales.daoCurso.obtenerPorId(idCurso);

		var alumnoConId = Globales.daoAlumno.insertar(alumno);
		Globales.daoAlumno.apuntarseACurso(alumnoConId.id(), idCurso);

		return new InscripcionDto(alumnoConId, curso);
	}
}
