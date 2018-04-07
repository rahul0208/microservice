package person;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableAutoConfiguration
@EnableSwagger2
@SpringBootApplication
public class PersonWriterApplication {

	public static final String topicExchangeName = "person-data-exchange";

	static final String queueName = "register-person";

	@Bean
	RabbitTemplate buildTemplate(ConnectionFactory connectionFactory){
		return new RabbitTemplate(connectionFactory);
	}

	public static void main(String[] args) {
		SpringApplication.run(PersonWriterApplication.class, args);
	}

}
