# servidor_configuracion — Servidor de Configuración Centralizada

Servidor de configuración basado en **Spring Cloud Config Server**. Centraliza los archivos de configuración de los microservicios en un directorio del sistema de archivos local, permitiendo gestionar propiedades sin necesidad de recompilar ni redesplegar cada servicio.

## Rol en la arquitectura

```
[ms_hoteles / ms_vuelos / ms_reservas]
      ↓ bootstrap.yml → solicita config al iniciar
[servidor_configuracion :8888]
      ↓ lee archivos locales
[c:\config\configuracion_servicio\]
```

## Tecnología

| Componente | Versión |
|------------|---------|
| Java | 17 |
| Spring Boot | 3.3.10 |
| Spring Cloud | 2023.0.5 |
| spring-cloud-config-server | — |

## Puerto

| Servicio | Puerto |
|----------|--------|
| Config Server | **8888** |

## Configuración (`application.yml`)

```yaml
server:
  port: 8888
spring:
  cloud:
    config:
      server:
        native:
          search-locations:
            - file:///c:\config\configuracion_servicio
  profiles:
    active:
      - native
```

El perfil `native` indica que los archivos de configuración se leen desde el sistema de archivos local (no desde Git). El directorio `c:\config\configuracion_servicio\` debe existir y contener los archivos `.properties` o `.yml` de cada microservicio.

## Anotación principal

```java
@SpringBootApplication
@EnableConfigServer
public class Application { ... }
```

## Estructura esperada de configuraciones locales

```
c:\config\configuracion_servicio\
├── servicio-hoteles.yml        # configuración de ms_hoteles
├── servicio-vuelos.yml         # configuración de ms_vuelos
└── servicio-reservas.yml       # configuración de ms_reservas
```

## Arranque

No depende de otros microservicios. Se recomienda iniciarlo después de `ms_eureka_server`.

```bash
mvn spring-boot:run
```

Verificar que el servidor responde:
```
http://localhost:8888/servicio-hoteles/default
```

## Nota

Este servidor es **opcional** en el estado actual del proyecto. Cada microservicio tiene su propio `application.yml`. 
Se puede activar la integración con Config Server añadiendo `spring.config.import=configserver:http://localhost:8888` en cada servicio.
