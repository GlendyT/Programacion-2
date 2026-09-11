CREATE TABLE Dept
(
	Dept_No			number				NOT NULL,
	DNombre			VARCHAR(50)		NULL,
	Loc				VARCHAR(50)		NULL,
	CONSTRAINT PK_Dept PRIMARY KEY(Dept_No)
);
 
CREATE TABLE Emp
(
	Emp_No			number				NOT NULL,
	Apellido		VARCHAR(50)		NULL,
	Oficio			VARCHAR(50)		NULL,
	Dir				number				NULL,
	Fecha_Alt		date	NULL,
	Salario			NUMERIC(9,2)	NULL,
	Comision		NUMERIC(9,2)	NULL,
	Dept_No			number				NULL,
	CONSTRAINT PK_Emp PRIMARY KEY(Emp_No),
	CONSTRAINT FK_Emp_Dept FOREIGN KEY (Dept_No) REFERENCES Dept(Dept_No)
);
 
CREATE TABLE Hospital
(
	Hospital_Cod  number			NOT NULL,
	Nombre			VARCHAR(50)		NULL,
	Direccion		VARCHAR(50)		NULL,
	Telefono		VARCHAR(50)		NULL,
	Num_Cama number			NULL,
	CONSTRAINT PK_Hospital PRIMARY KEY(Hospital_Cod)
);
 
CREATE TABLE Doctor
(
	Doctor_No  number			NOT NULL,
	Hospital_Cod  number			NOT NULL,
	Apellido		VARCHAR(50)		NULL,
	Especialidad	VARCHAR(50)		NULL,
	CONSTRAINT PK_Doctor PRIMARY KEY(Doctor_No),
	CONSTRAINT FK_Doctor_Hospital FOREIGN KEY (Hospital_Cod) REFERENCES Hospital(Hospital_Cod)
)
 ;
CREATE TABLE Sala
(
	Sala_Cod  number			NOT NULL,	
	Hospital_Cod  number			NOT NULL,
	Nombre			VARCHAR(50)		NULL,
	Num_Cama  number			NULL,
	CONSTRAINT PK_Sala PRIMARY KEY(Sala_Cod,Hospital_Cod),
	CONSTRAINT FK_Sala_Hospital FOREIGN KEY (Hospital_Cod) REFERENCES Hospital(Hospital_Cod)
)
 ;
CREATE TABLE Plantilla
(
	Empleado_No  number			NOT NULL,
	Sala_Cod  number			NOT NULL,	
	Hospital_Cod  number			NOT NULL,
	Apellido		VARCHAR(50)		NULL,
	Funcion			VARCHAR(50)		NULL,
	T				VARCHAR(15)		NULL,
	Salario			NUMERIC(9,2)	NULL,
	CONSTRAINT PK_Plantilla PRIMARY KEY(Empleado_No),
	CONSTRAINT FK_Plantilla_Sala01 FOREIGN KEY (Sala_Cod,Hospital_Cod) REFERENCES Sala(Sala_Cod,Hospital_Cod)
)
 ;
CREATE TABLE Enfermo
(
	Inscripcion  number			NOT NULL,
	Apellido		VARCHAR(50)		NULL,
	Direccion		VARCHAR(50)		NULL,
	Fecha_Nac		VARCHAR(50)		NULL,
	S				VARCHAR(2)		NULL,
	NSS		  number			NULL
);