package agh.cs.lab2;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

public class RectangularMap implements IWorldMap {

    private final int width;
    private final int height;

    private final List<Animal> animals;

    private final MapVisualiser mapVisualiser;

    public RectangularMap(int width, int height) {

        this.width = width;
        this.height = height;
        animals = new LinkedList<>();
        mapVisualiser = new MapVisualiser(this);

    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.x >= 0 && position.y >= 0 && position.x <= width && position.y <= height && !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        if (isOccupied(animal.getPosition())) return false;

        animals.add(animal);
        return true;

    }

    @Override
    public void run(List<MoveDirection> directions) {
        if (animals.isEmpty()) return;


        ListIterator<Animal> animalListIterator = animals.listIterator();

        for (MoveDirection direction : directions) {

            animalListIterator.next().move(direction);

            if (!animalListIterator.hasNext()) animalListIterator = animals.listIterator();
        }


    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (Animal animal : animals) {
            if (position.equals(animal.getPosition())) return true;
        }
        return false;
    }

    @Override
    public Optional<Object> objectAt(Vector2d position) {
        for (Animal animal : animals) {
            if (position.equals(animal.getPosition())) return Optional.of(animal);
        }
        return Optional.empty();
    }

    @Override
    public String toString() {
        return mapVisualiser.draw(new Vector2d(0, 0), new Vector2d(width, height));
    }
}
