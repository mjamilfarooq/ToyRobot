package org.example.command;

public interface ICommand {
    void execute(ICommandExecutor commandExecutor);

    default void parse(String params) throws InvalidCommandFormat {
    }
}
