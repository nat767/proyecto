# Oasis Aromático - Sistema de Gestión de Perfumería

Un sistema web responsivo para la gestión de tiendas de perfumes construido con HTML, CSS y JavaScript. Oasis Aromático proporciona una solución completa para gestionar inventario, ventas y reportes con una interfaz moderna y gradientes atractivos.

## Stack Tecnológico

- **Lenguaje:** HTML5, CSS3, JavaScript (Vanilla)
- **Arquitectura:** Single-Page Application (SPA) con navegación del lado del cliente
- **UI:** CSS personalizado con iconos de Font Awesome
- **Almacenamiento:** Local storage del navegador

## Organización del Proyecto

```
proyecto/
└── index.html          Aplicación completa en un archivo
                        - Páginas de Login y Registro
                        - Dashboard con inventario, ventas y reportes
                        - Navegación lateral
                        - Diseño responsivo (móvil)
```

## Características

### 📄 Páginas
- **Login** - Autenticación con correo y contraseña
- **Registro** - Registro de usuarios con datos personales (nombres, cédula, fecha de nacimiento)
- **Dashboard** - Panel principal con tres secciones:
  - 📦 **Inventario** - Gestión de productos
  - 💰 **Ventas** - Seguimiento de pedidos y transacciones
  - 📊 **Reportes** - Análisis e información

### 🎨 Componentes de UI
- Esquema de colores con gradiente (Azul #4f46e5 + Rosa #ec4899)
- Barra lateral con navegación
- Tablas de datos con búsqueda y filtros
- Botones de acción (editar/eliminar)
- Insignias de estado
- Animaciones suaves

## Cómo ejecutar

### 1. Clonar el repositorio
```bash
git clone https://github.com/nat767/proyecto.git
cd proyecto
```

### 2. Abrir en el navegador
**Opción A:** Hacer doble clic en `index.html`

**Opción B:** Usar un servidor local (recomendado)
```bash
# Python 3
python -m http.server 8000

# Python 2
python -m SimpleHTTPServer 8000

# Node.js
npx http-server
```

Luego visita: `http://localhost:8000`

### 3. Acceder
- La aplicación está en modo demo - acepta cualquier correo y contraseña
- Flujo: Login → Registro → Dashboard

## Diseño

- **Colores primarios:** Índigo (#4f46e5) y Rosa (#ec4899)
- **Responsivo:** Compatible con dispositivos móviles y escritorio
- **Iconos:** Font Awesome 6.4.0
- **Efectos:** Transiciones fade-in y efectos al pasar el mouse

## Notas Importantes

⚠️ **Este es un prototipo de frontend.** Para usar en producción necesitarás:
- Conectar a una API backend
- Implementar autenticación real
- Agregar integración con base de datos
- Desplegar en un servidor web

---

**Autor:** [@nat767](https://github.com/nat767)
**Licencia:** MIT
