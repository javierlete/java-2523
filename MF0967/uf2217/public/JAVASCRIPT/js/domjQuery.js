'use strict';

$(function () {
    $('h1').html('Cambiado desde JavaScript');

    $('#saludar').on('click', function () {
        var spanSaludo = $('#saludo').length ? $('#saludo') : $('<span id="saludo" class="mensaje" style="font-weight: bold">').appendTo($('form'));

        spanSaludo.html('Hola ' + $('#nombre').val());

        $('#terminado').length ? $('#terminado') : $('<p id="terminado">¡Terminado!</p>').appendTo($('body'));
    });
});
