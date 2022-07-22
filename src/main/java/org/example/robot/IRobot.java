package org.example.robot;

import org.example.model.Position;
import org.example.surface.ISurface;

public interface IRobot {
    void place(Position position, ISurface surface);
    void move();
    void left();
    void right();
    Position report();
}
