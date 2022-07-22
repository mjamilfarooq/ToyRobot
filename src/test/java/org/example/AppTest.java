package org.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.example.model.Direction;
import org.example.model.Position;
import org.example.surface.ISurface;
import org.example.surface.Table;
import org.example.robot.IRobot;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void test5by5Table() {
        IRobot robot = null;
        ISurface table = new Table(5, 5);
        robot.place(new Position(0, 0, Direction.NORTH), table);
        robot.move();
        robot.move();
        robot.left();
        robot.right();
        robot.left();
        assertEquals(new Position(0, 0, Direction.NORTH), robot.report());
    }
}
