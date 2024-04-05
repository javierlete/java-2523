'use strict';

var euro = new Intl.NumberFormat('es-ES', {
    style: 'currency',
    currency: 'EUR',
    useGrouping: true
});

var URL = 'http://localhost:3001/productos/';

$(function () {
    $('form').on('submit', guardar);

    console.log('LISTADO GLOBAL');
    listado();
});

function guardar(e) {
    e.preventDefault();

    if (!$('form')[0].checkValidity()) {
        $('form').addClass('was-validated');
        return;
    }

    var producto = { nombre: $('#nombre').val(), precio: +$('#precio').val() };

    if ($('#id').val()) {
        producto.id = +$('#id').val();

        console.log(producto);

        $.ajax(URL + producto.id, {
            method: 'PUT',
            data: producto
        }).then(listado);
    } else {
        $.ajax(URL, {
            method: 'POST',
            data: producto
        }).then(listado);
    }
}
function listado() {
    $.getJSON(URL).then(function (productos) {
        $('tbody').empty();

        $.each(productos, function (clave, p) {
            console.log(p);

            $(`<tr><th class="text-end">${p.id}</th><td>${p.nombre}</td><td class="text-end">${euro.format(p.precio)}</td>
            <td>
                <a class="btn btn-sm btn-primary" href="javascript:formulario(${p.id})">Editar</a>
                <a class="btn btn-sm btn-danger" href="javascript:borrar(${p.id})">Borrar</a>
            </td></tr>`).appendTo($('tbody'));
        });

        $('form').hide();
        $('table').show();

        new DataTable('table', {
            language: {
                url: 'json/datatables_es-ES.json',
            },
        });
    });
}

function formulario(id) {
    if (id) {
        $.getJSON(URL + id).then(function (p) {
            $('#id').val(p.id);
            $('#nombre').val(p.nombre);
            $('#precio').val(p.precio);
        });
    } else {
        $('form')[0].reset();
    }

    $('form').show();
    $('table').hide();
}

function borrar(id) {
    $.ajax(URL + id, { method: 'DELETE' }).then(listado);
}
