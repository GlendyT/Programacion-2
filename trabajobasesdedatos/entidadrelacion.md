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
        string Dir
        date Fecha_Alt
        decimal Salario
        decimal Comision
        int Dept_No FK
    }

    ENFERMO {
        int Inscripcion PK
        string Apellido
        string Direccion
        date Fecha_Nac
        string S
        string NSS
    }

    HOSPITAL {
        int Hospital_Cod PK
        string Nombre
        string Direccion
        string Telefono
        int Num_Cama
    }

    DOCTOR {
        int Doctor_No PK
        int Hospital_Cod FK
        string Apellido
        string Especialidad
    }

    SALA {
        int Sala_Cod PK
        int Hospital_Cod PK, FK
        string Nombre
        int Num_Cama
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

    DEPT ||--o{ EMP : tiene

    HOSPITAL ||--o{ DOCTOR : tiene
    HOSPITAL ||--o{ SALA : contiene
    SALA ||--o{ PLANTILLA : tiene