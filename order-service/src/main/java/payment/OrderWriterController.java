package payment;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import domain.Constants;
import domain.Item;
import domain.Order;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Controller
public class OrderWriterController {
    private static final Logger LOG = LoggerFactory.getLogger(OrderWriterController.class);
    @Resource
    RabbitTemplate rabbitTemplate;

    @Resource
    IdService idService;

    private Map<Long, Order> orderStore = new HashMap<>();

    @PostMapping("/create")
    @ResponseBody
    public String createOrder(@RequestParam(name = "desc") String desc) {
        LOG.info("calling home");
        Long id = idService.generateId();
        Order order = new Order(id);
        order.setDescription(desc);
        orderStore.put(order.getId(), order);
        return order.getId().toString();
    }

    @PostMapping("/add-item/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void addItem(@PathVariable(name = "id") Long id, @RequestParam Integer quantity, @RequestParam String name, @RequestParam Double unitPrice) {
        orderStore.get(id).addItem(new Item(quantity, name, unitPrice));
    }

    @PostMapping("/payment/{id}")
    @ResponseBody
    public Order payOrder(@PathVariable(name = "id") Long id) {
        Order order = orderStore.get(id);
        rabbitTemplate.convertAndSend(Constants.orderExchangeName, Constants.paymentRouteKey + order.getId(), order);
        return order;
    }


}


