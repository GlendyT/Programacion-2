# Diagrama UML de Clases — Proyecto Primerp

## Diagrama

```mermaid
classDiagram
    class Vehiculo {
        ~String marca
        ~String modelo
        ~int anio
        -double precio
        -Llanta llanta
        -Motor motor
        +getModelo() String
        +getMarca() String
        +getLlanta() Llanta
        +getMotor() Motor
        +GetPrecio() double
        +getAnio() int
        +setPrecio(double nprecio) void
        +setAnio(int anio) void
        +setLlanta(Llanta nllanta) void
        +setMotor(Motor nmotor) void
        +Vehiculo()
        +Vehiculo(String, String, int, double, Llanta)
        +Vehiculo(String, String, int, double, Llanta, Motor)
        ~mostrarInformacion() void
    }

    class Llanta {
        -String marca
        -int tamanio
        -double presion
        +Llanta(String, int, double)
        +mostrarInformacion() void
    }

    class Motor {
        -String tipo
        -int cilindrada
        +Motor()
        +Motor(String, int)
        +getTipo() String
        +getCilindrada() int
        +setTipo(String tipo) void
        +setCilindrada(int cilindrada) void
        +mostrarInformacion() void
    }

    class FrmVehiculo {
        ~JLabel lblTitulo
        ~JLabel lblMarca
        ~JTextField txtMarca
        ~JLabel lblModelo
        ~JTextField txtModelo
        ~JLabel lblAnio
        ~JTextField txtAnio
        ~JLabel lblPrecio
        ~JTextField txtPrecio
        ~JLabel lblMarcaLlanta
        ~JTextField txtMarcaLlanta
        ~JLabel lblTamanioLlanta
        ~JTextField txtTamanioLlanta
        ~JLabel lblPresionLlanta
        ~JTextField txtPresionLlanta
        ~JButton btnGuardar
        ~JButton btnMotor
        ~int contador
        ~JTable tablaVehiculos
        ~DefaultTableModel modeloTabla
        ~JScrollPane scrollTabla
        ~ArrayList~Vehiculo~ carros
        ~FrmVehiculo()
        -functionbtn() void
    }

    class FrmMotor {
        ~JLabel lblTitulo
        ~JLabel lblTipo
        ~JComboBox~String~ cmbTipo
        ~JLabel lblCilindrada
        ~JTextField txtCilindrada
        ~JButton btnCrear
        ~JButton btnActualizar
        ~JButton btnEliminar
        ~JButton btnLimpiar
        ~JTable tablaMotores
        ~DefaultTableModel modeloTabla
        ~JScrollPane scrollTabla
        ~ArrayList~Motor~ motores
        ~int filaSeleccionada
        ~FrmMotor()
        -crearMotor() void
        -actualizarMotor() void
        -eliminarMotor() void
        -cargarMotorEnCampos(int fila) void
        -limpiarCampos() void
    }

    class FormMotos {
        ~FormMotos()
    }

    class FrrmBuses {
        ~FrrmBuses()
    }

    class Primerp {
        +main(String[] args)$ void
    }

    Vehiculo *-- Llanta : composición
    Vehiculo *-- Motor : composición
    FrmVehiculo <|-- FormMotos : herencia
    FrmVehiculo <|-- FrrmBuses : herencia
    FrmVehiculo --> Vehiculo : crea/gestiona
    FrmVehiculo --> Llanta : crea
    FrmVehiculo ..> FrmMotor : abre
    FrmMotor --> Motor : CRUD
    FrmVehiculo --|> JFrame : herencia
    FrmMotor --|> JFrame : herencia
    Primerp ..> FrmVehiculo : instancia
    Primerp ..> FrrmBuses : instancia
    Primerp ..> FormMotos : instancia
```

## Descripción de relaciones

| Relación | Tipo | Descripción |
|---|---|---|
| `Vehiculo` ◆── `Llanta` | **Composición** | Un vehículo tiene una llanta (parte esencial) |
| `Vehiculo` ◆── `Motor` | **Composición** | Un vehículo tiene un motor (parte esencial) |
| `FrmVehiculo` ◁── `FormMotos` | **Herencia** | FormMotos hereda de FrmVehiculo |
| `FrmVehiculo` ◁── `FrrmBuses` | **Herencia** | FrrmBuses hereda de FrmVehiculo |
| `FrmVehiculo` ──▷ `FrmMotor` | **Dependencia** | El formulario de vehículo abre el formulario de motor |
| `FrmMotor` ──▷ `Motor` | **Asociación** | FrmMotor realiza CRUD sobre Motor |
| `FrmVehiculo` ──▷ `JFrame` | **Herencia** | FrmVehiculo extiende JFrame (Swing) |
| `FrmMotor` ──▷ `JFrame` | **Herencia** | FrmMotor extiende JFrame (Swing) |

## Resumen de clases nuevas

### Motor
- **Atributos:** `tipo`, `cilindrada`
- **Comportamientos:** getters, setters (con validación), `mostrarInformacion()`

### FrmMotor
- **Propósito:** Formulario CRUD completo para gestionar motores
- **Operaciones:** Crear, Actualizar, Eliminar, Limpiar campos
- **Interacción:** Se abre desde el botón "Gestionar Motor" en FrmVehiculo
