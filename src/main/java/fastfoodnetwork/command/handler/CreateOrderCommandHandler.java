package fastfoodnetwork.command.handler;

import fastfoodnetwork.command.command.CreateOrderCommand;
import fastfoodnetwork.command.model.Order;
import fastfoodnetwork.command.repository.OrderRepository;

public class CreateOrderCommandHandler implements CommandHandler<CreateOrderCommand> {
    private final OrderRepository orderRepository;

    public CreateOrderCommandHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void handle(CreateOrderCommand command) {
        Order order = new Order();
        orderRepository.save(order);
    }
}