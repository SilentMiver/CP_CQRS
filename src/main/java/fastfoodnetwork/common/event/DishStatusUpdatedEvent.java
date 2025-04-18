package fastfoodnetwork.common.event;

import fastfoodnetwork.common.model.DishStatus;

public class DishStatusUpdatedEvent extends Event {
    private final String orderId;
    private final String dishId;
    private final DishStatus newStatus;

    public DishStatusUpdatedEvent(String orderId, String dishId, DishStatus newStatus) {
        this.orderId = orderId;
        this.dishId = dishId;
        this.newStatus = newStatus;
    }

    public String getOrderId() { return orderId; }
    public String getDishId() { return dishId; }
    public DishStatus getNewStatus() { return newStatus; }
}