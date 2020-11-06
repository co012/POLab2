package agh.cs.lab2;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class GrassField implements IWorldMap {

    private final LinkedList<Grass> grassList;
    private final LinkedList<Animal> animalList;
    private final MapVisualiser mapVisualiser;


    public GrassField(int grassCount) {
        if (grassCount <= 0) throw new IllegalArgumentException("grassCount must be greater than 0");

        mapVisualiser = new MapVisualiser(this);
        grassList = new LinkedList<>();
        animalList = new LinkedList<>();

        int grassUpperBoundary = (int) Math.sqrt(grassCount * 10);
        while (grassList.size() < grassCount) {
            int x = ThreadLocalRandom.current().nextInt(0,grassUpperBoundary + 1);
            int y = ThreadLocalRandom.current().nextInt(0,grassUpperBoundary + 1);

            Vector2d position = new Vector2d(x,y);
            Optional<Object> optional = objectAt(position);

            if(optional.isEmpty()) grassList.add(new Grass(position));

        }


    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !(objectAt(position).orElse(null) instanceof Animal);
    }

    @Override
    public boolean place(Animal animal) {
        if (!canMoveTo(animal.getPosition())) return false;
        animalList.add(animal);
        return true;

    }

    @Override
    public void run(List<MoveDirection> directions) {
        if (animalList.isEmpty()) return;
        System.out.print(this);

        ListIterator<Animal> animalListIterator = animalList.listIterator();

        for (MoveDirection direction : directions) {
            System.out.print(this);

            animalListIterator.next().move(direction);

            if (!animalListIterator.hasNext()) animalListIterator = animalList.listIterator();
        }


    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position).isPresent();
    }

    @Override
    public Optional<Object> objectAt(Vector2d position) {

        for (Animal animal : animalList) {
            if (position.equals(animal.getPosition())) return Optional.of(animal);
        }

        for (Grass grass : grassList) {
            if (position.equals(grass.getPosition())) return Optional.of(grass);
        }

        return Optional.empty();
    }

    @Override
    public String toString() {
        Vector2d lowerLeftBoundary = null;
        Vector2d upperRightBoundary = null;

        for (Animal animal : animalList) {
            lowerLeftBoundary = animal.getPosition().lowerLeft(lowerLeftBoundary);
            upperRightBoundary = animal.getPosition().upperRight(upperRightBoundary);

        }

        for (Grass grass : grassList) {
            lowerLeftBoundary = grass.getPosition().lowerLeft(lowerLeftBoundary);
            upperRightBoundary = grass.getPosition().upperRight(upperRightBoundary);

        }

        if (lowerLeftBoundary == null) throw new RuntimeException("grassList or animalList is empty");

        return mapVisualiser.draw(lowerLeftBoundary, upperRightBoundary);
    }
}
