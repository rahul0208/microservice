package payment;

import java.util.List;

import domain.Order;
import domain.Payment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
public class PaymentController {

    @Resource
    OrderRegistry registry;

    List<Payment> payments;

    @GetMapping("/list")
    @ResponseBody
    public List<Payment> list() {
        return payments;
    }

    @PostMapping("/pay/{orderId}")
    @ResponseBody
    public String payOrder(@PathVariable Long orderId, @RequestAttribute Long paymentId, @RequestAttribute Double paymentValue) {
        for (Order order : registry.getOrders()) {
            if (order.getId().equals(orderId) && order.getTotalCost().equals(paymentValue)) {
                payments.add(new Payment(paymentId,order,paymentValue));
                return "SUCCESS";
            }
        }
        return "FAILED";
    }

}
