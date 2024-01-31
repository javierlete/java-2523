SELECT 
    *
FROM
    clientes AS c,
    facturas AS f
WHERE
    c.id = f.clientes_id
        AND c.nombre = 'Javier';

SELECT 
    *
FROM
    clientes AS c
        LEFT JOIN
    facturas AS f ON c.id = f.clientes_id
WHERE
    f.id IS NULL;

SELECT NULL IS NOT NULL;

SELECT 
    u.email, SUM(p.precio * fp.cantidad)
FROM
    usuarios u
        JOIN
    clientes c ON u.clientes_id = c.id
        JOIN
    facturas f ON c.id = f.clientes_id
        JOIN
    facturas_has_productos fp ON f.id = fp.facturas_id
        JOIN
    productos p ON fp.productos_id = p.id
GROUP BY u.email;

SELECT 
    *
FROM
    clientes
WHERE
    id = 1;

SELECT 
    *
FROM
    facturas
WHERE
    clientes_id = 1;

SELECT 
    c.id,
    c.dni,
    c.dni_diferencial,
    c.nombre,
    c.apellidos,
    c.fecha_nacimiento,
    f.id,
    f.numero,
    f.fecha,
    (SELECT 
            SUM(p.precio * fp.cantidad)
        FROM
            facturas ft
                JOIN
            facturas_has_productos fp ON ft.id = fp.facturas_id
                JOIN
            productos p ON fp.productos_id = p.id
        WHERE
            ft.id = f.id
        GROUP BY ft.id) AS total_factura,
    fp.cantidad,
    p.id,
    p.nombre,
    p.precio,
    fp.cantidad * p.precio AS total
FROM
    clientes c
        JOIN
    facturas f ON f.clientes_id = c.id
        JOIN
    facturas_has_productos fp ON fp.facturas_id = f.id
        JOIN
    productos p ON fp.productos_id = p.id
WHERE
    c.id = 1
ORDER BY f.id , p.id
;
    
SELECT 
    f.numero,
    (SELECT 
            SUM(p.precio * fp.cantidad)
        FROM
            facturas ft
                JOIN
            facturas_has_productos fp ON f.id = fp.facturas_id
                JOIN
            productos p ON fp.productos_id = p.id
        WHERE
            ft.id = f.id
        GROUP BY ft.id) AS total
FROM
    facturas f;
    
SELECT COUNT(*) AS numero, AVG(list_price) AS medio, MAX(list_price) AS maximo, MIN(list_price) AS minimo, SUM(list_price) AS total FROM products;

SELECT * FROM products WHERE list_price = (SELECT MAX(list_price) FROM products);

SELECT * FROM products ORDER BY list_price DESC LIMIT 1;

SELECT COUNT(DISTINCT first_name) FROM customers;

SELECT company, CONCAT(SUBSTRING(company, 9, LENGTH(company - 9)), '#')  FROM customers;

SELECT first_name, last_name, company, CONCAT('#', LCASE(first_name), '@company', LCASE(SUBSTRING(company, 9, LENGTH(company) - 8)), '.net', '#') FROM customers WHERE INSTR(first_name, ' ') = 0;
SELECT first_name, company, emailizar(first_name, company) FROM customers WHERE INSTR(first_name, ' ') = 0;

SELECT emailizar('anna', 'Company C');

SELECT * FROM customers WHERE INSTR(first_name, ' ') = 0;

UPDATE customers SET email_address = CONCAT(LCASE(first_name), '@company', LCASE(SUBSTRING(company, 9, LENGTH(company) - 8)), '.net') WHERE INSTR(first_name, ' ') = 0;

UPDATE customers SET email_address = NULL;

SELECT COUNT(email_address) FROM customers;

SELECT * FROM customers WHERE email_address IS NULL;

SELECT e.nombre AS empleado, j.nombre AS jefe
FROM empleados e
LEFT JOIN empleados j
ON e.jefe_id = j.id;

USE manana_tienda;

CREATE TABLE nombres_tabla SELECT nombres.tipo, nombres.nombre
FROM (SELECT 'CLIENTE' AS tipo, nombre COLLATE utf8mb4_0900_ai_ci AS nombre FROM clientes
UNION
SELECT 'EMPLEADO' AS tipo, nombre COLLATE utf8mb4_0900_ai_ci AS nombre FROM empleados) AS nombres;

SELECT c.id, c.nombre, COUNT(c.id) AS numero_facturas
FROM clientes c
JOIN facturas f ON c.id = f.clientes_id
GROUP BY c.id
ORDER BY numero_facturas;

SELECT 
    name
FROM
    person
WHERE
    id = (SELECT 
            person_id
        FROM
            facebook_event_checkin
        WHERE
            event_name = 'SQL Symphony Concert'
                AND date LIKE '201712__'
                AND person_id IN (SELECT 
                    p.id
                FROM
                    person p
                        JOIN
                    income i ON p.ssn = i.ssn
                        JOIN
                    drivers_license d ON d.id = p.license_id
                WHERE
                    d.gender = 'female'
                        AND d.height BETWEEN 65 AND 67
                        AND d.hair_color = 'red'
                        AND d.car_make = 'Tesla'
                        AND d.car_model = 'Model S'
                ORDER BY i.annual_income DESC)
        GROUP BY person_id
        HAVING COUNT(person_id) = 3);

SELECT COALESCE(NULL, 0);

SELECT COALESCE(5, 0);

SELECT IF(NULL IS NULL, 0, NULL);

SELECT IF(5 IS NULL, 0, 5);

SELECT 5%2;

SELECT LENGTH('Garcia'), LENGTH('García'), CHAR_LENGTH('Garcia'), CHAR_LENGTH('García');

SELECT sumar(1, 2);

SELECT 
    *
FROM
    clientes AS c,
    facturas AS f
WHERE
    c.id = f.clientes_id
        AND c.nombre = 'Javier';

SELECT 
    *
FROM
    clientes AS c
        LEFT JOIN
    facturas AS f ON c.id = f.clientes_id
WHERE
    f.id IS NULL;

SELECT NULL IS NOT NULL;

SELECT 
    u.email, SUM(p.precio * fp.cantidad)
FROM
    usuarios u
        JOIN
    clientes c ON u.clientes_id = c.id
        JOIN
    facturas f ON c.id = f.clientes_id
        JOIN
    facturas_has_productos fp ON f.id = fp.facturas_id
        JOIN
    productos p ON fp.productos_id = p.id
GROUP BY u.email;

SELECT 
    *
FROM
    clientes
WHERE
    id = 1;

SELECT 
    *
FROM
    facturas
WHERE
    clientes_id = 1;

CALL facturas_productos_totales(1);

SET @nada = 'nada';
SET @entrada = 'entrada';
SET @salida = 'salida'; -- NO SE VA A UTILIZAR EL VALOR INICIAL. SE CAMBIARÁ A NULL.
SET @entrada_salida = 'entrada_salida';

CALL manana_tienda.tipos_parametros(@nada, @entrada, @salida, @entrada_salida);

SELECT @nada, @entrada, @salida, @entrada_salida;

