package fastfoodnetwork.common.event;

import fastfoodnetwork.common.model.OrderStatus;

public class OrderCreatedEvent extends Event {
    private final String orderId;
    private final OrderStatus status;

    public OrderCreatedEvent(String orderId, OrderStatus status) {
        this.orderId = orderId;
        this.status = status;
    }

    public String getOrderId() { return orderId; }
    public OrderStatus getStatus() { return status; }
}
