package agh.cs.lab2;

import java.util.LinkedList;

public class Animal implements IWorldMapElement,IPositionChangedPublisher {


    private MapDirection mapDirection;
    private Vector2d position;

    private final IWorldMap map;

    private final LinkedList<IPositionChangeObserver> observers;

    public Animal(IWorldMap map) {
        this(map, new Vector2d(2, 2));
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        if (initialPosition == null) throw new NullPointerException("initialPosition can't be null");

        this.map = map;
        mapDirection = MapDirection.NORTH;
        position = initialPosition;
        observers = new LinkedList<>();

    }

    public MapDirection getMapDirection() {
        return mapDirection;
    }

    @Override
    public boolean isTakingTheWholeSpace() {
        return true;
    }

    public Vector2d getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return switch (mapDirection) {
            case NORTH -> "^";
            case EAST -> ">";
            case WEST -> "<";
            case SOUTH -> "v";
        };
    }


    public void move(MoveDirection direction) {
        if (direction == MoveDirection.LEFT) {
            mapDirection = mapDirection.previous();
        } else if (direction == MoveDirection.RIGHT) {
            mapDirection = mapDirection.next();
        } else {
            Vector2d newPosition = direction == MoveDirection.FORWARD ? position.add(mapDirection.toUnitVector()) : position.subtract(mapDirection.toUnitVector());
            if (map.canMoveTo(newPosition)) {
                positionChanged(newPosition);
                position = newPosition;
            }
        }
    }

    @Override
    public void addObserver(IPositionChangeObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IPositionChangeObserver observer) {
        observers.remove(observer);
    }

    private void positionChanged(Vector2d newPosition){
        observers.forEach(observer -> observer.positionChanged(position,newPosition));
    }

}
