package person;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
public class PersonWriterController {

    @Resource
    RabbitTemplate rabbitTemplate;
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/hello-world")
    @ResponseBody
    public Greeting sayHello(@RequestParam(name = "name", required = false, defaultValue = "Stranger") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @PostMapping("/add")
    @ResponseStatus(value = HttpStatus.OK)
    public void addPerson(@RequestParam(name = "name") String name) {
        rabbitTemplate.convertAndSend(PersonWriterApplication.topicExchangeName, "create.person."+counter.incrementAndGet(), "Hello from RabbitMQ! Added  "+name);
    }
}
