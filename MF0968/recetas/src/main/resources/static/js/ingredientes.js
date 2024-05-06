'use strict';

const URL = 'http://localhost:8080/api/recetas/ingredientes';

window.addEventListener('DOMContentLoaded', async function() {
	const ul = document.querySelector('ul');
	
	const respuesta = await fetch(URL);
	const ingredientes = await respuesta.json();
	
	for(const ingrediente of ingredientes) {
		const li = document.createElement('li');
		
		li.innerText = ingrediente.nombre;
		
		ul.appendChild(li);
	}	
});