package com.uab.taller.store.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(apiInfo());

    }
    private Info apiInfo(){
        return new Info()
                .title("Banking System API")
                .version("1.0.0")
                .description("""
API de un sistema bancario para gestión de usuarios, perfiles y cuentas.
Permite realizar operaciones CRUD sobre entidades clave como Usuarios y Cuentas. Desarrollado con Spring Boot (Java).

**Funciones disponibles:**
- Crear, Leer, Actualizar y Eliminar Usuarios
- Crear, Leer, Actualizar y Eliminar Cuentas


""")
                .contact(new Contact()
                        .name("Dayana Hassel Cano")
                        .email("hasselc081@gmail.com")
                        .url("https://github.com/MaybeDH"))
                .license(new License()
                        .name("Apache 2.0")
                        .url("https://www.apache.org/licenses/LICENSE-2.0"));


    }
}
