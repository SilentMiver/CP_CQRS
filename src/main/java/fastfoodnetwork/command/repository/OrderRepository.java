package fastfoodnetwork.command.repository;

import fastfoodnetwork.command.model.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OrderRepository {
    private final Map<String, Order> orders = new HashMap<>();

    public void save(Order order) {
        orders.put(order.getOrderId(), order);
    }

    public Order findById(String orderId) {
        return Optional.ofNullable(orders.get(orderId))
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + orderId));
    }

    public List<Order> findAll() {
        return new ArrayList<>(orders.values());
    }
}