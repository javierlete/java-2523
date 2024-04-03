'use strict';

let productos = [
    { id: 1, nombre: 'Producto 1', precio: 12.34 },
    { id: 2, nombre: 'Producto 2', precio: 22.34 },
    { id: 3, nombre: 'Producto 3', precio: 32.34 },
];

let form, table, tbody;

window.addEventListener('DOMContentLoaded', function(){
    form = document.querySelector('form');
    table = document.querySelector('table');
    tbody = document.querySelector('tbody');
    
    form.addEventListener('submit', function(e) {
        e.preventDefault();

        form.style.display = 'none';
        table.style.display = null;
    });

    form.style.display = 'none';

    listado();
});

function listado() {
    tbody.innerHTML = '';

    productos.forEach(p => {
        const tr = document.createElement('tr');
        tr.innerHTML = `<th>${p.id}</th><td>${p.nombre}</td><td>${p.precio}</td>
            <td>
                <a href="javascript:formulario()">Editar</a>
                <a href="javascript:borrar(${p.id})">Borrar</a>
            </td>`;

        tbody.appendChild(tr);
    });
}

function formulario() {
    form.style.display = null;
    table.style.display = 'none';
}

function borrar(id) {
    productos = productos.filter(p => p.id !== id);

    listado();
}