# ms_hoteles — Microservicio de Hoteles

Microservicio REST encargado de gestionar el catálogo de hoteles disponibles para reserva. Expone los hoteles con disponibilidad activa que pueden ser seleccionados al crear una reserva.

## Rol en la arquitectura

```
[cliente_viajes]
      ↓ GET /shoteles/hoteles
[servidor_gateway :7000]
      ↓ lb://SERVICIO-HOTELES
[ms_hoteles :8000] ──► MySQL viajes.hoteles
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
| REST API | **8000** |

## Endpoints REST

| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/hoteles` | Retorna lista de hoteles con `disponible = 1` |

## Entidad principal

```
Hotel { idHotel, nombre, categoria, precio, disponible }
```

## Estructura del proyecto

```
com.ms.hotel
├── Application.java          # @SpringBootApplication + @EnableEurekaClient
├── model/Hotel.java          # Entidad JPA
├── dao/
│   ├── HotelesDao            # Interfaz DAO
│   ├── HotelesDaoImpl        # Implementación (filtra disponibles)
│   └── HotelesJpaSpring      # JpaRepository<Hotel, Integer>
├── service/
│   ├── ServiceHotel          # Interfaz de servicio
│   └── ServiceHotelesImpl    # Lógica: filtra disponible==1
└── controllers/HotelesCO     # REST Controller
```

## Configuración (`application.yml`)

```yaml
spring:
  application:
    name: servicio-hoteles
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
  port: 8000
eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka
```

> **Seguridad:** Las credenciales de base de datos deben configurarse mediante variables de entorno (`DB_USER`, `DB_PASSWORD`) o en un archivo `application-local.yml` (ignorado por git) en lugar de escribirse directamente en `application.yml`.

## Arranque

Requiere que **ms_eureka_server** esté en ejecución.

```bash
mvn spring-boot:run
```

## Base de datos

- **Esquema:** `viajes`
- **Tabla:** `hoteles`
- **Driver:** `com.mysql.cj.jdbc.Driver`
