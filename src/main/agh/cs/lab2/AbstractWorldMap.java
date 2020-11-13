package agh.cs.lab2;

import java.util.*;

public abstract class AbstractWorldMap implements IWorldMap {
    protected final LinkedHashMap<Vector2d, Animal> animalHashMap;
    private final MapVisualiser mapVisualiser;

    AbstractWorldMap() {
        mapVisualiser = new MapVisualiser(this);
        animalHashMap = new LinkedHashMap<>();
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
        animalHashMap.put(animal.getPosition(),animal);


    }

    @Override
    public void run(List<MoveDirection> directions) {
        if (animalHashMap.isEmpty()) return;

        Set<Map.Entry<Vector2d,Animal>> animalHashMapSet = animalHashMap.entrySet();
        Iterator<Map.Entry<Vector2d, Animal>> animalListIterator = animalHashMapSet.iterator();

        for (MoveDirection direction : directions) {
            animalListIterator.next().getValue().move(direction);

            if (!animalListIterator.hasNext()) animalListIterator = animalHashMapSet.iterator();
        }

        LinkedList<Animal> animalsToReAdd = new LinkedList<>();
        LinkedList<Vector2d> keysToRemove = new LinkedList<>();

        for (Map.Entry<Vector2d,Animal> entry : animalHashMapSet){
            if(entry.getValue().getPosition().equals(entry.getKey())) continue;
            animalsToReAdd.add(entry.getValue());
            keysToRemove.add(entry.getKey());
        }

        keysToRemove.forEach(animalHashMap::remove);
        animalsToReAdd.forEach(animal -> animalHashMap.put(animal.getPosition(),animal));

    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position).isPresent();
    }

    @Override
    public Optional<IWorldMapElement> objectAt(Vector2d position) {
        return Optional.ofNullable(animalHashMap.get(position));
    }
}
