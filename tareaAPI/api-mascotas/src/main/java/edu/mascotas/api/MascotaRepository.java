package edu.mascotas.api;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MascotaRepository {

    private static final String SQL_LISTAR = """
            SELECT id, nombre, especie, raza, anios, peso,
                   esta_vacunado, estatus, fecha_registro
            FROM mascotas_adopcion
            ORDER BY fecha_registro DESC
            """;

    private final JdbcTemplate jdbcTemplate;

    public MascotaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JSONArray listarComoJson() {
        return jdbcTemplate.query(SQL_LISTAR, resultado -> {
            JSONArray mascotas = new JSONArray();

            while (resultado.next()) {
                JSONObject mascota = new JSONObject();
                mascota.put("id", resultado.getString("id"));
                mascota.put("nombre", resultado.getString("nombre"));
                mascota.put("especie", resultado.getString("especie"));

                String raza = resultado.getString("raza");
                mascota.put("raza", raza == null ? JSONObject.NULL : raza);

                mascota.put("anios", resultado.getInt("anios"));
                BigDecimal peso = resultado.getBigDecimal("peso");
                mascota.put("peso", peso);
                mascota.put("esta_vacunado", "S".equals(resultado.getString("esta_vacunado")));
                mascota.put("estatus", resultado.getString("estatus"));

                OffsetDateTime fecha = resultado.getObject("fecha_registro", OffsetDateTime.class);
                mascota.put("timestamp", fecha.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));

                mascotas.put(mascota);
            }

            return mascotas;
        });
    }
}
