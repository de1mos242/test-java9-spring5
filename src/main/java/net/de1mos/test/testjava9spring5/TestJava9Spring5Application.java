package net.de1mos.test.testjava9spring5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class TestJava9Spring5Application {

	private Logger LOGGER = Logger.getLogger(getClass().getName());

	public static void main(String[] args) {
		SpringApplication.run(TestJava9Spring5Application.class, args);
	}
}
