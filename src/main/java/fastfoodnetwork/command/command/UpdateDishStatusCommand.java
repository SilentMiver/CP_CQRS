package fastfoodnetwork.command.command;

import fastfoodnetwork.common.model.DishStatus;
import java.util.UUID;

public class UpdateDishStatusCommand implements Command {
    private final String commandId;
    private final String orderId;
    private final String dishId;
    private final DishStatus newStatus;

    public UpdateDishStatusCommand(String orderId, String dishId, DishStatus newStatus) {
        this.commandId = UUID.randomUUID().toString();
        this.orderId = orderId;
        this.dishId = dishId;
        this.newStatus = newStatus;
    }

    public String getOrderId() { return orderId; }
    public String getDishId() { return dishId; }
    public DishStatus getNewStatus() { return newStatus; }
    @Override
    public String getCommandId() { return commandId; }
}