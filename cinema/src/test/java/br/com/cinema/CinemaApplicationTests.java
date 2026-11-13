package br.com.cinema;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "cinema.console.enabled=false")
class CinemaApplicationTests {

	@Test
	void contextLoads() {
	}

}
