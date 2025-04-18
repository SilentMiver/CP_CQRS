package fastfoodnetwork.query.service;

import fastfoodnetwork.common.event.*;
import fastfoodnetwork.common.model.DishStatus;
import fastfoodnetwork.common.model.OrderStatus;
import fastfoodnetwork.query.model.DishItemView;
import fastfoodnetwork.query.model.OrderView;
import fastfoodnetwork.query.repository.OrderViewRepository;

public class OrderEventHandler implements EventBus.EventHandler {
    private final OrderViewRepository orderViewRepository;

    public OrderEventHandler(OrderViewRepository orderViewRepository) {
        this.orderViewRepository = orderViewRepository;
    }

    @Override
    public void handle(Event event) {
        if (event instanceof OrderCreatedEvent) {
            handleOrderCreated((OrderCreatedEvent) event);
        } else if (event instanceof DishAddedToOrderEvent) {
            handleDishAdded((DishAddedToOrderEvent) event);
        } else if (event instanceof DishRemovedFromOrderEvent) {
            handleDishRemoved((DishRemovedFromOrderEvent) event);
        } else if (event instanceof DishQuantityUpdatedEvent) {
            handleDishQuantityUpdated((DishQuantityUpdatedEvent) event);
        } else if (event instanceof DishStatusUpdatedEvent) {
            handleDishStatusUpdated((DishStatusUpdatedEvent) event);
        } else if (event instanceof OrderCompletedEvent) {
            handleOrderCompleted((OrderCompletedEvent) event);
        }
    }

    private void handleOrderCreated(OrderCreatedEvent event) {
        OrderView orderView = new OrderView(event.getOrderId(), event.getStatus());
        orderViewRepository.save(orderView);
    }

    private void handleDishAdded(DishAddedToOrderEvent event) {
        OrderView orderView = orderViewRepository.findById(event.getOrderId());
        DishItemView dishItem = new DishItemView(event.getDishId(), event.getQuantity(), DishStatus.ORDERED);
        orderView.getDishes().add(dishItem);
        orderViewRepository.save(orderView);
    }

    private void handleDishRemoved(DishRemovedFromOrderEvent event) {
        OrderView orderView = orderViewRepository.findById(event.getOrderId());
        orderView.getDishes().removeIf(dish -> dish.getDishId().equals(event.getDishId()));
        orderViewRepository.save(orderView);
    }

    private void handleDishQuantityUpdated(DishQuantityUpdatedEvent event) {
        OrderView orderView = orderViewRepository.findById(event.getOrderId());
        DishItemView dishItem = orderView.getDishes().stream()
                .filter(d -> d.getDishId().equals(event.getDishId()))
                .findFirst().orElse(null);
        if (dishItem != null) {
            dishItem.setQuantity(event.getNewQuantity());
            orderViewRepository.save(orderView);
        }
    }

    private void handleDishStatusUpdated(DishStatusUpdatedEvent event) {
        OrderView orderView = orderViewRepository.findById(event.getOrderId());
        DishItemView dishItem = orderView.getDishes().stream()
                .filter(d -> d.getDishId().equals(event.getDishId()))
                .findFirst().orElse(null);
        if (dishItem != null) {
            dishItem.setStatus(event.getNewStatus());
            orderViewRepository.save(orderView);
        }
    }

    private void handleOrderCompleted(OrderCompletedEvent event) {
        OrderView orderView = orderViewRepository.findById(event.getOrderId());
        orderView.setStatus(OrderStatus.COMPLETED);
        orderViewRepository.save(orderView);
    }
}