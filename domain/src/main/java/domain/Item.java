package domain;

import java.io.Serializable;

public class Item implements Serializable{
    private Integer quantity;
    private String name;
    private Double unitPrice;

    public Item(Integer quantity, String name, Double unitPrice) {
        this.quantity = quantity;
        this.name = name;
        this.unitPrice = unitPrice;
    }

    public Double getCost() {
        return quantity * unitPrice;
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }
}