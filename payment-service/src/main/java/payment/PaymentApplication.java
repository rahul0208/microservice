package payment;

import domain.Constants;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableAutoConfiguration
@EnableSwagger2
@SpringBootApplication
public class PaymentApplication {

	@Bean
	Queue queue() {
		return new Queue(Constants.paymentQueueName, true);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(Constants.orderExchangeName);
	}

	@Bean
	Object binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(Constants.paymentRouteKey+"#");
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
											 MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(Constants.paymentQueueName);
		container.setMessageListener(listenerAdapter);
		return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(OrderRegistry receiver) {
		return new MessageListenerAdapter(receiver, "register");
	}


	public static void main(String[] args) {
		SpringApplication.run(PaymentApplication.class, args);
	}

}
