# Cliente de escritorio de mascotas

Aplicación Java Swing que consume `GET http://localhost:8080/api/mascotas`, procesa el JSON con `org.json` y muestra los registros en un `JTable`.

## Ejecutar

1. Inicia primero la API desde `../api-mascotas` y deja esa terminal abierta.
2. Abre una terminal en esta carpeta.
3. Ejecuta:

   ```powershell
   mvn compile exec:java
   ```

4. En la ventana, presiona **Cargar mascotas**.

La tabla muestra ID, nombre, especie, raza, años, peso en libras, vacunación, estatus y timestamp. Los campos JSON `raza: null` se muestran vacíos; `esta_vacunado` se presenta como Sí/No. La tabla permite ordenar sus columnas.

Si no carga los datos, comprueba que la API esté activa y que responda en `http://localhost:8080/api/mascotas`. Para la configuración completa y solución de problemas, consulta [`../README.md`](../README.md).
