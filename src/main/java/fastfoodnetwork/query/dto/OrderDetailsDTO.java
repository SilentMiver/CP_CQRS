package fastfoodnetwork.query.dto;

import fastfoodnetwork.common.model.OrderStatus;

import java.util.List;

public class OrderDetailsDTO {
    private String orderId;
    private OrderStatus status;
    private List<DishItemDTO> dishes;

    public OrderDetailsDTO(String orderId, OrderStatus status, List<DishItemDTO> dishes) {
        this.orderId = orderId;
        this.status = status;
        this.dishes = dishes;
    }

    public String getOrderId() { return orderId; }
    public OrderStatus getStatus() { return status; }
    public List<DishItemDTO> getDishes() { return dishes; }

    @Override
    public String toString() {
        return "OrderDetailsDTO{" +
                "orderId='" + orderId + '\'' +
                ", status=" + status +
                ", dishes=" + dishes +
                '}';
    }
}