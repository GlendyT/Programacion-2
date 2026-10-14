package edu.mascotas.api;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestControllerAdvice
public class ApiExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(CannotGetJdbcConnectionException.class)
    public ResponseEntity<String> manejarErrorConexion(CannotGetJdbcConnectionException exception) {
        logger.error("No se pudo abrir una conexion JDBC con Oracle.", exception);
        return error(HttpStatus.SERVICE_UNAVAILABLE,
                "No fue posible conectar con la base de datos Oracle.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> manejarErrorGeneral(Exception exception) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR,
                "Ocurrio un error al consultar las mascotas.");
    }

    private ResponseEntity<String> error(HttpStatus estado, String mensaje) {
        String json = new JSONObject().put("error", mensaje).toString();
        return ResponseEntity.status(estado)
                .contentType(MediaType.APPLICATION_JSON)
                .body(json);
    }
}
