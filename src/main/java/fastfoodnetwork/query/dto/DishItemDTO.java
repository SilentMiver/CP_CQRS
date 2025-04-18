package fastfoodnetwork.query.dto;

import fastfoodnetwork.common.model.DishStatus;

public class DishItemDTO {
    private String dishId;
    private int quantity;
    private DishStatus status;

    public DishItemDTO(String dishId, int quantity, DishStatus status) {
        this.dishId = dishId;
        this.quantity = quantity;
        this.status = status;
    }

    public String getDishId() { return dishId; }
    public int getQuantity() { return quantity; }
    public DishStatus getStatus() { return status; }

    @Override
    public String toString() {
        return "DishItemDTO{" +
                "dishId='" + dishId + '\'' +
                ", quantity=" + quantity +
                ", status=" + status +
                '}';
    }
}