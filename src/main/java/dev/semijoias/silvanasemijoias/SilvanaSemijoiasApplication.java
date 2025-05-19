package dev.semijoias.silvanasemijoias;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SilvanaSemijoiasApplication {

    public static void main(String[] args) {
        // Carrega as variáveis de ambiente ANTES de tudo
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

        // Define as variáveis pro sistema
        System.setProperty("DB_URL", dotenv.get("DB_URL"));
        System.setProperty("DB_USER", dotenv.get("DB_USER"));
        System.setProperty("DB_PASS", dotenv.get("DB_PASS"));

        SpringApplication.run(SilvanaSemijoiasApplication.class, args);
    }

}
