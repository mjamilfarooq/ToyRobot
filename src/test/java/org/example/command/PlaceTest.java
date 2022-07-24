package org.example.command;


import org.example.position.EWNSFacingImpl;
import org.example.position.IVector;
import org.example.position.TwoDimensionalVector;
import org.example.surface.ISurface;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Matches;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PlaceTest {
    @Mock
    private ICommandExecutor commandExecutor;

    ArgumentCaptor<TwoDimensionalVector> captor = ArgumentCaptor.forClass(TwoDimensionalVector.class);

    @Test
    public void testExecute_Positive() {
        Place place = new Place(new TwoDimensionalVector(2, 3, EWNSFacingImpl.NORTH));
        place.execute(commandExecutor);
        Mockito.verify(commandExecutor).place(captor.capture());
        assertEquals(new TwoDimensionalVector(2, 3, EWNSFacingImpl.NORTH), captor.getValue());
    }

    @Test
    public void testInitializePositive() {
        assertEquals(new TwoDimensionalVector(2, 3, EWNSFacingImpl.NORTH),
                new Place(new TwoDimensionalVector(2, 3, EWNSFacingImpl.NORTH)).getVector());
        assertEquals(new TwoDimensionalVector(2, 3, EWNSFacingImpl.NORTH),
                new Place(" 2, 3,     NORTH").getVector());
        assertEquals(new TwoDimensionalVector(1, 1, EWNSFacingImpl.EAST),
                new Place("1    ,   1,   EAST").getVector());
        assertEquals(new TwoDimensionalVector(2, 3, EWNSFacingImpl.WEST),
                new Place("2    ,3,   WEST").getVector());
        assertEquals(new TwoDimensionalVector(-1, 1, EWNSFacingImpl.SOUTH),
                new Place("-1    ,   1,   SOUTH").getVector());
    }

    @Test
    public void testInitializeNegative_InvalidFacing() {
        String expectedMessage = "No enum constant org.example.position.EWNSFacingImpl.NOWHERE Valid Format is -> " +
                "Command PLACE is not in correct format: PLACE X,Y,F";
        Exception exception = assertThrows(InvalidCommandFormat.class,  () -> new Place(" 2, 3, NOWHERE"));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testInitializeNegative_InsufficientParameters() {
        String expectedMessage = "For input string: \"\" Valid Format is -> " +
                "Command PLACE is not in correct format: PLACE X,Y,F";
        Exception exception = assertThrows(InvalidCommandFormat.class,  () -> new Place(" 2, , NORTH"));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testInitializeNegative_OnlyOneDimensionIsGiven() {
        String expectedMessage = "Insufficient parameters Valid Format is -> " +
                "Command PLACE is not in correct format: PLACE X,Y,F";
        Exception exception = assertThrows(InvalidCommandFormat.class,  () -> new Place(" 2, 3"));
        assertEquals(expectedMessage, exception.getMessage());
    }
}