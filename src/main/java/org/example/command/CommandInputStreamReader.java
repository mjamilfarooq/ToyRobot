package org.example.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CommandInputStreamReader implements ICommandReader, AutoCloseable {

    private final BufferedReader bufferedReader;

    public CommandInputStreamReader(InputStream inputStream) {
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }

    /**
     * @return return parsed command
     */
    @Override
    public ICommand read() throws IOException {
        String commandStr = bufferedReader.readLine();
        if (commandStr == null || commandStr.equals("")) return null;
        return CommandFactory.createCommand(commandStr);
    }

    /**
     */
    @Override
    public void close() throws Exception {
        bufferedReader.close();
    }
}
