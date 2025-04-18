package fastfoodnetwork.command.handler;

import fastfoodnetwork.command.command.Command;

public interface CommandHandler<T extends Command> {
    void handle(T command);
}