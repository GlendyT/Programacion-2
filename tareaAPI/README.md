# Práctica: API y aplicación de escritorio para mascotas en adopción

Este proyecto conecta una base de datos **Oracle Autonomous Database** con una API REST en **Java y Spring Boot**. Una aplicación de escritorio **Java Swing** consume la API, procesa el JSON con `org.json` y presenta las mascotas en un `JTable`.

## Flujo de la aplicación

```text
Oracle Autonomous Database
        │ SELECT
        ▼
API Spring Boot: GET /api/mascotas
        │ JSON por HTTP
        ▼
Cliente Java Swing + org.json
        │ convierte objetos JSON a filas
        ▼
JTable de mascotas
```

## Contenido del proyecto

| Ruta | Contenido |
|---|---|
| `crear_tabla_mascotas.sql` | DDL para crear la tabla y sus restricciones. |
| `insertar_mascotas_prueba.sql` | Inserta cuatro mascotas de ejemplo y consulta los datos. Ejecutar una sola vez. |
| `api-mascotas/` | API REST Spring Boot y conexión JDBC a Oracle. |
| `cliente-mascotas/` | Aplicación de escritorio Swing que consume la API. |
| `instrucciones.md` | Enunciado original de la práctica. |

`JavaApiConsumir` es un ejemplo independiente y no forma parte de estos proyectos.

## Estructura de datos

La tabla se llama `MASCOTAS_ADOPCION` y contiene:

| Columna | Tipo/uso |
|---|---|
| `ID` | UUID como texto; clave primaria. |
| `NOMBRE` | Nombre de la mascota; obligatorio. |
| `ESPECIE` | Especie; obligatorio. |
| `RAZA` | Raza; admite `NULL`. |
| `ANIOS` | Edad en años; entero no negativo. |
| `PESO` | Peso decimal en libras; debe ser mayor que cero. |
| `ESTA_VACUNADO` | `S` o `N` en Oracle; la API lo transforma a `true` o `false`. |
| `ESTATUS` | Solo `disponible` o `adoptado`. |
| `FECHA_REGISTRO` | Fecha y hora con zona; Oracle asigna `SYSTIMESTAMP` por defecto. |

Los scripts de creación e inserción ya se ejecutaron para esta instancia. Para consultar los datos manualmente:

```sql
SELECT *
FROM mascotas_adopcion
ORDER BY fecha_registro DESC;
```

No vuelvas a ejecutar `insertar_mascotas_prueba.sql` sobre los mismos registros: sus UUID son fijos y la clave primaria rechazará duplicados.

## Requisitos locales

- Java 21 o posterior. El código se compila para Java 21; Java 25 también puede ejecutar el proyecto.
- Maven 3.6.3 o posterior.
- La instancia de Oracle Autonomous Database activa y con la tabla creada.
- Wallet de Oracle si la conexión usa mTLS mediante TNS Names.

## Configurar la conexión a Oracle

La configuración se pasa mediante variables de entorno. No se guardan contraseñas ni archivos wallet en el repositorio.

### Con wallet y TNS Names (configuración usada para esta práctica)

1. En Oracle Cloud, abre tu Autonomous Database y entra a **Database connection**.
2. Descarga las credenciales del cliente (wallet) y descomprime el ZIP.
3. Conserva la carpeta descomprimida fuera del proyecto, en una ruta sencilla **sin espacios**, por ejemplo:

   ```text
   C:\Oracle\Wallet_mascotasproject
   ```

   La carpeta debe contener `tnsnames.ora` y los archivos del wallet. No indiques la ruta al ZIP ni a una carpeta contenedora que no tenga esos archivos directamente.
4. Usa el TNS alias `mascotasproject_tp`. Para una aplicación normal basta el servicio `tp`; `tpurgent` es el servicio de prioridad urgente.
5. En PowerShell, define las variables antes de iniciar la API:

   ```powershell
   $walletPath = 'C:\Oracle\Wallet_mascotasproject'
   Test-Path (Join-Path $walletPath 'tnsnames.ora')
   $env:ORACLE_JDBC_URL = "jdbc:oracle:thin:@mascotasproject_tp?TNS_ADMIN=$($walletPath -replace '\\','/')"
   $env:ORACLE_DB_USER = 'ADMIN'
   $env:ORACLE_DB_PASSWORD = 'TU_CONTRASENA'
   ```

   `Test-Path` debe mostrar `True`. Sustituye la ruta y el usuario/contraseña por los de tu configuración. No publiques la contraseña.

### Con cadena TLS sin wallet

Si Oracle Cloud ofrece **TLS Authentication**, copia la cadena de conexión TLS desde esa sección. Configura `ORACLE_JDBC_URL` como `jdbc:oracle:thin:@` seguido de la cadena completa. Conserva tal cual el host, servicio, protocolo, puerto y paréntesis que Oracle entrega. La cadena TNS de la lista de TNS Names puede requerir wallet; no asumas que cualquier cadena es TLS sin wallet.

## Iniciar y consultar la API

1. Abre una terminal de PowerShell en `tareaAPI\api-mascotas`.
2. Define las variables de conexión anteriores en esa terminal.
3. Inicia el servidor:

   ```powershell
   mvn spring-boot:run
   ```

4. Deja esa terminal abierta mientras uses el cliente.
5. Consulta en el navegador o en un cliente HTTP:

   ```text
   http://localhost:8080/api/mascotas
   ```

La ruta raíz `http://localhost:8080/` no es el endpoint de datos. El endpoint implementado es `GET /api/mascotas`.

### Respuesta JSON esperada

La API devuelve un arreglo. El contenido exacto depende de los registros en Oracle:

```json
[
  {
    "id": "550e8400-e29b-41d4-a716-446655440001",
    "nombre": "Luna",
    "especie": "Perro",
    "raza": "Labrador",
    "anios": 3,
    "peso": 52.4,
    "esta_vacunado": true,
    "estatus": "disponible",
    "timestamp": "2026-10-14T20:00:00Z"
  }
]
```

Cuando la raza no está definida, se devuelve como JSON `null`. El timestamp es ISO 8601 con zona horaria.

## Iniciar el cliente de escritorio

La API debe estar activa primero. Abre **otra terminal** en `tareaAPI\cliente-mascotas` y ejecuta:

```powershell
mvn compile exec:java
```

En la ventana **Mascotas en adopción**, presiona **Cargar mascotas**. El cliente hace una petición HTTP al endpoint, recorre el arreglo JSON con `org.json` y agrega cada mascota al modelo del `JTable`. La ventana muestra las columnas ID, nombre, especie, raza, años, peso en libras, vacunación, estatus y fecha de registro.

La llamada HTTP se ejecuta fuera del hilo de la interfaz para que la ventana siga respondiendo mientras espera la API. Si la petición falla, el cliente muestra un mensaje de error.

## Solución de problemas

### `ORA-12263` o no encuentra `tnsnames.ora`

- Comprueba que `$walletPath` apunte a la carpeta descomprimida correcta.
- Ejecuta `Test-Path (Join-Path $walletPath 'tnsnames.ora')`; debe resultar `True`.
- Mantén el wallet en una ruta sin espacios, por ejemplo `C:\Oracle\Wallet_mascotasproject`.
- Revisa que la URL use el alias disponible dentro de `tnsnames.ora`, por ejemplo `mascotasproject_tp`.
- Define las variables y ejecuta Maven desde la misma ventana de PowerShell.

### La API arranca, pero responde que no puede conectar con Oracle

- Revisa el error `ORA-...` y la línea `Caused by:` en la terminal donde corre Spring Boot.
- Confirma que `ORACLE_DB_USER` y `ORACLE_DB_PASSWORD` correspondan al usuario de la base.
- Asegúrate de que la base esté disponible y de que la URL sea TLS o TNS/wallet según el método elegido.

### El cliente muestra que no puede conectarse a la API

- Asegúrate de que Spring Boot continúe ejecutándose en otra terminal.
- Comprueba que responde `http://localhost:8080/api/mascotas`.
- Confirma que la aplicación no esté usando otro puerto.

### La API devuelve un arreglo vacío

La consulta funcionó, pero no hay registros visibles para el usuario/esquema conectado. Revisa que la tabla y los registros estén en ese mismo esquema:

```sql
SELECT COUNT(*) FROM mascotas_adopcion;
```

## Correspondencia con los requisitos

- **API:** Spring Boot expone `GET /api/mascotas`, consulta Oracle y responde JSON.
- **Aplicación de escritorio:** Java Swing presenta `JTable` y botón de carga.
- **Consumo:** el botón llama al endpoint de la API.
- **JSON a tabla:** `org.json` itera sobre los objetos y extrae los campos para cada fila.
