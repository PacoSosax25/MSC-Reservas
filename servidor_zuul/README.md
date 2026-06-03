# servidor_zuul — Gateway MVC Alternativo

Implementación alternativa de API Gateway usando **Spring Cloud Gateway MVC** (basado en servlet, no reactivo). Sirve como segunda opción de enrutamiento frente a `servidor_gateway`, 
útil en entornos donde no se desea el modelo reactivo de Webflux.

## Diferencia con servidor_gateway

| | servidor_gateway | servidor_zuul |
|-|-----------------|---------------|
| Base | Spring Cloud Gateway (Reactor/Webflux) | Spring Cloud Gateway MVC (Servlet) |
| Modelo | Reactivo (non-blocking) | Bloqueante tradicional |
| Rutas configuradas | Sí (hoteles, vuelos, reservas) | Pendiente de configuración |

## Tecnología

| Componente | Versión |
|------------|---------|
| Java | 17 |
| Spring Boot | 3.3.10 |
| Spring Cloud | 2023.0.5 |
| spring-cloud-starter-gateway-mvc | — |
| spring-cloud-starter-netflix-eureka-client | — |
| spring-boot-starter-web | — |

## Estado actual

El proyecto está creado y compilable pero **no tiene rutas configuradas** en su `application.properties`. Para activarlo como gateway funcional, añadir la configuración de rutas equivalente a `servidor_gateway` en el archivo de propiedades.

## Configuración base (`application.properties`)

```properties
spring.application.name=21_servidor_zuul
server.port=7001
```

> Ajustar el puerto para evitar conflicto con `servidor_gateway` (:7000).

## Ejemplo de rutas (por configurar)

```properties
spring.cloud.gateway.mvc.routes[0].id=serv-vuelos
spring.cloud.gateway.mvc.routes[0].uri=lb://SERVICIO-VUELOS
spring.cloud.gateway.mvc.routes[0].predicates[0]=Path=/svuelos/**

spring.cloud.gateway.mvc.routes[1].id=serv-hoteles
spring.cloud.gateway.mvc.routes[1].uri=lb://SERVICIO-HOTELES
spring.cloud.gateway.mvc.routes[1].predicates[0]=Path=/shoteles/**

spring.cloud.gateway.mvc.routes[2].id=serv-reservas
spring.cloud.gateway.mvc.routes[2].uri=lb://SERVICIO-RESERVAS
spring.cloud.gateway.mvc.routes[2].predicates[0]=Path=/sreservas/**

eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka
```

## Arranque

Requiere que **ms_eureka_server** esté en ejecución.

```bash
mvn spring-boot:run
```

## Estructura del proyecto

```
com.ms.zuul
└── Application.java    # @SpringBootApplication
```
