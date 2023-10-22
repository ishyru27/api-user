# API de Creación de Usuarios

Esta es una aplicación Java que expone una API RESTful para la creación de usuarios. La API permite registrar nuevos usuarios con información como nombre, correo y contraseña, junto con detalles de teléfono. También se realizan validaciones de formato de correo y contraseña. La aplicación utiliza una base de datos H2 en memoria y Spring Boot.

## Requisitos

- Java 8+
- Maven
- Base de datos H2 o HSQLDB
- EclipseLink, Hibernate u OpenJPA (para la persistencia)
- Framework Spring Boot

## Configuración

1. Clona este repositorio en tu máquina local.

2. Configura la base de datos H2 modificando `src/main/resources/application.properties`.

3. Ejecuta la aplicación usando Maven

4. Accede a la documentación de la API en [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Endpoints

### Registro de Usuario

- **POST** `/users/register`: Registra un nuevo usuario con los siguientes campos:
```json
{
  "name": "Juan Rodriguez",
  "email": "luiscarvajal2208@gmail.com",
  "password": "Abcdefg1",
  "phones": [
    {
      "number": "1156021438",
      "citycode": "1",
      "contrycode": "57"
    }
  ]
}
# Formato de Respuesta
En caso de éxito, la API retornará un objeto JSON con los siguientes campos:

id: ID del usuario.
created: Fecha de creación del usuario.
modified: Fecha de la última actualización del usuario.
last_login: Fecha del último inicio de sesión (coincide con la fecha de creación para nuevos usuarios).
token: Token de acceso de la API.
isactive: Indica si el usuario está habilitado en el sistema.
Expresiones Regulares
El correo debe seguir el formato aaaaaaa@dominio.cl.
La contraseña debe cumplir con la expresión regular configurada en el proyecto.
Repositorio
El código fuente de este proyecto se encuentra en GitHub.

Autor
Luis Carvajal