package domain;

import java.io.Serializable;

public class Payment implements Serializable{
    private Long paymentId;
    private Order order;
    private Double paymentValue;

    public Payment(Long paymentId, Order order, Double paymentValue) {
        this.paymentId = paymentId;
        this.order = order;
        this.paymentValue = paymentValue;
    }

    public Double getPaymentValue() {
        return paymentValue;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public Order getOrder() {
        return order;
    }
}