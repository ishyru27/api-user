-- Crear tabla de usuarios
CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    correo VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Insertar datos de prueba
INSERT INTO usuario (nombre, correo, password) VALUES ('Juan Rodriguez', 'juan@rodriguez.org', 'hunter2');


