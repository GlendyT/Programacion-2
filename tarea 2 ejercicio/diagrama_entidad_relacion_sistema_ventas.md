# Diagrama Entidad-Relación — Sistema de Ventas

```mermaid
erDiagram

    PROVEEDOR {
        VARCHAR2 rut PK
        VARCHAR2 nombre
        VARCHAR2 calle
        VARCHAR2 numero
        VARCHAR2 comuna
        VARCHAR2 ciudad
        VARCHAR2 telefono
        VARCHAR2 pagina_web
    }

    CATEGORIA {
        NUMBER id_categoria PK
        VARCHAR2 nombre
        VARCHAR2 descripcion
    }

    PRODUCTO {
        NUMBER id_producto PK
        VARCHAR2 nombre
        NUMBER precio_actual
        NUMBER stock
        VARCHAR2 rut_proveedor FK
        NUMBER id_categoria FK
    }

    CLIENTE {
        VARCHAR2 rut PK
        VARCHAR2 nombre
        VARCHAR2 calle
        VARCHAR2 numero
        VARCHAR2 comuna
        VARCHAR2 ciudad
    }

    CLIENTE_TELEFONO {
        NUMBER id_telefono PK
        VARCHAR2 rut_cliente FK
        VARCHAR2 telefono
    }

    VENTA {
        NUMBER id_venta PK
        DATE fecha
        VARCHAR2 rut_cliente FK
        NUMBER descuento
        NUMBER monto_final
    }

    DETALLE_VENTA {
        NUMBER id_detalle PK
        NUMBER id_venta FK
        NUMBER id_producto FK
        NUMBER precio_momento
        NUMBER cantidad
        NUMBER monto_total
    }

    PROVEEDOR ||--o{ PRODUCTO : "suministra"
    CATEGORIA ||--o{ PRODUCTO : "clasifica"
    CLIENTE ||--o{ CLIENTE_TELEFONO : "tiene"
    CLIENTE ||--o{ VENTA : "realiza"
    VENTA ||--o{ DETALLE_VENTA : "contiene"
    PRODUCTO ||--o{ DETALLE_VENTA : "incluido en"
```

## Entidades

### PROVEEDOR
- `rut`: Identificador único (PK)
- `nombre`: Nombre del proveedor
- `calle`, `numero`, `comuna`, `ciudad`: Dirección
- `telefono`: Teléfono de contacto
- `pagina_web`: Sitio web

### CATEGORIA
- `id_categoria`: Identificador único (PK)
- `nombre`: Nombre de la categoría
- `descripcion`: Descripción

### PRODUCTO
- `id_producto`: Identificador único (PK)
- `nombre`: Nombre del producto
- `precio_actual`: Precio vigente
- `stock`: Cantidad disponible
- `rut_proveedor`: Proveedor del producto (FK)
- `id_categoria`: Categoría del producto (FK)

### CLIENTE
- `rut`: Identificador único (PK)
- `nombre`: Nombre del cliente
- `calle`, `numero`, `comuna`, `ciudad`: Dirección

### CLIENTE_TELEFONO
- `id_telefono`: Identificador único (PK)
- `rut_cliente`: Cliente al que pertenece (FK)
- `telefono`: Número de teléfono

### VENTA
- `id_venta`: Identificador único (PK)
- `fecha`: Fecha de la venta
- `rut_cliente`: Cliente que realizó la venta (FK)
- `descuento`: Descuento aplicado
- `monto_final`: Monto total final

### DETALLE_VENTA
- `id_detalle`: Identificador único (PK)
- `id_venta`: Venta a la que pertenece (FK)
- `id_producto`: Producto vendido (FK)
- `precio_momento`: Precio del producto al momento de la venta
- `cantidad`: Cantidad vendida
- `monto_total`: Monto total por este producto

## Relaciones

| Relación | Cardinalidad | Descripción |
|---|---|---|
| PROVEEDOR — PRODUCTO | 1:N | Un proveedor suministra varios productos. |
| CATEGORIA — PRODUCTO | 1:N | Una categoría clasifica varios productos. |
| CLIENTE — CLIENTE_TELEFONO | 1:N | Un cliente puede tener varios teléfonos. |
| CLIENTE — VENTA | 1:N | Un cliente puede realizar varias ventas. |
| VENTA — DETALLE_VENTA | 1:N | Una venta contiene varios productos. |
| PRODUCTO — DETALLE_VENTA | 1:N | Un producto puede aparecer en varios detalles de venta. |

> **Nota:** `PK` = Primary Key, `FK` = Foreign Key.
