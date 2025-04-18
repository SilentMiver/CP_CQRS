package fastfoodnetwork.query.model;

import fastfoodnetwork.common.model.OrderStatus;

import java.util.ArrayList;
import java.util.List;

public class OrderView {
    private String orderId;
    private OrderStatus status;
    private List<DishItemView> dishes = new ArrayList<>();

    public OrderView(String orderId, OrderStatus status) {
        this.orderId = orderId;
        this.status = status;
    }

    public String getOrderId() { return orderId; }
    public OrderStatus getStatus() { return status; }
    public List<DishItemView> getDishes() { return dishes; }
    public void setStatus(OrderStatus status) { this.status = status; }
}