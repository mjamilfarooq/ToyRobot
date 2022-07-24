package org.example.command;

public class Right implements ICommand {

    /**
     * @param commandExecutor executor for which command needs to be executed.
     */
    @Override
    public void execute(ICommandExecutor commandExecutor) {
        commandExecutor.right();
    }
}
