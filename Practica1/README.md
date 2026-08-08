# Práctica 1 - Sistema de Facturación

Este proyecto es una implementación en Java basada en el diagrama de clases UML proporcionado en el documento **"Practica 1 (2).docx"**.

## Resumen de la Implementación

Se han modelado y programado las 4 clases principales con sus respectivos atributos, constructores, métodos y relaciones, respetando estrictamente el diagrama UML:

### 1. Clase `Proveedor`
- **Atributos:** `codigo` (int), `nombre` (String), `nit` (String), `telefono` (String).
- **Métodos:** Constructor para inicializar todos los datos, `getNombre()` y `getNit()`.
- **Relación:** Se implementó una relación donde **1 Proveedor tiene de 0 a muchos (0..*) Facturas**. Esto se logró agregando una lista `List<Factura>` dentro del Proveedor.

### 2. Clase `Factura`
- **Atributos:** `numero` (int), `fecha` (String), `total` (double).
- **Métodos:** Constructor, `agregarDetalle(detalle: DetalleFactura)` y `calcularTotal()`.
- **Relaciones:** 
  - **Composición:** **1 Factura contiene de 1 a muchos (1..*) DetalleFactura**. Al eliminarse o crearse la factura, debe manejar sus propios detalles (implementado mediante una `List<DetalleFactura>`).
  - Cada factura recibe en su constructor al `Proveedor` al que pertenece.

### 3. Clase `DetalleFactura`
- **Atributos:** `cantidad` (int), `precio` (double), `subtotal` (double).
- **Métodos:** Constructor, `calcularSubtotal()` y métodos "getter".
- **Relación:** **0 a muchos (0..*) DetalleFactura corresponden a 1 Articulo**. Se incluyó una referencia directa al objeto `Articulo` para poder obtener su precio y datos en el detalle.

### 4. Clase `Articulo`
- **Atributos:** `codigo` (int), `nombre` (String), `precio` (double).
- **Métodos:** Constructor, `getNombre()` y `getPrecio()`.
- Es una clase independiente que provee la información que `DetalleFactura` utiliza.

## Estructura del Proyecto

El código fuente ha sido estructurado en un formato compatible con proyectos de **NetBeans** (similar a `primerp`), dentro de la carpeta `Practica1`. 

Se agregó una clase extra llamada **`Practica1.java`** que contiene el método `main`. Esto permite ejecutar el proyecto, crear instancias de prueba (crear un proveedor, artículos, agregarlos a una factura y calcular su total) y comprobar de forma práctica que las clases y sus relaciones funcionan correctamente tal y como indica el diagrama UML.
