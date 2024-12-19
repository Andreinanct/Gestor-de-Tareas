## PRESENTACIÓN DEL PROYECTO: GESTOR DE TAREAS

El **Gestor de Tareas** es una solución innovadora diseñada para optimizar la gestión de proyectos y tareas en equipos de trabajo. Este prototipo combina eficiencia, simplicidad y escalabilidad, haciendo que la asignación y el seguimiento de tareas sea intuitivo y efectivo.
![image](https://github.com/user-attachments/assets/79e9ba24-32ba-4dd3-b9b3-9fc5f0f9f93e)

---

## PROPUESTA DE VALOR

- **Organización centralizada**: Consolida tareas y responsabilidades en un solo lugar.
- **Colaboración simplificada**: Mejora la comunicación entre miembros del equipo.
- **Escalabilidad**: Diseñado para adaptarse a equipos y proyectos de diferentes tamaños.
- **Rendimiento medible**: Facilita el seguimiento del progreso de las tareas y el rendimiento del equipo.

---

## FUNCIONALIDADES CLAVE

1. **Gestor de Tareas**:
   - Creación, edición y eliminación de tareas.
   - Priorización basada en niveles (alta, media, baja).

2. **Asignación de Usuarios**:
   - Vinculación de tareas a usuarios específicos.
   - Roles personalizados para diferenciar administradores de colaboradores.

3. **Seguimiento de Progreso**:
   - Estados de tareas (pendiente, en progreso, completada).
   - Historial de cambios para auditoría.

4. **Reportes Automatizados**:
   - Informes sobre productividad y cumplimientos.
   - Estadísticas visuales para facilitar decisiones.

5. **Interfaz Gráfica Amigable**:
   - Diseño intuitivo para usuarios técnicos y no técnicos.
   - Opciones de personalización según necesidades del cliente.

---

## ARQUITECTURA DEL SISTEMA

### **Modular y Flexible**
El proyecto está estructurado en módulos independientes para facilitar su mantenimiento y extensión:

- **Clase Tarea**: Modela las tareas individuales con propiedades clave como título, descripción, prioridad y estado.
- **Clase Usuario**: Representa a los usuarios del sistema, permitiendo asignaciones y gestión de roles.
- **Clase GestorDeTareas**: Actúa como el centro de lógica del negocio, coordinando las operaciones entre tareas y usuarios.

---

## INNOVACIONES

- **Persistencia simplificada**: Utiliza archivos planos para almacenar datos, garantizando compatibilidad y facilidad de implementación.
- **Optimizado para integración**: Diseñado para conectarse con bases de datos u otras plataformas de software.
- **Fácil despliegue**: Compatible con cualquier entorno que soporte Java, ideal para integraciones rápidas.

---

## CASOS DE USO

1. **Empresas emergentes**:
   - Administrar tareas y asignaciones en proyectos pequeños y medianos.

2. **Equipos multidisciplinarios**:
   - Coordinar actividades entre departamentos o equipos remotos.

3. **Entornos educativos**:
   - Seguimiento de asignaciones y proyectos en clases o programas académicos.

---

## ESCENARIO PRÁCTICO

Un administrador crea un proyecto y asigna tareas a los miembros del equipo. Cada miembro visualiza sus tareas pendientes en su panel personal, actualiza el progreso y marca las tareas completadas. El administrador genera un reporte para analizar el cumplimiento y reasignar recursos si es necesario.

---

## TECNOLOGÍAS UTILIZADAS

- **Lenguaje**: Java
- **IDE**: NetBeans
- **Persistencia**: Archivos de texto (con posibilidad de migración a bases de datos).
- **Frameworks**: Swing para la interfaz gráfica.

---

## BENEFICIOS PARA LA EMPRESA

- **Aumento de la productividad**: Reducción del tiempo invertido en la gestión manual de tareas.
- **Visibilidad clara**: Mejora en la transparencia del progreso de los proyectos.
- **Ahorro de costos**: Implementación rápida y sin necesidad de infraestructura adicional.

---

## MANUAL DE USUARIO: GESTOR DE TAREAS

### **1. INTRODUCCIÓN**

Bienvenido al **Gestor de Tareas**, una herramienta diseñada para ayudarte a gestionar proyectos y tareas de manera eficiente. Este manual te guiará a través de las principales funcionalidades del sistema, asegurando que puedas aprovechar al máximo todas sus capacidades.

---

### **2. REQUISITOS DEL SISTEMA**

- **Sistema operativo**: Windows, macOS, Linux.
- **Java**: Versión 8 o superior instalada.
- **Espacio en disco**: 50 MB de espacio libre.
- **Hardware recomendado**: 4 GB de RAM, procesador dual-core o superior.

---

### **3. ACCESO AL SISTEMA**

1. **Iniciar la aplicación**:
   - Descarga y ejecuta el archivo `GestorDeTareas.jar`.
   - Se abrirá la ventana principal del sistema.

2. **Pantalla principal**:
   - Panel de resumen con todas las tareas asignadas y su estado.
   - Botones de acceso rápido para crear, editar o eliminar tareas.

> **Nota**: Asegúrate de guardar los cambios antes de salir para evitar pérdida de datos.

---

### **4. FUNCIONALIDADES**

#### **4.1. Crear una nueva tarea**
1. Haz clic en el botón **"Nueva Tarea"**.
2. Completa los siguientes campos:
   - **Título**: Nombre breve que describa la tarea.
   - **Descripción**: Detalle adicional sobre la tarea.
   - **Prioridad**: Selecciona entre baja, media o alta.
   - **Estado inicial**: Por defecto, se establece en "Pendiente".
3. Haz clic en **"Guardar"**.

#### **4.2. Editar una tarea existente**
1. Selecciona la tarea de la lista principal.
2. Haz clic en el botón **"Editar"**.
3. Realiza los cambios necesarios y presiona **"Actualizar"**.

#### **4.3. Eliminar una tarea**
1. Selecciona la tarea de la lista.
2. Presiona el botón **"Eliminar"**.
3. Confirma la acción en la ventana emergente.

#### **4.4. Asignar una tarea a un usuario**
1. Durante la creación o edición, selecciona un usuario del menú desplegable.
2. Haz clic en **"Guardar"** para aplicar los cambios.

#### **4.5. Generar un reporte**
1. Accede al menú superior y selecciona **"Reportes"**.
2. Elige el tipo de reporte (tareas completadas, pendientes, etc.).
3. El sistema generará un archivo PDF con los datos solicitados.

---

### **5. VALIDACIONES Y NOTIFICACIONES**

- **Campos obligatorios**: Si un campo esencial queda vacío, el sistema mostrará un mensaje de error.
- **Confirmaciones**: Acciones críticas como eliminar una tarea requerirán confirmación del usuario.
- **Errores comunes**:
  - Tarea duplicada: Aparece un mensaje si intentas crear una tarea con el mismo título y usuario asignado.
  - Fecha inválida: Si ingresas un formato incorrecto, el sistema te pedirá corregirlo.

---

### **6. PREGUNTAS FRECUENTES**

#### **6.1. ¿Qué hago si el sistema no guarda las tareas?**
Verifica que tengas permisos de escritura en la carpeta donde se almacena el archivo de datos.

#### **6.2. ¿Cómo puedo cambiar el usuario asignado a una tarea?**
Edita la tarea desde la pantalla principal y selecciona un nuevo usuario en el menú desplegable.

---

### **7. CONTACTO Y SOPORTE**

Para consultas o soporte técnico, contacta al desarrollador del sistema a través de:  
- **Correo electrónico**: soporte@gestordetareas.com  
- **Teléfono**: +34 123 456 789  

---

Este prototipo representa una herramienta poderosa y accesible para cualquier organización que busque optimizar la gestión de sus proyectos. Con un diseño modular y escalable, está listo para convertirse en una solución integral de productividad.

