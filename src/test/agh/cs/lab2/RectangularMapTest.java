package agh.cs.lab2;

import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class RectangularMapTest {

    @Test
    public void shouldPlaceAnimalOnGivenPosition() {
        IWorldMap map = new RectangularMap(20,20);

        Vector2d position = new Vector2d(2, 3);

        assertTrue(map.canMoveTo(position));
        map.place(new Animal(map, position));
        assertFalse(map.canMoveTo(position));

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAllowPlacingTwoAnimalsOnTheSamePosition() {
        IWorldMap map = new RectangularMap(20,20);

        Vector2d position = new Vector2d(2, 3);
        map.place(new Animal(map, position));
        map.place(new Animal(map, position));

    }

    @Test
    public void shouldKnowWhenAnAnimalCanMoveThere() {
        IWorldMap map = new RectangularMap(20,20);

        Vector2d position = new Vector2d(2, 1);
        assertTrue(map.canMoveTo(position));
        map.place(new Animal(map, position));
        assertFalse(map.canMoveTo(position));

        assertFalse(map.canMoveTo(new Vector2d(21,0)));
        assertFalse(map.canMoveTo(new Vector2d(7,21)));
        assertFalse(map.canMoveTo(new Vector2d(-1,1)));
        assertFalse(map.canMoveTo(new Vector2d(8,-12)));

    }

    private LinkedList<MoveDirection> wrapWithLinkedList(MoveDirection m) {
        LinkedList<MoveDirection> l = new LinkedList<>();
        l.add(m);
        return l;
    }

    @Test
    public void shouldMoveAsSaid() {
        IWorldMap map = new RectangularMap(20,20);

        Animal animal = new Animal(map);
        map.place(animal);

        assertEquals(MapDirection.NORTH, animal.getMapDirection());
        assertEquals(new Vector2d(2, 2), animal.getPosition());

        map.run(wrapWithLinkedList(MoveDirection.FORWARD));
        assertEquals(MapDirection.NORTH, animal.getMapDirection());
        assertEquals(new Vector2d(2, 3), animal.getPosition());

        map.run(wrapWithLinkedList(MoveDirection.LEFT));
        assertEquals(MapDirection.WEST, animal.getMapDirection());
        assertEquals(new Vector2d(2, 3), animal.getPosition());

        map.run(wrapWithLinkedList(MoveDirection.BACKWARD));
        assertEquals(MapDirection.WEST, animal.getMapDirection());
        assertEquals(new Vector2d(3, 3), animal.getPosition());

        map.run(wrapWithLinkedList(MoveDirection.RIGHT));
        assertEquals(MapDirection.NORTH, animal.getMapDirection());
        assertEquals(new Vector2d(3, 3), animal.getPosition());
    }

    @Test
    public void shouldNotAllowRunningInToTheSamePosition() {
        IWorldMap map = new RectangularMap(20,20);

        Animal animal1 = new Animal(map, new Vector2d(0, 0));
        map.place(animal1);
        Animal animal2 = new Animal(map, new Vector2d(0, 1));
        map.place(animal2);

        map.run(wrapWithLinkedList(MoveDirection.FORWARD));

        assertEquals(new Vector2d(0, 0), animal1.getPosition());
        assertEquals(new Vector2d(0, 1), animal2.getPosition());
    }

    @Test
    public void shouldReturnObjectAtGivenPosition() {
        IWorldMap map = new RectangularMap(20,20);
        Vector2d position = new Vector2d(10, 10);

        assertTrue(map.objectAt(position).isEmpty());
        map.place(new Animal(map, position));
        assertTrue(map.objectAt(position).isPresent());

    }

    @Test
    public void shouldCheckIfIsOccupiedReturnsTheRightValue() {
        IWorldMap map = new RectangularMap(20,20);
        Vector2d position = new Vector2d(10, 10);

        assertFalse(map.isOccupied(position));
        map.place(new Animal(map, position));
        assertTrue(map.isOccupied(position));

    }

}