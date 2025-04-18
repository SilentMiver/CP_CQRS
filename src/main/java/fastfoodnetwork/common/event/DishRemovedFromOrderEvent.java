package fastfoodnetwork.common.event;

public class DishRemovedFromOrderEvent extends Event {
    private final String orderId;
    private final String dishId;

    public DishRemovedFromOrderEvent(String orderId, String dishId) {
        this.orderId = orderId;
        this.dishId = dishId;
    }

    public String getOrderId() { return orderId; }
    public String getDishId() { return dishId; }
}