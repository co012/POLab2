package agh.cs.lab2;

import java.util.*;

public class MapBoundary implements IPositionChangeObserver {

    private final LinkedList<IWorldMapElement> mapElementsByX;
    private final LinkedList<IWorldMapElement> mapElementsByY;

    public MapBoundary() {
        mapElementsByX = new LinkedList<>();
        mapElementsByY = new LinkedList<>();
    }

    @Override
    public void positionChanged(IWorldMapElement element,Vector2d oldPosition, Vector2d newPosition) {
        if(IWorldMapElement.compareByXThenByYLastlyByTakenSpace(mapElementsByX.getLast(),element)<0 || IWorldMapElement.compareByXThenByYLastlyByTakenSpace(element,mapElementsByX.getFirst())<0)
            mapElementsByX.sort(IWorldMapElement::compareByXThenByYLastlyByTakenSpace);
        if(IWorldMapElement.compareByYThenByXLastlyByTakenSpace(mapElementsByY.getLast(),element)<0 || IWorldMapElement.compareByYThenByXLastlyByTakenSpace(element,mapElementsByY.getFirst())<0)
            mapElementsByY.sort(IWorldMapElement::compareByYThenByXLastlyByTakenSpace);
    }

    public void addIWorldMamElement(IWorldMapElement element) {
        insertIntoSortedList(element, mapElementsByX);
        insertIntoSortedList(element, mapElementsByY);


    }

    private void insertIntoSortedList(IWorldMapElement element, List<IWorldMapElement> sortedList) {

        if (sortedList.size()<1||IWorldMapElement.compareByXThenByYLastlyByTakenSpace(element, sortedList.get(0)) < 0) {
            sortedList.add(0, element);
        } else {
            ListIterator<IWorldMapElement> listIterator = sortedList.listIterator();
            while (listIterator.hasNext()) {
                IWorldMapElement listElement = listIterator.next();
                if (IWorldMapElement.compareByXThenByYLastlyByTakenSpace(element, listElement) < 0) {
                    listIterator.previous();
                    listIterator.add(element);
                    return;
                }
            }
            sortedList.add(element);

        }
    }

    public boolean isEmpty(){
        return mapElementsByX.isEmpty();
    }

    public Vector2d getLowerLeftBoundary(){
        return mapElementsByX.getFirst().getPosition().lowerLeft(mapElementsByY.getFirst().getPosition());
    }

    public Vector2d getUpperRightBoundary(){
        return mapElementsByX.getLast().getPosition().upperRight(mapElementsByY.getLast().getPosition());
    }

}
