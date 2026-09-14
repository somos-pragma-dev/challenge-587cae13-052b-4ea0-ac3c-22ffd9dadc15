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