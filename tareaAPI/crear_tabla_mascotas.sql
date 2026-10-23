-- Ejecutar en el esquema de Autonomous Database donde se guardaran las mascotas.
-- El backend debe generar id como UUID canonico (xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx).
-- En Oracle, esta_vacunado se representa con S/N para compatibilidad entre versiones;
-- la API lo expondra como booleano true/false.

CREATE TABLE mascotas_adopcion (
    id               VARCHAR2(36 CHAR)  NOT NULL,
    nombre           VARCHAR2(100 CHAR) NOT NULL,
    especie          VARCHAR2(50 CHAR)  NOT NULL,
    raza             VARCHAR2(100 CHAR),
    anios            NUMBER(3, 0)       NOT NULL,
    peso             NUMBER(7, 2)       NOT NULL,
    esta_vacunado    CHAR(1 CHAR)       DEFAULT 'N' NOT NULL,
    estatus          VARCHAR2(20 CHAR)  DEFAULT 'disponible' NOT NULL,
    fecha_registro   TIMESTAMP WITH TIME ZONE DEFAULT SYSTIMESTAMP NOT NULL,

    CONSTRAINT pk_mascotas_adopcion PRIMARY KEY (id),
    CONSTRAINT ck_mascotas_anios CHECK (anios >= 0),
    CONSTRAINT ck_mascotas_peso CHECK (peso > 0),
    CONSTRAINT ck_mascotas_vacunado CHECK (esta_vacunado IN ('S', 'N')),
    CONSTRAINT ck_mascotas_estatus CHECK (estatus IN ('disponible', 'adoptado'))
);

COMMENT ON TABLE mascotas_adopcion IS 'Registro de mascotas en adopcion';
COMMENT ON COLUMN mascotas_adopcion.peso IS 'Peso expresado en libras';
COMMENT ON COLUMN mascotas_adopcion.esta_vacunado IS 'S = si; N = no. La API lo convierte a booleano';
COMMENT ON COLUMN mascotas_adopcion.fecha_registro IS 'Fecha y hora de registro';
