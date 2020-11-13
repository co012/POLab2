package agh.cs.lab2;

import java.util.LinkedList;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class GrassField extends AbstractWorldMap {

    private final LinkedList<Grass> grassList;

    public GrassField(int grassCount) {
        super();
        if (grassCount <= 0) throw new IllegalArgumentException("grassCount must be greater than 0");

        grassList = new LinkedList<>();

        int grassUpperBoundary = (int) Math.sqrt(grassCount * 10);
        while (grassList.size() < grassCount) {
            int x = ThreadLocalRandom.current().nextInt(0, grassUpperBoundary + 1);
            int y = ThreadLocalRandom.current().nextInt(0, grassUpperBoundary + 1);

            Vector2d position = new Vector2d(x, y);
            Optional<IWorldMapElement> optional = objectAt(position);
            if (optional.isEmpty()) grassList.add(new Grass(position));

        }


    }


    @Override
    protected Vector2d[] getBoundary() {
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

        return new Vector2d[]{lowerLeftBoundary, upperRightBoundary};
    }

    @Override
    public Optional<IWorldMapElement> objectAt(Vector2d position) {

        Optional<IWorldMapElement> optional = super.objectAt(position);
        if (optional.isPresent()) return optional;

        for (Grass grass : grassList) {
            if (position.equals(grass.getPosition())) return Optional.of(grass);
        }

        return Optional.empty();
    }

}
