----------------------------------------------------------------------------------------------------
-- Tabla: faenas_proveedores
----------------------------------------------------------------------------------------------------

CREATE TABLE faenas_proveedores (
    proveedor_id NUMBER NOT NULL,
    nombre NVARCHAR2(250) NOT NULL,
    rut NUMBER(8, 0) NOT NULL,
    direccion NVARCHAR2(250) NOT NULL,
    comuna NVARCHAR2(100) NOT NULL,

    -- Llave primaria
    CONSTRAINT fproveedores_pk PRIMARY KEY (proveedor_id),

    -- Columnas únicas
    CONSTRAINT fproveedores_uq UNIQUE (rut)
);

-- Secuencia
CREATE SEQUENCE faenas_proveedores_seq START WITH 20 INCREMENT BY 1 NOCACHE NOCYCLE ORDER;

----------------------------------------------------------------------------------------------------
-- Tabla: faenas_items
----------------------------------------------------------------------------------------------------

CREATE TABLE faenas_items (
    item_id NUMBER NOT NULL,
    descripcion NVARCHAR2(250) NOT NULL,
    stock NUMBER(19, 0) NOT NULL,
    preciounitario NUMBER NOT NULL,
    proveedor_id NUMBER NOT NULL,

    -- Llave primaria
    CONSTRAINT fitems_pk PRIMARY KEY (item_id),

    -- Llaves foráneas
    CONSTRAINT fitems_fk FOREIGN KEY (proveedor_id)
        REFERENCES faenas_proveedores (proveedor_id) ON DELETE CASCADE
);

-- Secuencia
CREATE SEQUENCE faenas_items_seq START WITH 50 INCREMENT BY 1 NOCACHE NOCYCLE ORDER;

----------------------------------------------------------------------------------------------------
-- Tabla: faenas_faenas
----------------------------------------------------------------------------------------------------

CREATE TABLE faenas_faenas (
    faena_id NUMBER NOT NULL,
    descripcion NVARCHAR2(250) NOT NULL,
    comuna NVARCHAR2(100) NOT NULL,
    fechainicio DATE NOT NULL,
    fechatermino DATE NOT NULL,

    -- Llave primaria
    CONSTRAINT ffaenas_pk PRIMARY KEY (faena_id)
);

-- Secuencia
CREATE SEQUENCE faenas_faenas_seq START WITH 20 INCREMENT BY 1 NOCACHE NOCYCLE ORDER;

----------------------------------------------------------------------------------------------------
-- Tabla: faenas_detalles
----------------------------------------------------------------------------------------------------

CREATE TABLE faenas_detalles (
    detalle_id NUMBER NOT NULL,
    cantidad NUMBER NOT NULL,
    faena_id NUMBER NOT NULL,
    item_id NUMBER NOT NULL,

    -- Llave primaria
    CONSTRAINT fdetalles_pk PRIMARY KEY (detalle_id),

    -- Llaves foráneas
    CONSTRAINT fdetalles_fk_faena FOREIGN KEY (faena_id)
        REFERENCES faenas_faenas (faena_id) ON DELETE CASCADE,
    CONSTRAINT fdetalles_fk_item FOREIGN KEY (item_id)
        REFERENCES faenas_items (item_id) ON DELETE CASCADE
);

-- Secuencia
CREATE SEQUENCE faenas_detalles_seq START WITH 40 INCREMENT BY 1 NOCACHE NOCYCLE ORDER;
