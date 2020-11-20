package agh.cs.lab2;

import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class GrassField extends AbstractWorldMap {

    private final LinkedHashMap<Vector2d, Grass> grassHashMap;

    public GrassField(int grassCount) {
        super();
        if (grassCount <= 0) throw new IllegalArgumentException("grassCount must be greater than 0");

        grassHashMap = new LinkedHashMap<>();

        int grassUpperBoundary = (int) Math.sqrt(grassCount * 10);
        while (grassHashMap.size() < grassCount) {
            int x = ThreadLocalRandom.current().nextInt(0, grassUpperBoundary + 1);
            int y = ThreadLocalRandom.current().nextInt(0, grassUpperBoundary + 1);

            Vector2d position = new Vector2d(x, y);
            Optional<IWorldMapElement> optional = objectAt(position);
            if (optional.isEmpty()){
                Grass grass = new Grass(position);
                grassHashMap.put(position,new Grass(position));
                mapBoundary.addIWorldMamElement(grass);
            }

        }


    }


    protected Vector2d[] getBoundary() {
        Vector2d lowerLeftBoundary = null;
        Vector2d upperRightBoundary = null;

        for (Animal animal : animalHashMap.values()) {
            lowerLeftBoundary = animal.getPosition().lowerLeft(lowerLeftBoundary);
            upperRightBoundary = animal.getPosition().upperRight(upperRightBoundary);

        }

        for (Grass grass : grassHashMap.values()) {
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

        return Optional.ofNullable(grassHashMap.get(position));
    }

}
