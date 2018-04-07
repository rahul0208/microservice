package payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableAutoConfiguration
@EnableSwagger2
@SpringBootApplication
public class IdGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdGeneratorApplication.class, args);
	}

}
