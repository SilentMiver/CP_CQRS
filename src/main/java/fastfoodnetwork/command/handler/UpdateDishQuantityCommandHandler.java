package fastfoodnetwork.command.handler;

import fastfoodnetwork.command.command.UpdateDishQuantityCommand;
import fastfoodnetwork.command.model.Order;
import fastfoodnetwork.command.repository.OrderRepository;

public class UpdateDishQuantityCommandHandler implements CommandHandler<UpdateDishQuantityCommand> {
    private final OrderRepository orderRepository;

    public UpdateDishQuantityCommandHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void handle(UpdateDishQuantityCommand command) {
        Order order = orderRepository.findById(command.getOrderId());
        order.updateDishQuantity(command.getDishId(), command.getNewQuantity());
        orderRepository.save(order);
    }
}