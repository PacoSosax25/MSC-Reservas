# ms_vuelos — Microservicio de Vuelos

Microservicio REST responsable de gestionar el catálogo de vuelos y el control de plazas disponibles. Provee los vuelos con asientos libres al cliente y actualiza la disponibilidad al confirmarse una reserva.

## Rol en la arquitectura

```
[cliente_viajes]
      ↓ GET /svuelos/vuelos/1
[servidor_gateway :7000]
      ↓ lb://SERVICIO-VUELOS
[ms_vuelos :9000] ──► MySQL viajes.vuelos

[ms_reservas :10000]
      ↓ PUT /vuelos/{id}/{plazas}  (RestTemplate lb://)
[ms_vuelos :9000]  ← reduce plazas al crear una reserva
```

## Tecnología

| Componente | Versión |
|------------|---------|
| Java | 17 |
| Spring Boot | 3.4.4 |
| Spring Cloud | 2024.0.1 |
| Spring Data JPA + Hibernate | — |
| MySQL Connector/J | — |
| Eureka Client | — |

## Puerto

| Servicio | Puerto |
|----------|--------|
| REST API | **9000** |

## Endpoints REST

| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/vuelos/{plazas}` | Retorna vuelos con al menos `plazas` asientos disponibles |
| PUT | `/vuelos/{idvuelo}/{plazas}` | Reduce `plazas` del vuelo indicado (llamado por ms_reservas) |

## Entidad principal

```
Vuelo { idvuelo, company, fecha, plazas, precio }
```

## Estructura del proyecto

```
com.ms.vuelo
├── Application.java          # @SpringBootApplication + @EnableEurekaClient
├── model/Vuelo.java          # Entidad JPA
├── dao/
│   ├── VuelosDao             # Interfaz DAO
│   ├── VuelosDaoImpl         # Implementación CRUD
│   └── VuelosJpaSpring       # JpaRepository<Vuelo, Integer>
├── service/
│   ├── VuelosService         # Interfaz de servicio
│   └── VuelosServiceImpl     # Filtra por plazas; actualiza disponibilidad
└── controllers/VuelosCO      # REST Controller
```

## Configuración (`application.yml`)

```yaml
spring:
  application:
    name: servicio-vuelos
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/viajes?serverTimezone=UTC
    username: ${DB_USER:root}         # usar variable de entorno
    password: ${DB_PASSWORD:}         # NO hardcodear contraseñas
  jpa:
    hibernate:
      naming:
        implicit-strategy: org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl
        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
server:
  port: 9000
eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka
```

> **Seguridad:** Las credenciales de base de datos deben configurarse mediante variables de entorno (`DB_USER`, `DB_PASSWORD`) o en un archivo `application-local.yml` (ignorado por git).

## Arranque

Requiere que **ms_eureka_server** esté en ejecución.

```bash
mvn spring-boot:run
```

## Base de datos

- **Esquema:** `viajes`
- **Tabla:** `vuelos`
- **Driver:** `com.mysql.cj.jdbc.Driver`
