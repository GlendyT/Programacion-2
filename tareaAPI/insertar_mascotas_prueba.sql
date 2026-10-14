-- Datos de prueba para MASCOTAS_ADOPCION.
-- fecha_registro se omite para que Oracle asigne SYSTIMESTAMP.

INSERT ALL
    INTO mascotas_adopcion
        (id, nombre, especie, raza, anios, peso, esta_vacunado, estatus)
    VALUES
        ('550e8400-e29b-41d4-a716-446655440001', 'Luna', 'Perro', 'Labrador', 3, 52.40, 'S', 'disponible')

    INTO mascotas_adopcion
        (id, nombre, especie, raza, anios, peso, esta_vacunado, estatus)
    VALUES
        ('550e8400-e29b-41d4-a716-446655440002', 'Milo', 'Gato', 'Siames', 2, 10.20, 'N', 'disponible')

    INTO mascotas_adopcion
        (id, nombre, especie, raza, anios, peso, esta_vacunado, estatus)
    VALUES
        ('550e8400-e29b-41d4-a716-446655440003', 'Rocky', 'Perro', NULL, 5, 63.00, 'S', 'adoptado')

    INTO mascotas_adopcion
        (id, nombre, especie, raza, anios, peso, esta_vacunado, estatus)
    VALUES
        ('550e8400-e29b-41d4-a716-446655440004', 'Nube', 'Conejo', 'Holandes', 1, 4.75, 'N', 'disponible')
SELECT 1 FROM dual;

COMMIT;

-- Verificacion de los registros insertados:
SELECT * FROM mascotas_adopcion ORDER BY fecha_registro;
