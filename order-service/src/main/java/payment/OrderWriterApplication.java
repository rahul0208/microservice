package payment;

import brave.sampler.Sampler;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.sleuth.zipkin2.ZipkinProperties;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableHystrixDashboard
@EnableHystrix
@EnableAutoConfiguration
@EnableSwagger2
@SpringBootApplication
public class OrderWriterApplication {

	@Bean
	RabbitTemplate buildTemplate(ConnectionFactory connectionFactory){
		return new RabbitTemplate(connectionFactory);
	}

	public static void main(String[] args) {
		SpringApplication.run(OrderWriterApplication.class, args);
	}

}
