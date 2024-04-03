'use strict';

const productos = [
    { id: 1, nombre: 'Producto 1', precio: 12.34 },
    { id: 2, nombre: 'Producto 2', precio: 22.34 },
    { id: 3, nombre: 'Producto 3', precio: 32.34 },
];

window.addEventListener('DOMContentLoaded', function(){
    const tbody = document.querySelector('tbody');

    productos.forEach(p => {
        const tr = document.createElement('tr');
        tr.innerHTML = `<th>${p.id}</th><td>${p.nombre}</td><td>${p.precio}</td>`;

        tbody.appendChild(tr);
    });
});