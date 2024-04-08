var URL = 'http://localhost:3001/productos';

$(function () {
    $.getJSON(URL).then(listado);
});

function listado(productos) {
    $.each(productos, function (clave, p) {
        console.log(p);
        $('.row-cols-1').append($(`<jl-tarjeta-producto nombre="${p.nombre}" precio="${p.precio}" foto="https://picsum.photos/300/200?${p.id}"></jl-tarjeta-producto>`));
    });
}
