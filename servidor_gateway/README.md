# servidor_gateway — API Gateway

Puerta de entrada única al sistema de microservicios. Basado en **Spring Cloud Gateway** (reactivo), enruta las peticiones del cliente hacia los microservicios correspondientes utilizando balanceo de carga a través de Eureka. 
También gestiona la política CORS para permitir llamadas desde el cliente web AngularJS.

## Rol en la arquitectura

```
[cliente_viajes AngularJS :browser]
      ↓ HTTP localhost:7000
[servidor_gateway :7000]
   /svuelos/**  ──► lb://SERVICIO-VUELOS   → [ms_vuelos  :9000]
   /shoteles/** ──► lb://SERVICIO-HOTELES  → [ms_hoteles :8000]
   /sreservas/**──► lb://SERVICIO-RESERVAS → [ms_reservas:10000]
```

## Tecnología

| Componente | Versión |
|------------|---------|
| Java | 17 |
| Spring Boot | 3.3.10 |
| Spring Cloud | 2023.0.5 |
| spring-cloud-starter-gateway | — |
| spring-cloud-starter-netflix-eureka-client | — |

## Puerto

| Servicio | Puerto |
|----------|--------|
| API Gateway | **7000** |

## Rutas configuradas

| Ruta de entrada | Destino (Eureka) | Reescritura |
|-----------------|------------------|-------------|
| `/svuelos/**` | `lb://SERVICIO-VUELOS` | `/svuelos/x` → `/x` |
| `/shoteles/**` | `lb://SERVICIO-HOTELES` | `/shoteles/x` → `/x` |
| `/sreservas/**` | `lb://SERVICIO-RESERVAS` | `/sreservas/x` → `/x` |

## Configuración CORS (`FiltroCors.java`)

Permite peticiones cross-origin desde el cliente web:

- **Orígenes permitidos:** `*`
- **Métodos:** `GET`, `PUT`, `POST`, `DELETE`, `OPTIONS`, `PATCH`
- **Headers:** `authorization`, `Content-Type`, `X-XSRF-TOKEN`, entre otros

## Configuración (`application.yml`)

```yaml
server:
  port: 7000
spring:
  application:
    name: servidor-gateway
  cloud:
    gateway:
      routes:
        - id: serv-vuelos
          uri: lb://SERVICIO-VUELOS
          predicates:
            - Path=/svuelos/**
          filters:
            - RewritePath=/svuelos/(?<segment>.*), /${segment}
        - id: serv-hoteles
          uri: lb://SERVICIO-HOTELES
          predicates:
            - Path=/shoteles/**
          filters:
            - RewritePath=/shoteles/(?<segment>.*), /${segment}
        - id: serv-reservas
          uri: lb://SERVICIO-RESERVAS
          predicates:
            - Path=/sreservas/**
          filters:
            - RewritePath=/sreservas/(?<segment>.*), /${segment}
eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka
```

## Arranque

Requiere que **ms_eureka_server** esté en ejecución. Los microservicios de negocio deben estar registrados en Eureka para que el enrutamiento funcione.

```bash
mvn spring-boot:run
```

## Estructura del proyecto

```
com.ms.gateway
├── Application.java      # @SpringBootApplication
└── FiltroCors.java       # Configuración de política CORS global
```
