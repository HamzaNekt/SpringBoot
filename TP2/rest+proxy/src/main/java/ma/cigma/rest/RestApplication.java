package ma.cigma.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootApplication scanne le classpath, détecte les
 * déppendance puis effectue automatiquement
 * toutes les configurations nécessaires.
 */


@SpringBootApplication
public class RestApplication {

	public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
	}

}
