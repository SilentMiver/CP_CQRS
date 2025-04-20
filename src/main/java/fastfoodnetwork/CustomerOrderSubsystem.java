package fastfoodnetwork;

import fastfoodnetwork.api.console.ConsoleInterface;
import fastfoodnetwork.api.facade.OrderFacade;
import fastfoodnetwork.command.command.*;
import fastfoodnetwork.command.handler.*;
import fastfoodnetwork.command.model.Order;
import fastfoodnetwork.command.repository.OrderRepository;
import fastfoodnetwork.common.event.EventBus;
import fastfoodnetwork.common.model.DishStatus;
import fastfoodnetwork.query.dto.OrderDetailsDTO;
import fastfoodnetwork.query.dto.OrderStatisticsDTO;
import fastfoodnetwork.query.repository.OrderViewRepository;
import fastfoodnetwork.query.service.OrderEventHandler;
import fastfoodnetwork.query.service.OrderQueryService;

import java.util.List;

public class CustomerOrderSubsystem {
    public static void main(String[] args) {
        try {

            OrderRepository orderRepository = new OrderRepository();
            OrderViewRepository orderViewRepository = new OrderViewRepository();


            CommandBus commandBus = new CommandBus();
            commandBus.register(CreateOrderCommand.class, new CreateOrderCommandHandler(orderRepository));
            commandBus.register(AddDishToOrderCommand.class, new AddDishToOrderCommandHandler(orderRepository));
            commandBus.register(RemoveDishFromOrderCommand.class, new RemoveDishFromOrderCommandHandler(orderRepository));
            commandBus.register(UpdateDishQuantityCommand.class, new UpdateDishQuantityCommandHandler(orderRepository));
            commandBus.register(UpdateDishStatusCommand.class, new UpdateDishStatusCommandHandler(orderRepository));
            commandBus.register(CompleteOrderCommand.class, new CompleteOrderCommandHandler(orderRepository));


            OrderEventHandler eventHandler = new OrderEventHandler(orderViewRepository);
            EventBus.getInstance().register(eventHandler);


            OrderQueryService queryService = new OrderQueryService(orderViewRepository);
            OrderFacade facade = new OrderFacade(commandBus, queryService);


            facade.createOrder();
            List<Order> orders = orderRepository.findAll();
            if (orders.isEmpty()) {
                throw new IllegalStateException("No orders created");
            }
            String orderId = orders.get(0).getOrderId();


            facade.addDishToOrder(orderId, "DISH001", 2);
            facade.updateDishStatus(orderId, "DISH001", DishStatus.IN_PREPARATION);
            facade.updateDishStatus(orderId, "DISH001", DishStatus.READY);
            OrderDetailsDTO details = facade.getOrderDetails(orderId);
            System.out.printf("Order %s is %s with %s \n", details.getOrderId(), details.getStatus(), details.getDishes().get(0).toString());
            facade.completeOrder(orderId);


            details = facade.getOrderDetails(orderId);
            System.out.println("Order " + details.getOrderId() + " Status: " + details.getStatus());
            OrderStatisticsDTO stats = facade.getOrderStatistics();
            System.out.println("Total Orders: " + stats.getTotalOrders());
            System.out.println("Average Dishes Per Order: " + stats.getAverageDishesPerOrder());
            System.out.println("Completed Orders: " + stats.getCompletedOrders());


            ConsoleInterface console = new ConsoleInterface(facade);
            console.start();


        } catch (Exception e) {
            System.err.println("Error during execution: " + e.getMessage());
            e.printStackTrace();
        }
    }
}