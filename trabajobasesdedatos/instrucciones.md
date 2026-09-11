Cree el departamento “informática” es el primero y su localidad será escuintla

Inserte el empleado de numero 8596, apellido “Martínez” , oficio: programador fecha de alta: 07/02/2022 y sueldo 7mil y pertenece al departamento informática

Inserte el empleado de numero 1200, apellido “Juárez” , oficio: programador fecha de alta: 03/02/2022 y sueldo 5mil y pertenece al departamento informática
Inserte el empleado de numero 1500, apellido “Perez” , oficio: programador fecha de alta: 03/02/2022 y sueldo 5mil y pertenece al departamento informática.

Cree el departamento “ventas” es el 2ndo y su localidad será escuintla

Inserte el empleado de numero 1000, apellido “Ordoñez ” , oficio: vendedor fecha de alta: 03/03/2022 y sueldo 5mil y pertenece al departamento vendedor
Inserte el empleado de numero 1800, apellido “Ortiz” , oficio: vendedor fecha de alta: 03/03/2022 y sueldo 5mil y pertenece al departamento vendedor.

Cree el hospital San Carlos, es el primero, dirección 1 calle, telefon :1234567 y numero de camas 50;

Cree el hospital San Miguel, es el segundo, dirección 3 calle, telefon :1234567 y numero de camas 80;

Cada hospital debe tener sala de emergencia, encamamiento y uci;

Cree 5 médicos por cada hospital en la tabla plantilla, recuerde que un medico puede trabajar en los 2 hospitales y asígnelos en las salas correspondientes;
Cree 5 enfermeros por cada hospital en la tabla plantilla;




Cree 10 enfermos de sexo masculino y 10 de sexo femenino en cada hospital.
Cree una tabla llamada “Mujeres” y traslade todos los enfermos de sexo femenino a la tabla Mujeres
CREATE TABLE MUJERES
(
Inscripcion number NOT NULL,
Apellido VARCHAR(50) NULL,
Direccion VARCHAR(50) NULL,
Fecha_Nac VARCHAR(50) NULL,
S VARCHAR(2) NULL,
id INT NULL
)
;

Modifique la tabla mujeres y agregue como llave primaria el id;
  
Crear una tabla llamada Empleados e introducir todos los datos de la tabla EMP en ella.



CREATE TABLE EMPLEADOS
(
Emp_No number NOT NULL,
Apellido VARCHAR(50) NULL,
Oficio VARCHAR(50) NULL,
Dir INT NULL,
Fecha_Alt SMALLDATETIME NULL,
Salario number(9,2) NULL,
Comision number(9,2) NULL,
Dept_No number NULL
)

Borrar la tabla y registros de Mujeres
