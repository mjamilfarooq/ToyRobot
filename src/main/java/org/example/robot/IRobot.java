package org.example.robot;

import org.example.command.ICommand;
import org.example.position.IVector;

public interface IRobot {
    void follow(ICommand command);
}
