# API de Creación de Usuarios

Esta es una aplicación Java que expone una API RESTful para la creación de usuarios. La API permite registrar nuevos usuarios con información como nombre, correo y contraseña, junto con detalles de teléfono. También se realizan validaciones de formato de correo y contraseña. La aplicación utiliza una base de datos H2 en memoria y Spring Boot.

# Arquitectura
Esta API sigue una arquitectura de aplicación basada en Spring Boot y sigue un patrón de diseño de estilo MVC (Modelo-Vista-Controlador). Algunos de los componentes clave de la arquitectura incluyen:

Controladores: Estos controlan las solicitudes HTTP y gestionan la lógica de negocio de la aplicación. Puedes encontrar los controladores en el paquete com.nisum.latam.controllers.

Servicios: Los servicios encapsulan la lógica de negocio y se utilizan para realizar operaciones en los datos. Los servicios pueden encontrarse en el paquete com.nisum.latam.service.impl.

Repositorios: Se utilizan para interactuar con la base de datos. En este proyecto, se emplea una base de datos H2 en memoria. Los repositorios se encuentran en el paquete com.nisum.latam.repository.

Modelos: Estos representan las entidades de datos de la aplicación, como User y Phone. Puedes encontrar los modelos en el paquete com.nisum.latam.entities.

# Patrones de Diseño
Esta API emplea varios patrones de diseño para promover la modularidad, la escalabilidad y la mantenibilidad del código. Algunos de los patrones utilizados incluyen:

MVC (Modelo-Vista-Controlador): Este patrón se utiliza para separar la lógica de negocio de la presentación y la interacción del usuario. Los controladores gestionan las solicitudes HTTP, los servicios contienen la lógica de negocio y los modelos representan las entidades de datos.

Inyección de Dependencias: Spring Boot utiliza ampliamente la inyección de dependencias para administrar los componentes y sus dependencias. Esto facilita la configuración y la gestión de los objetos en la aplicación.

Builder Pattern (Patrón Constructor): El patrón Builder se emplea en la creación de objetos complejos, como las entidades de usuario. Proporciona un método más legible y escalable para construir objetos con múltiples atributos.

Patrón Repositorio: El patrón Repositorio se utiliza para separar la lógica de acceso a datos de la lógica de negocio. Los repositorios proporcionan métodos para interactuar con la base de datos.

Patrón Singleton: En algunos casos, se utilizan Singleton para garantizar que exista solo una instancia de ciertos objetos, como el generador de tokens JWT.

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