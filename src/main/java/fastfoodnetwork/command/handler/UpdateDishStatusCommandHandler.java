package fastfoodnetwork.command.handler;

import fastfoodnetwork.command.command.UpdateDishStatusCommand;
import fastfoodnetwork.command.model.Order;
import fastfoodnetwork.command.repository.OrderRepository;

public class UpdateDishStatusCommandHandler implements CommandHandler<UpdateDishStatusCommand> {
    private final OrderRepository orderRepository;

    public UpdateDishStatusCommandHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void handle(UpdateDishStatusCommand command) {
        Order order = orderRepository.findById(command.getOrderId());
        order.updateDishStatus(command.getDishId(), command.getNewStatus());
        orderRepository.save(order);
    }
}