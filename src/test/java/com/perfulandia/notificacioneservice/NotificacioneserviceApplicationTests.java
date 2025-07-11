package com.perfulandia.notificacioneservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
    // H2 en memoria para JPA
    "spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1",
    "spring.datasource.driver-class-name=org.h2.Driver",
    "spring.datasource.username=sa",
    "spring.datasource.password=",
    "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
    "spring.jpa.hibernate.ddl-auto=create-drop",
    // Desactivar SpringDoc/OpenAPI en tests para no cargar el proveedor HATEOAS
    "springdoc.api-docs.enabled=false",
    "springdoc.swagger-ui.enabled=false"
})
class NotificacioneserviceApplicationTests {

    @Test
    void contextLoads() {
        // El contexto ahora arranca sin intentar inicializar SpringDoc/HATEOAS
    }
}
