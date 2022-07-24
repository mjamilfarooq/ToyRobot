package org.example.command;

public class InvalidCommandFormat extends RuntimeException {
    public InvalidCommandFormat(String msg) {
        super(msg);
    }
}
