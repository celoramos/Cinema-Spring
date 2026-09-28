package br.com.cinema;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CinemaApplication implements CommandLineRunner {
	public static void main(String[] args) {
		// a rota IPv4 até a OMDb (Cloudflare) está dando timeout nesta rede; o IPv6 responde normalmente
		System.setProperty("java.net.preferIPv6Addresses", "true");
		SpringApplication.run(CinemaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Iniciando a aplicação...");
		var Principal = new br.com.cinema.principal.Principal();
		Principal.exibirMenu();

	}
}


