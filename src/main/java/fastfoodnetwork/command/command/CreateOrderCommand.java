package fastfoodnetwork.command.command;

import java.util.UUID;

public class CreateOrderCommand implements Command {
    private final String commandId;

    public CreateOrderCommand() {
        this.commandId = UUID.randomUUID().toString();
    }

    @Override
    public String getCommandId() {
        return commandId;
    }
}