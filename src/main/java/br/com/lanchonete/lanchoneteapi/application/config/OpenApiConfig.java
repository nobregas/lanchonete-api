package br.com.lanchonete.lanchoneteapi.application.config;


import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Lanchonete API")
                        .description("Api para lanchonete")
                        .contact(new Contact().name("Gabriel Nóbrega").email("gabrielnobregasantos2@gmail.com"))
                        .version("1.0.0"));
    }
}
