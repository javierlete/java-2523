'use strict';

window.addEventListener('DOMContentLoaded', function() {
    const h1 = document.querySelector('h1');

    console.log(h1.innerHTML);

    h1.innerText = 'Cambiado desde JavaScript';

    const form = document.querySelector('form');
    const inputNombre = document.querySelector('#nombre');
    const botonSaludar = document.querySelector('#saludar');
    
    console.log(form, inputNombre, botonSaludar);
    
    botonSaludar.addEventListener('click', function() {
        const nombre = inputNombre.value;
        const texto = 'Hola ' + nombre;
        
        const spanSaludo = document.querySelector('#saludo') ?? document.createElement('span');
        
        spanSaludo.id = 'saludo';
        spanSaludo.className = 'mensaje';
        spanSaludo.style.fontWeight = 'bold';

        spanSaludo.innerText = texto;

        document.querySelector('#saludo') || form.appendChild(spanSaludo);

        const body = document.querySelector('body');

        const p = document.querySelector('#terminado') || document.createElement('p') ;

        p.id = 'terminado';
        p.innerText = '¡Terminado!';

        document.querySelector('#terminado') || body.appendChild(p);
    });
});