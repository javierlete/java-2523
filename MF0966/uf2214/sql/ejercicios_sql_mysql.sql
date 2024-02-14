SET FOREIGN_KEY_CHECKS=0;

-- Creación de la tabla Departamento
DROP TABLE IF EXISTS Departamento CASCADE;

CREATE TABLE Departamento (
    codDepto VARCHAR(4) PRIMARY KEY,
    nombreDpto VARCHAR(20) NOT NULL,
    ciudad VARCHAR(15),
    codDirector VARCHAR(12)
);
-- Creación de la tabla Empleado
DROP TABLE IF EXISTS Empleado;
CREATE TABLE Empleado (
    nDIEmp VARCHAR(12) NOT NULL PRIMARY KEY,
    nomEmp VARCHAR(30) NOT NULL,
    sexEmp CHAR(1) NOT NULL CHECK (sexEmp IN ('F' , 'M')),
    fecNac DATE NOT NULL,
    fecIncorporacion DATE NOT NULL,
    salEmp FLOAT NOT NULL,
    comisionE FLOAT NOT NULL,
    cargoE VARCHAR(15) NOT NULL,
    jefeID VARCHAR(12),
    codDepto VARCHAR(4) NOT NULL,
    CONSTRAINT FK_Empl FOREIGN KEY (jefeID)
        REFERENCES Empleado (nDIEmp),
    CONSTRAINT FK_Dpto FOREIGN KEY (codDepto)
        REFERENCES Departamento (codDepto)
);
-- Cada frase desde -- hasta el final de la línea es ignorado (es un comentario)
-- SQL es insensible a los espacios en blanco
-- SQL NO es sensible a las mayúsculas (ejemplo:...Empleado... es equivalente a
-- ...EMPLEADO...)
-- Declaración de una llave foránea en Departamento
ALTER TABLE Departamento
ADD CONSTRAINT FK_EmpDir
FOREIGN KEY (codDirector) REFERENCES Empleado (nDIEmp);

INSERT INTO Empleado (nDIEmp, nomEmp, sexEmp, fecNac, fecIncorporacion, salEmp, 
comisionE, cargoE, jefeID, codDepto)
VALUES
('31.840.269', 'María Rojas', 'F', '1959-01-15', '1990-05-16', 6250000, 1500000, 'Gerente', NULL, '1000'),
('16.211.383', 'Luis Pérez', 'M', '1956-02-25', '2000-01-01', 5050000, 0, 'Director', '31.840.269', '1500'),
('31.178.144', 'Rosa Angulo', 'F', '1957-03-15', '1998-08-16', 3250000, 3500000, 'Jefe Ventas', '31.840.269', '2000'),
('16.759.060', 'Darío Casas', 'M', '1960-04-05', '1992-11-01', 4500000, 500000, 'Investigador', '31.840.269', '3000'),
('22.222.222', 'Carla López', 'F', '1975-05-11', '2005-07-16', 4500000, 500000, 'Jefe Mercadeo', '31.840.269', '3500'),
('22.222.333', 'Carlos Rozo', 'M', '1975-05-11', '2001-09-16', 750000, 500000, 'Vigilante', '31.840.269', '3500') ,
('1.751.219', 'Melissa Roa', 'F', '1960-06-19', '2001-03-16', 2250000, 2500000, 'Vendedor', '31.178.144', '2100'),
('768.782', 'Joaquín Rosas', 'M', '1947-07-07', '1990-05-16', 2250000, 2500000, 'Vendedor', '31.178.144', '2200'),
('737.689', 'Mario Llano', 'M', '1945-08-30', '1990-05-16', 2250000, 2500000, 'Vendedor', '31.178.144', '2300'),
('333.333.333', 'Elisa Rojas', 'F', '1979-09-28', '2004-06-01', 3000000, 1000000, 'Jefe Mecánicos', '31.840.269', '4000'),
('888.888', 'Iván Duarte', 'M', '1955-08-12', '1998-05-16', 1050000, 200000, 'Mecánico', '333.333.333', '4100'),
('11.111.111', 'Irene Díaz', 'F', '1979-09-28', '2004-06-01', 1050000, 200000, 'Mecánico', '333.333.333', '4200'),
('444.444', 'Abel Gómez', 'M', '1939-12-24', '2000-10-01', 1050000, 200000, 'Mecánico', '333.333.333', '4300'),
('1.130.222', 'José Giraldo', 'M', '1985-01-20', '2000-11-01', 1200000, 400000, 'Asesor', '22.222.222', '3500'),
('19.709.802', 'William Daza', 'M', '1982-10-09', '1999-12-16', 2250000, 1000000,'Investigador', '16.759.060', '3000'),
('31.174.099', 'Diana Solarte', 'F', '1957-11-19', '1990-05-16', 1250000, 500000, 'Secretaria', '31.840.269', '1000'),
('1.130.777', 'Marcos Cortez', 'M', '1986-06-23', '2000-04-16', 2550000, 500000, 'Mecánico', '333.333.333', '4000'),
('1.130.782', 'Antonio Gil', 'M', '1980-01-23', '2010-04-16', 850000, 1500000, 'Técnico', '16.211.383', '1500'),
('333.333.334', 'Marisol Pulido', 'F', '1979-10-01', '1990-05-16', 3250000, 1000000, 'Investigador', '16.759.060', '3000'),
('333.333.335', 'Ana Moreno', 'F', '1992-01-05', '2004-06-01', 1200000, 400000, 'Secretaria', '16.759.060', '3000'),
('1.130.333', 'Pedro Blanco', 'M', '1987-10-28', '2000-10-01', 800000, 3000000, 'Vendedor', '31.178.144', '2000'),
('1.130.444', 'Jesús Alfonso', 'M', '1988-03-14', '2000-10-01', 800000, 3500000, 'Vendedor', '31.178.144', '2000'),
('333.333.336', 'Carolina Ríos', 'F', '1992-02-15', '2000-10-01', 1250000, 500000, 'Secretaria', '16.211.383', '1500'),
('333.333.337', 'Edith Muñoz', 'F', '1992-03-31', '2000-10-01', 800000, 3600000, 'Vendedor', '31.178.144', '2100'),
('1.130.555', 'Julián Mora', 'M', '1989-07-03', '2000-10-01', 800000, 3100000, 'Vendedor', '31.178.144', '2200'),
('1.130.666', 'Manuel Millán', 'M', '1990-12-08', '2004-06-01', 800000, 3700000, 'Vendedor', '31.178.144', '2300');




INSERT INTO Departamento (codDepto, nombreDpto, ciudad, coddirector) VALUES
('1000', 'GERENCIA', 'CALI', '31.840.269'), 
('1500', 'PRODUCCIÓN', 'CALI', '16.211.383'),
('2000', 'VENTAS', 'CALI', '31.178.144'),
('3000', 'INVESTIGACIÓN', 'CALI', '16.759.060'), 
('3500', 'MERCADEO', 'CALI', '22.222.222'),
('2100', 'VENTAS', 'POPAYAN', '31.751.219'),
('2200', 'VENTAS', 'BUGA', '768.782'),
('2300', 'VENTAS', 'CARTAGO', '737.689'),
('4000', 'MANTENIMIENTO', 'CALI', '333.333.333'),
('4100', 'MANTENIMIENTO', 'POPAYAN', '888.888'),
('4200', 'MANTENIMIENTO', 'BUGA', '11.111.111'),
('4300', 'MANTENIMIENTO', 'CARTAGO', '444.444');

INSERT INTO Departamento (codDepto, nombreDpto, ciudad, coddirector) VALUES 
('6000', 'TRANSPORTE', 'CALI', NULL), ('7000', 'COMPRAS', 'CALI', NULL);

SET FOREIGN_KEY_CHECKS=1;
