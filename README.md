# Implementación de autenticación JWT en una API REST

El sistema de préstamos de una entidad financiera necesita una capa de seguridad robusta para proteger los datos sensibles de los clientes. Se ha identificado una brecha en la implementación de autenticación JWT en la API REST que gestiona las solicitudes de préstamos. El objetivo es diseñar e implementar un mecanismo de autenticación JWT que garantice la seguridad de las transacciones y proteja la información de los usuarios.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Autenticación JWT en API REST |
| **Nivel** | junior-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 8 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Diseño del mecanismo de autenticación

**Objetivo:** Definir las especificaciones del mecanismo de autenticación JWT y sus interacciones con el sistema de préstamos.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Identificar los actores involucrados en el proceso de autenticación (usuario, API de autenticación, sistema de préstamos).
- Establecer las reglas de negocio para la autenticación (usuario debe estar registrado, contraseña debe cumplir ciertos criterios de seguridad).
- Definir los estados y transiciones del proceso de autenticación (pendiente, exitoso, fallido).
- Documentar las validaciones necesarias (nombre de usuario único, contraseña fuerte).
- Especificar los edge cases a considerar (usuario inexistente, contraseña incorrecta).

**Entregable:** Documento de diseño que describe el mecanismo de autenticación JWT, incluyendo actores, reglas de negocio, estados, transiciones, validaciones y edge cases.

<details>
<summary>Pistas de conocimiento</summary>

- Considera las mejores prácticas para la seguridad de contraseñas y la gestión de sesiones.
- Piensa en cómo manejar los errores de autenticación y cómo comunicarlos al usuario.

</details>

### Fase 2: Implementación del mecanismo de autenticación

**Objetivo:** Desarrollar el código necesario para implementar el mecanismo de autenticación JWT descrito en la fase anterior.

**Tiempo estimado:** 4 horas

**Instrucciones:**

- Crear los endpoints necesarios para el registro de usuarios y la autenticación.
- Implementar la lógica de generación y verificación de tokens JWT.
- Integrar el mecanismo de autenticación con el sistema de préstamos.
- Manejar los errores de autenticación y proporcionar respuestas adecuadas al usuario.

**Entregable:** Código implementado que permite el registro de usuarios y la autenticación JWT en la API REST de préstamos.

<details>
<summary>Pistas de conocimiento</summary>

- Utiliza librerías de seguridad para la generación y verificación de tokens JWT.
- Asegúrate de que el mecanismo de autenticación sea idempotente y tolerante a fallos.

</details>

### Fase 3: Pruebas y optimización del mecanismo de autenticación

**Objetivo:** Realizar pruebas exhaustivas del mecanismo de autenticación y optimizar su rendimiento.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Crear casos de prueba para verificar el funcionamiento del mecanismo de autenticación en diferentes escenarios.
- Realizar pruebas de carga para evaluar el rendimiento del mecanismo de autenticación.
- Identificar y corregir posibles vulnerabilidades de seguridad.
- Optimizar el código para mejorar el rendimiento y la eficiencia.

**Entregable:** Reporte de pruebas que documenta los resultados de las pruebas de funcionalidad, carga y seguridad, junto con el código optimizado del mecanismo de autenticación.

<details>
<summary>Pistas de conocimiento</summary>

- Utiliza herramientas de pruebas y de rendimiento para evaluar el mecanismo de autenticación.
- Asegúrate de que el mecanismo de autenticación sea resistente a ataques comunes de seguridad.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es un token JWT y cómo se utiliza en la autenticación de una API REST?
- **paraQueSirve**: ¿Para qué sirve la autenticación JWT en el contexto de una API REST de préstamos?
- **comoSeUsa**: ¿Cómo se implementa y utiliza un token JWT en la autenticación de una API REST?
- **erroresComunes**: ¿Cuáles son los errores comunes que pueden ocurrir durante la autenticación JWT y cómo se manejan?
- **queDecisionesImplica**: ¿Qué decisiones de diseño implica la implementación de un mecanismo de autenticación JWT en una API REST?

## Criterios de Evaluacion

- Diseño de un mecanismo de autenticación JWT que cumpla con las reglas de negocio y los requisitos de seguridad.
- Implementación de endpoints para registro de usuarios y autenticación JWT.
- Integración del mecanismo de autenticación con el sistema de préstamos.
- Manejo de errores de autenticación y respuestas adecuadas al usuario.
- Realización de pruebas exhaustivas y optimización del rendimiento del mecanismo de autenticación.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
el comando de build o arranque canonico del stack elegido
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
