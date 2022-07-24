package org.example;

import org.example.command.CommandInputStreamReader;
import org.example.command.ICommand;
import org.example.robot.IRobot;
import org.example.robot.SimpleToyRobot;
import org.example.surface.Table;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        IRobot robot = new SimpleToyRobot(new Table(5, 5), System.out);
        try (CommandInputStreamReader commandInputStreamReader = new CommandInputStreamReader(System.in)) {
            while (true) {
                ICommand command = commandInputStreamReader.read();
                if (command == null) break;
                robot.follow(command);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
