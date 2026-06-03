# cliente_viajes — Cliente Web (AngularJS)

Aplicación web estática que proporciona la interfaz de usuario del sistema de reservas de viaje. Desarrollada con **AngularJS 1.6.9** y **Bootstrap 3**, se comunica exclusivamente con los microservicios a través del API Gateway.

## Rol en la arquitectura

```
[Navegador]
   ↓ http://localhost:7000 (API Gateway)
cliente_viajes
├── nuevareserva.html  →  GET  /svuelos/vuelos/1     (lista vuelos disponibles)
│                      →  GET  /shoteles/hoteles      (lista hoteles disponibles)
│                      →  POST /sreservas/reserva/1   (crea reserva)
└── reservas.html      →  GET  /sreservas/reservas    (lista todas las reservas)
```

## Tecnología

| Componente | Versión |
|------------|---------|
| AngularJS | 1.6.9 |
| Bootstrap | 3.4.0 |
| jQuery | 3.3.1 |

> No requiere build ni servidor de aplicaciones. Es HTML/JS estático que puede abrirse directamente en el navegador o servirse con cualquier servidor web.

## Páginas

### `nuevareserva.html` — Crear reserva

Formulario para registrar una nueva reserva de viaje:
- Selector de **vuelo** (cargado dinámicamente desde `ms_vuelos`)
- Selector de **hotel** (cargado dinámicamente desde `ms_hoteles`)
- Campos: **DNI** y **Nombre** del pasajero
- Botón de confirmación que envía la reserva a `ms_reservas`

### `reservas.html` — Listar reservas

Tabla con todas las reservas registradas:
- ID de reserva
- ID de hotel y vuelo seleccionados
- Nombre del pasajero

## Estructura de archivos

```
cliente_viajes/
└── WebContent/
    ├── nuevareserva.html    # Formulario de nueva reserva
    └── reservas.html        # Listado de reservas
```

## Módulo y controlador AngularJS

```javascript
angular.module('comunicacionApp', [])
  .controller('comunicacionController', function($scope, $http) {
    // Carga vuelos y hoteles al iniciar
    // Envía formulario de reserva al Gateway
  });
```

## Prerequisitos para ejecutar

El cliente requiere que los siguientes servicios estén en ejecución:

| Servicio | Puerto |
|----------|--------|
| ms_eureka_server | 8761 |
| ms_hoteles | 8000 |
| ms_vuelos | 9000 |
| ms_reservas | 10000 |
| **servidor_gateway** | **7000** |

## Cómo usar

1. Iniciar todos los microservicios (ver orden recomendado en `ms_eureka_server/README.md`)
2. Abrir `WebContent/nuevareserva.html` en el navegador
3. Seleccionar vuelo, hotel e ingresar datos del pasajero
4. Confirmar la reserva
5. Navegar a `reservas.html` para ver las reservas creadas

## Nota de desarrollo

Al abrir los archivos HTML directamente desde el sistema de archivos (`file://`), el navegador puede bloquear las llamadas AJAX por políticas CORS. 
Se recomienda servir los archivos con un servidor local (p.ej. Live Server de VS Code o un servidor Nginx/Apache) apuntando al directorio `WebContent/`.
