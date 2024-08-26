package br.com.almavivasolution.integrador;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.almavivasolutions.integrador.IntegradorApplication;

@SpringBootTest(classes = IntegradorApplication.class)
@ActiveProfiles("test")
class IntegradorApplicationTests {

	@Test
	void contextLoads() {
	}

}
