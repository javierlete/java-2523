'use strict';

const URL = 'http://localhost:8080/fullstack/api/cursos/';

window.addEventListener('DOMContentLoaded', async function() {
    const ul = document.querySelector('ul');

    const respuesta = await fetch(URL);
    const cursos = await respuesta.json();
    
    cursos.forEach(c => {
		const li = document.createElement('li');
		li.innerHTML = `${c.id}: ${c.nombre}`;
		
		ul.appendChild(li);
	});
});
