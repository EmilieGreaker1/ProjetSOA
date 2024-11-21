package fr.insa.ms.signUP;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(properties = {
		"spring.profiles.active=test"
})
class SignUpApplicationTests {
	@Test
	void contextLoads() {
	}
}