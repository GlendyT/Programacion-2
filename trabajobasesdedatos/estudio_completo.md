# Trabajo Base de Datos — Estudio Completo

---

## 1. Diagrama Entidad-Relación (Corregido y Completo)

### Cambios respecto al ER original:
- `ENFERMO` ahora se relaciona con `SALA` (un enfermo está internado en una sala de un hospital)
- `DOCTOR` ahora tiene relación **muchos-a-muchos** con `HOSPITAL` a través de `DOCTOR_HOSPITAL` (un médico puede trabajar en más de un hospital)
- `DOCTOR` ahora se relaciona con `SALA` (un doctor trabaja en una sala específica)

```mermaid
erDiagram

    DEPT {
        int Dept_No PK
        string DNombre
        string Loc
    }

    EMP {
        int Emp_No PK
        string Apellido
        string Oficio
        int Dir
        date Fecha_Alt
        decimal Salario
        decimal Comision
        int Dept_No FK
    }

    HOSPITAL {
        int Hospital_Cod PK
        string Nombre
        string Direccion
        string Telefono
        int Num_Cama
    }

    SALA {
        int Sala_Cod PK
        int Hospital_Cod PK_FK
        string Nombre
        int Num_Cama
    }

    DOCTOR {
        int Doctor_No PK
        string Apellido
        string Especialidad
    }

    DOCTOR_HOSPITAL {
        int Doctor_No PK_FK
        int Hospital_Cod PK_FK
        int Sala_Cod FK
    }

    PLANTILLA {
        int Empleado_No PK
        int Sala_Cod FK
        int Hospital_Cod FK
        string Apellido
        string Funcion
        string T
        decimal Salario
    }

    ENFERMO {
        int Inscripcion PK
        string Apellido
        string Direccion
        string Fecha_Nac
        string S
        int NSS
        int Sala_Cod FK
        int Hospital_Cod FK
    }

    DEPT ||--o{ EMP : "tiene"
    HOSPITAL ||--o{ SALA : "contiene"
    SALA ||--o{ PLANTILLA : "tiene personal"
    SALA ||--o{ ENFERMO : "aloja"
    DOCTOR }o--o{ HOSPITAL : "trabaja en"
    DOCTOR_HOSPITAL }o--|| DOCTOR : ""
    DOCTOR_HOSPITAL }o--|| HOSPITAL : ""
    DOCTOR_HOSPITAL }o--|| SALA : "asignado a"
```

---

## 2. Creación de Tablas (DDL)

> Estas tablas ya están en `basededatos.sql`. Se incluyen aquí para referencia completa.

```sql
CREATE TABLE Dept (
    Dept_No   number       NOT NULL,
    DNombre   VARCHAR(50)  NULL,
    Loc       VARCHAR(50)  NULL,
    CONSTRAINT PK_Dept PRIMARY KEY (Dept_No)
);

CREATE TABLE Emp (
    Emp_No    number        NOT NULL,
    Apellido  VARCHAR(50)   NULL,
    Oficio    VARCHAR(50)   NULL,
    Dir       number        NULL,
    Fecha_Alt date          NULL,
    Salario   NUMERIC(9,2)  NULL,
    Comision  NUMERIC(9,2)  NULL,
    Dept_No   number        NULL,
    CONSTRAINT PK_Emp PRIMARY KEY (Emp_No),
    CONSTRAINT FK_Emp_Dept FOREIGN KEY (Dept_No) REFERENCES Dept(Dept_No)
);

CREATE TABLE Hospital (
    Hospital_Cod  number       NOT NULL,
    Nombre        VARCHAR(50)  NULL,
    Direccion     VARCHAR(50)  NULL,
    Telefono      VARCHAR(50)  NULL,
    Num_Cama      number       NULL,
    CONSTRAINT PK_Hospital PRIMARY KEY (Hospital_Cod)
);

CREATE TABLE Doctor (
    Doctor_No     number       NOT NULL,
    Hospital_Cod  number       NOT NULL,
    Apellido      VARCHAR(50)  NULL,
    Especialidad  VARCHAR(50)  NULL,
    CONSTRAINT PK_Doctor PRIMARY KEY (Doctor_No),
    CONSTRAINT FK_Doctor_Hospital FOREIGN KEY (Hospital_Cod) REFERENCES Hospital(Hospital_Cod)
);

CREATE TABLE Sala (
    Sala_Cod      number       NOT NULL,
    Hospital_Cod  number       NOT NULL,
    Nombre        VARCHAR(50)  NULL,
    Num_Cama      number       NULL,
    CONSTRAINT PK_Sala PRIMARY KEY (Sala_Cod, Hospital_Cod),
    CONSTRAINT FK_Sala_Hospital FOREIGN KEY (Hospital_Cod) REFERENCES Hospital(Hospital_Cod)
);

CREATE TABLE Plantilla (
    Empleado_No   number        NOT NULL,
    Sala_Cod      number        NOT NULL,
    Hospital_Cod  number        NOT NULL,
    Apellido      VARCHAR(50)   NULL,
    Funcion       VARCHAR(50)   NULL,
    T             VARCHAR(15)   NULL,
    Salario       NUMERIC(9,2)  NULL,
    CONSTRAINT PK_Plantilla PRIMARY KEY (Empleado_No),
    CONSTRAINT FK_Plantilla_Sala FOREIGN KEY (Sala_Cod, Hospital_Cod) REFERENCES Sala(Sala_Cod, Hospital_Cod)
);

CREATE TABLE Enfermo (
    Inscripcion  number       NOT NULL,
    Apellido     VARCHAR(50)  NULL,
    Direccion    VARCHAR(50)  NULL,
    Fecha_Nac    VARCHAR(50)  NULL,
    S            VARCHAR(2)   NULL,
    NSS          number       NULL
);
```

---

## 3. Inserts — Departamentos y Empleados

### Departamentos

```sql
-- Departamento 1: Informática
INSERT INTO Dept (Dept_No, DNombre, Loc)
VALUES (1, 'Informática', 'Escuintla');

-- Departamento 2: Ventas
INSERT INTO Dept (Dept_No, DNombre, Loc)
VALUES (2, 'Ventas', 'Escuintla');
```

### Empleados de Informática (Dept_No = 1)

```sql
INSERT INTO Emp (Emp_No, Apellido, Oficio, Fecha_Alt, Salario, Dept_No)
VALUES (8596, 'Martínez', 'Programador', TO_DATE('07/02/2022','DD/MM/YYYY'), 7000, 1);

INSERT INTO Emp (Emp_No, Apellido, Oficio, Fecha_Alt, Salario, Dept_No)
VALUES (1200, 'Juárez', 'Programador', TO_DATE('03/02/2022','DD/MM/YYYY'), 5000, 1);

INSERT INTO Emp (Emp_No, Apellido, Oficio, Fecha_Alt, Salario, Dept_No)
VALUES (1500, 'Perez', 'Programador', TO_DATE('03/02/2022','DD/MM/YYYY'), 5000, 1);
```

### Empleados de Ventas (Dept_No = 2)

```sql
INSERT INTO Emp (Emp_No, Apellido, Oficio, Fecha_Alt, Salario, Dept_No)
VALUES (1000, 'Ordoñez', 'Vendedor', TO_DATE('03/03/2022','DD/MM/YYYY'), 5000, 2);

INSERT INTO Emp (Emp_No, Apellido, Oficio, Fecha_Alt, Salario, Dept_No)
VALUES (1800, 'Ortiz', 'Vendedor', TO_DATE('03/03/2022','DD/MM/YYYY'), 5000, 2);
```

> **Nota:** `TO_DATE` es sintaxis Oracle. En SQL Server usar `CONVERT(date, '07/02/2022', 103)`.

---

## 4. Inserts — Hospitales

```sql
INSERT INTO Hospital (Hospital_Cod, Nombre, Direccion, Telefono, Num_Cama)
VALUES (1, 'San Carlos', '1 Calle', '1234567', 50);

INSERT INTO Hospital (Hospital_Cod, Nombre, Direccion, Telefono, Num_Cama)
VALUES (2, 'San Miguel', '3 Calle', '1234567', 80);
```

---

## 5. Inserts — Salas (Emergencia, Encamamiento, UCI por hospital)

> Clave primaria compuesta: (Sala_Cod, Hospital_Cod)

```sql
-- Hospital San Carlos (Hospital_Cod = 1)
INSERT INTO Sala (Sala_Cod, Hospital_Cod, Nombre, Num_Cama) VALUES (1, 1, 'Emergencia',   10);
INSERT INTO Sala (Sala_Cod, Hospital_Cod, Nombre, Num_Cama) VALUES (2, 1, 'Encamamiento', 30);
INSERT INTO Sala (Sala_Cod, Hospital_Cod, Nombre, Num_Cama) VALUES (3, 1, 'UCI',          10);

-- Hospital San Miguel (Hospital_Cod = 2)
INSERT INTO Sala (Sala_Cod, Hospital_Cod, Nombre, Num_Cama) VALUES (1, 2, 'Emergencia',   15);
INSERT INTO Sala (Sala_Cod, Hospital_Cod, Nombre, Num_Cama) VALUES (2, 2, 'Encamamiento', 50);
INSERT INTO Sala (Sala_Cod, Hospital_Cod, Nombre, Num_Cama) VALUES (3, 2, 'UCI',          15);
```

---

## 6. Inserts — Plantilla (5 médicos + 5 enfermeros por hospital)

> `Funcion` = 'Médico' o 'Enfermero' | `T` = turno (M=Mañana, T=Tarde, N=Noche)
> Un médico puede aparecer en ambos hospitales (mismo Empleado_No no es posible con PK simple — se asignan números distintos para cada registro de plantilla).

### Hospital San Carlos (Hospital_Cod = 1)

```sql
-- Médicos
INSERT INTO Plantilla (Empleado_No, Sala_Cod, Hospital_Cod, Apellido, Funcion, T, Salario)
VALUES (101, 1, 1, 'García',    'Médico', 'M', 12000);
INSERT INTO Plantilla (Empleado_No, Sala_Cod, Hospital_Cod, Apellido, Funcion, T, Salario)
VALUES (102, 1, 1, 'López',     'Médico', 'T', 12000);
INSERT INTO Plantilla (Empleado_No, Sala_Cod, Hospital_Cod, Apellido, Funcion, T, Salario)
VALUES (103, 2, 1, 'Ramírez',   'Médico', 'M', 12000);
INSERT INTO Plantilla (Empleado_No, Sala_Cod, Hospital_Cod, Apellido, Funcion, T, Salario)
VALUES (104, 2, 1, 'Torres',    'Médico', 'N', 12000);
INSERT INTO Plantilla (Empleado_No, Sala_Cod, Hospital_Cod, Apellido, Funcion, T, Salario)
VALUES (105, 3, 1, 'Morales',   'Médico', 'M', 12000);

-- Enfermeros
INSERT INTO Plantilla (Empleado_No, Sala_Cod, Hospital_Cod, Apellido, Funcion, T, Salario)
VALUES (106, 1, 1, 'Castillo',  'Enfermero', 'M', 6000);
INSERT INTO Plantilla (Empleado_No, Sala_Cod, Hospital_Cod, Apellido, Funcion, T, Salario)
VALUES (107, 1, 1, 'Mendoza',   'Enfermero', 'T', 6000);
INSERT INTO Plantilla (Empleado_No, Sala_Cod, Hospital_Cod, Apellido, Funcion, T, Salario)
VALUES (108, 2, 1, 'Herrera',   'Enfermero', 'N', 6000);
INSERT INTO Plantilla (Empleado_No, Sala_Cod, Hospital_Cod, Apellido, Funcion, T, Salario)
VALUES (109, 2, 1, 'Vásquez',   'Enfermero', 'M', 6000);
INSERT INTO Plantilla (Empleado_No, Sala_Cod, Hospital_Cod, Apellido, Funcion, T, Salario)
VALUES (110, 3, 1, 'Reyes',     'Enfermero', 'T', 6000);
```

### Hospital San Miguel (Hospital_Cod = 2)

```sql
-- Médicos (García y López también trabajan aquí → mismo apellido, nuevo Empleado_No)
INSERT INTO Plantilla (Empleado_No, Sala_Cod, Hospital_Cod, Apellido, Funcion, T, Salario)
VALUES (201, 1, 2, 'García',    'Médico', 'M', 12000);
INSERT INTO Plantilla (Empleado_No, Sala_Cod, Hospital_Cod, Apellido, Funcion, T, Salario)
VALUES (202, 1, 2, 'López',     'Médico', 'T', 12000);
INSERT INTO Plantilla (Empleado_No, Sala_Cod, Hospital_Cod, Apellido, Funcion, T, Salario)
VALUES (203, 2, 2, 'Fuentes',   'Médico', 'M', 12000);
INSERT INTO Plantilla (Empleado_No, Sala_Cod, Hospital_Cod, Apellido, Funcion, T, Salario)
VALUES (204, 2, 2, 'Aguilar',   'Médico', 'N', 12000);
INSERT INTO Plantilla (Empleado_No, Sala_Cod, Hospital_Cod, Apellido, Funcion, T, Salario)
VALUES (205, 3, 2, 'Cifuentes', 'Médico', 'M', 12000);

-- Enfermeros
INSERT INTO Plantilla (Empleado_No, Sala_Cod, Hospital_Cod, Apellido, Funcion, T, Salario)
VALUES (206, 1, 2, 'Pineda',    'Enfermero', 'M', 6000);
INSERT INTO Plantilla (Empleado_No, Sala_Cod, Hospital_Cod, Apellido, Funcion, T, Salario)
VALUES (207, 1, 2, 'Solís',     'Enfermero', 'T', 6000);
INSERT INTO Plantilla (Empleado_No, Sala_Cod, Hospital_Cod, Apellido, Funcion, T, Salario)
VALUES (208, 2, 2, 'Barrios',   'Enfermero', 'N', 6000);
INSERT INTO Plantilla (Empleado_No, Sala_Cod, Hospital_Cod, Apellido, Funcion, T, Salario)
VALUES (209, 2, 2, 'Lemus',     'Enfermero', 'M', 6000);
INSERT INTO Plantilla (Empleado_No, Sala_Cod, Hospital_Cod, Apellido, Funcion, T, Salario)
VALUES (210, 3, 2, 'Ajú',       'Enfermero', 'T', 6000);
```

---

## 7. Inserts — Enfermos (10 masculinos + 10 femeninos)

> `S` = 'M' (masculino) o 'F' (femenino)

```sql
-- Masculinos
INSERT INTO Enfermo (Inscripcion, Apellido, Direccion, Fecha_Nac, S, NSS)
VALUES (1,  'Alvarado',   '1 Av zona 1',  '15/03/1990', 'M', 10001);
INSERT INTO Enfermo (Inscripcion, Apellido, Direccion, Fecha_Nac, S, NSS)
VALUES (2,  'Bautista',   '2 Av zona 2',  '20/07/1985', 'M', 10002);
INSERT INTO Enfermo (Inscripcion, Apellido, Direccion, Fecha_Nac, S, NSS)
VALUES (3,  'Cárdenas',   '3 Av zona 3',  '11/11/1992', 'M', 10003);
INSERT INTO Enfermo (Inscripcion, Apellido, Direccion, Fecha_Nac, S, NSS)
VALUES (4,  'Domínguez',  '4 Av zona 4',  '05/05/1978', 'M', 10004);
INSERT INTO Enfermo (Inscripcion, Apellido, Direccion, Fecha_Nac, S, NSS)
VALUES (5,  'Estrada',    '5 Av zona 5',  '30/09/2000', 'M', 10005);
INSERT INTO Enfermo (Inscripcion, Apellido, Direccion, Fecha_Nac, S, NSS)
VALUES (6,  'Flores',     '6 Av zona 6',  '14/02/1995', 'M', 10006);
INSERT INTO Enfermo (Inscripcion, Apellido, Direccion, Fecha_Nac, S, NSS)
VALUES (7,  'González',   '7 Av zona 7',  '22/08/1988', 'M', 10007);
INSERT INTO Enfermo (Inscripcion, Apellido, Direccion, Fecha_Nac, S, NSS)
VALUES (8,  'Hernández',  '8 Av zona 8',  '03/01/1975', 'M', 10008);
INSERT INTO Enfermo (Inscripcion, Apellido, Direccion, Fecha_Nac, S, NSS)
VALUES (9,  'Ibáñez',     '9 Av zona 9',  '17/06/1983', 'M', 10009);
INSERT INTO Enfermo (Inscripcion, Apellido, Direccion, Fecha_Nac, S, NSS)
VALUES (10, 'Jiménez',    '10 Av zona 10','28/12/1997', 'M', 10010);

-- Femeninos
INSERT INTO Enfermo (Inscripcion, Apellido, Direccion, Fecha_Nac, S, NSS)
VALUES (11, 'Aguilar',    '1 Calle zona 1', '10/04/1991', 'F', 20001);
INSERT INTO Enfermo (Inscripcion, Apellido, Direccion, Fecha_Nac, S, NSS)
VALUES (12, 'Barrios',    '2 Calle zona 2', '25/09/1986', 'F', 20002);
INSERT INTO Enfermo (Inscripcion, Apellido, Direccion, Fecha_Nac, S, NSS)
VALUES (13, 'Castañeda',  '3 Calle zona 3', '08/12/1993', 'F', 20003);
INSERT INTO Enfermo (Inscripcion, Apellido, Direccion, Fecha_Nac, S, NSS)
VALUES (14, 'Díaz',       '4 Calle zona 4', '19/03/1979', 'F', 20004);
INSERT INTO Enfermo (Inscripcion, Apellido, Direccion, Fecha_Nac, S, NSS)
VALUES (15, 'Escobar',    '5 Calle zona 5', '07/07/2001', 'F', 20005);
INSERT INTO Enfermo (Inscripcion, Apellido, Direccion, Fecha_Nac, S, NSS)
VALUES (16, 'Fuentes',    '6 Calle zona 6', '13/11/1996', 'F', 20006);
INSERT INTO Enfermo (Inscripcion, Apellido, Direccion, Fecha_Nac, S, NSS)
VALUES (17, 'Gramajo',    '7 Calle zona 7', '02/05/1989', 'F', 20007);
INSERT INTO Enfermo (Inscripcion, Apellido, Direccion, Fecha_Nac, S, NSS)
VALUES (18, 'Herrera',    '8 Calle zona 8', '21/08/1976', 'F', 20008);
INSERT INTO Enfermo (Inscripcion, Apellido, Direccion, Fecha_Nac, S, NSS)
VALUES (19, 'Ixcot',      '9 Calle zona 9', '16/02/1984', 'F', 20009);
INSERT INTO Enfermo (Inscripcion, Apellido, Direccion, Fecha_Nac, S, NSS)
VALUES (20, 'Juárez',     '10 Calle zona 10','09/10/1998','F', 20010);
```

---

## 8. Crear tabla MUJERES y trasladar enfermas femeninas

### Crear tabla

```sql
CREATE TABLE MUJERES (
    Inscripcion  number       NOT NULL,
    Apellido     VARCHAR(50)  NULL,
    Direccion    VARCHAR(50)  NULL,
    Fecha_Nac    VARCHAR(50)  NULL,
    S            VARCHAR(2)   NULL,
    id           INT          NULL
);
```

### Trasladar datos (INSERT + SELECT)

```sql
INSERT INTO MUJERES (Inscripcion, Apellido, Direccion, Fecha_Nac, S, id)
SELECT Inscripcion, Apellido, Direccion, Fecha_Nac, S, NSS
FROM Enfermo
WHERE S = 'F';
```

### Agregar llave primaria al campo `id`

```sql
-- Primero asegurarse que id no tenga NULLs
ALTER TABLE MUJERES MODIFY id INT NOT NULL;

-- Agregar la llave primaria
ALTER TABLE MUJERES ADD CONSTRAINT PK_Mujeres PRIMARY KEY (id);
```

> **Nota SQL Server:** Usar `ALTER COLUMN` en lugar de `MODIFY`:
> ```sql
> ALTER TABLE MUJERES ALTER COLUMN id INT NOT NULL;
> ALTER TABLE MUJERES ADD CONSTRAINT PK_Mujeres PRIMARY KEY (id);
> ```

---

## 9. Crear tabla EMPLEADOS e insertar datos de EMP

### Crear tabla

```sql
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
```

### Insertar todos los datos de EMP

```sql
INSERT INTO EMPLEADOS (Emp_No, Apellido, Oficio, Dir, Fecha_Alt, Salario, Comision, Dept_No)
SELECT Emp_No, Apellido, Oficio, Dir, Fecha_Alt, Salario, Comision, Dept_No
FROM Emp;
```

---

## 10. Borrar tabla MUJERES

```sql
DROP TABLE MUJERES;
```

---

## 11. Resumen de conceptos clave

| Concepto | Descripción |
|---|---|
| `PRIMARY KEY` | Identifica de forma única cada fila. No puede ser NULL ni duplicada. |
| `FOREIGN KEY` | Referencia a la PK de otra tabla. Garantiza integridad referencial. |
| `PK compuesta` | Clave primaria formada por 2+ columnas (ej. `Sala_Cod + Hospital_Cod`). |
| `INSERT INTO ... SELECT` | Copia datos de una tabla a otra sin escribir cada fila manualmente. |
| `ALTER TABLE ... ADD CONSTRAINT` | Agrega restricciones (PK, FK, UNIQUE) a una tabla ya existente. |
| `DROP TABLE` | Elimina la tabla y todos sus datos permanentemente. |
| `TO_DATE` (Oracle) | Convierte string a fecha con formato especificado. |
| `CONVERT` (SQL Server) | Equivalente de `TO_DATE` en SQL Server. |

---

## 12. Orden correcto de ejecución

```
1. CREATE TABLE Dept, Emp, Hospital, Doctor, Sala, Plantilla, Enfermo
2. INSERT Dept (informática, ventas)
3. INSERT Emp (empleados de cada depto)
4. INSERT Hospital (San Carlos, San Miguel)
5. INSERT Sala (3 salas por hospital)
6. INSERT Plantilla (médicos y enfermeros)
7. INSERT Enfermo (masculinos y femeninos)
8. CREATE TABLE MUJERES
9. INSERT INTO MUJERES ... SELECT FROM Enfermo WHERE S='F'
10. ALTER TABLE MUJERES → agregar PK en id
11. CREATE TABLE EMPLEADOS
12. INSERT INTO EMPLEADOS ... SELECT FROM Emp
13. DROP TABLE MUJERES
```
