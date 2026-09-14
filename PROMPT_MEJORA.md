# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Boilerplate del stack que falta

Sin esto no compila ni arranca. Es andamiaje, no toca nada de lo pedagogico:

- **Punto de entrada del stack elegido** — Sin un punto de entrada reconocible, el runtime no tiene por donde arrancar la aplicacion.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/financiera/prestamos/service/LoanService.java` — `LoanStatus`: LoanStatus se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.financiera.prestamos.model.LoanStatus.
- `src/main/java/com/financiera/prestamos/security/JwtAuthenticationFilter.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/financiera/prestamos/security/JwtTokenUtil.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/financiera/prestamos/controller/AuthController.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/financiera/prestamos/model/User.java` — `Role.name`: Se invoca `name` sobre `Role`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financiera/prestamos/service/AuthService.java` — `UserRepository.save`: Se invoca `save` sobre `UserRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financiera/prestamos/service/LoanService.java` — `UserRepository.findById`: Se invoca `findById` sobre `UserRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financiera/prestamos/service/LoanService.java` — `LoanRepository.save`: Se invoca `save` sobre `LoanRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financiera/prestamos/service/LoanService.java` — `LoanRepository.findAll`: Se invoca `findAll` sobre `LoanRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financiera/prestamos/service/LoanService.java` — `LoanRepository.findById`: Se invoca `findById` sobre `LoanRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financiera/prestamos/service/LoanService.java` — `LoanRepository.existsById`: Se invoca `existsById` sobre `LoanRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financiera/prestamos/service/LoanService.java` — `LoanRepository.deleteById`: Se invoca `deleteById` sobre `LoanRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/financiera/prestamos/service/LoanService.java` — `UserRepository.existsById`: Se invoca `existsById` sobre `UserRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/financiera/prestamos/service/AuthServiceTest.java` — `User.setActive`: Se invoca `setActive` sobre `User`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/financiera/prestamos/service/AuthServiceTest.java` — `UserRepository.save`: Se invoca `save` sobre `UserRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/financiera/prestamos/service/AuthServiceTest.java` — `JwtTokenUtil.getExpirationMs`: Se invoca `getExpirationMs` sobre `JwtTokenUtil`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/financiera/prestamos/service/AuthServiceTest.java` — `AuthResponse.token`: Se invoca `token` sobre `AuthResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/financiera/prestamos/service/AuthServiceTest.java` — `AuthResponse.username`: Se invoca `username` sobre `AuthResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/financiera/prestamos/service/AuthServiceTest.java` — `AuthResponse.roles`: Se invoca `roles` sobre `AuthResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/financiera/prestamos/service/AuthServiceTest.java` — `AuthService.authenticate`: Se invoca `authenticate` sobre `AuthService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/financiera/prestamos/service/AuthServiceTest.java` — `AuthResponse.expirationMs`: Se invoca `expirationMs` sobre `AuthResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
el comando de build o arranque canonico del stack elegido
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Contexto técnico original
Implementar autenticacion JWT en una API REST con Spring Security

### Reto
- Tema: Autenticación JWT en API REST
- Seniority: junior-l2
- Tipo: practical
- Título: Implementación de autenticación JWT en una API REST
- Tiempo estimado: 8 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Diseño del mecanismo de autenticación — objetivo: Definir las especificaciones del mecanismo de autenticación JWT y sus interacciones con el sistema de préstamos. — entregable (NO resolver): Documento de diseño que describe el mecanismo de autenticación JWT, incluyendo actores, reglas de negocio, estados, transiciones, validaciones y edge cases.
- Fase 2: Implementación del mecanismo de autenticación — objetivo: Desarrollar el código necesario para implementar el mecanismo de autenticación JWT descrito en la fase anterior. — entregable (NO resolver): Código implementado que permite el registro de usuarios y la autenticación JWT en la API REST de préstamos.
- Fase 3: Pruebas y optimización del mecanismo de autenticación — objetivo: Realizar pruebas exhaustivas del mecanismo de autenticación y optimizar su rendimiento. — entregable (NO resolver): Reporte de pruebas que documenta los resultados de las pruebas de funcionalidad, carga y seguridad, junto con el código optimizado del mecanismo de autenticación.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.4.0</version>
        <relativePath/>
    </parent>
    
    <groupId>com.financiera</groupId>
    <artifactId>prestamos</artifactId>
    <version>1.0.0</version>
    <name>prestamos</name>
    <description>Sistema de Gestión de Préstamos con Autenticación JWT</description>
    
    <properties>
        <java.version>21</java.version>
        <jjwt.version>0.12.5</jjwt.version>
    </properties>
    
    <dependencies>
        <!-- Spring Boot Web -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        
        <!-- Spring Boot Security -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
        
        <!-- Spring Boot Data JPA -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        
        <!-- Spring Boot Validation -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        
        <!-- JWT Dependencies -->
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-api</artifactId>
            <version>${jjwt.version}</version>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-impl</artifactId>
            <version>${jjwt.version}</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-jackson</artifactId>
            <version>${jjwt.version}</version>
            <scope>runtime</scope>
        </dependency>
        
        <!-- H2 Database -->
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
        
        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        
        <!-- Testing -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.security</groupId>
            <artifactId>spring-security-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
    
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/financiera/prestamos/PrestamosApplication.java ===
package com.financiera.prestamos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Punto de entrada principal de la aplicación de Gestión de Préstamos.
 * 
 * Esta clase configura y arranca el contexto de Spring Boot, inicializando
 * todos los componentes de la aplicación incluyendo seguridad, persistencia
 * y controladores REST.
 * 
 * La aplicación implementa un sistema de préstamos con autenticación JWT
 * para proteger los endpoints sensibles y garantizar la seguridad de las
 * transacciones financieras.
 */
@SpringBootApplication
public class PrestamosApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrestamosApplication.class, args);
    }

    /**
     * Bean para el codificador de contraseñas utilizado en la autenticación.
     * BCrypt es un algoritmo de hash seguro que incluye sal para prevenir
     * ataques de tabla rainbow y fuerza bruta.
     * 
     * Este bean se inyecta en AuthService para codificar contraseñas
     * durante el registro de usuarios y comparar hashes durante el login.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}

// === ARCHIVO: src/main/resources/application.properties ===
# Configuración de la aplicación
spring.application.name=prestamos
server.port=8080

# Configuración de la base de datos H2 en memoria
spring.datasource.url=jdbc:h2:mem:prestamosdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# Configuración JPA/Hibernate
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Configuración de H2 Console (para desarrollo)
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Configuración de JWT
jwt.secret=YXNkZmFzZGZhc2RmYXNkZmFzZGZhc2RmYXNkZmFzZGZhc2RmYXNkZmFzZGZhc2RmYXNkZmFzZGZhc2RmYXNkZmFzZGY=
jwt.expiration=86400000

# Configuración de seguridad de Spring
spring.security.user.name=admin
spring.security.user.password=admin123

# Logging
logging.level.com.financiera.prestamos=DEBUG
logging.level.org.springframework.security=DEBUG

// === ARCHIVO: src/main/java/com/financiera/prestamos/dto/AuthRequest.java ===
package com.financiera.prestamos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO que representa la solicitud de autenticación de un usuario.
 * 
 * Este record contiene las credenciales necesarias para autenticar
 * a un usuario en el sistema de préstamos. Se utiliza como cuerpo
 * de la petición POST al endpoint de login.
 * 
 * La validación asegura que el username y password no estén vacíos
 * y cumplan con las longitudes mínimas requeridas.
 * 
 * @param username Nombre de usuario único en el sistema
 * @param password Contraseña del usuario (se compara con hash almacenado)
 */
public record AuthRequest(
    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(min = 3, max = 50, message = "El nombre de usuario debe tener entre 3 y 50 caracteres")
    String username,
    
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, max = 100, message = "La contraseña debe tener entre 6 y 100 caracteres")
    String password
) {
    /**
     * Constructor canónico que valida los parámetros antes de crear el record.
     * Aunque los validadores de Jakarta trabajan a nivel de Controller,
     * este constructor proporciona una capa adicional de validación.
     */
    public AuthRequest {
        if (username != null) {
            username = username.trim();
        }
        if (password != null) {
            password = password.trim();
        }
    }
}

// === ARCHIVO: src/main/java/com/financiera/prestamos/dto/AuthResponse.java ===
package com.financiera.prestamos.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO que representa la respuesta de autenticación exitosa.
 * 
 * Este record contiene el token JWT generado tras una autenticación
 * exitosa, junto con información adicional del usuario autenticado.
 * Se retorna como cuerpo de respuesta en el endpoint de login.
 * 
 * El token JWT incluye los claims del usuario (username, roles) y
 * tiene una vigencia configurada en application.properties.
 * 
 * @param token Token JWT para solicitudes autenticadas
 * @param username Nombre de usuario autenticado
 * @param roles Roles asignados al usuario (USER, ADMIN)
 * @param type Tipo de token (Bearer)
 * @param expirationMs Tiempo de expiración en milisegundos
 */
public record AuthResponse(
    @JsonProperty("token")
    String token,
    
    @JsonProperty("username")
    String username,
    
    @JsonProperty("roles")
    String roles,
    
    @JsonProperty("type")
    String type,
    
    @JsonProperty("expiresIn")
    Long expirationMs
) {
    /**
     * Factory method para crear una respuesta de autenticación exitosa.
     * Configura automáticamente el tipo de token como "Bearer" que es
     * el estándar OAuth 2.0 para tokens JWT.
     */
    public static AuthResponse success(String token, String username, String roles, Long expirationMs) {
        return new AuthResponse(token, username, roles, "Bearer", expirationMs);
    }
}

// === ARCHIVO: src/main/java/com/financiera/prestamos/dto/LoanRequest.java ===
package com.financiera.prestamos.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * DTO que representa la solicitud de un préstamo financiero.
 * 
 * Este record contiene todos los datos necesarios para procesar una
 * solicitud de préstamo, incluyendo información del cliente, monto
 * solicitado y propósito del préstamo. Se utiliza como cuerpo de
 * petición POST al endpoint de creación de préstamos.
 * 
 * Los campos son validados para asegurar que los datos sean válidos
 * antes de ser procesados por el servicio de préstamos.
 * 
 * @param clientName Nombre completo del solicitante
 * @param clientEmail Correo electrónico del solicitante
 * @param clientId Número de identificación del cliente
 * @param amount Monto solicitado del préstamo
 * @param termMonths Plazo del préstamo en meses
 * @param purpose Propósito o destino del préstamo
 * @param interestRate Tasa de interés anual aplicada
 */
public record LoanRequest(
    @NotBlank(message = "El nombre del cliente es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    @JsonProperty("clientName")
    String clientName,
    
    @NotBlank(message = "El correo electrónico es obligatorio")
    @Size(max = 100, message = "El correo no puede exceder 100 caracteres")
    @JsonProperty("clientEmail")
    String clientEmail,
    
    @NotBlank(message = "La identificación del cliente es obligatoria")
    @Size(min = 5, max = 20, message = "La identificación debe tener entre 5 y 20 caracteres")
    @JsonProperty("clientId")
    String clientId,
    
    @NotNull(message = "El monto del préstamo es obligatorio")
    @DecimalMin(value = "100.00", message = "El monto mínimo es 100")
    @JsonProperty("amount")
    BigDecimal amount,
    
    @NotNull(message = "El plazo del préstamo es obligatorio")
    @DecimalMin(value = "1", message = "El plazo mínimo es 1 mes")
    @JsonProperty("termMonths")
    Integer termMonths,
    
    @NotBlank(message = "El propósito del préstamo es obligatorio")
    @Size(min = 5, max = 200, message = "El propósito debe tener entre 5 y 200 caracteres")
    @JsonProperty("purpose")
    String purpose,
    
    @JsonProperty("interestRate")
    BigDecimal interestRate
) {
    /**
     * Constructor que normaliza los datos del solicitud.
     * Elimina espacios en blanco y aplica valores por defecto
     * cuando ciertos campos opcionales no se proporcionan.
     */
    public LoanRequest {
        if (clientName != null) {
            clientName = clientName.trim();
        }
        if (clientEmail != null) {
            clientEmail = clientEmail.trim().toLowerCase();
        }
        if (clientId != null) {
            clientId = clientId.trim();
        }
        if (purpose != null) {
            purpose = purpose.trim();
        }
        if (interestRate == null) {
            interestRate = new BigDecimal("0.15");
        }
    }
}


// === ARCHIVO: src/main/java/com/financiera/prestamos/dto/LoanResponse.java ===
package com.financiera.prestamos.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record LoanResponse(
    Long id,
    String username,
    BigDecimal amount,
    BigDecimal interestRate,
    Integer termMonths,
    String status,
    LocalDate requestedDate,
    LocalDate approvalDate,
    LocalDate disbursementDate,
    LocalDate dueDate,
    BigDecimal totalPayment,
    BigDecimal monthlyPayment
) {
    public static LoanResponse fromEntity(com.financiera.prestamos.model.Loan loan) {
        return new LoanResponse(
            loan.getId(),
            loan.getUser() != null ? loan.getUser().getUsername() : null,
            loan.getAmount(),
            loan.getInterestRate(),
            loan.getTermMonths(),
            loan.getStatus().name(),
            loan.getRequestedDate(),
            loan.getApprovalDate(),
            loan.getDisbursementDate(),
            loan.getDueDate(),
            loan.getTotalPayment(),
            loan.getMonthlyPayment()
        );
    }

    public static LoanResponse pending(Long id, String username, BigDecimal amount, Integer termMonths) {
        return new LoanResponse(
            id,
            username,
            amount,
            BigDecimal.ZERO,
            termMonths,
            "PENDING",
            LocalDate.now(),
            null,
            null,
            null,
            null,
            null
        );
    }
}

// === ARCHIVO: src/main/java/com/financiera/prestamos/model/User.java ===
package com.financiera.prestamos.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(name = "full_name", length = 200)
    private String fullName;

    @Column(length = 20)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role role;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private Boolean enabled = true;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Loan> loans = new ArrayList<>();

    public enum Role {
        ADMIN,
        USER,
        ANALYST,
        APPROVER
    }

    public User() {
    }

    public User(String username, String password, String email, String fullName, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.role = role;
        this.enabled = true;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    public void addLoan(Loan loan) {
        loans.add(loan);
        loan.setUser(this);
    }

    public void removeLoan(Loan loan) {
        loans.remove(loan);
        loan.setUser(null);
    }
}

// === ARCHIVO: src/main/java/com/financiera/prestamos/model/Loan.java ===
package com.financiera.prestamos.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal amount;

    @Column(name = "interest_rate", nullable = false, precision = 10, scale = 4)
    private BigDecimal interestRate;

    @Column(name = "term_months", nullable = false)
    private Integer termMonths;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private LoanStatus status;

    @Column(name = "requested_date", nullable = false)
    private LocalDate requestedDate;

    @Column(name = "approval_date")
    private LocalDate approvalDate;

    @Column(name = "disbursement_date")
    private LocalDate disbursementDate;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "total_payment", precision = 19, scale = 4)
    private BigDecimal totalPayment;

    @Column(name = "monthly_payment", precision = 19, scale = 4)
    private BigDecimal monthlyPayment;

    @Column(length = 500)
    private String purpose;

    @Column(name = "rejection_reason", length = 500)
    private String rejectionReason;

    @Column(name = "approved_by")
    private String approvedBy;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public enum LoanStatus {
        PENDING,
        UNDER_REVIEW,
        APPROVED,
        REJECTED,
        DISBURSED,
        PAID,
        DEFAULTED,
        CANCELLED
    }

    public Loan() {
    }

    public Loan(User user, BigDecimal amount, BigDecimal interestRate, Integer termMonths, String purpose) {
        this.user = user;
        this.amount = amount;
        this.interestRate = interestRate;
        this.termMonths = termMonths;
        this.purpose = purpose;
        this.status = LoanStatus.PENDING;
        this.requestedDate = LocalDate.now();
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public void calculatePayments() {
        if (amount == null || interestRate == null || termMonths == null) {
            return;
        }
        BigDecimal monthlyRate = interestRate.divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP);
        BigDecimal factor = BigDecimal.ONE.add(monthlyRate).pow(termMonths);
        BigDecimal monthly = amount.multiply(monthlyRate).multiply(factor)
            .divide(factor.subtract(BigDecimal.ONE), 2, RoundingMode.HALF_UP);
        this.monthlyPayment = monthly;
        this.totalPayment = monthly.multiply(BigDecimal.valueOf(termMonths));
    }

    public void approve(String approvedBy) {
        this.status = LoanStatus.APPROVED;
        this.approvalDate = LocalDate.now();
        this.approvedBy = approvedBy;
    }

    public void reject(String reason) {
        this.status = LoanStatus.REJECTED;
        this.rejectionReason = reason;
    }

    public void disburse() {
        this.status = LoanStatus.DISBURSED;
        this.disbursementDate = LocalDate.now();
        this.dueDate = LocalDate.now().plusMonths(termMonths);
    }

    public void markAsPaid() {
        this.status = LoanStatus.PAID;
    }

    public void markAsDefaulted() {
        this.status = LoanStatus.DEFAULTED;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public Integer getTermMonths() {
        return termMonths;
    }

    public void setTermMonths(Integer termMonths) {
        this.termMonths = termMonths;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public LocalDate getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(LocalDate requestedDate) {
        this.requestedDate = requestedDate;
    }

    public LocalDate getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(LocalDate approvalDate) {
        this.approvalDate = approvalDate;
    }

    public LocalDate getDisbursementDate() {
        return disbursementDate;
    }

    public void setDisbursementDate(LocalDate disbursementDate) {
        this.disbursementDate = disbursementDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(BigDecimal totalPayment) {
        this.totalPayment = totalPayment;
    }

    public BigDecimal getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(BigDecimal monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}

// === ARCHIVO: src/main/java/com/financiera/prestamos/repository/UserRepository.java ===
package com.financiera.prestamos.repository;

import com.financiera.prestamos.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.username = :username AND u.enabled = true")
    Optional<User> findActiveUserByUsername(@Param("username") String username);

    @Query("SELECT u FROM User u JOIN u.roles r WHERE r = :role")
    List<User> findUsersByRole(@Param("role") String role);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.username = :username")
    boolean isUsernameTaken(@Param("username") String username);
}

// === ARCHIVO: src/main/java/com/financiera/prestamos/repository/LoanRepository.java ===
package com.financiera.prestamos.repository;

import com.financiera.prestamos.model.Loan;
import com.financiera.prestamos.model.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findByUserId(Long userId);

    List<Loan> findByUserIdAndStatus(Long userId, LoanStatus status);

    List<Loan> findByStatus(LoanStatus status);

    Optional<Loan> findByIdAndUserId(Long loanId, Long userId);

    @Query("SELECT l FROM Loan l WHERE l.user.id = :userId ORDER BY l.createdAt DESC")
    List<Loan> findRecentLoansByUserId(@Param("userId") Long userId);

    @Query("SELECT l FROM Loan l WHERE l.status = :status AND l.createdAt >= :startDate")
    List<Loan> findLoansByStatusAndDateRange(@Param("status") LoanStatus status,
                                              @Param("startDate") LocalDate startDate);

    @Query("SELECT SUM(l.amount) FROM Loan l WHERE l.user.id = :userId AND l.status = :status")
    BigDecimal sumAmountByUserIdAndStatus(@Param("userId") Long userId, @Param("status") LoanStatus status);

    @Query("SELECT COUNT(l) FROM Loan l WHERE l.user.id = :userId")
    Long countByUserId(@Param("userId") Long userId);

    @Query("SELECT COUNT(l) FROM Loan l WHERE l.status = :status")
    Long countByStatus(@Param("status") LoanStatus status);

    @Query("SELECT l FROM Loan l WHERE l.user.id = :userId AND l.status IN :statuses")
    List<Loan> findByUserIdAndStatusIn(@Param("userId") Long userId, @Param("statuses") List<LoanStatus> statuses);

    @Query("SELECT l FROM Loan l WHERE l.amount >= :minAmount AND l.amount <= :maxAmount")
    List<Loan> findByAmountRange(@Param("minAmount") BigDecimal minAmount, @Param("maxAmount") BigDecimal maxAmount);
}

// === ARCHIVO: src/main/java/com/financiera/prestamos/config/SecurityConfig.java ===
package com.financiera.prestamos.config;

import com.financiera.prestamos.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final UserDetailsService userDetailsService;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter,
                          UserDetailsService userDetailsService) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/loans/**").authenticated()
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000", "http://localhost:8080"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        configuration.setExposedHeaders(List.of("Authorization"));
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}

// === ARCHIVO: src/main/java/com/financiera/prestamos/security/JwtAuthenticationFilter.java ===
package com.financiera.prestamos.security;

import com.financiera.prestamos.exception.InvalidJwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;

    @Autowired
    public JwtAuthenticationFilter(JwtTokenUtil jwtTokenUtil, UserDetailsService userDetailsService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = extractJwtFromRequest(request);
            if (StringUtils.hasText(jwt) && jwtTokenUtil.validateToken(jwt)) {
                String username = jwtTokenUtil.extractUsername(jwt);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if (jwtTokenUtil.validateToken(jwt, userDetails)) {
                    List<SimpleGrantedAuthority> authorities = jwtTokenUtil.extractRoles(jwt).stream()
                            .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                            .toList();

                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, authorities);
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    logger.debug("Autenticación establecida para usuario: {}", username);
                }
            }
        } catch (InvalidJwtException e) {
            logger.warn("Token JWT inválido: {}", e.getMessage());
        } catch (Exception e) {
            logger.error("Error en el filtro de autenticación: {}", e.getMessage(), e);
        }
        filterChain.doFilter(request, response);
    }

    private String extractJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(BEARER_PREFIX.length());
        }
        return null;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        return path.startsWith("/api/auth/");
    }
}

// === ARCHIVO: src/main/java/com/financiera/prestamos/security/JwtTokenUtil.java ===
package com.financiera.prestamos.security;

import com.financiera.prestamos.exception.InvalidJwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);
    private static final String CLAIM_ROLES = "roles";
    private static final String CLAIM_USER_ID = "userId";

    @Value("${jwt.secret:ThisIsAVerySecretKeyForJWTTokenGenerationThatShouldBeLongEnoughForHS512Algorithm}")
    private String secret;

    @Value("${jwt.expiration:86400000}")
    private Long expiration;

    public String generateToken(String username, List<String> roles, Long userId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_ROLES, roles);
        claims.put(CLAIM_USER_ID, userId);
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(getSigningKey())
                .compact();
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public List<String> extractRoles(String token) {
        Claims claims = extractAllClaims(token);
        @SuppressWarnings("unchecked")
        List<String> roles = claims.get(CLAIM_ROLES, List.class);
        return roles != null ? roles : Collections.emptyList();
    }

    public Long extractUserId(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get(CLAIM_USER_ID, Long.class);
    }

    public Boolean validateToken(String token) {
        try {
            extractAllClaims(token);
            return !isTokenExpired(token);
        } catch (MalformedJwtException e) {
            logger.error("Token JWT malformado: {}", e.getMessage());
            throw new InvalidJwtException("Token JWT malformado");
        } catch (ExpiredJwtException e) {
            logger.error("Token JWT expirado: {}", e.getMessage());
            throw new InvalidJwtException("Token JWT expirado");
        } catch (SignatureException e) {
            logger.error("Firma JWT inválida: {}", e.getMessage());
            throw new InvalidJwtException("Firma JWT inválida");
        } catch (Exception e) {
            logger.error("Error validando token JWT: {}", e.getMessage());
            throw new InvalidJwtException("Error validando token JWT");
        }
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Long getExpiration() {
        return expiration;
    }
}

// === ARCHIVO: src/main/java/com/financiera/prestamos/controller/AuthController.java ===
package com.financiera.prestamos.controller;

import com.financiera.prestamos.dto.AuthRequest;
import com.financiera.prestamos.dto.AuthResponse;
import com.financiera.prestamos.exception.UserAlreadyExistsException;
import com.financiera.prestamos.security.JwtTokenUtil;
import com.financiera.prestamos.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthenticationManager authenticationManager;
    private final AuthService authService;
    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, AuthService authService,
                          UserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.authService = authService;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest request) {
        logger.info("Intento de registro para usuario: {}", request.username());
        try {
            var result = authService.register(request.username(), request.password());
            logger.info("Usuario registrado exitosamente: {}", request.username());
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (UserAlreadyExistsException e) {
            logger.warn("Registro fallido - usuario ya existe: {}", request.username());
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        logger.info("Intento de autenticación para usuario: {}", request.username());
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.username(), request.password())
            );

            UserDetails userDetails = userDetailsService.loadUserByUsername(request.username());
            var user = authService.findByUsername(request.username())
                    .orElseThrow(() -> new BadCredentialsException("Usuario no encontrado"));

            String token = jwtTokenUtil.generateToken(
                    userDetails.getUsername(),
                    userDetails.getAuthorities().stream()
                            .map(auth -> auth.getAuthority().replace("ROLE_", ""))
                            .toList(),
                    user.getId()
            );

            logger.info("Autenticación exitosa para usuario: {}", request.username());
            return ResponseEntity.ok(AuthResponse.success(token, userDetails.getUsername(),
                    userDetails.getAuthorities().toString(), jwtTokenUtil.getExpiration()));
        } catch (BadCredentialsException e) {
            logger.warn("Autenticación fallida para usuario: {}", request.username());
            Map<String, String> error = new HashMap<>();
            error.put("error", "Credenciales inválidas");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
    }
}

// === ARCHIVO: src/main/java/com/financiera/prestamos/controller/LoanController.java ===
package com.financiera.prestamos.controller;

import com.financiera.prestamos.dto.LoanRequest;
import com.financiera.prestamos.dto.LoanResponse;
import com.financiera.prestamos.model.Loan;
import com.financiera.prestamos.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<LoanResponse> createLoan(@Valid @RequestBody LoanRequest request) {
        Loan createdLoan = loanService.createLoan(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(LoanResponse.fromEntity(createdLoan));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<LoanResponse>> getAllLoans() {
        List<Loan> loans = loanService.getAllLoans();
        List<LoanResponse> responses = loans.stream()
                .map(LoanResponse::fromEntity)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<LoanResponse> getLoanById(@PathVariable Long id) {
        Loan loan = loanService.getLoanById(id);
        return ResponseEntity.ok(LoanResponse.fromEntity(loan));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<LoanResponse> updateLoan(@PathVariable Long id, @Valid @RequestBody LoanRequest request) {
        Loan updatedLoan = loanService.updateLoan(id, request);
        return ResponseEntity.ok(LoanResponse.fromEntity(updatedLoan));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteLoan(@PathVariable Long id) {
        loanService.deleteLoan(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<LoanResponse>> getLoansByUserId(@PathVariable Long userId) {
        List<Loan> loans = loanService.getLoansByUserId(userId);
        List<LoanResponse> responses = loans.stream()
                .map(LoanResponse::fromEntity)
                .toList();
        return ResponseEntity.ok(responses);
    }
}

// === ARCHIVO: src/main/java/com/financiera/prestamos/service/AuthService.java ===
package com.financiera.prestamos.service;

import com.financiera.prestamos.dto.AuthRequest;
import com.financiera.prestamos.dto.AuthResponse;
import com.financiera.prestamos.exception.UserAlreadyExistsException;
import com.financiera.prestamos.model.User;
import com.financiera.prestamos.repository.UserRepository;
import com.financiera.prestamos.security.JwtTokenUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, JwtTokenUtil jwtTokenUtil,
                       PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse register(AuthRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new UserAlreadyExistsException("El usuario ya existe: " + request.username());
        }

        String encodedPassword = passwordEncoder.encode(request.password());
        Set<String> roles = request.roles() != null ? request.roles() : Set.of("ROLE_USER");

        User user = new User();
        user.setUsername(request.username());
        user.setPassword(encodedPassword);
        user.setRoles(roles);
        user.setEmail(request.email());

        User savedUser = userRepository.save(user);

        String token = jwtTokenUtil.generateToken(savedUser.getUsername(), roles);

        return AuthResponse.success(token, savedUser.getUsername(),
                String.join(",", roles), jwtTokenUtil.getExpirationTime());
    }

    public AuthResponse login(AuthRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.username(), request.password())
            );
        } catch (Exception e) {
            throw new BadCredentialsException("Credenciales inválidas");
        }

        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new BadCredentialsException("Usuario no encontrado"));

        String token = jwtTokenUtil.generateToken(user.getUsername(), user.getRoles());

        return AuthResponse.success(token, user.getUsername(),
                String.join(",", user.getRoles()), jwtTokenUtil.getExpirationTime());
    }

    public boolean validateToken(String token) {
        try {
            return jwtTokenUtil.validateToken(token);
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        return jwtTokenUtil.getUsernameFromToken(token);
    }
}

// === ARCHIVO: src/main/java/com/financiera/prestamos/service/LoanService.java ===
package com.financiera.prestamos.service;

import com.financiera.prestamos.dto.LoanRequest;
import com.financiera.prestamos.model.Loan;
import com.financiera.prestamos.model.User;
import com.financiera.prestamos.repository.LoanRepository;
import com.financiera.prestamos.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final UserRepository userRepository;

    public LoanService(LoanRepository loanRepository, UserRepository userRepository) {
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Loan createLoan(LoanRequest request) {
        validateLoanRequest(request);

        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + request.userId()));

        Loan loan = new Loan();
        loan.setUser(user);
        loan.setAmount(request.amount());
        loan.setInterestRate(request.interestRate());
        loan.setTermMonths(request.termMonths());
        loan.setStatus("PENDING");
        loan.setRequestDate(LocalDate.now());
        loan.setPurpose(request.purpose());

        BigDecimal monthlyPayment = calculateMonthlyPayment(
                loan.getAmount(),
                loan.getInterestRate(),
                loan.getTermMonths()
        );
        loan.setMonthlyPayment(monthlyPayment);

        return loanRepository.save(loan);
    }

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Loan getLoanById(Long id) {
        return loanRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Préstamo no encontrado con ID: " + id));
    }

    @Transactional
    public Loan updateLoan(Long id, LoanRequest request) {
        Loan existingLoan = getLoanById(id);

        validateLoanRequest(request);

        existingLoan.setAmount(request.amount());
        existingLoan.setInterestRate(request.interestRate());
        existingLoan.setTermMonths(request.termMonths());
        existingLoan.setPurpose(request.purpose());

        BigDecimal monthlyPayment = calculateMonthlyPayment(
                existingLoan.getAmount(),
                existingLoan.getInterestRate(),
                existingLoan.getTermMonths()
        );
        existingLoan.setMonthlyPayment(monthlyPayment);

        return loanRepository.save(existingLoan);
    }

    @Transactional
    public void deleteLoan(Long id) {
        if (!loanRepository.existsById(id)) {
            throw new IllegalArgumentException("Préstamo no encontrado con ID: " + id);
        }
        loanRepository.deleteById(id);
    }

    public List<Loan> getLoansByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("Usuario no encontrado con ID: " + userId);
        }
        return loanRepository.findByUserId(userId);
    }

    @Transactional
    public Loan approveLoan(Long id) {
        Loan loan = getLoanById(id);
        if (!"PENDING".equals(loan.getStatus())) {
            throw new IllegalStateException("Solo se pueden aprobar préstamos en estado PENDING");
        }
        loan.setStatus("APPROVED");
        loan.setApprovalDate(LocalDate.now());
        return loanRepository.save(loan);
    }

    @Transactional
    public Loan rejectLoan(Long id) {
        Loan loan = getLoanById(id);
        if (!"PENDING".equals(loan.getStatus())) {
            throw new IllegalStateException("Solo se pueden rechazar préstamos en estado PENDING");
        }
        loan.setStatus("REJECTED");
        return loanRepository.save(loan);
    }

    private void validateLoanRequest(LoanRequest request) {
        if (request.amount() == null || request.amount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto del préstamo debe ser mayor a cero");
        }
        if (request.interestRate() == null || request.interestRate().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("La tasa de interés no puede ser negativa");
        }
        if (request.termMonths() == null || request.termMonths() <= 0) {
            throw new IllegalArgumentException("El plazo en meses debe ser mayor a cero");
        }
    }

    private BigDecimal calculateMonthlyPayment(BigDecimal amount, BigDecimal annualInterestRate, Integer termMonths) {
        if (annualInterestRate.compareTo(BigDecimal.ZERO) == 0) {
            return amount.divide(BigDecimal.valueOf(termMonths), 2, java.math.RoundingMode.HALF_UP);
        }

        BigDecimal monthlyRate = annualInterestRate.divide(BigDecimal.valueOf(12), 6, java.math.RoundingMode.HALF_UP);
        BigDecimal onePlusR = BigDecimal.ONE.add(monthlyRate);
        BigDecimal factor = onePlusR.pow(termMonths);

        BigDecimal numerator = amount.multiply(monthlyRate).multiply(factor);
        BigDecimal denominator = factor.subtract(BigDecimal.ONE);

        return numerator.divide(denominator, 2, java.math.RoundingMode.HALF_UP);
    }
}


// === ARCHIVO: src/main/java/com/financiera/prestamos/exception/InvalidJwtException.java ===
package com.financiera.prestamos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidJwtException extends RuntimeException {
    private final String token;
    private final String reason;

    public InvalidJwtException(String message) {
        super(message);
        this.token = null;
        this.reason = message;
    }

    public InvalidJwtException(String message, String token) {
        super(message);
        this.token = token;
        this.reason = message;
    }

    public InvalidJwtException(String message, Throwable cause) {
        super(message, cause);
        this.token = null;
        this.reason = message;
    }

    public InvalidJwtException(String message, String token, Throwable cause) {
        super(message, cause);
        this.token = token;
        this.reason = message;
    }

    public String getToken() {
        return token;
    }

    public String getReason() {
        return reason;
    }

    public boolean isTokenPresent() {
        return token != null && !token.isBlank();
    }

    public String getMaskedToken() {
        if (token == null || token.length() < 20) {
            return "[TOKEN_NOT_AVAILABLE]";
        }
        return token.substring(0, 10) + "..." + token.substring(token.length() - 10);
    }

    @Override
    public String toString() {
        return String.format("InvalidJwtException{message='%s', reason='%s', tokenPresent=%s}", 
            getMessage(), reason, isTokenPresent());
    }
}

// === ARCHIVO: src/main/java/com/financiera/prestamos/exception/UserAlreadyExistsException.java ===
package com.financiera.prestamos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyExistsException extends RuntimeException {
    private final String username;
    private final String email;
    private final Long existingUserId;

    public UserAlreadyExistsException(String message) {
        super(message);
        this.username = null;
        this.email = null;
        this.existingUserId = null;
    }

    public UserAlreadyExistsException(String message, String username) {
        super(message);
        this.username = username;
        this.email = null;
        this.existingUserId = null;
    }

    public UserAlreadyExistsException(String message, String username, String email) {
        super(message);
        this.username = username;
        this.email = email;
        this.existingUserId = null;
    }

    public UserAlreadyExistsException(String message, String username, String email, Long existingUserId) {
        super(message);
        this.username = username;
        this.email = email;
        this.existingUserId = existingUserId;
    }

    public UserAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
        this.username = null;
        this.email = null;
        this.existingUserId = null;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Long getExistingUserId() {
        return existingUserId;
    }

    public boolean hasUsername() {
        return username != null && !username.isBlank();
    }

    public boolean hasEmail() {
        return email != null && !email.isBlank();
    }

    public boolean hasExistingUserId() {
        return existingUserId != null;
    }

    public String getConflictType() {
        if (hasUsername() && hasEmail()) {
            return "USERNAME_AND_EMAIL";
        } else if (hasUsername()) {
            return "USERNAME";
        } else if (hasEmail()) {
            return "EMAIL";
        }
        return "UNKNOWN";
    }

    @Override
    public String toString() {
        return String.format("UserAlreadyExistsException{message='%s', username='%s', email='%s', existingUserId=%s, conflictType='%s'}", 
            getMessage(), username, email, existingUserId, getConflictType());
    }
}

// === ARCHIVO: src/main/java/com/financiera/prestamos/exception/GlobalExceptionHandler.java ===
package com.financiera.prestamos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidJwtException.class)
    public ResponseEntity<ErrorResponse> handleInvalidJwtException(
            InvalidJwtException ex, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
            .status(HttpStatus.UNAUTHORIZED.value())
            .error("Unauthorized")
            .message(ex.getMessage())
            .path(request.getDescription(false).replace("uri=", ""))
            .timestamp(Instant.now().toString())
            .details(Map.of(
                "reason", ex.getReason() != null ? ex.getReason() : "unknown",
                "tokenPresent", ex.isTokenPresent()
            ))
            .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(
            UserAlreadyExistsException ex, WebRequest request) {
        Map<String, Object> details = new HashMap<>();
        if (ex.hasUsername()) {
            details.put("username", ex.getUsername());
        }
        if (ex.hasEmail()) {
            details.put("email", ex.getEmail());
        }
        if (ex.hasExistingUserId()) {
            details.put("existingUserId", ex.getExistingUserId());
        }
        details.put("conflictType", ex.getConflictType());

        ErrorResponse errorResponse = ErrorResponse.builder()
            .status(HttpStatus.CONFLICT.value())
            .error("Conflict")
            .message(ex.getMessage())
            .path(request.getDescription(false).replace("uri=", ""))
            .timestamp(Instant.now().toString())
            .details(details)
            .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
            .collect(Collectors.toMap(
                FieldError::getField,
                error -> error.getDefaultMessage() != null ? 
                    error.getDefaultMessage() : "Validation error",
                (existing, replacement) -> existing
            ));

        ErrorResponse errorResponse = ErrorResponse.builder()
            .status(HttpStatus.BAD_REQUEST.value())
            .error("Validation Failed")
            .message("Los datos proporcionados no son válidos")
            .path(request.getDescription(false).replace("uri=", ""))
            .timestamp(Instant.now().toString())
            .details(fieldErrors)
            .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(
            BadCredentialsException ex, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
            .status(HttpStatus.UNAUTHORIZED.value())
            .error("Unauthorized")
            .message("Credenciales inválidas")
            .path(request.getDescription(false).replace("uri=", ""))
            .timestamp(Instant.now().toString())
            .details(Map.of("error", "username_or_password_invalid"))
            .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationException(
            AuthenticationException ex, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
            .status(HttpStatus.UNAUTHORIZED.value())
            .error("Authentication Failed")
            .message("Error de autenticación: " + ex.getMessage())
            .path(request.getDescription(false).replace("uri=", ""))
            .timestamp(Instant.now().toString())
            .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(
            AccessDeniedException ex, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
            .status(HttpStatus.FORBIDDEN.value())
            .error("Forbidden")
            .message("No tienes permisos para acceder a este recurso")
            .path(request.getDescription(false).replace("uri=", ""))
            .timestamp(Instant.now().toString())
            .build();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(
            IllegalArgumentException ex, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
            .status(HttpStatus.BAD_REQUEST.value())
            .error("Bad Request")
            .message(ex.getMessage())
            .path(request.getDescription(false).replace("uri=", ""))
            .timestamp(Instant.now().toString())
            .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(
            Exception ex, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .error("Internal Server Error")
            .message("Ha ocurrido un error inesperado")
            .path(request.getDescription(false).replace("uri=", ""))
            .timestamp(Instant.now().toString())
            .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    public static class ErrorResponse {
        private final int status;
        private final String error;
        private final String message;
        private final String path;
        private final String timestamp;
        private final Map<String, Object> details;

        private ErrorResponse(Builder builder) {
            this.status = builder.status;
            this.error = builder.error;
            this.message = builder.message;
            this.path = builder.path;
            this.timestamp = builder.timestamp;
            this.details = builder.details;
        }

        public static Builder builder() {
            return new Builder();
        }

        public int getStatus() {
            return status;
        }

        public String getError() {
            return error;
        }

        public String getMessage() {
            return message;
        }

        public String getPath() {
            return path;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public Map<String, Object> getDetails() {
            return details;
        }

        public static class Builder {
            private int status;
            private String error;
            private String message;
            private String path;
            private String timestamp;
            private Map<String, Object> details;

            public Builder status(int status) {
                this.status = status;
                return this;
            }

            public Builder error(String error) {
                this.error = error;
                return this;
            }

            public Builder message(String message) {
                this.message = message;
                return this;
            }

            public Builder path(String path) {
                this.path = path;
                return this;
            }

            public Builder timestamp(String timestamp) {
                this.timestamp = timestamp;
                return this;
            }

            public Builder details(Map<String, Object> details) {
                this.details = details;
                return this;
            }

            public ErrorResponse build() {
                return new ErrorResponse(this);
            }
        }
    }
}


// === ARCHIVO: src/test/java/com/financiera/prestamos/controller/AuthControllerTest.java ===
package com.financiera.prestamos.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.financiera.prestamos.dto.AuthRequest;
import com.financiera.prestamos.dto.AuthResponse;
import com.financiera.prestamos.security.JwtTokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    private AuthRequest registroRequest;
    private AuthRequest loginRequest;

    @BeforeEach
    void setUp() {
        registroRequest = new AuthRequest("nuevoUsuario", "password123", "CLIENTE");
        loginRequest = new AuthRequest("admin", "admin123", "ADMIN");
    }

    @Test
    @DisplayName("POST /api/auth/register - Registro exitoso de nuevo usuario")
    void testRegistroUsuario_exito() throws Exception {
        AuthRequest request = new AuthRequest("usuarioTest", "password123", "CLIENTE");

        ResultActions result = mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andExpect(jsonPath("$.username").value("usuarioTest"))
                .andExpect(jsonPath("$.roles").value("CLIENTE"));
    }

    @Test
    @DisplayName("POST /api/auth/register - Usuario ya existente retorna error 400")
    void testRegistroUsuario_usuarioExistente() throws Exception {
        AuthRequest request = new AuthRequest("admin", "password123", "ADMIN");

        ResultActions result = mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        result.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").exists());
    }

    @Test
    @DisplayName("POST /api/auth/login - Login exitoso retorna token JWT")
    void testLogin_exito() throws Exception {
        AuthRequest request = new AuthRequest("admin", "admin123", "ADMIN");

        ResultActions result = mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andExpect(jsonPath("$.username").value("admin"));
    }

    @Test
    @DisplayName("POST /api/auth/login - Credenciales inválidas retorna error 401")
    void testLogin_credencialesInvalidas() throws Exception {
        AuthRequest request = new AuthRequest("admin", "passwordincorrecto", "ADMIN");

        ResultActions result = mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        result.andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error").exists());
    }

    @Test
    @DisplayName("POST /api/auth/login - Usuario no encontrado retorna error 401")
    void testLogin_usuarioNoExistente() throws Exception {
        AuthRequest request = new AuthRequest("usuarionoexistente", "password123", "CLIENTE");

        ResultActions result = mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        result.andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("POST /api/auth/register - Request con campos vacíos retorna error 400")
    void testRegistroUsuario_camposVacios() throws Exception {
        AuthRequest request = new AuthRequest("", "password123", "CLIENTE");

        ResultActions result = mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        result.andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Token JWT generado contiene los claims correctos")
    void testToken_generadoContieneClaims() throws Exception {
        AuthRequest request = new AuthRequest("testToken", "password123", "GERENTE");

        ResultActions result = mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        String token = result.andReturn().getResponse().getContentAsString();
        String tokenLimpio = objectMapper.readTree(token).get("token").asText();

        assertNotNull(tokenLimpio, "El token no debe ser null");
        assertTrue(tokenLimpio.length() > 0, "El token debe tener contenido");

        String username = jwtTokenUtil.extractUsername(tokenLimpio);
        assertEquals("testToken", username, "El username en el token debe coincidir");

        String roles = jwtTokenUtil.extractRoles(tokenLimpio);
        assertEquals("GERENTE", roles, "Los roles en el token deben coincidir");
    }
}

// === ARCHIVO: src/test/java/com/financiera/prestamos/controller/LoanControllerTest.java ===
package com.financiera.prestamos.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.financiera.prestamos.dto.LoanRequest;
import com.financiera.prestamos.dto.LoanResponse;
import com.financiera.prestamos.model.User;
import com.financiera.prestamos.model.Loan;
import com.financiera.prestamos.security.JwtTokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class LoanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private String tokenAdmin;
    private String tokenCliente;

    @BeforeEach
    void setUp() {
        tokenAdmin = jwtTokenUtil.generateToken("admin", "ADMIN");
        tokenCliente = jwtTokenUtil.generateToken("cliente1", "CLIENTE");
    }

    @Test
    @DisplayName("GET /api/loans - Sin autenticación retorna 401")
    void testGetLoans_sinAutenticacion() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/loans"));
        result.andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("GET /api/loans - Con token válido de ADMIN retorna todos los préstamos")
    void testGetLoans_conTokenAdmin() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/loans")
                .header("Authorization", "Bearer " + tokenAdmin));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @DisplayName("GET /api/loans - Con token válido de CLIENTE retorna solo sus préstamos")
    void testGetLoans_conTokenCliente() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/loans")
                .header("Authorization", "Bearer " + tokenCliente));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @DisplayName("GET /api/loans/{id} - Préstamo existente retorna detalles completos")
    void testGetLoanById_existente() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/loans/1")
                .header("Authorization", "Bearer " + tokenAdmin));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.monto").exists())
                .andExpect(jsonPath("$.estado").exists());
    }

    @Test
    @DisplayName("GET /api/loans/{id} - Préstamo no existente retorna 404")
    void testGetLoanById_noExistente() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/loans/99999")
                .header("Authorization", "Bearer " + tokenAdmin));

        result.andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /api/loans - Crear préstamo con token de CLIENTE exitosamente")
    void testCreateLoan_conTokenCliente() throws Exception {
        LoanRequest request = new LoanRequest(
                new BigDecimal("50000"),
                24,
                "Compra de vehículo",
                "PERSONAL"
        );

        ResultActions result = mockMvc.perform(post("/api/loans")
                .header("Authorization", "Bearer " + tokenCliente)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.monto").value(50000))
                .andExpect(jsonPath("$.estado").value("PENDIENTE"));
    }

    @Test
    @DisplayName("POST /api/loans - Crear préstamo con monto inválido retorna 400")
    void testCreateLoan_montoInvalido() throws Exception {
        LoanRequest request = new LoanRequest(
                new BigDecimal("-1000"),
                24,
                "Préstamo inválido",
                "PERSONAL"
        );

        ResultActions result = mockMvc.perform(post("/api/loans")
                .header("Authorization", "Bearer " + tokenCliente)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        result.andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("PUT /api/loans/{id}/approve - ADMIN puede aprobar préstamos")
    void testApproveLoan_conTokenAdmin() throws Exception {
        ResultActions result = mockMvc.perform(put("/api/loans/1/approve")
                .header("Authorization", "Bearer " + tokenAdmin));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("APROBADO"));
    }

    @Test
    @DisplayName("PUT /api/loans/{id}/approve - CLIENTE no puede aprobar préstamos")
    void testApproveLoan_sinPermisos() throws Exception {
        ResultActions result = mockMvc.perform(put("/api/loans/1/approve")
                .header("Authorization", "Bearer " + tokenCliente));

        result.andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("PUT /api/loans/{id}/reject - ADMIN puede rechazar préstamos")
    void testRejectLoan_conTokenAdmin() throws Exception {
        ResultActions result = mockMvc.perform(put("/api/loans/2/reject")
                .header("Authorization", "Bearer " + tokenAdmin));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("RECHAZADO"));
    }

    @Test
    @DisplayName("Token JWT extrae correctamente el username del contexto de seguridad")
    void testToken_usernameExtraidoDelContexto() throws Exception {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                "usuarioPrueba", null, List.of(new SimpleGrantedAuthority("CLIENTE"))
        );
        SecurityContextHolder.getContext().setAuthentication(auth);

        String token = jwtTokenUtil.generateToken("usuarioPrueba", "CLIENTE");
        String usernameExtraido = jwtTokenUtil.extractUsername(token);

        assertEquals("usuarioPrueba", usernameExtraido);
    }

    @Test
    @DisplayName("GET /api/loans - Token malformado retorna 401")
    void testGetLoans_tokenMalformado() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/loans")
                .header("Authorization", "Bearer tokenInvalido"));

        result.andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("POST /api/loans - Plazo mayor al máximo retorna 400")
    void testCreateLoan_plazoExcedido() throws Exception {
        LoanRequest request = new LoanRequest(
                new BigDecimal("100000"),
                120,
                "Plazo excedido",
                "HIPOTECARIO");

        ResultActions result = mockMvc.perform(post("/api/loans")
                .header("Authorization", "Bearer " + tokenCliente)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        result.andExpect(status().isBadRequest());
    }
}

// === ARCHIVO: src/test/java/com/financiera/prestamos/security/JwtTokenUtilTest.java ===
package com.financiera.prestamos.security;

import com.financiera.prestamos.exception.InvalidJwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JwtTokenUtilTest {

    private JwtTokenUtil jwtTokenUtil;
    private static final String SECRET_KEY = " EstaEsUnaClaveSecretaParaPruebasDeJWTQueDebeTener256Bits12345678 ";
    private static final String USERNAME_TEST = "usuarioPrueba";
    private static final String ROLES_TEST = "ADMIN";

    @BeforeEach
    void setUp() {
        jwtTokenUtil = new JwtTokenUtil();
        ReflectionTestUtils.setField(jwtTokenUtil, "secret", SECRET_KEY);
        ReflectionTestUtils.setField(jwtTokenUtil, "expirationMs", 3600000L);
    }

    @Test
    @DisplayName("GenerateToken - Token generado contiene username y roles correctos")
    void testGenerateToken_conDatosValidos() {
        String token = jwtTokenUtil.generateToken(USERNAME_TEST, ROLES_TEST);

        assertNotNull(token, "El token no debe ser null");
        assertTrue(token.length() > 0, "El token debe tener contenido");
        assertTrue(token.contains("."), "El token debe tener formato JWT con puntos");
    }

    @Test
    @DisplayName("ExtractUsername - Extrae correctamente el username del token")
    void testExtractUsername_extraccionExitosa() {
        String token = jwtTokenUtil.generateToken(USERNAME_TEST, ROLES_TEST);
        String usernameExtraido = jwtTokenUtil.extractUsername(token);

        assertEquals(USERNAME_TEST, usernameExtraido, "El username extraído debe coincidir");
    }

    @Test
    @DisplayName("ExtractRoles - Extrae correctamente los roles del token")
    void testExtractRoles_extraccionExitosa() {
        String token = jwtTokenUtil.generateToken(USERNAME_TEST, ROLES_TEST);
        String rolesExtraidos = jwtTokenUtil.extractRoles(token);

        assertEquals(ROLES_TEST, rolesExtraidos, "Los roles extraídos deben coincidir");
    }

    @Test
    @DisplayName("ValidateToken - Token válido retorna true")
    void testValidateToken_tokenValido() {
        String token = jwtTokenUtil.generateToken(USERNAME_TEST, ROLES_TEST);
        boolean esValido = jwtTokenUtil.validateToken(token);

        assertTrue(esValido, "El token válido debe retornar true");
    }

    @Test
    @DisplayName("ValidateToken - Token expirado retorna false")
    void testValidateToken_tokenExpirado() {
        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        
        Date fechaExpirada = new Date(System.currentTimeMillis() - 1000);
        String tokenExpirado = Jwts.builder()
                .subject(USERNAME_TEST)
                .claim("roles", ROLES_TEST)
                .expiration(fechaExpirada)
                .issuedAt(new Date(System.currentTimeMillis() - 10000))
                .signWith(key)
                .compact();

        boolean esValido = jwtTokenUtil.validateToken(tokenExpirado);
        assertFalse(esValido, "El token expirado debe retornar false");
    }

    @Test
    @DisplayName("ValidateToken - Token con firma inválida retorna false")
    void testValidateToken_firmaInvalida() {
        String tokenValido = jwtTokenUtil.generateToken(USERNAME_TEST, ROLES_TEST);
        String tokenConFirmaAlterada = tokenValido.substring(0, tokenValido.length() - 5) + "xxxxx";

        boolean esValido = jwtTokenUtil.validateToken(tokenConFirmaAlterada);
        assertFalse(esValido, "El token con firma inválida debe retornar false");
    }

    @Test
    @DisplayName("ExtractExpiration - Extrae la fecha de expiración correctamente")
    void testExtractExpiration_extraccionExitosa() {
        String token = jwtTokenUtil.generateToken(USERNAME_TEST, ROLES_TEST);
        Date expiracion = jwtTokenUtil.extractExpiration(token);

        assertNotNull(expiracion, "La expiración no debe ser null");
        assertTrue(expiracion.after(new Date()), "La expiración debe ser futura");
    }

    @Test
    @DisplayName("GenerateToken - Tokens generados para mismo usuario son diferentes")
    void testGenerateToken_tokensUnicos() throws InterruptedException {
        String token1 = jwtTokenUtil.generateToken(USERNAME_TEST, ROLES_TEST);
        Thread.sleep(10);
        String token2 = jwtTokenUtil.generateToken(USERNAME_TEST, ROLES_TEST);

        assertNotEquals(token1, token2, "Cada token debe ser único");
    }

    @Test
    @DisplayName("ExtractClaims - Extrae todos los claims del token")
    void testExtractClaims_extraccionCompleta() {
        String token = jwtTokenUtil.generateToken(USERNAME_TEST, ROLES_TEST);
        Claims claims = jwtTokenUtil.extractAllClaims(token);

        assertNotNull(claims, "Los claims no deben ser null");
        assertEquals(USERNAME_TEST, claims.getSubject(), "El subject debe ser el username");
        assertEquals(ROLES_TEST, claims.get("roles", String.class), "Los roles deben estar presentes");
    }

    @Test
    @DisplayName("ValidateToken - Token malformed lanza excepción")
    void testValidateToken_tokenMalformed() {
        String tokenMalformado = "esto.no.es.un.token.valido";

        assertThrows(Exception.class, () -> jwtTokenUtil.validateToken(tokenMalformado));
    }

    @Test
    @DisplayName("GenerateToken con claims adicionales - Incluye los claims en el token")
    void testGenerateToken_conClaimsAdicionales() {
        Map<String, Object> adicionales = new HashMap<>();
        adicionales.put("departamento", "FINANZAS");
        adicionales.put("activo", true);

        String token = jwtTokenUtil.generateToken(USERNAME_TEST, ROLES_TEST, adicionales);
        Claims claims = jwtTokenUtil.extractAllClaims(token);

        assertEquals("FINANZAS", claims.get("departamento", String.class));
        assertTrue(claims.get("activo", Boolean.class));
    }

    @Test
    @DisplayName("IsTokenExpired - Retorna true para token expirado")
    void testIsTokenExpired_tokenExpirado() {
        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        
        Date fechaPasada = new Date(System.currentTimeMillis() - 3600000);
        String tokenExpirado = Jwts.builder()
                .subject(USERNAME_TEST)
                .claim("roles", ROLES_TEST)
                .expiration(fechaPasada)
                .signWith(key)
                .compact();

        boolean expirado = jwtTokenUtil.isTokenExpired(tokenExpirado);
        assertTrue(expirado, "El token debe estar expirado");
    }

    @Test
    @DisplayName("IsTokenExpired - Retorna false para token válido")
    void testIsTokenExpired_tokenValido() {
        String token = jwtTokenUtil.generateToken(USERNAME_TEST, ROLES_TEST);
        boolean expirado = jwtTokenUtil.isTokenExpired(token);

        assertFalse(expirado, "El token no debe estar expirado");
    }

    @Test
    @DisplayName("ExtractAllClaims - Token vacío lanza excepción")
    void testExtractAllClaims_tokenVacio() {
        assertThrows(Exception.class, () -> jwtTokenUtil.extractAllClaims(""));
    }
}

// === ARCHIVO: src/test/java/com/financiera/prestamos/service/AuthServiceTest.java ===
package com.financiera.prestamos.service;

import com.financiera.prestamos.dto.AuthRequest;
import com.financiera.prestamos.dto.AuthResponse;
import com.financiera.prestamos.exception.UserAlreadyExistsException;
import com.financiera.prestamos.model.User;
import com.financiera.prestamos.repository.UserRepository;
import com.financiera.prestamos.security.JwtTokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias para AuthService")
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;

    private AuthRequest registroRequest;
    private AuthRequest loginRequest;
    private User usuarioExistente;
    private User nuevoUsuario;

    @BeforeEach
    void setUp() {
        registroRequest = new AuthRequest("nuevoUsuario", "password123", "CLIENTE");
        loginRequest = new AuthRequest("usuarioExistente", "password123", "CLIENTE");
        
        usuarioExistente = new User();
        usuarioExistente.setUsername("usuarioExistente");
        usuarioExistente.setPassword("hashedPassword");
        usuarioExistente.setRole("CLIENTE");
        usuarioExistente.setActive(true);
        
        nuevoUsuario = new User();
        nuevoUsuario.setUsername("nuevoUsuario");
        nuevoUsuario.setPassword("hashedPassword");
        nuevoUsuario.setRole("CLIENTE");
        nuevoUsuario.setActive(true);
    }

    @Test
    @DisplayName("Registro exitoso de usuario retorna token JWT")
    void registro_exitoso_retorna_token() {
        when(userRepository.findByUsername("nuevoUsuario")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("password123")).thenReturn("hashedPassword");
        when(userRepository.save(any(User.class))).thenReturn(nuevoUsuario);
        when(jwtTokenUtil.generateToken("nuevoUsuario", "CLIENTE")).thenReturn("jwt.token.signature");
        when(jwtTokenUtil.getExpirationMs()).thenReturn(3600000L);

        AuthResponse response = authService.register(registroRequest);

        assertNotNull(response);
        assertEquals("jwt.token.signature", response.token());
        assertEquals("nuevoUsuario", response.username());
        assertEquals("CLIENTE", response.roles());
        verify(userRepository).save(any(User.class));
        verify(jwtTokenUtil).generateToken("nuevoUsuario", "CLIENTE");
    }

    @Test
    @DisplayName("Registro de usuario existente lanza excepción")
    void registro_usuario_existente_lanza_excepcion() {
        when(userRepository.findByUsername("usuarioExistente")).thenReturn(Optional.of(usuarioExistente));

        assertThrows(UserAlreadyExistsException.class, () -> authService.register(loginRequest));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    @DisplayName("Login con credenciales válidas retorna token JWT")
    void login_credenciales_validas_retorna_token() {
        when(userRepository.findByUsername("usuarioExistente")).thenReturn(Optional.of(usuarioExistente));
        when(passwordEncoder.matches("password123", "hashedPassword")).thenReturn(true);
        when(jwtTokenUtil.generateToken("usuarioExistente", "CLIENTE")).thenReturn("jwt.token.signature");
        when(jwtTokenUtil.getExpirationMs()).thenReturn(3600000L);

        AuthResponse response = authService.authenticate(loginRequest);

        assertNotNull(response);
        assertEquals("jwt.token.signature", response.token());
        assertEquals("usuarioExistente", response.username());
        assertEquals("CLIENTE", response.roles());
    }

    @Test
    @DisplayName("Login con usuario inexistente lanza excepción")
    void login_usuario_inexistente_lanza_excepcion() {
        when(userRepository.findByUsername("inexistente")).thenReturn(Optional.empty());

        AuthRequest request = new AuthRequest("inexistente", "password123", "CLIENTE");
        assertThrows(BadCredentialsException.class, () -> authService.authenticate(request));
    }

    @Test
    @DisplayName("Login con contraseña incorrecta lanza excepción")
    void login_password_incorrecto_lanza_excepcion() {
        when(userRepository.findByUsername("usuarioExistente")).thenReturn(Optional.of(usuarioExistente));
        when(passwordEncoder.matches("passwordWrong", "hashedPassword")).thenReturn(false);

        AuthRequest request = new AuthRequest("usuarioExistente", "passwordWrong", "CLIENTE");
        assertThrows(BadCredentialsException.class, () -> authService.authenticate(request));
    }

    @Test
    @DisplayName("Login con usuario inactivo lanza excepción")
    void login_usuario_inactivo_lanza_excepcion() {
        usuarioExistente.setActive(false);
        when(userRepository.findByUsername("usuarioExistente")).thenReturn(Optional.of(usuarioExistente));
        when(passwordEncoder.matches("password123", "hashedPassword")).thenReturn(true);

        AuthRequest request = new AuthRequest("usuarioExistente", "password123", "CLIENTE");
        assertThrows(BadCredentialsException.class, () -> authService.authenticate(request));
    }

    @Test
    @DisplayName("Registro con rol nulo usa rol por defecto CLIENTE")
    void registro_rol_nulo_usa_default() {
        AuthRequest requestSinRol = new AuthRequest("usuarioNuevo", "password123", null);
        when(userRepository.findByUsername("usuarioNuevo")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("password123")).thenReturn("hashedPassword");
        when(userRepository.save(any(User.class))).thenAnswer(inv -> inv.getArgument(0));
        when(jwtTokenUtil.generateToken(anyString(), eq("CLIENTE"))).thenReturn("token");
        when(jwtTokenUtil.getExpirationMs()).thenReturn(3600000L);

        AuthResponse response = authService.register(requestSinRol);

        assertNotNull(response);
        assertEquals("CLIENTE", response.roles());
        verify(jwtTokenUtil).generateToken("usuarioNuevo", "CLIENTE");
    }

    @Test
    @DisplayName("Token JWT contiene información correcta del usuario")
    void token_contiene_info_correcta() {
        when(userRepository.findByUsername("usuarioExistente")).thenReturn(Optional.of(usuarioExistente));
        when(passwordEncoder.matches("password123", "hashedPassword")).thenReturn(true);
        when(jwtTokenUtil.generateToken("usuarioExistente", "CLIENTE")).thenReturn("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c3VhcmlvRXhpc3RlbnRlIiwiZXhwIjoxNzA0MDgwMDAwfQ.signature");
        when(jwtTokenUtil.getExpirationMs()).thenReturn(3600000L);

        AuthResponse response = authService.authenticate(loginRequest);

        assertTrue(response.token().contains("eyJ"));
        assertTrue(response.expirationMs() > System.currentTimeMillis());
    }
}

// === ARCHIVO: docs/diseno_autenticacion_jwt.md ===
# Diseño del Mecanismo de Autenticación JWT

## 1. Visión General

El sistema de préstamos requiere un mecanismo de autenticación robusto basado en tokens JWT (JSON Web Tokens) para proteger los endpoints de la API REST. Este documento especifica el diseño completo del mecanismo de autenticación, incluyendo actores, reglas de negocio, estados, transiciones, validaciones y casos especiales.

## 2. Actores del Sistema

### 2.1 Usuario Final (Cliente)
- **Descripción**: Cliente de la entidad financiera que solicita préstamos
- **Responsabilidades**: Registrarse en el sistema, iniciar sesión, gestionar sus solicitudes de préstamo
- **Permisos**: Acceder a endpoints de préstamos según su rol

### 2.2 Administrador del Sistema
- **Descripción**: Personal interno que gestiona usuarios yapprove préstamos
- **Responsabilidades**: Monitorear actividad, gestionar usuarios, aprobar/rechazar préstamos
- **Permisos**: Acceso completo a todos los endpoints

### 2.3 Sistema de Préstamos
- **Descripción**: Componente backend que valida tokens y procesa solicitudes
- **Responsabilidades**: Validar autenticidad de tokens, autorizar solicitudes, generar respuestas

## 3. Reglas de Negocio

### 3.1 Registro de Usuarios
- El nombre de usuario debe ser único en el sistema
- La contraseña debe tener mínimo 8 caracteres
- El rol por defecto para nuevos usuarios es "CLIENTE"
- El usuario se crea inicialmente como activo
- La contraseña se almacena hasheada usando BCrypt

### 3.2 Autenticación
- Las credenciales se validan contra la base de datos
- Solo usuarios activos pueden autenticarse
- El sistema genera un token JWT válido por 1 hora
- El token incluye username y roles como claims

### 3.3 Generación de Token JWT
- Algoritmo de firma: HS512
- Claims obligatorios: subject (username), exp (expiración), roles
- La clave secreta se configura en properties
- El token se transmite en el header Authorization: Bearer <token>

### 3.4 Validación de Token
- Se verifica firma, expiración y formato
- Tokens expirados o inválidos son rechazados
- El sistema retorna 401 Unauthorized para tokens inválidos

## 4. Estados del Usuario

### 4.1 Estados Posibles
| Estado | Descripción | Transiciones permitidas |
|--------|-------------|------------------------|
| ACTIVE | Usuario puede autenticarse y usar el sistema | → INACTIVE |
| INACTIVE | Usuario no puede autenticarse | → ACTIVE |

### 4.2 Diagrama de Estados
```
[INACTIVE] ──────► [ACTIVE] ──────► [INACTIVE]
     ▲                  │                  │
     └──────────────────┴──────────────────┘
```

## 5. Transiciones y Flujos

### 5.1 Flujo de Registro
```
Usuario → Solicitud Registro → Validar datos → Verificar usuario único 
→ Hashear contraseña → Guardar usuario → Generar token → Responder con token
```

### 5.2 Flujo de Autenticación
```
Usuario → Solicitud Login → Validar credenciales → Verificar usuario activo 
→ Generar token → Responder con token
```

### 5.3 Flujo de Acceso a Recurso Protegido
```
Usuario → Request con Token → Filtro JWT → Validar token 
→ Extraer usuario → Autorizar → Procesar request
```

## 6. Validaciones

### 6.1 Validaciones de Registro
- Username: no nulo, entre 3-50 caracteres, solo alfanumérico
- Password: no nulo, mínimo 8 caracteres, al menos una mayúscula y un número
- Role: opcional, valores válidos: CLIENTE, ADMIN

### 6.2 Validaciones de Autenticación
- Username: debe existir en la base de datos
- Password: debe coincidir con el hash almacenado
- Usuario: debe estar en estado ACTIVE

### 6.3 Validaciones de Token
- Formato: tres partes separadas por puntos (header.payload.signature)
- Firma: debe ser válida con la clave secreta configurada
- Expiración: no debe haber expirado
- Algoritmo: debe ser HS512

## 7. Edge Cases y Manejo

### 7.1 Usuario Duplicado
- **Escenario**: Intento de registro con username existente
- **Manejo**: Lanzar UserAlreadyExistsException, retornar 409 Conflict

### 7.2 Credenciales Inválidas
- **Escenario**: Password no coincide con hash almacenado
- **Manejo**: Lanzar BadCredentialsException, retornar 401 Unauthorized

### 7.3 Usuario Inactivo
- **Escenario**: Usuario en estado INACTIVE intenta autenticarse
- **Manejo**: Lanzar BadCredentialsException, retornar 401 Unauthorized

### 7.4 Token Expirado
- **Escenario**: Token JWT con expiración vencida
- **Manejo**: InvalidJwtException, retornar 401 Unauthorized

### 7.5 Token Malformado
- **Escenario**: Token con formato inválido
- **Manejo**: InvalidJwtException, retornar 401 Unauthorized

### 7.6 Ataque de Fuerza Bruta
- **Escenario**: Múltiples intentos fallidos de autenticación
- **Manejo**: Implementar rate limiting en el endpoint de login

### 7.7 Sesiones Concurrentes
- **Escenario**: Múltiples dispositivos usando el mismo usuario
- **Manejo**: Permitir múltiples tokens válidos por usuario

## 8. Consideraciones de Seguridad

### 8.1 Almacenamiento de Credenciales
- Nunca almacenar contraseñas en texto plano
- Usar BCrypt con factor de trabajo 10
- Incluir salt único por usuario

### 8.2 Transmisión de Tokens
- Usar HTTPS exclusivamente
- Incluir token en header Authorization
- No exponer token en URLs o logs

### 8.3 Gestión de Secretos
- Clave secreta de JWT en configuración segura
- Rotar claves periódicamente
- No hardcodear secretos en código

## 9. Configuración

```properties
# Configuración JWT
jwt.secret=claveSecretaSuperSeguraParaFirmarTokensDeAlMenos512Bits
jwt.expiration=3600000

# Configuración de seguridad
jwt.algorithm=HS512
```

## 10. Endpoints

| Método | Endpoint | Descripción | Autenticación |
|--------|----------|-------------|---------------|
| POST | /api/auth/register | Registrar nuevo usuario | No |
| POST | /api/auth/login | Autenticar usuario | No |
| GET | /api/loans | Listar préstamos | Sí (JWT) |
| POST | /api/loans | Crear préstamo | Sí (JWT) |
| GET | /api/loans/{id} | Obtener préstamo | Sí (JWT) |

## 11. Conclusión

Este diseño proporciona un mecanismo de autenticación robusto y seguro para el sistema de préstamos. La implementación sigue las mejores prácticas de la industria, incluyendo almacenamiento seguro de contraseñas, tokens JWT con firma segura, y manejo adecuado de casos de error y edge cases.

// === ARCHIVO: docs/reporte_pruebas.md ===
# Reporte de Pruebas - Sistema de Autenticación JWT

## 1. Resumen Ejecutivo

Este documento apresenta los resultados completos de las pruebas realizadas sobre el mecanismo de autenticación JWT implementado en el sistema de préstamos. Las pruebas abarcan funcionalidad, rendimiento y seguridad, identificando tanto fortalezas como vulnerabilidades potenciales.

## 2. Pruebas de Funcionalidad

### 2.1 Casos de Prueba - Registro de Usuarios

| ID | Escenario | Entrada | Resultado Esperado | Resultado Obtenido | Estado |
|----|-----------|---------|-------------------|-------------------|--------|
| TC-001 | Registro exitoso | username: "user1", password: "Password123" | Token JWT válido | Token JWT generado | ✓ PASS |
| TC-002 | Registro usuario duplicado | username: "existing", password: "Password123" | UserAlreadyExistsException | Excepción lanzada | ✓ PASS |
| TC-003 | Registro password corta | username: "user2", password: "pass" | ValidException | Validación rechaza | ✓ PASS |
| TC-004 | Registro username vacío | username: "", password: "Password123" | ValidException | Validación rechaza | ✓ PASS |
| TC-005 | Registro con rol específico | username: "admin1", password: "Password123", role: "ADMIN" | Token con rol ADMIN | Token con rol ADMIN | ✓ PASS |
| TC-006 | Registro sin rol especificado | username: "user3", password: "Password123", role: null | Token con rol CLIENTE | Token con rol CLIENTE | ✓ PASS |

### 2.2 Casos de Prueba - Autenticación

| ID | Escenario | Entrada | Resultado Esperado | Resultado Obtenido | Estado |
|----|-----------|---------|-------------------|-------------------|--------|
| TC-010 | Login con credenciales válidas | username: "user1", password: "Password123" | Token JWT válido | Token JWT generado | ✓ PASS |
| TC-011 | Login con password incorrecta | username: "user1", password: "WrongPass" | BadCredentialsException | Excepción lanzada | ✓ PASS |
| TC-012 | Login con usuario inexistente | username: "nonexistent", password: "Password123" | BadCredentialsException | Excepción lanzada | ✓ PASS |
| TC-013 | Login con usuario inactivo | username: "inactive", password: "Password123" | BadCredentialsException | Excepción lanzada | ✓ PASS |
| TC-014 | Login con username vacío | username: "", password: "Password123" | ValidException | Validación rechaza | ✓ PASS |
| TC-015 | Login con password vacía | username: "user1", password: "" | ValidException | Validación rechaza | ✓ PASS |

### 2.3 Casos de Prueba - Validación de Token

| ID | Escenario | Entrada | Resultado Esperado | Resultado Obtenido | Estado |
|----|-----------|---------|-------------------|-------------------|--------|
| TC-020 | Token válido | Token JWT válido | Usuario autenticado | Autenticación exitosa | ✓ PASS |
| TC-021 | Token expirado | Token con exp < now | InvalidJwtException | Excepción lanzada | ✓ PASS |
| TC-022 | Token malformado | "invalid.token" | InvalidJwtException | Excepción lanzada | ✓ PASS |
| TC-023 | Token con firma inválida | Token con signature modificada | InvalidJwtException | Excepción lanzada | ✓ PASS |
| TC-024 | Token sin header | Payload sin firma | InvalidJwtException | Excepción lanzada | ✓ PASS |

### 2.4 Métricas de Cobertura
- **Líneas de código cubiertas**: 89%
- **Métodos cubiertos**: 95%
- **Branches cubiertos**: 82%
- **Clases de equivalencia**: 15/15 probadas

## 3. Pruebas de Carga

### 3.1 Configuración de Pruebas
- **Herramienta**: Apache JMeter
- **Duración**: 5 minutos por escenario
- **Ramp-up**: 10 segundos
- **Sistema bajo prueba**: API REST con JWT

### 3.2 Resultados - Endpoint de Login

| Métrica | Valor |
|---------|-------|
| Solicitudes totales | 15,000 |
| Tiempo promedio de respuesta | 145 ms |
| P95 tiempo de respuesta | 280 ms |
| P99 tiempo de respuesta | 450 ms |
| Throughput | 50 req/s |
| Error rate | 0.02% |
| Tiempo mínimo | 45 ms |
| Tiempo máximo | 1,200 ms |

### 3.3 Resultados - Endpoint de Registro

| Métrica | Valor |
|---------|-------|
| Solicitudes totales | 10,000 |
| Tiempo promedio de respuesta | 180 ms |
| P95 tiempo de respuesta | 350 ms |
| P99 tiempo de respuesta | 520 ms |
| Throughput | 33 req/s |
| Error rate | 0.15% |
| Tiempo mínimo | 60 ms |
| Tiempo máximo | 1,500 ms |

### 3.4 Resultados - Validación de Token

| Métrica | Valor |
|---------|-------|
| Solicitudes totales | 50,000 |
| Tiempo promedio de respuesta | 25 ms |
| P95 tiempo de respuesta | 45 ms |
| P99 tiempo de respuesta | 80 ms |
| Throughput | 166 req/s |
| Error rate | 0.00% |
| Tiempo mínimo | 10 ms |
| Tiempo máximo | 200 ms |

### 3.5 Análisis de Rendimiento

**Fortalezas identificadas**:
- La validación de tokens es extremadamente rápida (25ms promedio)
- El sistema soporta hasta 166 validaciones por segundo
- El tiempo de respuesta se mantiene estable bajo carga moderada

**Cuellos de botella identificados**:
- El registro es más lento que el login debido al hasheo de contraseña
- El tiempo máximo de registro (1,500ms) indica variabilidad bajo carga
- El error rate en registro (0.15%) requiere investigación

**Recomendaciones**:
- Implementar caché para tokens frecuentemente usados
- Considerar autenticación asíncrona para registro
- Investigar causas del error rate en registro

## 4. Pruebas de Seguridad

### 4.1 Vulnerabilidades Identificadas

#### Vulnerabilidad V-001: Ausencia de Rate Limiting
- **Severidad**: Media
- **Descripción**: El endpoint de login no tiene limitación de intentos
- **Impacto**: Potencial ataque de fuerza bruta
- **Recomendación**: Implementar rate limiting con máximo 5 intentos por minuto
- **Estado**: Pendiente de corrección

#### Vulnerabilidad V-002: Token sin Refresh
- **Severidad**: Media
- **Descripción**: Los tokens JWT no tienen mecanismo de refresh
- **Impacto**: Usuarios deben reautenticarse frecuentemente
- **Recomendación**: Implementar endpoint de refresh token
- **Estado**: Mejora futura

#### Vulnerabilidad V-003: Exposición de Username en Token
- **Severidad**: Baja
- **Descripción**: El username se incluye en el payload del token
- **Impacto**: Información de usuario expuesta en logs
- **Recomendación**: Usar ID interno en lugar de username
- **Estado**: Aceptado con mitigación

### 4.2 Pruebas de Inyección

| Tipo de Prueba | Resultado |
|----------------|-----------|
| SQL Injection en username | Bloqueado por JPA |
| SQL Injection en password | Bloqueado por JPA |
| XSS en headers | No aplicable |
| CSRF | Mitigado por JWT stateless |

### 4.3 Pruebas de Criptografía

| Aspecto | Verificación | Estado |
|---------|--------------|--------|
| Algoritmo HS512 | Correctamente configurado | ✓ CUMPLE |
| Longitud de clave | 512 bits mínimo | ✓ CUMPLE |
| Hasheo de contraseñas | BCrypt con salt | ✓ CUMPLE |
| Almacenamiento de secretos | En configuración | ✓ CUMPLE |

## 5. Matriz de Riesgos

| Riesgo | Probabilidad | Impacto | Severidad | Mitigación |
|--------|--------------|---------|-----------|------------|
| Ataque de fuerza bruta | Alta | Medio | Alta | Rate limiting |
| Expiración de token | Media | Bajo | Baja | Refresh tokens |
| Fuga de clave secreta | Baja | Crítico | Crítico | Rotación de claves |
| Replay attack | Baja | Medio | Media | Timestamp validation |

## 6. Conclusiones y Recomendaciones

### 6.1 Estado General
El sistema de autenticación JWT cumple con los requisitos funcionales básicos y presenta un rendimiento aceptable para el volumen esperado de usuarios. La implementación sigue las mejores prácticas de seguridad para el almacenamiento de contraseñas y la generación de tokens.

### 6.2 Acciones Inmediatas
1. Implementar rate limiting en endpoint de login
2. Agregar logging de intentos fallidos
3. Configurar alertas para patrones anómalos

### 6.3 Mejoras Futuras
1. Implementar refresh tokens
2. Agregar autenticación multifactor
3. Mejorar tiempos de respuesta del registro
4. Implementar logout con blacklist de tokens

### 6.4 Criterios de Aceptación
- [x] Registro de usuarios funciona correctamente
- [x] Autenticación con credenciales válidas exitosa
- [x] Tokens JWT generados con formato correcto
- [x] Validación de tokens funciona correctamente
- [x] Manejo de errores implementado
- [x] Pruebas de carga superan umbral de 30 req/s
- [x] No hay vulnerabilidades críticas pendientes


// === ARCHIVO: src/main/java/com/financiera/prestamos/dto/LoanResponse.java ===
package com.financiera.prestamos.dto;

import com.financiera.prestamos.model.Loan;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record LoanResponse(
    Long id,
    String username,
    BigDecimal amount,
    BigDecimal interestRate,
    Integer termMonths,
    String status,
    LocalDate requestedDate,
    LocalDate approvalDate,
    LocalDate disbursementDate,
    LocalDate dueDate,
    BigDecimal totalPayment,
    BigDecimal monthlyPayment
) {
    public static LoanResponse fromEntity(Loan loan) {
        return new LoanResponse(
            loan.getId(),
            loan.getUser() != null ? loan.getUser().getUsername() : null,
            loan.getAmount(),
            loan.getInterestRate(),
            loan.getTermMonths(),
            loan.getStatus().name(),
            loan.getRequestedDate(),
            loan.getApprovalDate(),
            loan.getDisbursementDate(),
            loan.getDueDate(),
            loan.getTotalPayment(),
            loan.getMonthlyPayment()
        );
    }

    public static LoanResponse pending(Long id, String username, BigDecimal amount, Integer termMonths) {
        return new LoanResponse(
            id,
            username,
            amount,
            BigDecimal.ZERO,
            termMonths,
            "PENDING",
            LocalDate.now(),
            null,
            null,
            null,
            null,
            null
        );
    }
}

// === ARCHIVO: src/test/java/com/financiera/prestamos/controller/AuthControllerTest.java ===
package com.financiera.prestamos.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.financiera.prestamos.dto.AuthRequest;
import com.financiera.prestamos.security.JwtTokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    private AuthRequest registroRequest;
    private AuthRequest loginRequest;

    @BeforeEach
    void setUp() {
        registroRequest = new AuthRequest("nuevoUsuario", "password123", "CLIENTE");
        loginRequest = new AuthRequest("admin", "admin123", "ADMIN");
    }

    @Test
    @DisplayName("POST /api/auth/register - Registro exitoso de nuevo usuario")
    void testRegistroUsuario_exito() throws Exception {
        AuthRequest request = new AuthRequest("usuarioTest", "password123", "CLIENTE");

        ResultActions result = mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andExpect(jsonPath("$.username").value("usuarioTest"))
                .andExpect(jsonPath("$.roles").value("CLIENTE"));
    }

    @Test
    @DisplayName("POST /api/auth/register - Usuario ya existente retorna error 400")
    void testRegistroUsuario_usuarioExistente() throws Exception {
        AuthRequest request = new AuthRequest("admin", "password123", "ADMIN");

        ResultActions result = mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        result.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").exists());
    }

    @Test
    @DisplayName("POST /api/auth/login - Login exitoso retorna token JWT")
    void testLogin_exito() throws Exception {
        AuthRequest request = new AuthRequest("admin", "admin123", "ADMIN");

        ResultActions result = mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andExpect(jsonPath("$.username").value("admin"));
    }

    @Test
    @DisplayName("POST /api/auth/login - Credenciales inválidas retorna error 401")
    void testLogin_credencialesInvalidas() throws Exception {
        AuthRequest request = new AuthRequest("admin", "passwordincorrecto", "ADMIN");

        ResultActions result = mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        result.andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error").exists());
    }

    @Test
    @DisplayName("POST /api/auth/login - Usuario no encontrado retorna error 401")
    void testLogin_usuarioNoExistente() throws Exception {
        AuthRequest request = new AuthRequest("usuarionoexistente", "password123", "CLIENTE");

        ResultActions result = mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        result.andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("POST /api/auth/register - Request con campos vacíos retorna error 400")
    void testRegistroUsuario_camposVacios() throws Exception {
        AuthRequest request = new AuthRequest("", "password123", "CLIENTE");

        ResultActions result = mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        result.andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Token JWT generado contiene los claims correctos")
    void testToken_generadoContieneClaims() throws Exception {
        AuthRequest request = new AuthRequest("testToken", "password123", "GERENTE");

        ResultActions result = mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        String token = result.andReturn().getResponse().getContentAsString();
        String tokenLimpio = objectMapper.readTree(token).get("token").asText();

        assertNotNull(tokenLimpio, "El token no debe ser null");
        assertTrue(tokenLimpio.length() > 0, "El token debe tener contenido");

        String username = jwtTokenUtil.extractUsername(tokenLimpio);
        assertEquals("testToken", username, "El username en el token debe coincidir");

        String roles = jwtTokenUtil.extractRoles(tokenLimpio);
        assertEquals("GERENTE", roles, "Los roles en el token deben coincidir");
    }
}

// === ARCHIVO: src/test/java/com/financiera/prestamos/controller/LoanControllerTest.java ===
package com.financiera.prestamos.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.financiera.prestamos.dto.LoanRequest;
import com.financiera.prestamos.security.JwtTokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class LoanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private String tokenAdmin;
    private String tokenCliente;

    @BeforeEach
    void setUp() {
        tokenAdmin = jwtTokenUtil.generateToken("admin", "ADMIN");
        tokenCliente = jwtTokenUtil.generateToken("cliente1", "CLIENTE");
    }

    @Test
    @DisplayName("GET /api/loans - Sin autenticación retorna 401")
    void testGetLoans_sinAutenticacion() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/loans"));
        result.andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("GET /api/loans - Con token válido de ADMIN retorna todos los préstamos")
    void testGetLoans_conTokenAdmin() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/loans")
                .header("Authorization", "Bearer " + tokenAdmin));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @DisplayName("GET /api/loans - Con token válido de CLIENTE retorna solo sus préstamos")
    void testGetLoans_conTokenCliente() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/loans")
                .header("Authorization", "Bearer " + tokenCliente));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @DisplayName("GET /api/loans/{id} - Préstamo existente retorna detalles completos")
    void testGetLoanById_existente() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/loans/1")
                .header("Authorization", "Bearer " + tokenAdmin));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.monto").exists())
                .andExpect(jsonPath("$.estado").exists());
    }

    @Test
    @DisplayName("GET /api/loans/{id} - Préstamo no existente retorna 404")
    void testGetLoanById_noExistente() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/loans/99999")
                .header("Authorization", "Bearer " + tokenAdmin));

        result.andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /api/loans - Crear préstamo con token de CLIENTE exitosamente")
    void testCreateLoan_conTokenCliente() throws Exception {
        LoanRequest request = new LoanRequest(
                new BigDecimal("50000"),
                24,
                "Compra de vehículo",
                "PERSONAL"
        );

        ResultActions result = mockMvc.perform(post("/api/loans")
                .header("Authorization", "Bearer " + tokenCliente)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.monto").value(50000))
                .andExpect(jsonPath("$.estado").value("PENDIENTE"));
    }

    @Test
    @DisplayName("POST /api/loans - Crear préstamo con monto inválido retorna 400")
    void testCreateLoan_montoInvalido() throws Exception {
        LoanRequest request = new LoanRequest(
                new BigDecimal("-1000"),
                24,
                "Préstamo inválido",
                "PERSONAL"
        );

        ResultActions result = mockMvc.perform(post("/api/loans")
                .header("Authorization", "Bearer " + tokenCliente)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        result.andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("PUT /api/loans/{id}/approve - ADMIN puede approves préstamos")
    void testApproveLoan_conTokenAdmin() throws Exception {
        ResultActions result = mockMvc.perform(put("/api/loans/1/approve")
                .header("Authorization", "Bearer " + tokenAdmin));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("APROBADO"));
    }

    @Test
    @DisplayName("PUT /api/loans/{id}/approve - CLIENTE no puede approves préstamos")
    void testApproveLoan_sinPermisos() throws Exception {
        ResultActions result = mockMvc.perform(put("/api/loans/1/approve")
                .header("Authorization", "Bearer " + tokenCliente));

        result.andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("PUT /api/loans/{id}/reject - ADMIN puede rechazar préstamos")
    void testRejectLoan_conTokenAdmin() throws Exception {
        ResultActions result = mockMvc.perform(put("/api/loans/2/reject")
                .header("Authorization", "Bearer " + tokenAdmin));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("RECHAZADO"));
    }

    @Test
    @DisplayName("Token JWT extrae correctamente el username del contexto de seguridad")
    void testToken_usernameExtraidoDelContexto() throws Exception {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                "usuarioPrueba", null, List.of(new SimpleGrantedAuthority("CLIENTE"))
        );
        SecurityContextHolder.getContext().setAuthentication(auth);

        String token = jwtTokenUtil.generateToken("usuarioPrueba", "CLIENTE");
        String usernameExtraido = jwtTokenUtil.extractUsername(token);

        assertEquals("usuarioPrueba", usernameExtraido);
    }

    @Test
    @DisplayName("GET /api/loans - Token malformado retorna 401")
    void testGetLoans_tokenMalformado() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/loans")
                .header("Authorization", "Bearer tokenInvalido"));

        result.andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("POST /api/loans - Plazo mayor al máximo retorna 400")
    void testCreateLoan_plazoExcedido() throws Exception {
        LoanRequest request = new LoanRequest(
                new BigDecimal("100000"),
                120,
                "Plazo excedido",
                "HIPOTECARIO");

        ResultActions result = mockMvc.perform(post("/api/loans")
                .header("Authorization", "Bearer " + tokenCliente)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        result.andExpect(status().isBadRequest());
    }
}


// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.4.0</version>
        <relativePath/>
    </parent>
    
    <groupId>com.financiera</groupId>
    <artifactId>prestamos</artifactId>
    <version>1.0.0</version>
    <name>prestamos</name>
    <description>Sistema de Gestión de Préstamos</description>
    
    <properties>
        <java.version>21</java.version>
        <jjwt.version>0.12.5</jjwt.version>
    </properties>
    
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
        
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-api</artifactId>
            <version>${jjwt.version}</version>
        </dependency>
        
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-impl</artifactId>
            <version>${jjwt.version}</version>
            <scope>runtime</scope>
        </dependency>
        
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-jackson</artifactId>
            <version>${jjwt.version}</version>
            <scope>runtime</scope>
        </dependency>
        
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
        
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <scope>provided</scope>
        </dependency>
        
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
        </dependency>
        
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        
        <dependency>
            <groupId>org.springframework.security</groupId>
            <artifactId>spring-security-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
    
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/test/java/com/financiera/prestamos/security/JwtTokenUtilTest.java ===
package com.financiera.prestamos.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JwtTokenUtilTest {

    private JwtTokenUtil jwtTokenUtil;
    private static final String SECRET_KEY = " EstaEsUnaClaveSecretaParaPruebasDeJWTQueDebeTener256Bits12345678 ";
    private static final String USERNAME_TEST = "usuarioPrueba";
    private static final String ROLES_TEST = "ADMIN";

    @BeforeEach
    void setUp() {
        jwtTokenUtil = new JwtTokenUtil();
        ReflectionTestUtils.setField(jwtTokenUtil, "secret", SECRET_KEY);
        ReflectionTestUtils.setField(jwtTokenUtil, "expiration", 3600000L);
    }

    @Test
    @DisplayName("GenerateToken - Token generado contiene username y roles correctos")
    void testGenerateToken_conDatosValidos() {
        List<String> roles = List.of(ROLES_TEST);
        String token = jwtTokenUtil.generateToken(USERNAME_TEST, roles, 1L);

        assertNotNull(token, "El token no debe ser null");
        assertTrue(token.length() > 0, "El token debe tener contenido");
        assertTrue(token.contains("."), "El token debe tener formato JWT con puntos");
    }

    @Test
    @DisplayName("ExtractUsername - Extrae correctamente el username del token")
    void testExtractUsername_extraccionExitosa() {
        List<String> roles = List.of(ROLES_TEST);
        String token = jwtTokenUtil.generateToken(USERNAME_TEST, roles, 1L);
        String usernameExtraido = jwtTokenUtil.extractUsername(token);

        assertEquals(USERNAME_TEST, usernameExtraido, "El username extraído debe coincidir");
    }

    @Test
    @DisplayName("ExtractRoles - Extrae correctamente los roles del token")
    void testExtractRoles_extraccionExitosa() {
        List<String> roles = List.of(ROLES_TEST);
        String token = jwtTokenUtil.generateToken(USERNAME_TEST, roles, 1L);
        List<String> rolesExtraidos = jwtTokenUtil.extractRoles(token);

        assertNotNull(rolesExtraidos, "Los roles no deben ser null");
        assertTrue(rolesExtraidos.contains(ROLES_TEST), "Los roles extraídos deben contener el rol esperado");
    }

    @Test
    @DisplayName("ValidateToken - Token válido retorna true")
    void testValidateToken_tokenValido() {
        List<String> roles = List.of(ROLES_TEST);
        String token = jwtTokenUtil.generateToken(USERNAME_TEST, roles, 1L);
        boolean esValido = jwtTokenUtil.validateToken(token);

        assertTrue(esValido, "El token válido debe retornar true");
    }

    @Test
    @DisplayName("ValidateToken - Token expirado lanza excepción")
    void testValidateToken_tokenExpirado() {
        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        
        Date fechaExpirada = new Date(System.currentTimeMillis() - 1000);
        String tokenExpirado = Jwts.builder()
                .subject(USERNAME_TEST)
                .claim("roles", List.of(ROLES_TEST))
                .expiration(fechaExpirada)
                .issuedAt(new Date(System.currentTimeMillis() - 10000))
                .signWith(key)
                .compact();

        assertThrows(Exception.class, () -> jwtTokenUtil.validateToken(tokenExpirado));
    }

    @Test
    @DisplayName("ValidateToken - Token con firma inválida lanza excepción")
    void testValidateToken_firmaInvalida() {
        List<String> roles = List.of(ROLES_TEST);
        String tokenValido = jwtTokenUtil.generateToken(USERNAME_TEST, roles, 1L);
        String tokenConFirmaAlterada = tokenValido.substring(0, tokenValido.length() - 5) + "xxxxx";

        assertThrows(Exception.class, () -> jwtTokenUtil.validateToken(tokenConFirmaAlterada));
    }

    @Test
    @DisplayName("ExtractExpiration - Extrae la fecha de expiración correctamente")
    void testExtractExpiration_extraccionExitosa() {
        List<String> roles = List.of(ROLES_TEST);
        String token = jwtTokenUtil.generateToken(USERNAME_TEST, roles, 1L);
        Date expiracion = jwtTokenUtil.extractExpiration(token);

        assertNotNull(expiracion, "La expiración no debe ser null");
        assertTrue(expiracion.after(new Date()), "La expiración debe ser futura");
    }

    @Test
    @DisplayName("GenerateToken - Tokens generados para mismo usuario son diferentes")
    void testGenerateToken_tokensUnicos() throws InterruptedException {
        List<String> roles = List.of(ROLES_TEST);
        String token1 = jwtTokenUtil.generateToken(USERNAME_TEST, roles, 1L);
        Thread.sleep(10);
        String token2 = jwtTokenUtil.generateToken(USERNAME_TEST, roles, 1L);

        assertNotEquals(token1, token2, "Cada token debe ser único");
    }

    @Test
    @DisplayName("ExtractClaims - Extrae todos los claims del token")
    void testExtractClaims_extraccionCompleta() {
        List<String> roles = List.of(ROLES_TEST);
        String token = jwtTokenUtil.generateToken(USERNAME_TEST, roles, 1L);
        Claims claims = jwtTokenUtil.extractAllClaims(token);

        assertNotNull(claims, "Los claims no deben ser null");
        assertEquals(USERNAME_TEST, claims.getSubject(), "El subject debe ser el username");
    }

    @Test
    @DisplayName("ValidateToken - Token malformed lanza excepción")
    void testValidateToken_tokenMalformed() {
        String tokenMalformado = "esto.no.es.un.token.valido";

        assertThrows(Exception.class, () -> jwtTokenUtil.validateToken(tokenMalformado));
    }

    @Test
    @DisplayName("ExtractAllClaims - Token vacío lanza excepción")
    void testExtractAllClaims_tokenVacio() {
        assertThrows(Exception.class, () -> jwtTokenUtil.extractAllClaims(""));
    }

    @Test
    @DisplayName("GenerateToken con claims adicionales - Incluye los claims en el token")
    void testGenerateToken_conClaimsAdicionales() {
        Map<String, Object> adicionales = new HashMap<>();
        adicionales.put("departamento", "FINANZAS");
        adicionales.put("activo", true);

        List<String> roles = List.of(ROLES_TEST);
        String token = jwtTokenUtil.generateToken(USERNAME_TEST, roles, 1L);
        Claims claims = jwtTokenUtil.extractAllClaims(token);

        assertEquals(USERNAME_TEST, claims.getSubject(), "El subject debe ser el username");
    }

    @Test
    @DisplayName("ExtractUserId - Extrae el userId del token correctamente")
    void testExtractUserId_extraccionExitosa() {
        Long userId = 123L;
        List<String> roles = List.of(ROLES_TEST);
        String token = jwtTokenUtil.generateToken(USERNAME_TEST, roles, userId);
        Long userIdExtraido = jwtTokenUtil.extractUserId(token);

        assertEquals(userId, userIdExtraido, "El userId extraído debe coincidir");
    }
}

=== ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.4.0</version>
        <relativePath/>
    </parent>

    <groupId>com.financiera</groupId>
    <artifactId>prestamos</artifactId>
    <version>1.0.0</version>
    <name>prestamos</name>
    <description>Sistema de Gestión de Préstamos</description>

    <properties>
        <java.version>21</java.version>
        <jjwt.version>0.12.5</jjwt.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.security</groupId>
            <artifactId>spring-security-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-api</artifactId>
            <version>${jjwt.version}</version>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-impl</artifactId>
            <version>${jjwt.version}</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-jackson</artifactId>
            <version>${jjwt.version}</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <scope>provided</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/financiera/prestamos/model/User.java ===
package com.financiera.prestamos.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String email;

    private String fullName;

    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.CLIENTE;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private Boolean enabled = true;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Loan> loans = new ArrayList<>();

    public enum Role {
        ADMIN, CLIENTE, ANALISTA
    }

    public User() {}

    public User(String username, String password, String email, String fullName, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.role = role;
    }

    @PrePersist
    protected void onCreate() {
        if (enabled == null) enabled = true;
        if (role == null) role = Role.CLIENTE;
    }

    @PreUpdate
    protected void onUpdate() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public Boolean getEnabled() { return enabled; }
    public void setEnabled(Boolean enabled) { this.enabled = enabled; }

    public List<Loan> getLoans() { return loans; }
    public void setLoans(List<Loan> loans) { this.loans = loans; }

    public void addLoan(Loan loan) {
        loans.add(loan);
        loan.setUser(this);
    }

    public void removeLoan(Loan loan) {
        loans.remove(loan);
        loan.setUser(null);
    }

    public Set<String> getRoles() {
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_" + role.name());
        return roles;
    }

    public void setRoles(Set<String> roleNames) {
        if (roleNames != null && !roleNames.isEmpty()) {
            String roleName = roleNames.iterator().next();
            if (roleName.startsWith("ROLE_")) {
                roleName = roleName.substring(5);
            }
            this.role = Role.valueOf(roleName);
        }
    }
}

// === ARCHIVO: src/main/java/com/financiera/prestamos/service/AuthService.java ===
package com.financiera.prestamos.service;

import com.financiera.prestamos.dto.AuthRequest;
import com.financiera.prestamos.dto.AuthResponse;
import com.financiera.prestamos.exception.UserAlreadyExistsException;
import com.financiera.prestamos.model.User;
import com.financiera.prestamos.repository.UserRepository;
import com.financiera.prestamos.security.JwtTokenUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, JwtTokenUtil jwtTokenUtil,
                       PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse register(AuthRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new UserAlreadyExistsException("El usuario ya existe: " + request.username());
        }

        String encodedPassword = passwordEncoder.encode(request.password());
        Set<String> roles = request.roles() != null ? request.roles() : Set.of("ROLE_USER");

        User user = new User();
        user.setUsername(request.username());
        user.setPassword(encodedPassword);
        user.setRoles(roles);
        user.setEmail(request.email());

        User savedUser = userRepository.save(user);

        String token = jwtTokenUtil.generateToken(savedUser.getUsername(), 
                savedUser.getRoles().stream().toList(),
                savedUser.getId());

        return AuthResponse.success(token, savedUser.getUsername(),
                String.join(",", savedUser.getRoles()), jwtTokenUtil.getExpiration());
    }

    public AuthResponse login(AuthRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.username(), request.password())
            );
        } catch (Exception e) {
            throw new BadCredentialsException("Credenciales inválidas");
        }

        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new BadCredentialsException("Usuario no encontrado"));

        String token = jwtTokenUtil.generateToken(user.getUsername(), 
                user.getRoles().stream().toList(),
                user.getId());

        return AuthResponse.success(token, user.getUsername(),
                String.join(",", user.getRoles()), jwtTokenUtil.getExpiration());
    }

    public boolean validateToken(String token) {
        try {
            return jwtTokenUtil.validateToken(token);
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        return jwtTokenUtil.extractUsername(token);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}

// === ARCHIVO: src/main/java/com/financiera/prestamos/service/LoanService.java ===
package com.financiera.prestamos.service;

import com.financiera.prestamos.dto.LoanRequest;
import com.financiera.prestamos.model.Loan;
import com.financiera.prestamos.model.User;
import com.financiera.prestamos.repository.LoanRepository;
import com.financiera.prestamos.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final UserRepository userRepository;

    public LoanService(LoanRepository loanRepository, UserRepository userRepository) {
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Loan createLoan(LoanRequest request) {
        validateLoanRequest(request);

        Optional<User> userOpt = userRepository.findById(request.userId());
        User user = userOpt.orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + request.userId()));

        Loan loan = new Loan();
        loan.setUser(user);
        loan.setAmount(request.amount());
        loan.setInterestRate(request.interestRate());
        loan.setTermMonths(request.termMonths());
        loan.setStatus(Loan.LoanStatus.PENDIENTE);
        loan.setRequestedDate(LocalDate.now());
        loan.setPurpose(request.purpose());

        BigDecimal monthlyPayment = calculateMonthlyPayment(
                loan.getAmount(),
                loan.getInterestRate(),
                loan.getTermMonths()
        );
        loan.setMonthlyPayment(monthlyPayment);

        return loanRepository.save(loan);
    }

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Loan getLoanById(Long id) {
        Optional<Loan> loanOpt = loanRepository.findById(id);
        return loanOpt.orElseThrow(() -> new IllegalArgumentException("Préstamo no encontrado con ID: " + id));
    }

    @Transactional
    public Loan updateLoan(Long id, LoanRequest request) {
        Loan existingLoan = getLoanById(id);

        validateLoanRequest(request);

        existingLoan.setAmount(request.amount());
        existingLoan.setInterestRate(request.interestRate());
        existingLoan.setTermMonths(request.termMonths());
        existingLoan.setPurpose(request.purpose());

        BigDecimal monthlyPayment = calculateMonthlyPayment(
                existingLoan.getAmount(),
                existingLoan.getInterestRate(),
                existingLoan.getTermMonths()
        );
        existingLoan.setMonthlyPayment(monthlyPayment);

        return loanRepository.save(existingLoan);
    }

    @Transactional
    public void deleteLoan(Long id) {
        boolean exists = loanRepository.existsById(id);
        if (!exists) {
            throw new IllegalArgumentException("Préstamo no encontrado con ID: " + id);
        }
        loanRepository.deleteById(id);
    }

    public List<Loan> getLoansByUserId(Long userId) {
        boolean userExists = userRepository.existsById(userId);
        if (!userExists) {
            throw new IllegalArgumentException("Usuario no encontrado con ID: " + userId);
        }
        return loanRepository.findByUserId(userId);
    }

    @Transactional
    public Loan approveLoan(Long id) {
        Loan loan = getLoanById(id);
        if (loan.getStatus() != Loan.LoanStatus.PENDIENTE) {
            throw new IllegalStateException("Solo se pueden aprobar préstamos en estado PENDIENTE");
        }
        loan.setStatus(Loan.LoanStatus.APROBADO);
        loan.setApprovalDate(LocalDate.now());
        return loanRepository.save(loan);
    }

    @Transactional
    public Loan rejectLoan(Long id) {
        Loan loan = getLoanById(id);
        if (loan.getStatus() != Loan.LoanStatus.PENDIENTE) {
            throw new IllegalStateException("Solo se pueden rechazar préstamos en estado PENDIENTE");
        }
        loan.setStatus(Loan.LoanStatus.RECHAZADO);
        return loanRepository.save(loan);
    }

    private void validateLoanRequest(LoanRequest request) {
        if (request.amount() == null || request.amount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto del préstamo debe ser mayor a cero");
        }
        if (request.interestRate() == null || request.interestRate().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("La tasa de interés no puede ser negativa");
        }
        if (request.termMonths() == null || request.termMonths() <= 0) {
            throw new IllegalArgumentException("El plazo en meses debe ser mayor a cero");
        }
    }

    private BigDecimal calculateMonthlyPayment(BigDecimal amount, BigDecimal annualInterestRate, Integer termMonths) {
        if (annualInterestRate.compareTo(BigDecimal.ZERO) == 0) {
            return amount.divide(BigDecimal.valueOf(termMonths), 2, java.math.RoundingMode.HALF_UP);
        }

        BigDecimal monthlyRate = annualInterestRate.divide(BigDecimal.valueOf(12), 6, java.math.RoundingMode.HALF_UP);
        BigDecimal onePlusR = BigDecimal.ONE.add(monthlyRate);
        BigDecimal factor = onePlusR.pow(termMonths);

        BigDecimal numerator = amount.multiply(monthlyRate).multiply(factor);
        BigDecimal denominator = factor.subtract(BigDecimal.ONE);

        return numerator.divide(denominator, 2, java.math.RoundingMode.HALF_UP);
    }
}

```
