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
            facturas_has_productos fp ON f.id = fp.facturas_id
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