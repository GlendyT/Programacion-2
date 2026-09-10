-- ============================================================
-- DML — Inserts y operaciones
-- Ejecutar DESPUÉS de ddl.sql
-- ============================================================

-- ------------------------------------------------------------
-- 1. DEPARTAMENTOS
-- ------------------------------------------------------------
INSERT INTO Dept (Dept_No, DNombre, Loc) VALUES (1, 'Informática', 'Escuintla');
INSERT INTO Dept (Dept_No, DNombre, Loc) VALUES (2, 'Ventas',      'Escuintla');

-- ------------------------------------------------------------
-- 2. EMPLEADOS
-- ------------------------------------------------------------
INSERT INTO Emp (Emp_No, Apellido, Oficio,       Fecha_Alt,                          Salario, Dept_No)
VALUES (8596, 'Martínez', 'Programador', TO_DATE('07/02/2022','DD/MM/YYYY'), 7000, 1);
INSERT INTO Emp (Emp_No, Apellido, Oficio,       Fecha_Alt,                          Salario, Dept_No)
VALUES (1200, 'Juárez',   'Programador', TO_DATE('03/02/2022','DD/MM/YYYY'), 5000, 1);
INSERT INTO Emp (Emp_No, Apellido, Oficio,       Fecha_Alt,                          Salario, Dept_No)
VALUES (1500, 'Perez',    'Programador', TO_DATE('03/02/2022','DD/MM/YYYY'), 5000, 1);

INSERT INTO Emp (Emp_No, Apellido, Oficio,    Fecha_Alt,                          Salario, Dept_No)
VALUES (1000, 'Ordoñez', 'Vendedor', TO_DATE('03/03/2022','DD/MM/YYYY'), 5000, 2);
INSERT INTO Emp (Emp_No, Apellido, Oficio,    Fecha_Alt,                          Salario, Dept_No)
VALUES (1800, 'Ortiz',   'Vendedor', TO_DATE('03/03/2022','DD/MM/YYYY'), 5000, 2);

-- ------------------------------------------------------------
-- 3. HOSPITALES
-- ------------------------------------------------------------
INSERT INTO Hospital (Hospital_Cod, Nombre,       Direccion,  Telefono,  Num_Cama)
VALUES (1, 'San Carlos', '1 Calle', '1234567', 50);
INSERT INTO Hospital (Hospital_Cod, Nombre,       Direccion,  Telefono,  Num_Cama)
VALUES (2, 'San Miguel', '3 Calle', '1234567', 80);

-- ------------------------------------------------------------
-- 4. SALAS
-- ------------------------------------------------------------
INSERT INTO Sala (Sala_Cod, Hospital_Cod, Nombre,         Num_Cama) VALUES (1, 1, 'Emergencia',   10);
INSERT INTO Sala (Sala_Cod, Hospital_Cod, Nombre,         Num_Cama) VALUES (2, 1, 'Encamamiento', 30);
INSERT INTO Sala (Sala_Cod, Hospital_Cod, Nombre,         Num_Cama) VALUES (3, 1, 'UCI',          10);

INSERT INTO Sala (Sala_Cod, Hospital_Cod, Nombre,         Num_Cama) VALUES (1, 2, 'Emergencia',   15);
INSERT INTO Sala (Sala_Cod, Hospital_Cod, Nombre,         Num_Cama) VALUES (2, 2, 'Encamamiento', 50);
INSERT INTO Sala (Sala_Cod, Hospital_Cod, Nombre,         Num_Cama) VALUES (3, 2, 'UCI',          15);

-- ------------------------------------------------------------
-- 5. PLANTILLA — Hospital San Carlos (Hospital_Cod = 1)
-- ------------------------------------------------------------
-- Médicos
INSERT INTO Plantilla (Empleado_No, Sala_Cod, Hospital_Cod, Apellido,  Funcion,    T,   Salario) VALUES (101, 1, 1, 'García',   'Médico', 'M', 12000);
INSERT INTO Plantilla (Empleado_No, Sala_Cod, Hospital_Cod, Apellido,  Funcion,    T,   Salario) VALUES (102, 1, 1, 'López',    'Médico', 'T', 12000);
INSERT INTO Plantilla (Empleado_No, Sala_Cod, Hospital_Cod, Apellido,  Funcion,    T,   Salario) VALUES (103, 2, 1, 'Ramírez',  'Médico', 'M', 12000);
INSERT INTO Plantilla (Empleado_No, Sala_Cod, Hospital_Cod, Apellido,  Funcion,    T,   Salario) VALUES (104, 2, 1, 'Torres',   'Médico', 'N', 12000);
INSERT INTO Plantilla (Empleado_No, Sala_Cod, Hospital_Cod, Apellido,  Funcion,    T,   Salario) VALUES (105, 3, 1, 'Morales',  'Médico', 'M', 12000);
-- Enfermeros
INSERT INTO Plantilla (Empleado_No, Sala_Cod, Hospital_Cod, Apellido,  Funcion,      T,   Salario) VALUES (106, 1, 1, 'Castillo', 'Enfermero', 'M', 6000);
INSERT INTO Plantilla (Empleado_No, Sala_Cod, Hospital_Cod, Apellido,  Funcion,      T,   Salario) VALUES (107, 1, 1, 'Mendoza',  'Enfermero', 'T', 6000);
INSERT INTO Plantilla (Empleado_No, Sala_Cod, Hospital_Cod, Apellido,  Funcion,      T,   Salario) VALUES (108, 2, 1, 'Herrera',  'Enfermero', 'N', 6000);
INSERT INTO Plantilla (Empleado_No, Sala_Cod, Hospital_Cod, Apellido,  Funcion,      T,   Salario) VALUES (109, 2, 1, 'Vásquez',  'Enfermero', 'M', 6000);
INSERT INTO Plantilla (Empleado_No, Sala_Cod, Hospital_Cod, Apellido,  Funcion,      T,   Salario) VALUES (110, 3, 1, 'Reyes',    'Enfermero', 'T', 6000);

-- ------------------------------------------------------------
-- 5. PLANTILLA — Hospital San Miguel (Hospital_Cod = 2)
-- ------------------------------------------------------------
-- Médicos (García y López también trabajan aquí)
INSERT INTO Plantilla (Empleado_No, Sala_Cod, Hospital_Cod, Apellido,    Funcion,    T,   Salario) VALUES (201, 1, 2, 'García',    'Médico', 'M', 12000);
INSERT INTO Plantilla (Empleado_No, Sala_Cod, Hospital_Cod, Apellido,    Funcion,    T,   Salario) VALUES (202, 1, 2, 'López',     'Médico', 'T', 12000);
INSERT INTO Plantilla (Empleado_No, Sala_Cod, Hospital_Cod, Apellido,    Funcion,    T,   Salario) VALUES (203, 2, 2, 'Fuentes',   'Médico', 'M', 12000);
INSERT INTO Plantilla (Empleado_No, Sala_Cod, Hospital_Cod, Apellido,    Funcion,    T,   Salario) VALUES (204, 2, 2, 'Aguilar',   'Médico', 'N', 12000);
INSERT INTO Plantilla (Empleado_No, Sala_Cod, Hospital_Cod, Apellido,    Funcion,    T,   Salario) VALUES (205, 3, 2, 'Cifuentes', 'Médico', 'M', 12000);
-- Enfermeros
INSERT INTO Plantilla (Empleado_No, Sala_Cod, Hospital_Cod, Apellido,  Funcion,      T,   Salario) VALUES (206, 1, 2, 'Pineda',  'Enfermero', 'M', 6000);
INSERT INTO Plantilla (Empleado_No, Sala_Cod, Hospital_Cod, Apellido,  Funcion,      T,   Salario) VALUES (207, 1, 2, 'Solís',   'Enfermero', 'T', 6000);
INSERT INTO Plantilla (Empleado_No, Sala_Cod, Hospital_Cod, Apellido,  Funcion,      T,   Salario) VALUES (208, 2, 2, 'Barrios', 'Enfermero', 'N', 6000);
INSERT INTO Plantilla (Empleado_No, Sala_Cod, Hospital_Cod, Apellido,  Funcion,      T,   Salario) VALUES (209, 2, 2, 'Lemus',   'Enfermero', 'M', 6000);
INSERT INTO Plantilla (Empleado_No, Sala_Cod, Hospital_Cod, Apellido,  Funcion,      T,   Salario) VALUES (210, 3, 2, 'Ajú',     'Enfermero', 'T', 6000);

-- ------------------------------------------------------------
-- 6. ENFERMOS — Masculinos
-- ------------------------------------------------------------
INSERT INTO Enfermo (Inscripcion, Apellido,    Direccion,       Fecha_Nac,    S,   NSS)   VALUES (1,  'Alvarado',  '1 Av zona 1',   '15/03/1990', 'M', 10001);
INSERT INTO Enfermo (Inscripcion, Apellido,    Direccion,       Fecha_Nac,    S,   NSS)   VALUES (2,  'Bautista',  '2 Av zona 2',   '20/07/1985', 'M', 10002);
INSERT INTO Enfermo (Inscripcion, Apellido,    Direccion,       Fecha_Nac,    S,   NSS)   VALUES (3,  'Cárdenas',  '3 Av zona 3',   '11/11/1992', 'M', 10003);
INSERT INTO Enfermo (Inscripcion, Apellido,    Direccion,       Fecha_Nac,    S,   NSS)   VALUES (4,  'Domínguez', '4 Av zona 4',   '05/05/1978', 'M', 10004);
INSERT INTO Enfermo (Inscripcion, Apellido,    Direccion,       Fecha_Nac,    S,   NSS)   VALUES (5,  'Estrada',   '5 Av zona 5',   '30/09/2000', 'M', 10005);
INSERT INTO Enfermo (Inscripcion, Apellido,    Direccion,       Fecha_Nac,    S,   NSS)   VALUES (6,  'Flores',    '6 Av zona 6',   '14/02/1995', 'M', 10006);
INSERT INTO Enfermo (Inscripcion, Apellido,    Direccion,       Fecha_Nac,    S,   NSS)   VALUES (7,  'González',  '7 Av zona 7',   '22/08/1988', 'M', 10007);
INSERT INTO Enfermo (Inscripcion, Apellido,    Direccion,       Fecha_Nac,    S,   NSS)   VALUES (8,  'Hernández', '8 Av zona 8',   '03/01/1975', 'M', 10008);
INSERT INTO Enfermo (Inscripcion, Apellido,    Direccion,       Fecha_Nac,    S,   NSS)   VALUES (9,  'Ibáñez',    '9 Av zona 9',   '17/06/1983', 'M', 10009);
INSERT INTO Enfermo (Inscripcion, Apellido,    Direccion,       Fecha_Nac,    S,   NSS)   VALUES (10, 'Jiménez',   '10 Av zona 10', '28/12/1997', 'M', 10010);

-- ------------------------------------------------------------
-- 6. ENFERMOS — Femeninos
-- ------------------------------------------------------------
INSERT INTO Enfermo (Inscripcion, Apellido,    Direccion,          Fecha_Nac,    S,   NSS)  VALUES (11, 'Aguilar',   '1 Calle zona 1',  '10/04/1991', 'F', 20001);
INSERT INTO Enfermo (Inscripcion, Apellido,    Direccion,          Fecha_Nac,    S,   NSS)  VALUES (12, 'Barrios',   '2 Calle zona 2',  '25/09/1986', 'F', 20002);
INSERT INTO Enfermo (Inscripcion, Apellido,    Direccion,          Fecha_Nac,    S,   NSS)  VALUES (13, 'Castañeda', '3 Calle zona 3',  '08/12/1993', 'F', 20003);
INSERT INTO Enfermo (Inscripcion, Apellido,    Direccion,          Fecha_Nac,    S,   NSS)  VALUES (14, 'Díaz',      '4 Calle zona 4',  '19/03/1979', 'F', 20004);
INSERT INTO Enfermo (Inscripcion, Apellido,    Direccion,          Fecha_Nac,    S,   NSS)  VALUES (15, 'Escobar',   '5 Calle zona 5',  '07/07/2001', 'F', 20005);
INSERT INTO Enfermo (Inscripcion, Apellido,    Direccion,          Fecha_Nac,    S,   NSS)  VALUES (16, 'Fuentes',   '6 Calle zona 6',  '13/11/1996', 'F', 20006);
INSERT INTO Enfermo (Inscripcion, Apellido,    Direccion,          Fecha_Nac,    S,   NSS)  VALUES (17, 'Gramajo',   '7 Calle zona 7',  '02/05/1989', 'F', 20007);
INSERT INTO Enfermo (Inscripcion, Apellido,    Direccion,          Fecha_Nac,    S,   NSS)  VALUES (18, 'Herrera',   '8 Calle zona 8',  '21/08/1976', 'F', 20008);
INSERT INTO Enfermo (Inscripcion, Apellido,    Direccion,          Fecha_Nac,    S,   NSS)  VALUES (19, 'Ixcot',     '9 Calle zona 9',  '16/02/1984', 'F', 20009);
INSERT INTO Enfermo (Inscripcion, Apellido,    Direccion,          Fecha_Nac,    S,   NSS)  VALUES (20, 'Juárez',    '10 Calle zona 10','09/10/1998', 'F', 20010);

-- ------------------------------------------------------------
-- 7. CREAR TABLA MUJERES
-- ------------------------------------------------------------
CREATE TABLE MUJERES (
    Inscripcion  number       NOT NULL,
    Apellido     VARCHAR(50)  NULL,
    Direccion    VARCHAR(50)  NULL,
    Fecha_Nac    VARCHAR(50)  NULL,
    S            VARCHAR(2)   NULL,
    id           INT          NULL
);

-- Trasladar enfermas femeninas
INSERT INTO MUJERES (Inscripcion, Apellido, Direccion, Fecha_Nac, S, id)
SELECT Inscripcion, Apellido, Direccion, Fecha_Nac, S, NSS
FROM Enfermo
WHERE S = 'F';

-- Agregar llave primaria al campo id
-- Oracle:
ALTER TABLE MUJERES MODIFY id INT NOT NULL;
-- SQL Server:
-- ALTER TABLE MUJERES ALTER COLUMN id INT NOT NULL;

ALTER TABLE MUJERES ADD CONSTRAINT PK_Mujeres PRIMARY KEY (id);

-- ------------------------------------------------------------
-- 8. CREAR TABLA EMPLEADOS E INSERTAR DATOS DE EMP
-- ------------------------------------------------------------
CREATE TABLE EMPLEADOS (
    Emp_No     number        NOT NULL,
    Apellido   VARCHAR(50)   NULL,
    Oficio     VARCHAR(50)   NULL,
    Dir        INT           NULL,
    Fecha_Alt  SMALLDATETIME NULL,
    Salario    NUMERIC(9,2)  NULL,
    Comision   NUMERIC(9,2)  NULL,
    Dept_No    number        NULL
);

INSERT INTO EMPLEADOS (Emp_No, Apellido, Oficio, Dir, Fecha_Alt, Salario, Comision, Dept_No)
SELECT Emp_No, Apellido, Oficio, Dir, Fecha_Alt, Salario, Comision, Dept_No
FROM Emp;

-- ------------------------------------------------------------
-- 9. BORRAR TABLA MUJERES
-- ------------------------------------------------------------
DROP TABLE MUJERES;
