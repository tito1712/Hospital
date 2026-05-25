CREATE TABLE IF NOT EXISTS paciente (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    nombre          VARCHAR(100),
    apellido        VARCHAR(100),
    correo          VARCHAR(150),
    telefono        VARCHAR(20),
    direccion       VARCHAR(255),
    fecha_nacimiento VARCHAR(20)
);
