package fastfoodnetwork.query.service;

import fastfoodnetwork.common.model.OrderStatus;
import fastfoodnetwork.query.dto.DishItemDTO;
import fastfoodnetwork.query.dto.OrderDetailsDTO;
import fastfoodnetwork.query.dto.OrderStatisticsDTO;
import fastfoodnetwork.query.model.OrderView;
import fastfoodnetwork.query.repository.OrderViewRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderQueryService {
    private final OrderViewRepository orderViewRepository;

    public OrderQueryService(OrderViewRepository orderViewRepository) {
        this.orderViewRepository = orderViewRepository;
    }
    public List<OrderDetailsDTO> getAllOrderDetails(){
        var orderViewList = orderViewRepository.findAll();
        List<OrderDetailsDTO> orders = new ArrayList<>();
        for(var order : orderViewList){
            if (order == null) {
                throw new IllegalArgumentException("Order not found: ");
            }
            List<DishItemDTO> dishDTOs = order.getDishes().stream()
                    .map(d -> new DishItemDTO(d.getDishId(), d.getQuantity(), d.getStatus()))
                    .collect(Collectors.toList());
            orders.add(new OrderDetailsDTO(order.getOrderId(), order.getStatus(), dishDTOs));

        }
        return orders;

    }

    public OrderDetailsDTO getOrderDetails(String orderId) {
        OrderView orderView = orderViewRepository.findById(orderId);
        if (orderView == null) {
            throw new IllegalArgumentException("Order not found: " + orderId);
        }
        List<DishItemDTO> dishDTOs = orderView.getDishes().stream()
                .map(d -> new DishItemDTO(d.getDishId(), d.getQuantity(), d.getStatus()))
                .collect(Collectors.toList());
        return new OrderDetailsDTO(orderView.getOrderId(), orderView.getStatus(), dishDTOs);
    }

    public OrderStatisticsDTO getOrderStatistics() {
        List<OrderView> orders = orderViewRepository.findAll();
        int totalOrders = orders.size();
        int completedOrders = (int) orders.stream()
                .filter(o -> o.getStatus() == OrderStatus.COMPLETED)
                .count();
        double averageDishesPerOrder = orders.isEmpty() ? 0 :
                orders.stream().mapToInt(o -> o.getDishes().size()).average().orElse(0);
        return new OrderStatisticsDTO(totalOrders, completedOrders, averageDishesPerOrder);
    }
}