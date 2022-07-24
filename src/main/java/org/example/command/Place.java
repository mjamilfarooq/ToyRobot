package org.example.command;

import org.example.position.EWNSFacingImpl;
import org.example.position.IVector;
import org.example.position.TwoDimensionalVector;

public class Place implements ICommand {

    IVector vector;

    public Place(String params) throws InvalidCommandFormat{
        parse(params);
    }

    public Place(IVector vector) {
        this.vector = vector;
    }

    @Override
    public void execute(ICommandExecutor commandExecutor) {
        commandExecutor.place(vector);
    }

    @Override
    public void parse(String pStr) throws InvalidCommandFormat {
        try {
            String[] params = pStr.split(",");
            if (params.length != 3) throw new InvalidCommandFormat("Insufficient parameters");
            this.vector = new TwoDimensionalVector(Integer.parseInt(params[0].trim()),
                    Integer.parseInt(params[1].trim()),
                    EWNSFacingImpl.valueOf(params[2].trim().toUpperCase()));
        } catch (Exception ex) {
            final String validFormat = "Command PLACE is not in correct format: PLACE X,Y,F";
            throw new InvalidCommandFormat(ex.getMessage() + " Valid Format is -> " + validFormat);
        }
    }

    public IVector getVector() {
        return vector;
    }
}
