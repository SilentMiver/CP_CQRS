package fastfoodnetwork.command.command;

import java.util.UUID;

public class AddDishToOrderCommand implements Command {
    private final String commandId;
    private final String orderId;
    private final String dishId;
    private final int quantity;

    public AddDishToOrderCommand(String orderId, String dishId, int quantity) {
        this.commandId = UUID.randomUUID().toString();
        this.orderId = orderId;
        this.dishId = dishId;
        this.quantity = quantity;
    }

    public String getOrderId() { return orderId; }
    public String getDishId() { return dishId; }
    public int getQuantity() { return quantity; }
    @Override
    public String getCommandId() { return commandId; }
}