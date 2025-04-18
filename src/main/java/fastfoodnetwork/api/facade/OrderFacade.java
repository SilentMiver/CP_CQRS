package fastfoodnetwork.api.facade;

import fastfoodnetwork.command.command.*;
import fastfoodnetwork.command.handler.CommandBus;
import fastfoodnetwork.common.model.DishStatus;
import fastfoodnetwork.query.dto.OrderDetailsDTO;
import fastfoodnetwork.query.dto.OrderStatisticsDTO;
import fastfoodnetwork.query.service.OrderQueryService;

public class OrderFacade {
    private final CommandBus commandBus;
    private final OrderQueryService queryService;

    public OrderFacade(CommandBus commandBus, OrderQueryService queryService) {
        this.commandBus = commandBus;
        this.queryService = queryService;
    }

    public void createOrder() {
        commandBus.dispatch(new CreateOrderCommand());
    }

    public void addDishToOrder(String orderId, String dishId, int quantity) {
        commandBus.dispatch(new AddDishToOrderCommand(orderId, dishId, quantity));
    }

    public void removeDishFromOrder(String orderId, String dishId) {
        commandBus.dispatch(new RemoveDishFromOrderCommand(orderId, dishId));
    }

    public void updateDishQuantity(String orderId, String dishId, int newQuantity) {
        commandBus.dispatch(new UpdateDishQuantityCommand(orderId, dishId, newQuantity));
    }

    public void updateDishStatus(String orderId, String dishId, DishStatus newStatus) {
        commandBus.dispatch(new UpdateDishStatusCommand(orderId, dishId, newStatus));
    }

    public void completeOrder(String orderId) {
        commandBus.dispatch(new CompleteOrderCommand(orderId));
    }

    public OrderDetailsDTO getOrderDetails(String orderId) {
        return queryService.getOrderDetails(orderId);
    }

    public OrderStatisticsDTO getOrderStatistics() {
        return queryService.getOrderStatistics();
    }
}