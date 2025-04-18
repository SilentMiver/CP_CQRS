package fastfoodnetwork.command.handler;

import fastfoodnetwork.command.command.CompleteOrderCommand;
import fastfoodnetwork.command.model.Order;
import fastfoodnetwork.command.repository.OrderRepository;

public class CompleteOrderCommandHandler implements CommandHandler<CompleteOrderCommand> {
    private final OrderRepository orderRepository;

    public CompleteOrderCommandHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void handle(CompleteOrderCommand command) {
        Order order = orderRepository.findById(command.getOrderId());
        order.completeOrder();
        orderRepository.save(order);
    }
}