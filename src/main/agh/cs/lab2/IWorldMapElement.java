package agh.cs.lab2;

public interface IWorldMapElement {

    boolean isTakingTheWholeSpace();
    Vector2d getPosition();
    static int compareByXThenByYLastlyByTakenSpace(IWorldMapElement e1,IWorldMapElement e2){
        if(e1 == null && e2 != null) return -1;
        if(e1 != null && e2 == null) return 1;
        if(e1 == null) return 0;

        Vector2d position1 = e1.getPosition();
        Vector2d position2 = e2.getPosition();

        if(position1.x != position2.x) return position1.x - position2.x;
        if(position1.y != position2.y) return position1.y - position2.y;
        return Boolean.compare(e1.isTakingTheWholeSpace(),e2.isTakingTheWholeSpace());

    }
    static int compareByYThenByXLastlyByTakenSpace(IWorldMapElement e1,IWorldMapElement e2){
        if(e1 == null || e2 == null) throw new IllegalArgumentException("passed arguments shouldn't be null");

        Vector2d position1 = e1.getPosition();
        Vector2d position2 = e2.getPosition();

        if(position1.y != position2.y) return position1.y - position2.y;
        if(position1.x != position2.x) return position1.x - position2.x;
        return Boolean.compare(e1.isTakingTheWholeSpace(),e2.isTakingTheWholeSpace());

    }
}
