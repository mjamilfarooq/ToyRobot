package org.example.command;

import java.util.Objects;

import static org.example.command.CommandType.valueOf;

public class CommandFactory {

    public static ICommand createCommand(String commandStr) throws InvalidCommandFormat {
        try {
            Objects.requireNonNull(commandStr);
            String[] params = commandStr.trim().split(" ", 2);
            ICommand command = null;
            switch (valueOf(params[0].toUpperCase())) {
                case MOVE:
                    command = new Move();
                    break;
                case LEFT:
                    command = new Left();
                    break;
                case RIGHT:
                    command = new Right();
                    break;
                case REPORT:
                    command = new Report();
                    break;
                case PLACE:
                    command = new Place(params[1].trim());
                    break;
            }
            return command;
        } catch (Exception ex) {
            throw new InvalidCommandFormat(ex.getMessage());
        }
    }

}
