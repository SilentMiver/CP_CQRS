package fastfoodnetwork.common.event;

public class OrderCompletedEvent extends Event {
    private final String orderId;

    public OrderCompletedEvent(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() { return orderId; }
}