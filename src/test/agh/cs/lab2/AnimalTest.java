package agh.cs.lab2;

import org.junit.Test;

import static org.junit.Assert.*;

public class AnimalTest {

    @Test
    public void shouldChangeMapDirection() {
        Animal animal = new Animal();

        animal.move(MoveDirection.LEFT);
        assertEquals(MapDirection.WEST,animal.getMapDirection());

        animal.move(MoveDirection.RIGHT);
        assertEquals(MapDirection.NORTH,animal.getMapDirection());
    }

    @Test
    public void shouldChangePosition(){
        Animal animal = new Animal();

        animal.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(2,3),animal.getPosition());

        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(1,3),animal.getPosition());

        animal.move(MoveDirection.BACKWARD);
        assertEquals(new Vector2d(2,3),animal.getPosition());

        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.BACKWARD);
        assertEquals(new Vector2d(2,2),animal.getPosition());

    }

    @Test
    public void shouldNotAllowUpperRightBoundaryCrossing(){
        //upperRight (4,4)


        Animal animal = new Animal();

        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(2,4),animal.getPosition());

        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(4,4),animal.getPosition());

        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.BACKWARD);
        assertEquals(new Vector2d(4,4),animal.getPosition());

        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.BACKWARD);
        assertEquals(new Vector2d(4,4),animal.getPosition());


    }

    @Test
    public void shouldNotAllowLowerLeftBoundaryCrossing(){
        //lowerLeft (0,0)

        Animal animal = new Animal();

        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(2,0),animal.getPosition());

        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(0,0),animal.getPosition());

        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.BACKWARD);
        assertEquals(new Vector2d(0,0),animal.getPosition());

        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.BACKWARD);
        assertEquals(new Vector2d(0,0),animal.getPosition());
    }

}