'use strict';

const URL = 'http://localhost:8080/api/recetas/';
let sections, ul, plantillaClaseLiIngrediente, divPlatos, modeloPlato, platoNombre, platoPreparacion, platoTipoCocina, platoDificultad, platoForm;

window.addEventListener('DOMContentLoaded', async function() {
	platoForm = document.getElementById('plato-form');
	platoNombre = document.getElementById('plato-nombre');
	platoPreparacion = document.getElementById('plato-preparacion');
	platoTipoCocina = document.getElementById('plato-tipo-cocina');
	platoDificultad = document.getElementById('plato-dificultad');
	divPlatos = document.querySelector('#platos .row')
	modeloPlato = document.querySelector('#platos .col');
	sections = document.querySelectorAll('main > section');
	ul = document.querySelector('#ingredientes ul');
	plantillaClaseLiIngrediente = document.querySelector('#ingredientes ul li').className;

	mostrarPlatos();
});

function anadirIngrediente() {
	mostrar('ingrediente');
}

async function guardarIngrediente() {
	const input = document.querySelector('#ingrediente input[name=nombre]');
	const nombre = input.value;

	const ingrediente = { nombre };

	const respuesta = await fetch(URL + 'ingredientes', {
		method: 'POST',
		body: JSON.stringify(ingrediente),
		headers: {
			'Content-type': 'application/json'
		}
	});

	const ingredienteConfirmado = await respuesta.json();

	console.log(ingredienteConfirmado);

	input.value = '';

	mostrarIngredientes();
}

function mostrar(id) {
	for (const section of sections) {
		section.style.display = 'none';
	}

	document.getElementById(id).style.display = null;
}

async function mostrarIngredientes() {

	const respuesta = await fetch(URL + 'ingredientes');
	const ingredientes = await respuesta.json();

	ul.innerHTML = '';

	for (const ingrediente of ingredientes) {
		const li = document.createElement('li');

		li.className = plantillaClaseLiIngrediente;

		li.innerText = ingrediente.nombre;

		ul.appendChild(li);
	}

	mostrar('ingredientes');
}

async function mostrarPlatos() {
	divPlatos.innerHTML = '';

	const respuesta = await fetch(URL + 'platos');
	const platos = await respuesta.json();

	for (const plato of platos) {

		const divPlato = modeloPlato.cloneNode(true);

		divPlato.querySelector('.platos-imagen').src = 'https://picsum.photos/300/200?' + plato.id;
		divPlato.querySelector('.platos-nombre').innerText = plato.nombre;
		divPlato.querySelector('.platos-tipo-cocina').innerText = plato.tipoCocina.nombre;
		divPlato.querySelector('.platos-dificultad').innerText = plato.dificultad.nombre;

		divPlato.querySelector('.platos-ingredientes').href = `javascript:platoIngredientes(${plato.id})`;
		divPlato.querySelector('.platos-editar').href = `javascript:platoEditar(${plato.id})`;
		divPlato.querySelector('.platos-borrar').href = `javascript:platoBorrar(${plato.id})`;

		divPlatos.appendChild(divPlato);
	}
	
	mostrar('platos');
}

async function mostrarPlato() {
	const respuesta = await fetch(URL + 'plato/form');
	const platoForm = await respuesta.json();
	
	const dificultades = platoForm.dificultades;
	const tiposCocina = platoForm.tiposCocina;
	
	console.log(dificultades, tiposCocina);

	platoDificultad.innerHTML = '';
	
	for(const dificultad of dificultades) {
		const option = document.createElement('option');
		
		option.value = +dificultad.id;
		option.innerText = dificultad.nombre;
		
		platoDificultad.appendChild(option);
	}
	
	platoTipoCocina.innerHTML = '';
	
	for(const tipoCocina of tiposCocina) {
		const option = document.createElement('option');
		
		option.value = +tipoCocina.id;
		option.innerText = tipoCocina.nombre;
		
		platoTipoCocina.appendChild(option);
	}
	
	mostrar('plato');
}

async function guardarPlato() {
	
	if(!platoForm.checkValidity()) {
		platoForm.classList.add('was-validated');
		return;
	}
	
	const plato = { 
		nombre: platoNombre.value, 
		preparacion: platoPreparacion.value, 
		dificultad: { id: +platoDificultad.value },
		tipoCocina: { id: +platoTipoCocina.value }, 
	};	
	
	console.log(plato);

	const respuesta = await fetch(URL + 'plato', {
		method: 'POST',
		body: JSON.stringify(plato),
		headers: {
			'Content-type': 'application/json'
		}
	});
	
	const platoRecibido = await respuesta.json();
	
	console.log(platoRecibido);

	mostrarPlatos();
	
	platoForm.reset();
	platoForm.classList.remove('was-validated');
}

function platoIngredientes(id) {
	console.log('ingredientes', id);
}

function platoEditar(id) {
	console.log('editar', id);
}

async function platoBorrar(id) {
	console.log('borrar', id);
	
	const respuesta = await fetch(URL + 'plato/' + id, { method: 'DELETE' });
	
	console.log(respuesta);
	
	mostrarPlatos();
}