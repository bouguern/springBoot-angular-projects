package med.bouguern.head;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@EntityScan(
			basePackageClasses = { SchedulerSpringBootApplication.class, Jsr310JpaConverters.class}
		)

@SpringBootApplication
public class SchedulerSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchedulerSpringBootApplication.class, args);
	}

}
