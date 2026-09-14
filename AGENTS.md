# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Implementación de autenticación JWT en una API REST**.

| | |
|---|---|
| Tema | Autenticación JWT en API REST |
| Nivel | junior-l2 |
| Chapter | Generico |
| Especialidad | Inferido del contexto |
| Stack | Java / Spring Boot 3.4 |
| Patron arquitectonico | capas estándar con seguridad en filtro dedicado |
| Tiempo estimado | 8 horas |

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `el comando de build o arranque canonico del stack elegido` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `el comando de build o arranque canonico del stack elegido` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Diseño del mecanismo de autenticación**: Documento de diseño que describe el mecanismo de autenticación JWT, incluyendo actores, reglas de negocio, estados, transiciones, validaciones y edge cases.
- **Fase 2 — Implementación del mecanismo de autenticación**: Código implementado que permite el registro de usuarios y la autenticación JWT en la API REST de préstamos.
- **Fase 3 — Pruebas y optimización del mecanismo de autenticación**: Reporte de pruebas que documenta los resultados de las pruebas de funcionalidad, carga y seguridad, junto con el código optimizado del mecanismo de autenticación.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Lo que falta y tenes que completar

### 1. Boilerplate del stack (1)

Sin esto el proyecto no compila ni arranca. **Es tu trabajo crearlo**, y no toca nada de lo pedagogico: es andamiaje del stack.

- [ ] **Punto de entrada del stack elegido** — Sin un punto de entrada reconocible, el runtime no tiene por donde arrancar la aplicacion.

### 2. Referencias colgando (21)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/com/financiera/prestamos/service/LoanService.java` — `LoanStatus`
      LoanStatus se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.financiera.prestamos.model.LoanStatus.
- [ ] `src/main/java/com/financiera/prestamos/security/JwtAuthenticationFilter.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/financiera/prestamos/security/JwtTokenUtil.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/financiera/prestamos/controller/AuthController.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/financiera/prestamos/model/User.java` — `Role.name`
      Se invoca `name` sobre `Role`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financiera/prestamos/service/AuthService.java` — `UserRepository.save`
      Se invoca `save` sobre `UserRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financiera/prestamos/service/LoanService.java` — `UserRepository.findById`
      Se invoca `findById` sobre `UserRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financiera/prestamos/service/LoanService.java` — `LoanRepository.save`
      Se invoca `save` sobre `LoanRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financiera/prestamos/service/LoanService.java` — `LoanRepository.findAll`
      Se invoca `findAll` sobre `LoanRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financiera/prestamos/service/LoanService.java` — `LoanRepository.findById`
      Se invoca `findById` sobre `LoanRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financiera/prestamos/service/LoanService.java` — `LoanRepository.existsById`
      Se invoca `existsById` sobre `LoanRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financiera/prestamos/service/LoanService.java` — `LoanRepository.deleteById`
      Se invoca `deleteById` sobre `LoanRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/financiera/prestamos/service/LoanService.java` — `UserRepository.existsById`
      Se invoca `existsById` sobre `UserRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/financiera/prestamos/service/AuthServiceTest.java` — `User.setActive`
      Se invoca `setActive` sobre `User`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/financiera/prestamos/service/AuthServiceTest.java` — `UserRepository.save`
      Se invoca `save` sobre `UserRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/financiera/prestamos/service/AuthServiceTest.java` — `JwtTokenUtil.getExpirationMs`
      Se invoca `getExpirationMs` sobre `JwtTokenUtil`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/financiera/prestamos/service/AuthServiceTest.java` — `AuthResponse.token`
      Se invoca `token` sobre `AuthResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/financiera/prestamos/service/AuthServiceTest.java` — `AuthResponse.username`
      Se invoca `username` sobre `AuthResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/financiera/prestamos/service/AuthServiceTest.java` — `AuthResponse.roles`
      Se invoca `roles` sobre `AuthResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/financiera/prestamos/service/AuthServiceTest.java` — `AuthService.authenticate`
      Se invoca `authenticate` sobre `AuthService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/financiera/prestamos/service/AuthServiceTest.java` — `AuthResponse.expirationMs`
      Se invoca `expirationMs` sobre `AuthResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

### Presentes (27)

- `pom.xml`
- `src/main/java/com/financiera/prestamos/PrestamosApplication.java`
- `src/main/resources/application.properties`
- `src/main/java/com/financiera/prestamos/dto/AuthRequest.java`
- `src/main/java/com/financiera/prestamos/dto/AuthResponse.java`
- `src/main/java/com/financiera/prestamos/dto/LoanRequest.java`
- `src/main/java/com/financiera/prestamos/dto/LoanResponse.java`
- `src/main/java/com/financiera/prestamos/model/User.java`
- `src/main/java/com/financiera/prestamos/model/Loan.java`
- `src/main/java/com/financiera/prestamos/repository/UserRepository.java`
- `src/main/java/com/financiera/prestamos/repository/LoanRepository.java`
- `src/main/java/com/financiera/prestamos/config/SecurityConfig.java`
- `src/main/java/com/financiera/prestamos/security/JwtAuthenticationFilter.java`
- `src/main/java/com/financiera/prestamos/security/JwtTokenUtil.java`
- `src/main/java/com/financiera/prestamos/controller/AuthController.java`
- `src/main/java/com/financiera/prestamos/controller/LoanController.java`
- `src/main/java/com/financiera/prestamos/service/AuthService.java`
- `src/main/java/com/financiera/prestamos/service/LoanService.java`
- `src/main/java/com/financiera/prestamos/exception/InvalidJwtException.java`
- `src/main/java/com/financiera/prestamos/exception/UserAlreadyExistsException.java`
- `src/main/java/com/financiera/prestamos/exception/GlobalExceptionHandler.java`
- `src/test/java/com/financiera/prestamos/controller/AuthControllerTest.java`
- `src/test/java/com/financiera/prestamos/controller/LoanControllerTest.java`
- `src/test/java/com/financiera/prestamos/security/JwtTokenUtilTest.java`
- `src/test/java/com/financiera/prestamos/service/AuthServiceTest.java`
- `docs/diseno_autenticacion_jwt.md`
- `docs/reporte_pruebas.md`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/financiera/prestamos`
- `src/main/java/com/financiera/prestamos/config`
- `src/main/java/com/financiera/prestamos/controller`
- `src/main/java/com/financiera/prestamos/dto`
- `src/main/java/com/financiera/prestamos/exception`
- `src/main/java/com/financiera/prestamos/model`
- `src/main/java/com/financiera/prestamos/repository`
- `src/main/java/com/financiera/prestamos/security`
- `src/main/java/com/financiera/prestamos/service`
- `src/main/java/com/financiera/prestamos/util`
- `src/test/java/com/financiera/prestamos`

## Verificacion

```bash
el comando de build o arranque canonico del stack elegido
```

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **capas estándar con seguridad en filtro dedicado**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Brecha que el reto ataca: Implementar autenticacion JWT en una API REST con Spring Security

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
