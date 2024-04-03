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

        const producto = { nombre: form.nombre.value, precio: form.precio.value };

        if(form.id.value) {
            producto.id = +form.id.value;

            console.log(producto);

            productos = productos.filter(p => p.id !== producto.id);
            productos.push(producto);
        } else {
            producto.id = productos.length ? Math.max(...productos.map(p => p.id)) + 1 : 1;

            productos.push(producto);
        }

        listado();
    });

    listado();
});

function listado() {
    tbody.innerHTML = '';

    productos.forEach(p => {
        const tr = document.createElement('tr');
        tr.innerHTML = `<th>${p.id}</th><td>${p.nombre}</td><td>${p.precio}</td>
            <td>
                <a href="javascript:formulario(${p.id})">Editar</a>
                <a href="javascript:borrar(${p.id})">Borrar</a>
            </td>`;

        tbody.appendChild(tr);
    });

    form.style.display = 'none';
    table.style.display = null;
}

function formulario(id) {
    if(id) {
        const p = productos.find(p => p.id === id);

        form.id.value = p.id;
        form.nombre.value = p.nombre;
        form.precio.value = p.precio;
    } else {
        form.reset();
    }

    form.style.display = null;
    table.style.display = 'none';
}

function borrar(id) {
    productos = productos.filter(p => p.id !== id);

    listado();
}