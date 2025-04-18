package fastfoodnetwork.common.event;

public class DishAddedToOrderEvent extends Event {
    private final String orderId;
    private final String dishId;
    private final int quantity;

    public DishAddedToOrderEvent(String orderId, String dishId, int quantity) {
        this.orderId = orderId;
        this.dishId = dishId;
        this.quantity = quantity;
    }

    public String getOrderId() { return orderId; }
    public String getDishId() { return dishId; }
    public int getQuantity() { return quantity; }
}