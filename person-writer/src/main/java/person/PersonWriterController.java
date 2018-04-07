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
    private final AtomicLong counter = new AtomicLong();

    @PostMapping("/add")
    @ResponseStatus(value = HttpStatus.OK)
    public void addPerson(@RequestParam(name = "name") String name) {
        rabbitTemplate.convertAndSend(PersonWriterApplication.topicExchangeName, "create.person."+counter.incrementAndGet(), "Added  "+name);
    }
}
