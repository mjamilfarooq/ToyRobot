package org.example.surface;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.model.Position;

@Data
@AllArgsConstructor
public class Table implements ISurface {
    private int x;
    private int y;

    @Override
    public boolean isOutOfBound(Position position) {
        return position.getX() >= x || position.getX() < 0 || position.getY() >= y || position.getY() < 0;
    }
}
