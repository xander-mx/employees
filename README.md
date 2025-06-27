# Employees API

Proyecto para la gestión de empleados. Construido con Spring Boot, MySQL y Docker.

## 🛠 Requisitos

- [Docker](https://www.docker.com/products/docker-desktop)
- [Docker Compose](https://docs.docker.com/compose/)
- (Opcional) Java 17+ y Gradle si deseas ejecutar sin Docker

## 🚀 Cómo levantar el proyecto

1. Clonar el repositorio:

```bash
git clone  https://github.com/xander-mx/employees.git 
cd EmployeesInvex
 ./gradlew bootJar
docker compose up --build
```
2. Validar que levanto el proyecto
```
http://localhost:8080/actuator/health
```

3. Acceder a swagger
```html
http://localhost:8080/swagger-ui/index.html#/
```

4. Probar endpoints
    
    El proyecto cuenta con un collection que puede ser importado en postman.

## Base de Datos

El proyecto utiliza MYSQL como base de datos y se levanta con un servicio Docker, 
utiliza las siguientes variables

```json
      INVEX_DATASOURCE_URL: jdbc:mysql://mysql:3306/employees_db
      INVEX_DATASOURCE_USER: root
      INVEX_DATASOURCE_PASSWORD: root
```
