package fastfoodnetwork.command.model;

import fastfoodnetwork.common.model.DishStatus;

public class DishItem {
    private String dishId;
    private int quantity;
    private DishStatus status;

    public DishItem(String dishId, int quantity, DishStatus status) {
        this.dishId = dishId;
        this.quantity = quantity;
        this.status = status;
    }

    public String getDishId() { return dishId; }
    public int getQuantity() { return quantity; }
    public DishStatus getStatus() { return status; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setStatus(DishStatus status) { this.status = status; }
}