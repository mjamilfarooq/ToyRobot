package org.example.position;


import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class TwoDimensionalVector implements IVector {

    private int x;
    private int y;
    private EWNSFacingImpl facing;


    @Override
    public void copy(IVector vector) {
        this.x = ((TwoDimensionalVector)vector).x;
        this.y = ((TwoDimensionalVector)vector).y;
        this.facing = ((TwoDimensionalVector)vector).facing;
    }
    @Override
    public void move() {
        next(this);
    }

    @Override
    public IVector peek() {
        TwoDimensionalVector location = new TwoDimensionalVector(this.x, this.y, facing) ;
        return next(location);
    }

    @Override
    public void rotate(String direction) {
        facing = (EWNSFacingImpl) facing.change(direction);
    }

    private static TwoDimensionalVector next(TwoDimensionalVector location) {
        switch (location.facing ) {
            case NORTH:
                location.y++;
                break;
            case SOUTH:
                location.y--;
                break;
            case EAST:
                location.x++;
                break;
            case WEST:
                location.x--;
                break;
        }
        return location;
    }

    @Override
    public String toString() {
        return String.format("%d,%d,%s", x, y, facing.toString());
    }
}
