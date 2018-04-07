package payment;

import domain.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderRegistry {

    private List<Order> orders = new ArrayList<>();

    public void register(Order message) {
        orders.add(message);
    }

    public List<Order> getOrders() {
        return orders;
    }
}