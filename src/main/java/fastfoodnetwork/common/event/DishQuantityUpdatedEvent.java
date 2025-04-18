package fastfoodnetwork.common.event;

public class DishQuantityUpdatedEvent extends Event {
    private final String orderId;
    private final String dishId;
    private final int newQuantity;

    public DishQuantityUpdatedEvent(String orderId, String dishId, int newQuantity) {
        this.orderId = orderId;
        this.dishId = dishId;
        this.newQuantity = newQuantity;
    }

    public String getOrderId() { return orderId; }
    public String getDishId() { return dishId; }
    public int getNewQuantity() { return newQuantity; }
}