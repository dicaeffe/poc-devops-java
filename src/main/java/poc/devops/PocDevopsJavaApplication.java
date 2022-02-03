package poc.devops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
/* The @EnableScheduling annotation ensures that a background task executor is created. Without it, nothing gets scheduled. */
@EnableScheduling
public class PocDevopsJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocDevopsJavaApplication.class, args);
	}

}
