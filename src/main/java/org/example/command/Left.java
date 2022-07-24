package org.example.command;

public class Left implements ICommand {

    /**
     * @param commandExecutor executor for which command needs to be executed.
     */
    @Override
    public void execute(ICommandExecutor commandExecutor) {
        commandExecutor.left();
    }
}
