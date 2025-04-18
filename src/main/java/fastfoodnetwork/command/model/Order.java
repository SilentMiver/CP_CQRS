package fastfoodnetwork.command.model;

import fastfoodnetwork.common.event.*;
import fastfoodnetwork.common.model.OrderStatus;
import fastfoodnetwork.common.model.DishStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {
    private String orderId;
    private OrderStatus status;
    private List<DishItem> dishes = new ArrayList<>();

    public Order() {
        this.orderId = UUID.randomUUID().toString();
        this.status = OrderStatus.CREATED;
        EventBus.getInstance().publish(new OrderCreatedEvent(this.orderId, this.status));
    }

    public void addDish(String dishId, int quantity) {
        if (this.status == OrderStatus.COMPLETED) {
            throw new IllegalStateException("Cannot modify completed order");
        }
        DishItem item = new DishItem(dishId, quantity, DishStatus.ORDERED);
        this.dishes.add(item);
        EventBus.getInstance().publish(new DishAddedToOrderEvent(this.orderId, dishId, quantity));
    }

    public void removeDish(String dishId) {
        if (this.status == OrderStatus.COMPLETED) {
            throw new IllegalStateException("Cannot modify completed order");
        }
        DishItem item = findDishItem(dishId);
        if (item != null) {
            this.dishes.remove(item);
            EventBus.getInstance().publish(new DishRemovedFromOrderEvent(this.orderId, dishId));
        }
    }

    public void updateDishQuantity(String dishId, int newQuantity) {
        if (this.status == OrderStatus.COMPLETED) {
            throw new IllegalStateException("Cannot modify completed order");
        }
        DishItem item = findDishItem(dishId);
        if (item != null && newQuantity > 0) {
            item.setQuantity(newQuantity);
            EventBus.getInstance().publish(new DishQuantityUpdatedEvent(this.orderId, dishId, newQuantity));
        }
    }

    public void updateDishStatus(String dishId, DishStatus newStatus) {
        if (this.status == OrderStatus.COMPLETED) {
            throw new IllegalStateException("Cannot modify completed order");
        }
        DishItem item = findDishItem(dishId);
        if (item != null) {
            item.setStatus(newStatus);
            EventBus.getInstance().publish(new DishStatusUpdatedEvent(this.orderId, dishId, newStatus));
        }
    }

    public void completeOrder() {
        if (this.status == OrderStatus.COMPLETED) {
            throw new IllegalStateException("Order already completed");
        }
        boolean allReady = this.dishes.stream().allMatch(d -> d.getStatus() == DishStatus.READY);
        if (!allReady) {
            throw new IllegalStateException("Not all dishes are ready");
        }
        this.status = OrderStatus.COMPLETED;
        EventBus.getInstance().publish(new OrderCompletedEvent(this.orderId));
    }

    private DishItem findDishItem(String dishId) {
        return this.dishes.stream().filter(d -> d.getDishId().equals(dishId)).findFirst().orElse(null);
    }

    public String getOrderId() {
        return orderId;
    }
}