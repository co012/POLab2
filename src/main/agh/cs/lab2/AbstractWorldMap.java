package agh.cs.lab2;

import java.util.*;

public abstract class AbstractWorldMap implements IWorldMap,IPositionChangeObserver{
    protected final LinkedHashMap<Vector2d, Animal> animalHashMap;
    protected final MapBoundary mapBoundary;
    private final MapVisualiser mapVisualiser;


    AbstractWorldMap() {
        mapVisualiser = new MapVisualiser(this);
        animalHashMap = new LinkedHashMap<>();
        mapBoundary = new MapBoundary();
    }

    @Override
    public String toString() {
        return mapVisualiser.draw(mapBoundary);
    }



    @Override
    public boolean canMoveTo(Vector2d position) {
        Optional<IWorldMapElement> optional = objectAt(position);
        return optional.isEmpty()|| !optional.get().isTakingTheWholeSpace();
    }

    @Override
    public void place(Animal animal) {
        if (!canMoveTo(animal.getPosition())) throw new IllegalArgumentException(animal.getPosition().toString() + " is not available");
        animalHashMap.put(animal.getPosition(),animal);
        animal.addObserver(this);

        mapBoundary.addIWorldMamElement(animal);
        animal.addObserver(mapBoundary);


    }

    @Override
    public void run(List<MoveDirection> directions) {
        if (animalHashMap.isEmpty()) return;
        List<Animal> animalList = new LinkedList<>(animalHashMap.values());

        Iterator<Animal> animalListIterator = animalList.iterator();

        for (MoveDirection direction : directions) {
            animalListIterator.next().move(direction);
            System.out.println(this.toString());
            if (!animalListIterator.hasNext()) animalListIterator = animalList.iterator();
        }

    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position).isPresent();
    }

    @Override
    public Optional<IWorldMapElement> objectAt(Vector2d position) {
        return Optional.ofNullable(animalHashMap.get(position));
    }

    @Override
    public void positionChanged(IWorldMapElement element, Vector2d oldPosition, Vector2d newPosition) {
        Animal animal = animalHashMap.remove(oldPosition);
        animalHashMap.put(newPosition,animal);
    }
}
