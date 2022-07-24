package org.example.command;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class CommandInputStreamReaderTest {

    @Test
    void readTest() throws Exception {
        String data = "PLACE 2,3,NORTH\nMOVE\nLEFT\nRIGHT\nREPORT";
        CommandInputStreamReader commandInputStreamReader =
                new CommandInputStreamReader(new ByteArrayInputStream(data.getBytes()));
        CommandInputStreamReader spy = Mockito.spy(commandInputStreamReader);
        assertEquals(Place.class, commandInputStreamReader.read().getClass());
        assertEquals(Move.class, commandInputStreamReader.read().getClass());
        assertEquals(Left.class, commandInputStreamReader.read().getClass());
        assertEquals(Right.class, commandInputStreamReader.read().getClass());
        assertEquals(Report.class, commandInputStreamReader.read().getClass());
    }
}