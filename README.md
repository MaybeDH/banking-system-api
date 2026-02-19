# 🏦 Banking System API

Un sistema de gestión bancaria construido con **Spring Boot 3** que proporciona una API RESTful para operaciones bancarias, administración de usuarios, cuentas, transacciones y beneficiarios.

---

## 📖 Descripción General

Este proyecto es una **plataforma bancaria digital** que gestiona:
- Registro y login de usuarios (sin encriptación)
- Administración de cuentas bancarias
- Transacciones entre cuentas con validaciones
- Gestión de perfiles y roles
- Sistema de beneficiarios para transferencias rápidas

El sistema utiliza una arquitectura en capas con patrones de diseño como **Use Cases** e **Inyección de Dependencias**.

---

## ✨ Características Principales

### 1. **Gestión de Usuarios**
   - Registro de usuarios con email y contraseña
   - Login básico (sin cifrado, sin JWT)
   - Búsqueda de usuarios por email o ID
   - Actualización y eliminación de usuarios
   - Asociación a roles (solo registro, sin control en endpoints)

### 2. **Administración de Cuentas**
   - Creación de múltiples cuentas por usuario
   - Tipos de cuentas personalizables
   - Gestión de saldos y actualizaciones
   - Consulta de cuentas por usuario
   - Número de cuenta único

### 3. **Transacciones Bancarias**
   - Transferencias entre cuentas con validaciones
   - Retiros y depósitos
   - Validación de saldo suficiente
   - Validación de moneda (no permite transferencias entre monedas diferentes)
   - Historial de transacciones
   - Validación: no permite transferencias a la misma cuenta

### 4. **Beneficiarios**
   - Registro de cuentas beneficiarias
   - Aliases personalizados para beneficiarios
   - Consulta de beneficiarios por usuario

### 5. **Perfiles**
   - Información adicional del usuario (nombre, apellido, cédula, teléfono, dirección)
   - Relación 1:1 con Usuario
   - Auditoría de cambios (fecha y usuario)

### 6. **Auditoría**
   - Registro de fecha de creación y usuario
   - Registro de fecha de modificación y usuario
   - Flag de eliminación lógica (soft delete)

---

## 🛠️ Tecnologías Utilizadas

| Tecnología | Versión | Descripción |
|------------|---------|------------|
| **Java** | 17 | Lenguaje de programación principal |
| **Spring Boot** | 3.4.3 | Framework web e inyección de dependencias |
| **Spring Data JPA** | 3.4.3 | ORM para manejo de datos |
| **MySQL** | 8+ | Base de datos relacional |
| **Lombok** | Latest | Generación automática de getters/setters |
| **SpringDoc OpenAPI** | 2.4.0 | Documentación automática con Swagger/OpenAPI |
| **Maven** | 3.8+ | Gestor de dependencias y construcción |

**Nota:** No incluye Spring Security, ni usa cifrado de contraseñas o tokens JWT.

---

## 🏗️ Arquitectura

El proyecto sigue una **arquitectura en capas** con separación clara de responsabilidades:

```
┌─────────────────────────────────────┐
│      CONTROLADORES (REST API)       │
├─────────────────────────────────────┤
│      USE CASES (Lógica de Negocio)  │
├─────────────────────────────────────┤
│    SERVICIOS (Implementación)       │
├─────────────────────────────────────┤
│   REPOSITORIOS (Acceso a Datos)     │
├─────────────────────────────────────┤
│   BASE DE DATOS (MySQL)             │
└─────────────────────────────────────┘
```
---

---

## 📋 Requisitos Previos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

- **Java 17 o superior**
- **Maven 3.8 o superior**
- **MySQL 8.0 o superior**
- **Git** (opcional, para clonar el repositorio)

### Verificar versiones:
```bash
java -version
mvn -version
mysql --version
```

---

## 🚀 Instalación y Configuración

### 1. **Clonar el Repositorio**
```bash
git clone <url-del-repositorio>
cd banking-system-api
```

### 2. **Crear la Base de Datos MySQL**
```sql
CREATE DATABASE testdb;

-- Usa la base de datos
USE testdb;

-- Las tablas se crearán automáticamente por Hibernate
```

### 3. **Configurar la Conexión a Base de Datos**

Modifica [src/main/resources/application.properties](src/main/resources/application.properties):

```properties
# Conexión a MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/testdb
spring.datasource.username=root
spring.datasource.password=1234
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate
spring.jpa.database=mysql
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
```

**Nota:** Ajusta `spring.datasource.password` según tu contraseña de MySQL.

### 4. **Instalar Dependencias**
```bash
mvn clean install
```

---

## ▶️ Ejecución

### Opción 1: Desde Maven (línea de comandos)
```bash
mvn spring-boot:run
```

### Opción 2: Compilar y ejecutar JAR
```bash
mvn clean package
java -jar target/store-0.0.1-SNAPSHOT.jar
```

### Opción 3: Desde un IDE (IntelliJ IDEA, Eclipse, VS Code)
1. Abre el proyecto en tu IDE
2. Ejecuta `StoreApplication.java`
3. La aplicación se iniciará en `http://localhost:8080`

---

## 📡 API Endpoints

### Documentación Interactiva
Una vez ejecutada la aplicación, accede a:
- **Swagger UI:** http://localhost:8080/swagger-ui.html
- **OpenAPI JSON:** http://localhost:8080/v3/api-docs

## 🤝 Contribuciones

Este es un proyecto académico. Las contribuciones y sugerencias son bienvenidas.

---

## 📄 Licencia

Este proyecto se proporciona con fines educativos.

---

## 📞 Contacto y Soporte

Para preguntas o soporte, contacta al equipo de desarrollo o abre un issue en el repositorio.

---

## 📚 Referencias y Recursos

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [SpringDoc OpenAPI](https://springdoc.org/)
- [Maven Documentation](https://maven.apache.org/)
- [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/)

---

## 👤 Autor

**Dayana Hassel Cano**
- Email: hasselc081@gmail.com
- GitHub: [@MaybeDH](https://github.com/MaybeDH)

---