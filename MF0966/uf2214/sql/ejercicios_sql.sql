-- 1. Obtener los datos completos de los empleados.
SELECT 
    *
FROM
    Empleado;

-- 2. Obtener los datos completos de los departamentos
SELECT 
    *
FROM
    Departamento;

-- 3. Obtener los datos de los empleados con cargo 'Secretaria'.
SELECT 
    *
FROM
    Empleado
WHERE
    cargoE = 'Secretaria';

-- 4. Obtener el nombre y salario de los empleados.
SELECT 
    nomEmp, salEmp
FROM
    Empleado;

-- 5. Obtener los datos de los empleados vendedores, ordenado por nombre.
SELECT 
    *
FROM
    Empleado
WHERE
    cargoE = 'Vendedor'
ORDER BY nomEmp;

-- 6. Listar el nombre de los departamentos
SELECT DISTINCT
    nombreDpto
FROM
    Departamento;

-- 7. Listar el nombre de los departamentos, ordenado por nombre
SELECT DISTINCT
    nombreDpto
FROM
    Departamento
ORDER BY nombreDpto;

-- 8. Listar el nombre de los departamentos, ordenado por ciudad
SELECT DISTINCT
    ciudad, nombreDpto
FROM
    Departamento
ORDER BY ciudad;

-- 9. Listar el nombre de los departamentos, ordenado por ciudad, en orden inverso
SELECT DISTINCT
    ciudad, nombreDpto
FROM
    Departamento
ORDER BY ciudad DESC;

-- 10. Obtener el nombre y cargo de todos los empleados, ordenado por salario
SELECT 
    salEmp, nomEmp, cargoE
FROM
    Empleado
ORDER BY salEmp;

-- 11. Obtener el nombre y cargo de todos los empleados, ordenado por cargo y por salario
SELECT 
    cargoE, salEmp, nomEmp
FROM
    Empleado
ORDER BY cargoE , salEmp;

-- 12. Obtener el nombre y cargo de todos los empleados, en orden inverso por cargo
SELECT 
    cargoE, nomEmp
FROM
    Empleado
ORDER BY cargoE DESC;

-- 13. Listar los salarios y comisiones de los empleados del departamento 2000
SELECT 
    nomEmp, salEmp, comisionE
FROM
    Empleado
WHERE
    codDepto = '2000';

-- 14. Listar los salarios y comisiones de los empleados del departamento 2000, ordenado por comisión
SELECT 
    nomEmp, salEmp, comisionE
FROM
    Empleado
WHERE
    codDepto = '2000'
ORDER BY comisionE;

-- 15. Listar todas las comisiones
SELECT 
    nomEmp, comisionE
FROM
    Empleado;

-- 16. Listar las comisiones que sean diferentes, ordenada por valor
SELECT DISTINCT
    comisionE
FROM
    Empleado
ORDER BY comisionE;

-- 17. Listar los diferentes salarios
SELECT DISTINCT
    salEmp
FROM
    Empleado;

-- 18. Obtener el valor total a pagar que resulta de sumar a los empleados del departamento 3000 una 
-- bonificación de $500.000, en orden alfabético del empleado
SELECT 
    *, salEmp + 500000 AS 'Salario con bonificación'
FROM
    Empleado
WHERE
    codDepto = '3000'
ORDER BY nomEmp;

-- 19. Obtener la lista de los empleados que ganan una comisión superior a su sueldo.
SELECT 
    *
FROM
    Empleado
WHERE
    comisionE > salEmp;

-- 20. Listar los empleados cuya comisión es menor o igual que el 30% de su sueldo.
SELECT 
    *
FROM
    Empleado
WHERE
    comisionE <= salEmp * 0.3;

-- 21. Elabore un listado donde para cada fila, figure ‘Nombre’ y ‘Cargo’ antes del valor respectivo para 
-- cada empleado
SELECT 
    'Nombre', nomEmp, 'Cargo', cargoE
FROM
    Empleado;

-- 22. Hallar el salario y la comisión de aquellos empleados cuyo número de documento de identidad es 
-- superior al '19.709.802'
SELECT 
    nDIEmp, nomEmp, salEmp, comisionE
FROM
    Empleado
WHERE
    nDIEmp > '19.709.802';

SELECT 
    nDIEmp, nomEmp, salEmp, comisionE
FROM
    Empleado
WHERE
    + REPLACE(nDIEmp, '.', '') > 19709802;

SELECT +REPLACE('444.333', '.', '') > 19000000;

-- 23. Listar los empleados cuyo salario es menor o igual que el 40% de su comisión
SELECT 
    *
FROM
    Empleado
WHERE
    salEmp <= 0.4 * comisionE;

-- 24. Divida los empleados, generando un grupo cuyo nombre inicie por la letra J y termine en la letra Z.
-- Liste estos empleados y su cargo por orden alfabético.
SELECT 
    nomEmp, cargoE
FROM
    Empleado
WHERE
    nomEmp BETWEEN 'J' AND 'Z'
        OR nomEmp LIKE 'Z%';

SELECT 'Zaira' BETWEEN 'J' AND 'Z' OR 'Zaira' LIKE 'Z%';

-- 25. Listar el salario, la comisión, el salario total (salario + comisión), documento de identidad del 
-- empleado y nombre, de aquellos empleados que tienen comisión superior a $1.000.000, ordenar el 
-- informe por el número del documento de identidad 
SELECT 
    nDIEmp,
    nomEmp,
    salEmp,
    comisionE,
    salEmp + comisionE AS 'total'
FROM
    Empleado
WHERE
    comisionE > 1000000
ORDER BY nDIEmp;

SELECT 
    nDIEmp,
    nomEmp,
    salEmp,
    comisionE,
    salEmp + comisionE AS 'total'
FROM
    Empleado
WHERE
    comisionE > 1000000
ORDER BY REPLACE(nDIEmp, '.', '') * 1;

-- 26. Obtener un listado similar al anterior, pero de aquellos empleados que NO tienen comisión
SELECT 
    nDIEmp,
    nomEmp,
    salEmp,
    comisionE,
    salEmp + comisionE AS 'total'
FROM
    Empleado
WHERE
    comisionE = 0
ORDER BY REPLACE(nDIEmp, '.', '') * 1;


-- 27. Hallar el nombre de los empleados que tienen un salario superior a $1.000.000, y tienen como jefe al 
-- empleado con documento de identidad '31.840.269'
SELECT 
    nomEmp
FROM
    Empleado
WHERE
    salEmp > 1000000
        AND jefeID = '31.840.269';

-- 28. Hallar el conjunto complementario del resultado del ejercicio anterior.
SELECT 
    nomEmp
FROM
    Empleado
WHERE
    NOT (salEmp > 1000000
        AND jefeID = '31.840.269');

SELECT 
    nomEmp
FROM
    Empleado
WHERE
    salEmp <= 1000000
        OR jefeID <> '31.840.269';

-- 29. Hallar los empleados cuyo nombre no contiene la cadena “MA”
SELECT 
    *
FROM
    Empleado
WHERE
    UCASE(nomEmp) NOT LIKE '%MA%';

-- 30. Obtener los nombres de los departamentos que no sean “Ventas” ni “Investigación” NI 
-- ‘MANTENIMIENTO’, ordenados por ciudad.
SELECT 
    ciudad, nombreDpto
FROM
    Departamento
WHERE
    nombreDpto NOT IN ('Ventas' , 'Investigación', 'Mantenimiento')
ORDER BY ciudad;

-- 31. Obtener el nombre y el departamento de los empleados con cargo 'Secretaria' o 'Vendedor', que 
-- no trabajan en el departamento de “PRODUCCION”, cuyo salario es superior a $1.000.000, 
-- ordenados por fecha de incorporación.
SELECT 
    fecIncorporacion, nomEmp, nombreDpto
FROM
    Empleado e
        JOIN
    Departamento d ON e.codDepto = d.codDepto
WHERE
    cargoE IN ('Secretaria' , 'Vendedor')
        AND nombreDpto <> 'Producción'
        AND salEmp > 1000000
ORDER BY fecIncorporacion;

-- 32. Obtener información de los empleados cuyo nombre tiene exactamente 11 caracteres
SELECT 
    *
FROM
    Empleado
WHERE
    nomEmp LIKE '___________';

-- 33. Obtener información de los empleados cuyo nombre tiene al menos 11 caracteres
SELECT 
    *
FROM
    Empleado
WHERE
    nomEmp LIKE '___________%';

-- 34. Listar los datos de los empleados cuyo nombre inicia por la letra 'M', su salario es mayor a $800.000 
-- o reciben comisión y trabajan para el departamento de 'VENTAS'
SELECT 
    *, nombreDpto, salEmp, nomEmp, comisionE
FROM
    Empleado e
        JOIN
    Departamento d ON e.codDepto = d.codDepto
WHERE
    (nomEmp LIKE 'M%' AND salEmp > 800000)
        OR (comisionE > 0 AND nombreDpto = 'Ventas');

-- 35. Obtener los nombres, salarios y comisiones de los empleados que reciben un salario situado entre la 
-- mitad de la comisión la propia comisión
SELECT 
    nomEmp, salEmp, comisionE
FROM
    Empleado
WHERE
    salEmp BETWEEN comisionE / 2 AND comisionE;

-- 36. Suponga que la empresa va a aplicar un reajuste salarial del 7%. Listar los nombres de los empleados, su 
-- salario actual y su nuevo salario, indicando para cada uno de ellos si tiene o no comisión
SELECT 
    nomEmp,
    salEmp,
    salEmp * 0.93 AS 'nuevo',
    IF(comisionE > 0, 'TIENE', 'NO TIENE') AS 'comision'
FROM
    Empleado;

-- 37. Obtener la información disponible del empleado cuyo número de documento de identidad sea: 
-- '31.178.144', '16.759.060', '1.751.219', '768.782', '737.689', '19.709.802', '31.174.099', '1.130.782'
SELECT 
    *
FROM
    Empleado
WHERE
    nDIEmp IN ('31.178.144' , '16.759.060',
        '1.751.219',
        '768.782',
        '737.689',
        '19.709.802',
        '31.174.099',
        '1.130.782');

-- 38. Entregar un listado de todos los empleados ordenado por su departamento, y alfabético dentro del 
-- departamento.
SELECT 
    nombreDpto, nomEmp
FROM
    Empleado e
        JOIN
    Departamento d ON e.codDepto = d.codDepto
ORDER BY d.nombreDpto , e.nomEmp;

-- 39. Entregar el salario más alto de la empresa.
SELECT 
    MAX(salEmp)
FROM
    Empleado;

SELECT 
    *
FROM
    Empleado
WHERE
    salEmp = (SELECT 
            MAX(salEmp)
        FROM
            Empleado);

SELECT 
    *
FROM
    Empleado
ORDER BY salEmp DESC
LIMIT 1;

-- 40. Entregar el total a pagar por comisiones, y el número de empleados que las reciben.
SELECT 
    SUM(comisionE), COUNT(comisionE)
FROM
    Empleado
WHERE
    comisionE > 0;

-- 41. Entregar el nombre del último empleado de la lista por orden alfabético.
SELECT 
    MAX(nomEmp)
FROM
    Empleado;

-- 42. Hallar el salario más alto, el más bajo y la diferencia entre ellos.
SELECT 
    MAX(salEmp) AS max,
    MIN(salEmp) AS min,
    MAX(salEmp) - MIN(salEmp) AS dif
FROM
    Empleado;

-- 43. Conocido el resultado anterior, entregar el nombre de los empleados que reciben el salario más alto 
-- y más bajo. Cuanto suman estos salarios?
SELECT 
    (SELECT 
            nomEmp
        FROM
            Empleado
        ORDER BY salEmp ASC
        LIMIT 1) AS 'El que gana menos',
    (SELECT 
            nomEmp
        FROM
            Empleado
        ORDER BY salEmp DESC
        LIMIT 1) AS 'El que gana más',
    (SELECT 
            MAX(salEmp) + MIN(salEmp)
        FROM
            Empleado) AS suma;

(SELECT 
    'El que gana menos' AS 'Clave', nomEmp AS 'Valor'
FROM
    Empleado
ORDER BY salEmp ASC
LIMIT 1) UNION (SELECT 
    'El que gana más', nomEmp
FROM
    Empleado
ORDER BY salEmp DESC
LIMIT 1) UNION (SELECT 
    'Suma', MAX(salEmp) + MIN(salEmp)
FROM
    Empleado);

-- 44. Entregar el número de empleados de sexo femenino y de sexo masculino, por departamento.
SELECT 
    nombreDpto, sexEmp, COUNT(sexEmp)
FROM
    Empleado e
        JOIN
    Departamento d ON e.codDepto = d.codDepto
GROUP BY nombreDpto , sexEmp;

-- 45. Hallar el salario promedio por departamento.
SELECT 
    nombreDpto, AVG(salEmp)
FROM
    Empleado e
        JOIN
    Departamento d ON e.codDepto = d.codDepto
GROUP BY nombreDpto;

-- 46. Hallar el salario promedio por departamento, considerando aquellos empleados cuyo salario supera 
-- $900.000, y aquellos con salarios inferiores a $575.000. Entregar el código y el nombre del 
-- departamento.
SELECT 
    nombreDpto, AVG(salEmp)
FROM
    Empleado e
        JOIN
    Departamento d ON e.codDepto = d.codDepto
WHERE
    salEmp > 900000 OR salEmp < 5750000
GROUP BY nombreDpto;

-- 47. Entregar la lista de los empleados cuyo salario es mayor o igual que el promedio de la empresa. 
-- Ordenarlo por departamento.
SELECT 
    *
FROM
    Empleado e
        JOIN
    Departamento d ON e.codDepto = d.codDepto
WHERE
    salEmp > (SELECT 
            AVG(salEmp)
        FROM
            Empleado)
ORDER BY d.nombreDpto;

-- 48. Hallar los departamentos que tienen más de tres (3) empleados. Entregar el número de empleados de 
-- esos departamentos.
SELECT 
    nombreDpto, COUNT(*)
FROM
    Empleado e
        JOIN
    Departamento d ON e.codDepto = d.codDepto
GROUP BY nombreDpto
HAVING COUNT(*) > 3;

-- 49. Obtener la lista de empleados jefes, que tienen al menos un empleado a su cargo. Ordene el informe 
-- inversamente por el nombre.
SELECT 
    j.nomEmp, COUNT(*)
FROM
    Empleado e
        JOIN
    Empleado j ON e.jefeID = j.nDIEmp
GROUP BY j.nomEmp
ORDER BY j.nomEmp DESC;

-- 50. Hallar los departamentos que no tienen empleados
SELECT 
    d.*
FROM
    Empleado e
        RIGHT JOIN
    Departamento d ON e.codDepto = d.codDepto
WHERE
    nDIEmp IS NULL;

-- 51. Entregar un reporte con el numero de cargos en cada departamento y cual es el promedio de salario 
-- de cada uno. Indique el nombre del departamento en el resultado.
SELECT
    nombreDpto, COUNT(DISTINCT cargoE), AVG(salEmp)
FROM
    Empleado e
        JOIN
    Departamento d ON e.codDepto = d.codDepto
    GROUP BY d.nombreDpto
    ORDER BY d.nombreDpto;

-- 52. Entregar el nombre del departamento cuya suma de salarios sea la más alta, indicando el valor de la 
-- suma.

SELECT 
    nombreDpto, SUM(salEmp)
FROM
    Empleado e
        JOIN
    Departamento d ON e.codDepto = d.codDepto
GROUP BY nombreDpto
ORDER BY SUM(salEmp) DESC
LIMIT 1;
    
-- 53. Entregar un reporte con el código y nombre de cada jefe, junto al número de empleados que dirige. 
-- Puede haber empleados que no tengan supervisores, para esto se indicará solamente el numero de 
-- ellos dejando los valores restantes en NULL.

SELECT 
    j.nDIEmp, j.nomEmp, COUNT(e.nDIEmp)
FROM
    Empleado e
        LEFT JOIN
    Empleado j ON e.jefeID = j.nDIEmp
GROUP BY j.nDIEmp;