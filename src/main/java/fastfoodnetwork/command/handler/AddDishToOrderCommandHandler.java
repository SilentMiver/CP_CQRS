package fastfoodnetwork.command.handler;

import fastfoodnetwork.command.command.AddDishToOrderCommand;
import fastfoodnetwork.command.model.Order;
import fastfoodnetwork.command.repository.OrderRepository;

public class AddDishToOrderCommandHandler implements CommandHandler<AddDishToOrderCommand> {
    private final OrderRepository orderRepository;

    public AddDishToOrderCommandHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void handle(AddDishToOrderCommand command) {
        Order order = orderRepository.findById(command.getOrderId());
        order.addDish(command.getDishId(), command.getQuantity());
        orderRepository.save(order);
    }
}