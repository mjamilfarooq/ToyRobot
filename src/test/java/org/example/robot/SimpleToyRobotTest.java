package org.example.robot;

import org.example.command.*;
import org.example.position.EWNSFacingImpl;
import org.example.position.TwoDimensionalVector;
import org.example.surface.Table;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class SimpleToyRobotTest {

    final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    final String utf8 = StandardCharsets.UTF_8.name();

    PrintStream ps;

    @BeforeEach
    void setup() throws IOException {
        ps = new PrintStream(byteArrayOutputStream, true, utf8);
    }

    @Test
    void simpleTestBoundaryCases() throws IOException {

        IRobot robot = new SimpleToyRobot(new Table(5, 5), ps);
        robot.follow(new Place(new TwoDimensionalVector(0, 0, EWNSFacingImpl.EAST)));
        assertReport(new TwoDimensionalVector(0, 0, EWNSFacingImpl.EAST), robot);
        robot.follow(new Move());
        assertReport(new TwoDimensionalVector(1, 0, EWNSFacingImpl.EAST), robot);
        robot.follow(new Left());
        assertReport(new TwoDimensionalVector(1, 0, EWNSFacingImpl.NORTH), robot);
        robot.follow(new Left());
        assertReport(new TwoDimensionalVector(1, 0, EWNSFacingImpl.WEST), robot);
        robot.follow(new Left());
        assertReport(new TwoDimensionalVector(1, 0, EWNSFacingImpl.SOUTH), robot);
        robot.follow(new Left());
        assertReport(new TwoDimensionalVector(1, 0, EWNSFacingImpl.EAST), robot);
        robot.follow(new Move());
        robot.follow(new Move());
        robot.follow(new Move());

        //at right edge of the table
        assertReport(new TwoDimensionalVector(4, 0, EWNSFacingImpl.EAST), robot);

        //next move should be ignored
        robot.follow(new Move());
        assertReport(new TwoDimensionalVector(4, 0, EWNSFacingImpl.EAST), robot);

        //should move the face to bottom side of the table
        robot.follow(new Right());
        assertReport(new TwoDimensionalVector(4, 0, EWNSFacingImpl.SOUTH), robot);

        //next move should be ignored
        robot.follow(new Move());
        assertReport(new TwoDimensionalVector(4, 0, EWNSFacingImpl.SOUTH), robot);

        //place to NORTH/EAST corner
        robot.follow(new Place(new TwoDimensionalVector(4, 4, EWNSFacingImpl.NORTH)));
        assertReport(new TwoDimensionalVector(4, 4, EWNSFacingImpl.NORTH), robot);

        //another move to NORTH should be ignored
        robot.follow(new Move());
        assertReport(new TwoDimensionalVector(4, 4, EWNSFacingImpl.NORTH), robot);

        //
        robot.follow(new Right());
        assertReport(new TwoDimensionalVector(4, 4, EWNSFacingImpl.EAST), robot);

        //next move is ignored
        robot.follow(new Move());
        assertReport(new TwoDimensionalVector(4, 4, EWNSFacingImpl.EAST), robot);

        robot.follow(new Right());
        robot.follow(new Right());
        assertReport(new TwoDimensionalVector(4, 4, EWNSFacingImpl.WEST), robot);

        robot.follow(new Move());
        robot.follow(new Move());
        robot.follow(new Move());
        robot.follow(new Move());
        assertReport(new TwoDimensionalVector(0, 4, EWNSFacingImpl.WEST), robot);

        //another move should be ignored
        robot.follow(new Move());
        assertReport(new TwoDimensionalVector(0, 4, EWNSFacingImpl.WEST), robot);


        robot.follow(new Right());
        assertReport(new TwoDimensionalVector(0, 4, EWNSFacingImpl.NORTH), robot);
    }


    @Test
    void testPlacementAtOutsideTable() throws IOException {
        IRobot robot = new SimpleToyRobot(new Table(5, 5), ps);
        robot.follow(new Place(new TwoDimensionalVector(5, 5, EWNSFacingImpl.EAST)));
        assertNoReport(robot);

        robot.follow(new Place(new TwoDimensionalVector(-1, -1, EWNSFacingImpl.EAST)));
        assertNoReport(robot);
    }

    void assertReport(TwoDimensionalVector expected, IRobot robot) throws IOException {
        robot.follow(new Report());
        String expectedMessage = "Output: " + expected.getX() + "," +
                expected.getY() + "," + expected.getFacing().toString();
        assertEquals(expectedMessage, byteArrayOutputStream.toString(utf8).trim());
        byteArrayOutputStream.reset();
    }

    void assertNoReport(IRobot robot) throws IOException {
        assertEquals("", byteArrayOutputStream.toString(utf8));
    }

}