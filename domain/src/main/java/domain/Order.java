package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order implements Serializable{
    private Long id;
    private String description;
    private List<Item> items;
    private Double totalCost;


    public Order(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public Order addItem(Item item) {
        if (items == null) {
            items = new ArrayList<>();
            totalCost = 0.0;
        }
        items.add(item);
        totalCost += item.getCost();
        return this;
    }
}