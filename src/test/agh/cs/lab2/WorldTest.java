package agh.cs.lab2;

import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class WorldTest {

    public void applyMoves(Animal a, LinkedList<MoveDirection> l) {
        while (!l.isEmpty()) a.move(l.remove());
    }

    @Test
    public void shouldRotate() {

        Animal animal = new Animal();

        applyMoves(animal,OptionsParser.parse(new String[]{"l"}));
        assertEquals(MapDirection.WEST,animal.getMapDirection());

        applyMoves(animal,OptionsParser.parse(new String[]{"left"}));
        assertEquals(MapDirection.SOUTH,animal.getMapDirection());

        applyMoves(animal,OptionsParser.parse(new String[]{"r"}));
        assertEquals(MapDirection.WEST,animal.getMapDirection());

        applyMoves(animal,OptionsParser.parse(new String[]{"right"}));
        assertEquals(MapDirection.NORTH,animal.getMapDirection());
    }

    @Test
    public void shouldGoBackAndForth(){
        Animal animal = new Animal();

        applyMoves(animal,OptionsParser.parse(new String[]{"f"}));
        assertEquals(new Vector2d(2,3),animal.getPosition());

        applyMoves(animal,OptionsParser.parse(new String[]{"forward"}));
        assertEquals(new Vector2d(2,4),animal.getPosition());

        applyMoves(animal,OptionsParser.parse(new String[]{"b"}));
        assertEquals(new Vector2d(2,3),animal.getPosition());

        applyMoves(animal,OptionsParser.parse(new String[]{"backward"}));
        assertEquals(new Vector2d(2,2),animal.getPosition());
    }

    @Test
    public void shouldFollowComplexScenario(){
        Animal animal = new Animal();

        applyMoves(animal,OptionsParser.parse(new String[]{"f","r","f","left","f","l","f","f","r","b","backward","l"}));
        assertEquals(new Vector2d(1,2),animal.getPosition());
        assertEquals(MapDirection.WEST,animal.getMapDirection());

    }

    @Test
    public void shouldFollowComplexScenarioAndNotGoBeyond(){
        Animal animal = new Animal();

        applyMoves(animal,OptionsParser.parse(new String[]{"f","f","f","left","f","f","f","f","r","b",
                "backward","backward","backward","backward","backward","backward","backward","backward","r"}));
        assertEquals(new Vector2d(0,0),animal.getPosition());
        assertEquals(MapDirection.EAST,animal.getMapDirection());
    }

}