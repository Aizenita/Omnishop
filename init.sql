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
                       fecha_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       estado_venta VARCHAR(30) DEFAULT 'CARRITO_ACTIVO'
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

INSERT INTO categoria (nombre) VALUES ('Informática'), ('Accesorios'),('Smartphones y Telefonía'),
                                      ('Moda y Accesorios'),('Hogar y Cocina'),('Libros y Entretenimiento');


INSERT INTO producto (nombre, descripcion, precio, stock, categoria_id) VALUES
                                                                            ('Portátil Gaming', 'Equipo potente para jugar y trabajar', 1299.99, 5, 1),
                                                                            ('Ratón Inalámbrico', 'Ratón óptico sin cables', 29.99, 20, 2);
-- PRODUCTOS ADICIONALES
INSERT INTO producto (nombre, descripcion, precio, stock, imagen, categoria_id, visible, destacado) VALUES
                                                                                                            ('Laptop UltraBook Pro X2', 'Diseño elegante, rendimiento superior para profesionales y creativos.', 1499.99, 20, 'https://via.placeholder.com/350/FF5733/FFFFFF?Text=LaptopX2', 1, true, true),
                                                                                                            ('Teléfono Móvil Galaxy S25', 'La última generación en tecnología móvil, cámara revolucionaria y pantalla AMOLED.', 979.00, 50, 'https://via.placeholder.com/350/33FF57/FFFFFF?Text=GalaxyS25', 2, true, true),
                                                                                                            ('Zapatillas Deportivas RunnerFlex', 'Comodidad y estilo para tus entrenamientos y uso diario.', 89.90, 120, 'https://via.placeholder.com/350/3357FF/FFFFFF?Text=RunnerFlex', 3, true, false),
                                                                                                            ('Cafetera Express Automática', 'Disfruta de un café perfecto cada mañana con solo pulsar un botón.', 199.50, 35, 'https://via.placeholder.com/350/FFFF33/000000?Text=Cafetera', 4, true, true),
                                                                                                            ('Set de Teclado y Ratón Ergonómico', 'Maximiza tu productividad y confort durante largas horas de trabajo.', 75.00, 60, 'https://via.placeholder.com/350/FF33FF/FFFFFF?Text=TecladoErgo', 1, true, false),
                                                                                                            ('Auriculares Inalámbricos SoundWave', 'Calidad de sonido Hi-Fi, cancelación de ruido y batería de larga duración.', 150.75, 70, 'https://via.placeholder.com/350/33FFFF/000000?Text=SoundWave', 2, true, false),
                                                                                                            ('Bestseller de Ficción ''El Último Secreto''', 'Una novela intrigante que te atrapará desde la primera página.', 22.95, 200, 'https://via.placeholder.com/350/F3F3F3/000000?Text=LibroSecreto', 5, true, true);
