-- USUARIOS
CREATE TABLE usuario (
                         id SERIAL PRIMARY KEY,
                         nombre VARCHAR(100) NOT NULL,
                         email VARCHAR(100) UNIQUE NOT NULL,
                         contraseña VARCHAR(255) NOT NULL,
                         rol VARCHAR(20) DEFAULT 'CLIENTE',
                         activo BOOLEAN DEFAULT TRUE,
                         creado_por VARCHAR(100),
                         modificado_por VARCHAR(100),
                         fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         fecha_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- CATEGORIAS
CREATE TABLE categoria (
                           id SERIAL PRIMARY KEY,
                           nombre VARCHAR(50) NOT NULL,
                           creado_por VARCHAR(100),
                           modificado_por VARCHAR(100),
                           fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           fecha_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- PRODUCTOS
CREATE TABLE producto (
                          id SERIAL PRIMARY KEY,
                          nombre VARCHAR(100) NOT NULL,
                          descripcion TEXT,
                          precio NUMERIC(10,2) NOT NULL,
                          stock INT DEFAULT 0,
                          imagen VARCHAR(255),
                          categoria_id INT REFERENCES categoria(id),
                          visible BOOLEAN DEFAULT TRUE,
                          destacado BOOLEAN DEFAULT FALSE,
                          creado_por VARCHAR(100),
                          modificado_por VARCHAR(100),
                          fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          fecha_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- VENTAS
CREATE TABLE venta (
                       id SERIAL PRIMARY KEY,
                       fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       usuario_id INT REFERENCES usuario(id),
                       total NUMERIC(10,2),
                       creado_por VARCHAR(100),
                       modificado_por VARCHAR(100),
                       fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       fecha_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- LÍNEAS DE VENTA
CREATE TABLE linea_venta (
                             id SERIAL PRIMARY KEY,
                             venta_id INT REFERENCES venta(id),
                             producto_id INT REFERENCES producto(id),
                             cantidad INT NOT NULL,
                             precio_unitario NUMERIC(10,2) NOT NULL
);

-- DIRECCIONES DE ENVÍO
CREATE TABLE direccion_envio (
                                 id SERIAL PRIMARY KEY,
                                 usuario_id INT REFERENCES usuario(id),
                                 calle VARCHAR(255),
                                 ciudad VARCHAR(100),
                                 cp VARCHAR(10),
                                 pais VARCHAR(50),
                                 predeterminada BOOLEAN DEFAULT FALSE
);

-- BANNERS (Portada del sitio)
CREATE TABLE banner (
                        id SERIAL PRIMARY KEY,
                        titulo VARCHAR(100),
                        imagen_url VARCHAR(255),
                        enlace_destino VARCHAR(255),
                        activo BOOLEAN DEFAULT TRUE,
                        creado_por VARCHAR(100),
                        modificado_por VARCHAR(100),
                        fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        fecha_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- NOTICIAS / BLOG
CREATE TABLE noticia (
                         id SERIAL PRIMARY KEY,
                         titulo VARCHAR(150),
                         contenido TEXT,
                         imagen_url VARCHAR(255),
                         fecha_publicacion TIMESTAMP,
                         creado_por VARCHAR(100),
                         modificado_por VARCHAR(100),
                         fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         fecha_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- LOGS DE ACCIONES ADMIN
CREATE TABLE log_accion_admin (
                                  id SERIAL PRIMARY KEY,
                                  admin_id INT REFERENCES usuario(id),
                                  accion VARCHAR(255),
                                  fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- DATOS DE PRUEBA
INSERT INTO usuario (nombre, email, contraseña, rol) VALUES
                                                         ('Admin', 'admin@omnishop.com', '1234', 'ADMIN'),
                                                         ('Cliente', 'cliente@omnishop.com', '1234', 'CLIENTE');

INSERT INTO categoria (nombre) VALUES ('Informática'), ('Accesorios');

INSERT INTO producto (nombre, descripcion, precio, stock, categoria_id) VALUES
                                                                            ('Portátil Gaming', 'Equipo potente para jugar y trabajar', 1299.99, 5, 1),
                                                                            ('Ratón Inalámbrico', 'Ratón óptico sin cables', 29.99, 20, 2);
