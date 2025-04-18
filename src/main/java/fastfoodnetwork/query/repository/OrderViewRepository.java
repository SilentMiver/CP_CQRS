package fastfoodnetwork.query.repository;

import fastfoodnetwork.query.model.OrderView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderViewRepository {
    private final Map<String, OrderView> orders = new HashMap<>();

    public void save(OrderView orderView) {
        orders.put(orderView.getOrderId(), orderView);
    }

    public OrderView findById(String orderId) {
        return orders.get(orderId);
    }

    public List<OrderView> findAll() {
        return new ArrayList<>(orders.values());
    }
}