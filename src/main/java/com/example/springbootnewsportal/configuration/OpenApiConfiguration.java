package com.example.springbootnewsportal.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI openApiDescription() {
        Server localhostServer = new Server();
        localhostServer.setUrl("http://localhost:8889");
        localhostServer.setDescription("Local env");
        Server productionServer = new Server();
        productionServer.setUrl("https://set.prod.url");
        productionServer.setDescription("Production env");
        Contact contact = new Contact();
        contact.setName("alex-lib");
        contact.setEmail("docode@inbox.ru");
        contact.setUrl("http://localhost:8889");
        Info info = new Info()
                .title("News Portal API")
                .version("1.0")
                .description("API for managing news")
                .contact(contact);
        return new OpenAPI().info(info).servers(List.of(localhostServer, productionServer));
    }
}