package org.example.command;

import java.io.IOException;

public interface ICommandReader {
    ICommand read() throws IOException;
}
