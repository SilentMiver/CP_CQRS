package fastfoodnetwork.query.model;

import fastfoodnetwork.common.model.DishStatus;

public class DishItemView {
    private String dishId;
    private int quantity;
    private DishStatus status;

    public DishItemView(String dishId, int quantity, DishStatus status) {
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