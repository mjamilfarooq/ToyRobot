package org.example.robot;

import org.example.command.ICommand;
import org.example.command.ICommandExecutor;
import org.example.position.IVector;
import org.example.surface.ISurface;

import java.io.PrintStream;
import java.util.Objects;

public class SimpleToyRobot implements IRobot , ICommandExecutor {

    private IVector vector = null;
    private final ISurface surface;

    private final PrintStream printStream;

    public SimpleToyRobot(ISurface surface, PrintStream printStream) {
        this.surface = surface;
        this.printStream = printStream;
    }

    @Override
    public void follow(ICommand command) {
        command.execute(this);
    }

    /**
     * @param vector position where to place robot
     */
    @Override
    public void place(IVector vector) {
        if (Objects.nonNull(surface) && Objects.nonNull(vector) && !surface.isOutOfBound(vector)) {
            this.vector = vector;
        }
    }

    /**
     *
     */
    @Override
    public void move() {
        if (Objects.nonNull(surface) && Objects.nonNull(this.vector) && !surface.isOutOfBound(this.vector.peek())) {
            vector.move();
        }
    }

    /**
     *
     */
    @Override
    public void left() {
        if (Objects.nonNull(surface) && Objects.nonNull(this.vector) && !surface.isOutOfBound(this.vector)) {
            this.vector.rotate("LEFT");
        }
    }

    /**
     *
     */
    @Override
    public void right() {
        if (Objects.nonNull(surface) && Objects.nonNull(this.vector) && !surface.isOutOfBound(this.vector)) {
            this.vector.rotate("RIGHT");
        }
    }

    /**
     *
     */
    @Override
    public void report() {
        if (Objects.nonNull(surface) && Objects.nonNull(this.vector) && !surface.isOutOfBound(this.vector)) {
            printStream.println("Output: " + this.vector);
        }
    }
}
