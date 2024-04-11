'use strict';

function basicos() {
    // alert('prueba');

    console.debug('Depuración');
    console.log('Texto en consola');
    console.warn('Aviso');
    console.error('Error');

    console.table([[1, 'Portátil', 1000], [2, 'Escritorio', 2000]]);

    var variable = 'Javier';

    console.log(variable, typeof variable);

    variable = 5;

    console.log(variable, typeof variable);

    console.log(v1, typeof undefined);

    var v1 = 10;

    // console.log(v2);

    let v2;

    const c1 = 'Valor';

    // c1 = 10;

    const arr = []; // new Array(3);

    arr[0] = 5;
    arr[1] = 6;
    arr[2] = 7;

    arr[3] = 8;
    arr[8] = 10;

    arr[5] = 'asdfadsf';

    arr['otro'] = 'Vale';
    arr.otromas = 'Pues también vale';

    arr.push('Añadido');

    console.table(arr);
    console.log(typeof arr);

    for (let i = 0; i < arr.length; i++) {
        console.log(i, arr[i]);
    }

    for (let clave in arr) {
        console.log(clave, arr[clave]);
    }

    for (let dato of arr) {
        console.log(dato);
    }

    arr.forEach((dato, indice) => console.log(dato, indice));

}

function funciones() {
    function saludar() {
        console.log('Hola');
    }

    saludar();

    console.log(saludar);
    console.log(typeof saludar);
    function sumar(a, b) {
        return a + b;
    }

    console.log(sumar(6, 7));

    const resultado = sumar(6);

    console.log(resultado, typeof resultado);

    if (isNaN(resultado)) {
        console.log('El resultado no es un número');
    } else {
        console.log('El resultado es ' + resultado);
    }

    console.log(10 / 0, typeof (10 / 0));

    const mes = +'3';

    console.log(mes, typeof mes);

    console.log(sumar('a', 'b'));

    const restar = function (a, b) {
        return a - b;
    };

    console.log(typeof restar, restar, restar(10, 7));

    const operacion = restar;

    console.log(operacion(10, 7));

    const multiplicar = (a, b) => a * b;

    console.log(multiplicar(2, 3));

    function operar(a, operacion, b) {
        return operacion(a, b);
    }

    console.log(operar(10, restar, 7), typeof operar(10, restar, 7));

    console.log(operar(1, Math.max, 3));

    console.log(typeof Math);

    (function ejemploIIFE(nombre) {
        const saludo = `Hola ${nombre}`;
        console.log(saludo);
    })('Javier');

    {
        const nombre = 'Pepe';
        console.log(`Hola ${nombre}`);
    }

    // console.log(saludo);
};

function objetosYPrototipos() {
    const javier = {};

    javier.nombre = 'Javier';
    javier['apellidos'] = 'Lete';

    javier.nombreCompleto = function () {
        return this.nombre + ' ' + this.apellidos;
    }

    console.log(javier, typeof javier, javier.nombreCompleto());

    const pepe = { nombre: 'Pepe', apellidos: 'Pérez' };

    pepe.nombreCompleto = javier.nombreCompleto;

    console.log(pepe, pepe.nombreCompleto());

    function Persona(nombre, apellidos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    Persona.prototype.nombreCompleto = function () {
        return this.nombre + ' ' + this.apellidos;
    }

    const p1 = new Persona('Javier', 'Lete');

    console.log(p1, typeof p1, p1.constructor.name);

    console.log(p1.nombreCompleto());
}

function clases() {
    class Persona {
        constructor(nombre, apellidos) {
            this.nombre = nombre;
            this.apellidos = apellidos;
        }

        get nombre() {
            return this._nombre;
        }

        set nombre(nuevoNombre) {
            if (nuevoNombre && typeof nuevoNombre !== 'string') {
                throw new Error('El nombre debe ser un string');
            }

            this._nombre = nuevoNombre;
        }

        get apellidos() {
            return this._apellidos;
        }

        set apellidos(nuevosApellidos) {
            if (nuevosApellidos && typeof nuevosApellidos !== 'string') {
                throw new Error('Los apellidos deben ser un string');
            }

            this._apellidos = nuevosApellidos;
        }
        nombreCompleto() {
            return this.nombre + ' ' + this.apellidos;
        }
    }

    const p1 = new Persona('Javier', 'Lete');

    // p1.nombre = 5;

    console.log(p1, typeof p1, p1.constructor.name);

    console.log(p1.nombreCompleto());

    p1.patatas = 'Con ensalada';

    delete p1.apellidos;

    console.log(p1);

    const p2 = new Persona();

    console.log(p2);
}

function ejemploMaleabilidadJavaScript() {
    String.prototype.toUpperCase = function () { return 'JAJAJAJAJA'; };
    String.prototype.aNumero = function () { return +this; };
    
    console.log('hola'.toUpperCase());

    const numero = '100'.aNumero();
    console.log(numero, typeof numero);
}

clases();
