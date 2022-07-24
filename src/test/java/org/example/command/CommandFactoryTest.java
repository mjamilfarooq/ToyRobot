package org.example.command;

import org.junit.jupiter.api.Test;

import static org.example.command.CommandFactory.*;
import static org.junit.jupiter.api.Assertions.*;

class CommandFactoryTest {

    @Test
    void createCommandTest_Positive() {
        assertEquals(Move.class, createCommand(" MOVE ").getClass());
        assertEquals(Move.class, createCommand(" move ").getClass());
        assertEquals(Left.class, createCommand("LEFT").getClass());
        assertEquals(Left.class, createCommand(" left ").getClass());
        assertEquals(Right.class, createCommand("RIGHT").getClass());
        assertEquals(Right.class, createCommand("right").getClass());
        assertEquals(Place.class, createCommand("PLACE 2,3,NORTH").getClass());
        assertEquals(Place.class, createCommand("place 2,3, north").getClass());
    }

    @Test
    void createCommandTest_IllegalCommand() {
        String expectMessage = "No enum constant org.example.command.CommandType.UNKNOWNCOMMAND";
        Exception ex = assertThrows(InvalidCommandFormat.class, () -> createCommand("UNKNOWNCOMMAND"));
        assertEquals(expectMessage, ex.getMessage());
    }

    @Test
    void createCommandTest_NullPointerException() {
        Exception ex = assertThrows(InvalidCommandFormat.class, () -> createCommand(null));
        assertNull(ex.getMessage());
    }
}