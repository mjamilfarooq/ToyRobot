package org.example.surface;

import org.example.position.IVector;

public interface ISurface {
    boolean isOutOfBound(IVector position);
}
