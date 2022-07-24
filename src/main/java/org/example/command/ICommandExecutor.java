package org.example.command;

import org.example.position.IVector;

public interface ICommandExecutor {
    void place(IVector vector);
    void move();
    void left();
    void right();
    void report();
}
