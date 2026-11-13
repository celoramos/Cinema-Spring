package br.com.cinema;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CinemaApplication {
	public static void main(String[] args) {
		System.setProperty("java.net.preferIPv6Addresses", "true");
		SpringApplication.run(CinemaApplication.class, args);
	}

	@Bean
	@ConditionalOnProperty(name = "cinema.console.enabled", havingValue = "true", matchIfMissing = true)
	CommandLineRunner consoleRunner() {
		return args -> {
			System.out.println("Iniciando a aplicação...");
			var principal = new br.com.cinema.principal.Principal();
			principal.exibirMenu();
		};
	}
}

