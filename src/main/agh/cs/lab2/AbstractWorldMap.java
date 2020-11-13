package agh.cs.lab2;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

public abstract class AbstractWorldMap implements IWorldMap {
    protected final List<Animal> animalList;
    private final MapVisualiser mapVisualiser;

    AbstractWorldMap() {
        mapVisualiser = new MapVisualiser(this);
        animalList = new LinkedList<>();
    }

    @Override
    public String toString() {
        Vector2d[] boundary = getBoundary();
        return mapVisualiser.draw(boundary[0], boundary[1]);
    }

    protected abstract Vector2d[] getBoundary();

    @Override
    public boolean canMoveTo(Vector2d position) {
        Optional<IWorldMapElement> optional = objectAt(position);
        return optional.isEmpty()|| !optional.get().isTakingTheWholeSpace();
    }

    @Override
    public void place(Animal animal) {
        if (!canMoveTo(animal.getPosition())) throw new IllegalArgumentException(animal.getPosition().toString() + " is not available");
        animalList.add(animal);


    }

    @Override
    public void run(List<MoveDirection> directions) {
        if (animalList.isEmpty()) return;

        ListIterator<Animal> animalListIterator = animalList.listIterator();

        for (MoveDirection direction : directions) {
            animalListIterator.next().move(direction);

            if (!animalListIterator.hasNext()) animalListIterator = animalList.listIterator();
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position).isPresent();
    }

    @Override
    public Optional<IWorldMapElement> objectAt(Vector2d position) {
        for (Animal animal : animalList) {
            if (position.equals(animal.getPosition())) return Optional.of(animal);
        }
        return Optional.empty();
    }
}
