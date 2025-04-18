package fastfoodnetwork.command.handler;

import fastfoodnetwork.command.command.RemoveDishFromOrderCommand;
import fastfoodnetwork.command.model.Order;
import fastfoodnetwork.command.repository.OrderRepository;

public class RemoveDishFromOrderCommandHandler implements CommandHandler<RemoveDishFromOrderCommand> {
    private final OrderRepository orderRepository;

    public RemoveDishFromOrderCommandHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void handle(RemoveDishFromOrderCommand command) {
        Order order = orderRepository.findById(command.getOrderId());
        order.removeDish(command.getDishId());
        orderRepository.save(order);
    }
}