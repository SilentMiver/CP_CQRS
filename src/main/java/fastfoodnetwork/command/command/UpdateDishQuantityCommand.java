package fastfoodnetwork.command.command;

import java.util.UUID;

public class UpdateDishQuantityCommand implements Command {
    private final String commandId;
    private final String orderId;
    private final String dishId;
    private final int newQuantity;

    public UpdateDishQuantityCommand(String orderId, String dishId, int newQuantity) {
        this.commandId = UUID.randomUUID().toString();
        this.orderId = orderId;
        this.dishId = dishId;
        this.newQuantity = newQuantity;
    }

    public String getOrderId() { return orderId; }
    public String getDishId() { return dishId; }
    public int getNewQuantity() { return newQuantity; }
    @Override
    public String getCommandId() { return commandId; }
}