package payment;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import domain.Constants;
import domain.Item;
import domain.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
public class OrderWriterController {

    @Resource
    RabbitTemplate rabbitTemplate;
    private final AtomicLong counter = new AtomicLong();
    private Map<Long, Order> orderStore = new HashMap<>();

    @PostMapping("/create")
    @ResponseBody
    public Long createOrder(@RequestParam(name = "desc") String desc) {
        Order order = new Order(counter.incrementAndGet());
        order.setDescription(desc);
        orderStore.put(order.getId(), order);
        return order.getId();
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
