'use strict';

const URL = 'http://localhost:3001/productos';

window.addEventListener('DOMContentLoaded', async function () {
    const tarjetas = document.querySelector('.row-cols-1');

    const respuesta = await fetch(URL);
    const productos = await respuesta.json();

    const antes = performance.now();

    // MÁS LENTO

    // for(let p of productos) {
    //     tarjetas.innerHTML += `<jl-tarjeta-producto nombre="${p.nombre}" foto="" precio="${p.precio}"></jl-tarjeta-producto>`;
    // }
    
    // MÁS RÁPIDO
    productos.forEach(p => {
        const tarjeta = document.createElement('jl-tarjeta-producto');

        tarjeta.setAttribute('nombre', p.nombre);
        tarjeta.setAttribute('foto', '');
        tarjeta.setAttribute('precio', p.precio);

        tarjetas.appendChild(tarjeta);
    });

    const despues = performance.now();

    console.log(despues - antes);
});