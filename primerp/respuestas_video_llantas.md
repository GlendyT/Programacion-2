# Explicación de la relación entre vehículos y llantas

Este documento contiene las respuestas a los cinco puntos solicitados para el video de la práctica.

## 1. ¿Cómo se relacionó Vehículo con Llanta?

`Vehiculo` y `Llanta` se relacionaron mediante el campo `ID_VEHICULO`.

En la tabla `VEHICULO`, `ID_VEHICULO` es la llave primaria que identifica de forma única a cada vehículo. En la tabla `LLANTAS`, ese mismo campo funciona como llave foránea y señala a qué vehículo pertenece cada llanta.

La relación es de **uno a muchos**:

- Un vehículo puede no tener llantas registradas o puede tener varias.
- Cada llanta pertenece a un solo vehículo.

La relación en Oracle se creó con la siguiente restricción:

```sql
CONSTRAINT FK_LLANTAS_VEHICULO
FOREIGN KEY (ID_VEHICULO)
REFERENCES VEHICULO (ID_VEHICULO)
```

También se agregó `idVehiculo` a la clase `Llanta`:

```java
private int idVehiculo;
```

De esta manera, el objeto `Llanta` conserva en Java el identificador del vehículo al que pertenece. Al insertar una llanta se utiliza el ID del vehículo seleccionado:

```java
Llanta nuevaLlanta = new Llanta(
    0,
    vehiculo.id,
    marca,
    tamanio,
    presion
);
```

El cero representa que todavía no existe un `ID_LLANTA`, porque Oracle lo genera automáticamente al realizar la inserción.

### Respuesta breve para el video

> Relacioné Vehículo con Llanta mediante `ID_VEHICULO`. Este campo es llave primaria en la tabla `VEHICULO` y llave foránea en `LLANTAS`. La relación es de uno a muchos porque un vehículo puede tener varias llantas, pero cada llanta pertenece a un solo vehículo. La clase `Llanta` también tiene el atributo `idVehiculo` para conservar esta relación en Java.

## 2. ¿Cómo se obtiene el vehículo seleccionado?

El vehículo se obtiene a partir de la fila seleccionada en el `JTable` de vehículos.

Primero se obtiene el número de fila con:

```java
int fila = tablaVehiculos.getSelectedRow();
```

Si `fila` es menor que cero, significa que el usuario no ha seleccionado ningún vehículo. Si la fila es válida, se usa esa posición para recuperar el objeto correspondiente de `listaVehiculos`:

```java
Vehiculo vehiculo = listaVehiculos.get(fila);
```

El método completo utilizado por el formulario es:

```java
private Vehiculo obtenerVehiculoSeleccionado() {
    int fila = tablaVehiculos.getSelectedRow();
    if (fila < 0 || fila >= listaVehiculos.size()) {
        return null;
    }
    return listaVehiculos.get(fila);
}
```

El objeto recuperado contiene el valor de `ID_VEHICULO` que vino de Oracle. Ese identificador se obtiene con:

```java
vehiculo.id
```

Al cambiar la selección del `JTable`, el programa carga los datos del vehículo en los campos y consulta sus llantas:

```java
cargarLlantasDelVehiculo(vehiculo.id);
```

La tabla principal también muestra una vista previa con la marca, el tamaño y la presión de las llantas asociadas. Si el vehículo tiene varias llantas, sus valores aparecen separados por comas y en el mismo orden.

Después de consultar las llantas, el formulario selecciona automáticamente la primera:

```java
tablaLlantas.setRowSelectionInterval(0, 0);
cargarLlantaSeleccionada();
```

Esto llena automáticamente los campos de marca, tamaño y presión de la llanta, de la misma manera que se llenan los datos del vehículo. Para editar otra llanta solamente se selecciona su fila en el `JTable` de llantas.

### Respuesta breve para el video

> Obtengo la fila seleccionada mediante `tablaVehiculos.getSelectedRow()`. Después utilizo esa posición para recuperar el objeto de `listaVehiculos`. El objeto contiene el ID real guardado en Oracle. Con ese ID consulto sus llantas y selecciono automáticamente la primera para llenar sus campos de edición.

## 3. ¿Cómo se consultan las llantas del vehículo?

Cuando se selecciona un vehículo, su ID se envía al método:

```java
mostrarLlantasPorVehiculo(int idVehiculo)
```

Este método ejecuta la siguiente consulta:

```sql
SELECT ID_LLANTA, ID_VEHICULO, MARCA, TAMANIO, PRESION
FROM LLANTAS
WHERE ID_VEHICULO = ?
ORDER BY ID_LLANTA
```

El signo de interrogación es un parámetro. Se reemplaza de manera segura por el ID del vehículo seleccionado:

```java
ps.setInt(1, idVehiculo);
```

La consulta se ejecuta con:

```java
ResultSet resultado = ps.executeQuery();
```

Después se recorren los resultados. Cada registro se convierte en un objeto `Llanta`:

```java
Llanta llanta = new Llanta(
    resultado.getInt("ID_LLANTA"),
    resultado.getInt("ID_VEHICULO"),
    resultado.getString("MARCA"),
    resultado.getInt("TAMANIO"),
    resultado.getDouble("PRESION")
);
```

Los objetos se guardan en un `ArrayList<Llanta>` y posteriormente se agregan al modelo del `JTable` de llantas.

Además, los datos se resumen en las columnas **Marca llanta**, **Tamaño llanta** y **Presión llanta** de la tabla principal de vehículos. La tabla separada de llantas conserva cada registro individual para poder seleccionar exactamente cuál se desea modificar.

Si la consulta no devuelve registros, el formulario muestra el mensaje:

> El vehículo no tiene llantas. Puede agregar una.

Si encuentra registros, estos aparecen en la tabla y el usuario puede seleccionar uno para modificarlo.

### Respuesta breve para el video

> Al seleccionar un vehículo envío su ID al método `mostrarLlantasPorVehiculo`. El método ejecuta un `SELECT` con `WHERE ID_VEHICULO = ?`, convierte cada resultado en un objeto `Llanta` y devuelve una lista. La lista llena el JTable de llantas y también genera la vista previa en la tabla principal.

## 4. ¿Cómo se decide si se debe insertar o actualizar?

La decisión se toma mediante la variable:

```java
Llanta llantaSeleccionada;
```

El formulario utiliza un solo botón llamado **Guardar vehículo y llanta**. Primero comprueba si hay un vehículo seleccionado y si hay una llanta seleccionada.

Cuando no hay un vehículo seleccionado, el programa entiende que tanto el vehículo como la llanta son nuevos. Por eso inserta ambos registros usando una sola transacción.

Cuando se selecciona un vehículo existente, sus datos y los datos de su primera llanta se cargan automáticamente. Si `llantaSeleccionada` contiene un objeto, el botón actualiza el vehículo y esa llanta:

```java
boolean nuevoVehiculo = vehiculoSeleccionado == null;
boolean nuevaLlanta = nuevoVehiculo || llantaSeleccionada == null;
```

Si se presiona **Nueva llanta**, los campos de llanta se limpian y `llantaSeleccionada` vuelve a ser `null`. Al guardar, el vehículo se actualiza y se inserta una llanta adicional.

La operación completa se realiza mediante:

```java
conexion.guardarVehiculoConLlanta(
    idVehiculo,
    marcaVehiculo,
    modelo,
    anio,
    precio,
    color,
    llanta,
    nuevoVehiculo,
    nuevaLlanta
);
```

Este método utiliza una transacción. Primero desactiva la confirmación automática con `setAutoCommit(false)`. Si se guardan correctamente el vehículo y la llanta, ejecuta `commit()`. Si alguna operación falla, ejecuta `rollback()` para impedir que quede guardada solamente una parte de la información.

Cuando se actualiza una llanta, se utiliza `ID_LLANTA` para modificar únicamente la seleccionada y también se verifica `ID_VEHICULO`:

```sql
UPDATE LLANTAS
SET MARCA = ?, TAMANIO = ?, PRESION = ?
WHERE ID_LLANTA = ? AND ID_VEHICULO = ?
```

Después de guardar se vuelven a consultar los vehículos y las llantas. Por eso los `JTable` muestran los datos actualizados.

### Respuesta breve para el video

> El programa usa un solo botón para guardar el vehículo y la llanta. Si no hay un vehículo seleccionado, inserta ambos. Si se seleccionó un vehículo y una llanta, actualiza ambos. Si se presionó Nueva llanta, actualiza el vehículo e inserta la nueva llanta. Las dos operaciones se ejecutan dentro de una transacción para guardar todo o no guardar nada.

## 5. ¿Qué función cumple PreparedStatement?

`PreparedStatement` permite preparar una instrucción SQL usando signos de interrogación como parámetros, sin concatenar directamente los datos escritos por el usuario.

Por ejemplo, la inserción de una llanta utiliza:

```java
String sql = "INSERT INTO LLANTAS "
        + "(ID_VEHICULO, MARCA, TAMANIO, PRESION) "
        + "VALUES (?, ?, ?, ?)";

PreparedStatement ps = conexion.prepareStatement(sql);
ps.setInt(1, llanta.getIdVehiculo());
ps.setString(2, llanta.getMarca());
ps.setInt(3, llanta.getTamanio());
ps.setDouble(4, llanta.getPresion());
ps.executeUpdate();
```

Los métodos `setInt`, `setString` y `setDouble` colocan cada valor en su parámetro correspondiente y conservan el tipo de dato correcto.

Sus principales funciones y ventajas son:

1. Evita concatenar directamente los valores dentro del SQL.
2. Reduce el riesgo de inyección SQL.
3. Maneja correctamente textos, números y caracteres especiales.
4. Hace que el código sea más ordenado y fácil de entender.
5. Permite que el gestor de base de datos prepare la instrucción antes de ejecutarla.

Para un `SELECT` se utiliza:

```java
ps.executeQuery();
```

Para un `INSERT`, `UPDATE` o `DELETE` se utiliza:

```java
ps.executeUpdate();
```

### Respuesta breve para el video

> PreparedStatement sirve para ejecutar instrucciones SQL usando parámetros. Los signos de interrogación se reemplazan con métodos como `setInt`, `setString` y `setDouble`. Esto evita concatenar los datos del usuario, reduce el riesgo de inyección SQL y asegura que cada valor se envíe con el tipo correcto.

## Funcionamiento final del formulario

### Guardado unificado

Existe un solo botón llamado **Guardar vehículo y llanta**. Este botón valida todos los campos y guarda ambas entidades en la misma operación:

- Si no se seleccionó un vehículo, inserta el vehículo y su primera llanta.
- Si se seleccionaron un vehículo y una llanta, actualiza ambos.
- Si se seleccionó un vehículo y después se presionó **Nueva llanta**, actualiza el vehículo e inserta una llanta adicional.

El guardado se realiza dentro de una transacción de Oracle:

```java
conexion.setAutoCommit(false);

// INSERT o UPDATE del vehículo
// INSERT o UPDATE de la llanta

conexion.commit();
```

Si una de las operaciones falla, se ejecuta:

```java
conexion.rollback();
```

Esto garantiza que el vehículo y la llanta se guarden juntos. No puede quedar guardada solamente una parte de la información.

### Eliminación unificada

El botón **Eliminar** borra el vehículo y todas sus llantas asociadas. Primero solicita confirmación porque la operación afecta ambos tipos de registros.

Oracle no permite eliminar directamente un vehículo que todavía está referenciado por `LLANTAS.ID_VEHICULO`. Por eso, dentro de una sola transacción, primero se eliminan las filas hijas:

```sql
DELETE FROM LLANTAS
WHERE ID_VEHICULO = ?;
```

Después se elimina el vehículo:

```sql
DELETE FROM VEHICULO
WHERE ID_VEHICULO = ?;
```

Si alguna de las dos operaciones falla, se ejecuta `rollback()` y no se elimina nada.

### Consulta de un vehículo con todas sus llantas

Para consultar un vehículo por su ID y obtener también todas sus llantas se utiliza:

```sql
SELECT
    v.ID_VEHICULO,
    v.MARCA AS MARCA_VEHICULO,
    v.MODELO,
    v.ANIO,
    v.COLOR,
    v.PRECIO,
    l.ID_LLANTA,
    l.MARCA AS MARCA_LLANTA,
    l.TAMANIO,
    l.PRESION
FROM VEHICULO v
LEFT JOIN LLANTAS l
    ON l.ID_VEHICULO = v.ID_VEHICULO
WHERE v.ID_VEHICULO = 1
ORDER BY l.ID_LLANTA;
```

El número `1` se cambia por el ID que se desea consultar. Se utiliza `LEFT JOIN` para que el vehículo aparezca aunque todavía no tenga llantas. Si tiene varias, la consulta devuelve una fila por cada llanta.

## Guion sugerido para un video de máximo tres minutos

> Primero relacioné las tablas `VEHICULO` y `LLANTAS` mediante `ID_VEHICULO`. Este campo es llave primaria en `VEHICULO` y llave foránea en `LLANTAS`, por lo que un vehículo puede tener varias llantas.
>
> Para obtener el vehículo seleccionado uso `getSelectedRow()` en la tabla de vehículos. Con la posición recupero el objeto de `listaVehiculos` y obtengo su ID. Al seleccionarlo se llenan sus datos, se consultan sus llantas y se carga automáticamente la primera llanta para editarla.
>
> Después envío ese ID al método `mostrarLlantasPorVehiculo`. Este método ejecuta un SELECT con `WHERE ID_VEHICULO = ?`. Los resultados aparecen en la tabla de llantas y como vista previa en la tabla principal.
>
> Para decidir entre insertar y actualizar reviso el vehículo y la llanta seleccionados. El único botón de guardado inserta ambos cuando son nuevos, actualiza ambos cuando ya existen, o actualiza el vehículo e inserta otra llanta después de presionar Nueva llanta. Todo se realiza en una transacción y después vuelvo a consultar Oracle para actualizar los JTable.
>
> Finalmente, PreparedStatement permite utilizar parámetros en las instrucciones SQL. Los valores se asignan con `setInt`, `setString` y `setDouble`. Esto conserva los tipos correctos, evita concatenaciones y reduce el riesgo de inyección SQL.
