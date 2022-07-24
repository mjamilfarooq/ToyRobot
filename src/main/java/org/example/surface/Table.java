package org.example.surface;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.position.IVector;
import org.example.position.TwoDimensionalVector;

@Data
@AllArgsConstructor
public class Table implements ISurface {
    private int x;
    private int y;

    @Override
    public boolean isOutOfBound(IVector location) {
        return isOutOfBound((TwoDimensionalVector)location);
    }

    private boolean isOutOfBound(TwoDimensionalVector location) {
        return location.getX() >= x || location.getX() < 0 || location.getY() >= y || location.getY() < 0;
    }
}
