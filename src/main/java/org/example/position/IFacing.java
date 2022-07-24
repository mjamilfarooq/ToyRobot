package org.example.position;

public interface IFacing {
    boolean isValid(String direction);
    IFacing change(String to);
}
