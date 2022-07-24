package org.example.position;

public interface IVector {
    void move();
    IVector peek();
    void rotate(String direction);

    void copy(IVector vector);
}
