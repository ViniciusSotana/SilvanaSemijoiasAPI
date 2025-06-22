package dev.semijoias.silvanasemijoias.config; // Mude para o seu pacote de configuração

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class CorsConfiguration {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Aplica a configuração a todos os endpoints
                        .allowedOrigins("http://localhost:3000") // Permite requisições desta origem
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT") // Permite estes métodos HTTP
                        .allowedHeaders("*")
                        .allowCredentials(true)
                ;
            }
        };
    }
}

