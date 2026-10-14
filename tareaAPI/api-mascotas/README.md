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

Desde esta carpeta, con las variables definidas:

```powershell
mvn spring-boot:run
```

Deja la API activa mientras el cliente de escritorio la consuma.
