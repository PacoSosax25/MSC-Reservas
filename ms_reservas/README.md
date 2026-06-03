# ms_reservas — Microservicio de Reservas

Microservicio orquestador central del sistema. Gestiona la creación y consulta de reservas de viaje, coordinando con el microservicio de vuelos para actualizar la disponibilidad de plazas en tiempo real.

## Rol en la arquitectura

```
[cliente_viajes]
      ↓ POST /sreservas/reserva/1  |  GET /sreservas/reservas
[servidor_gateway :7000]
      ↓ lb://SERVICIO-RESERVAS
[ms_reservas :10000] ──► MySQL viajes.reservas
      ↓ PUT lb://SERVICIO-VUELOS/vuelos/{id}/{plazas}
[ms_vuelos :9000]  ← actualiza plazas disponibles
```

## Tecnología

| Componente | Versión |
|------------|---------|
| Java | 17 |
| Spring Boot | 3.4.4 |
| Spring Cloud | 2024.0.1 |
| Spring Data JPA + Hibernate | — |
| RestTemplate con @LoadBalanced | — |
| MySQL Connector/J | — |
| Eureka Client | — |

## Puerto

| Servicio | Puerto |
|----------|--------|
| REST API | **10000** |

## Endpoints REST

| Método | Ruta | Descripción |
|--------|------|-------------|
| POST | `/reserva/{personas}` | Crea una nueva reserva y reduce plazas en ms_vuelos |
| GET | `/reservas` | Retorna la lista de todas las reservas registradas |

## Entidad principal

```
Reserva { idreserva, dni, nombre, hotel, vuelo }
```

## Comunicación entre servicios

Usa `RestTemplate` con balanceo de carga de Eureka para llamar a `ms_vuelos`:

```java
@Bean
@LoadBalanced
public RestTemplate restTemplate() { ... }

// En ServiceImpl:
restTemplate.put("http://SERVICIO-VUELOS/vuelos/{id}/{plazas}", null, idVuelo, personas);
```

## Estructura del proyecto

```
com.ms.reserva
├── Application.java             # @SpringBootApplication + @LoadBalanced RestTemplate
├── model/Reserva.java           # Entidad JPA
├── dao/
│   ├── ReservasDao              # Interfaz DAO
│   ├── ReservasDaoImpl          # Implementación de persistencia
│   └── ReservasJpaSpring        # JpaRepository<Reserva, Integer>
├── service/
│   ├── ReservasService          # Interfaz de servicio
│   └── ReservasServiceImpl      # Guarda reserva + llama ms_vuelos para reducir plazas
└── controllers/ReservasCO       # REST Controller
```

## Configuración (`application.yml`)

```yaml
spring:
  application:
    name: servicio-reservas
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
  port: 10000
eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka
```

> **Seguridad:** Las credenciales de base de datos deben configurarse mediante variables de entorno (`DB_USER`, `DB_PASSWORD`) o en un archivo `application-local.yml` (ignorado por git).

## Arranque

Requiere que **ms_eureka_server** y **ms_vuelos** estén en ejecución.

```bash
mvn spring-boot:run
```

## Base de datos

- **Esquema:** `viajes`
- **Tabla:** `reservas`
- **Driver:** `com.mysql.cj.jdbc.Driver`
