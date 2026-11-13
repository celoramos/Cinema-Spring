package br.com.cinema;

import br.com.cinema.principal.Principal;
import br.com.cinema.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "br.com.cinema.repository")
@EntityScan(basePackages = "br.com.cinema.model")

public class CinemaApplication implements CommandLineRunner {
	private final SerieRepository repositorio;

	public CinemaApplication(SerieRepository repositorio) {
		this.repositorio = repositorio;
	}

	public static void main(String[] args) {
		SpringApplication.run(CinemaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repositorio);
		principal.exibeMenu();
	}
}
