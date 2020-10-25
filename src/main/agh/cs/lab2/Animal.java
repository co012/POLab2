package agh.cs.lab2;

public class Animal {


    private MapDirection mapDirection;
    private Vector2d position;

    // field
    private static Vector2d upperRightBoundary;
    private static Vector2d lowerLeftBoundary;


    public Animal() {

        if (upperRightBoundary == null) {
            upperRightBoundary = new Vector2d(4, 4);
            lowerLeftBoundary = new Vector2d(0, 0);
        }

        mapDirection = MapDirection.NORTH;
        position = new Vector2d(2, 2);

    }

    public MapDirection getMapDirection() {
        return mapDirection;
    }

    public Vector2d getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "kierunek=" + mapDirection +
                ", pozycja=" + position +
                '}';
    }


    public void move(MoveDirection direction) {
        if (direction == MoveDirection.LEFT) {
            mapDirection = mapDirection.previous();
        } else if (direction == MoveDirection.RIGHT) {
            mapDirection = mapDirection.next();
        } else if (direction == MoveDirection.FORWARD) {
            Vector2d newPosition = position.add(mapDirection.toUnitVector());
            if (newPosition.precedes(upperRightBoundary) && newPosition.follows(lowerLeftBoundary)) {
                position = newPosition;
            }
        } else if (direction == MoveDirection.BACKWARD) {
            Vector2d newPosition = position.subtract(mapDirection.toUnitVector());
            if (newPosition.precedes(upperRightBoundary) && newPosition.follows(lowerLeftBoundary)) {
                position = newPosition;
            }
        }

    }
}
