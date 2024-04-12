'use strict';

const URL = 'http://localhost:8080/fullstack/api/cursos/';

let ul;
let form;
let idCurso;

window.addEventListener('DOMContentLoaded', async function() {
	ul = document.querySelector('ul');
	form = document.querySelector('form');

	form.style.display = 'none';

	const respuesta = await fetch(URL);
	const cursos = await respuesta.json();

	cursos.forEach(c => {
		const li = document.createElement('li');
		li.innerHTML = `${c.id}: ${c.nombre}
			<a href="javascript:formulario(${c.id})">Apuntarme</a>
		`;

		ul.appendChild(li);
	});
});

function formulario(id) {
	ul.style.display = 'none';
	form.style.display = null;

	idCurso = id;
}

async function apuntarme() {
	const alumno = {
		nombre: form.nombre.value,
		apellidos: form.apellidos.value,
		fechaNacimiento: form['fecha-nacimiento'].value,
	};

	console.log(idCurso, alumno);

	const respuesta = await fetch(URL + idCurso + '/alumnos', {
		body: JSON.stringify(alumno),
		method: 'POST',
		headers: {
			'Content-type': 'application/json'
		}
	});

	console.log(respuesta);
}