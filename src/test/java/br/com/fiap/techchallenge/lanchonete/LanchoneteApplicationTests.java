package br.com.fiap.techchallenge.lanchonete;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class LanchoneteApplicationTests {

	@Test
	void fakeTest() {
		Assertions.assertTrue(true);
	}

}
