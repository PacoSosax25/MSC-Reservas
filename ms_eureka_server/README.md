# ms_eureka_server — Servidor de Descubrimiento de Servicios

Servidor de registro y descubrimiento de servicios basado en **Netflix Eureka**. Actúa como el directorio central donde todos los microservicios del sistema se registran y se localizan entre sí.

## Rol en la arquitectura

Es el primer servicio que debe estar en funcionamiento. Sin él, ningún otro microservicio puede registrarse ni ser encontrado por el API Gateway.

```
[ms_hoteles   :8000] ─┐
[ms_vuelos    :9000] ──┼──► [ms_eureka_server :8761]
[ms_reservas  :10000]─┤
[servidor_gateway :7000] ─┘
```

## Tecnología

| Componente | Versión |
|------------|---------|
| Java | 17 |
| Spring Boot | 3.4.4 |
| Spring Cloud | 2024.0.1 |
| spring-cloud-starter-netflix-eureka-server | — |

## Puerto

| Servicio | Puerto |
|----------|--------|
| Eureka Dashboard | **8761** |

## Configuración (`application.yml`)

```yaml
spring:
  application:
    name: 17_ms_eureka_server
server:
  port: 8761
eureka:
  client:
    register-with-eureka: false   # no se auto-registra
    fetch-registry: false         # no consulta otros registros
```

## Arranque

```bash
mvn spring-boot:run
```

Una vez iniciado, el dashboard de Eureka está disponible en:
`http://localhost:8761`

## Anotación principal

```java
@SpringBootApplication
@EnableEurekaServer
public class Application { ... }
```

## Orden de inicio recomendado

1. **ms_eureka_server** ← primero
2. servidor_configuracion (opcional)
3. ms_hoteles, ms_vuelos, ms_reservas
4. servidor_gateway
