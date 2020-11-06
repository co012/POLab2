package agh.cs.lab2;

import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

public class RectangularMap extends AbstractWorldMap {

    private final int width;
    private final int height;


    public RectangularMap(int width, int height) {
        super();
        this.width = width;
        this.height = height;


    }

    @Override
    protected Vector2d[] getBoundary() {
        return new Vector2d[]{new Vector2d(0, 0), new Vector2d(width, height)};
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.x >= 0 && position.y >= 0 && position.x <= width && position.y <= height && super.canMoveTo(position);
    }

}
