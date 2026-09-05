-- ============================================
-- Sistema de Ventas — Creacion de tablas
-- ============================================

CREATE TABLE PROVEEDOR (
    rut         VARCHAR2(20)    NOT NULL,
    nombre      VARCHAR2(100)   NOT NULL,
    calle       VARCHAR2(100)   NOT NULL,
    numero      VARCHAR2(10)    NOT NULL,
    comuna      VARCHAR2(50)    NOT NULL,
    ciudad      VARCHAR2(50)    NOT NULL,
    telefono    VARCHAR2(20),
    pagina_web  VARCHAR2(200),
    CONSTRAINT pk_proveedor PRIMARY KEY (rut)
);

CREATE TABLE CATEGORIA (
    id_categoria    NUMBER          GENERATED ALWAYS AS IDENTITY,
    nombre          VARCHAR2(100)   NOT NULL,
    descripcion     VARCHAR2(300),
    CONSTRAINT pk_categoria PRIMARY KEY (id_categoria)
);

CREATE TABLE PRODUCTO (
    id_producto     NUMBER          GENERATED ALWAYS AS IDENTITY,
    nombre          VARCHAR2(100)   NOT NULL,
    precio_actual   NUMBER(10,2)    NOT NULL,
    stock           NUMBER          NOT NULL,
    rut_proveedor   VARCHAR2(20)    NOT NULL,
    id_categoria    NUMBER          NOT NULL,
    CONSTRAINT pk_producto      PRIMARY KEY (id_producto),
    CONSTRAINT fk_prod_prov     FOREIGN KEY (rut_proveedor) REFERENCES PROVEEDOR(rut),
    CONSTRAINT fk_prod_cat      FOREIGN KEY (id_categoria)  REFERENCES CATEGORIA(id_categoria)
);

CREATE TABLE CLIENTE (
    rut     VARCHAR2(20)    NOT NULL,
    nombre  VARCHAR2(100)   NOT NULL,
    calle   VARCHAR2(100)   NOT NULL,
    numero  VARCHAR2(10)    NOT NULL,
    comuna  VARCHAR2(50)    NOT NULL,
    ciudad  VARCHAR2(50)    NOT NULL,
    CONSTRAINT pk_cliente PRIMARY KEY (rut)
);

CREATE TABLE CLIENTE_TELEFONO (
    id_telefono     NUMBER          GENERATED ALWAYS AS IDENTITY,
    rut_cliente     VARCHAR2(20)    NOT NULL,
    telefono        VARCHAR2(20)    NOT NULL,
    CONSTRAINT pk_cliente_tel   PRIMARY KEY (id_telefono),
    CONSTRAINT fk_tel_cliente   FOREIGN KEY (rut_cliente) REFERENCES CLIENTE(rut)
);

CREATE TABLE VENTA (
    id_venta        NUMBER          GENERATED ALWAYS AS IDENTITY,
    fecha           DATE            NOT NULL,
    rut_cliente     VARCHAR2(20)    NOT NULL,
    descuento       NUMBER(5,2)     DEFAULT 0,
    monto_final     NUMBER(10,2)    NOT NULL,
    CONSTRAINT pk_venta         PRIMARY KEY (id_venta),
    CONSTRAINT fk_venta_cliente FOREIGN KEY (rut_cliente) REFERENCES CLIENTE(rut)
);

CREATE TABLE DETALLE_VENTA (
    id_detalle      NUMBER          GENERATED ALWAYS AS IDENTITY,
    id_venta        NUMBER          NOT NULL,
    id_producto     NUMBER          NOT NULL,
    precio_momento  NUMBER(10,2)    NOT NULL,
    cantidad        NUMBER          NOT NULL,
    monto_total     NUMBER(10,2)    NOT NULL,
    CONSTRAINT pk_detalle       PRIMARY KEY (id_detalle),
    CONSTRAINT fk_det_venta     FOREIGN KEY (id_venta)    REFERENCES VENTA(id_venta),
    CONSTRAINT fk_det_producto  FOREIGN KEY (id_producto) REFERENCES PRODUCTO(id_producto)
);
