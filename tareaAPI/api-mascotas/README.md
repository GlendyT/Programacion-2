# API de mascotas

Servicio REST Java/Spring Boot que consulta `MASCOTAS_ADOPCION` en Oracle Autonomous Database y devuelve un arreglo JSON construido con `org.json`.

## Endpoint

```text
GET http://localhost:8080/api/mascotas
```

Los campos devueltos son `id`, `nombre`, `especie`, `raza`, `anios`, `peso`, `esta_vacunado`, `estatus` y `timestamp`. `esta_vacunado` es booleano, `raza` puede ser `null` y el peso está en libras.

## Configuración

La aplicación requiere `ORACLE_JDBC_URL`, `ORACLE_DB_USER` y `ORACLE_DB_PASSWORD` como variables de entorno. Para el wallet TNS, configura una ruta absoluta sin espacios y el alias `mascotasproject_tp`, por ejemplo:

```powershell
$walletPath = 'C:\Oracle\Wallet_mascotasproject'
Test-Path (Join-Path $walletPath 'tnsnames.ora')
$env:ORACLE_JDBC_URL = "jdbc:oracle:thin:@mascotasproject_tp?TNS_ADMIN=$($walletPath -replace '\\','/')"
$env:ORACLE_DB_USER = 'ADMIN'
$env:ORACLE_DB_PASSWORD = 'TU_CONTRASENA'
```

El `Test-Path` debe mostrar `True`. Sustituye los datos de ejemplo. Nunca guardes wallet o contraseñas en el repositorio. Consulta la guía principal en [`../README.md`](../README.md) para TLS sin wallet, ejecución y diagnóstico.

## Ejecutar

Desde esta carpeta, con las variables definidas en la **misma terminal** (Spring Boot no carga archivos `.env` automáticamente):

```powershell
mvn spring-boot:run
```

Deja la API activa mientras el cliente de escritorio la consuma.

## Nota sobre el error `"Ocurrio un error al consultar las mascotas."`

Si al consultar la API recibes `{"error":"Ocurrio un error al consultar las mascotas."}` (HTTP 500):

1. **Verifica la URL:** asegúrate de consultar `http://localhost:8080/api/mascotas` y no `http://localhost:8080`. La ruta raíz no tiene controlador asignado y `ApiExceptionHandler` captura cualquier `Exception` (incluyendo rutas no encontradas) devolviendo ese mismo mensaje.
2. **Verifica las variables de entorno en la terminal activa:** si iniciaste `mvn spring-boot:run` en una ventana de PowerShell donde no ejecutaste antes los `$env:ORACLE_JDBC_URL`, `$env:ORACLE_DB_USER` y `$env:ORACLE_DB_PASSWORD`, la propiedad `${ORACLE_JDBC_URL}` queda sin resolver y HikariCP falla al intentar abrir la conexión. Detén el servidor (`Ctrl + C`), exporta las variables en esa misma terminal y vuelve a ejecutar `mvn spring-boot:run`.
