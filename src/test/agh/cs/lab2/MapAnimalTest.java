package agh.cs.lab2;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MapAnimalTest {

    @Test
    public void shouldFollowScenario() {
        List<MoveDirection> directions = OptionsParser.parse("f b r l f f r r f f f f f f f f".split(" "));
        IWorldMap map = new RectangularMap(10, 5);
        Animal animalOne = new Animal(map);
        Animal animalTwo = new Animal(map, new Vector2d(3, 4));
        map.place(animalOne);
        map.place(animalTwo);

        Vector2d[] animalOnePositions = {new Vector2d(2, 3), new Vector2d(2, 3), new Vector2d(2, 3), new Vector2d(2, 3), new Vector2d(2, 2), new Vector2d(2, 1), new Vector2d(2, 0), new Vector2d(2, 0)};
        Vector2d[] animalTwoPositions = {new Vector2d(3, 3), new Vector2d(3, 3), new Vector2d(3, 3), new Vector2d(3, 3), new Vector2d(3, 4), new Vector2d(3, 5), new Vector2d(3, 5), new Vector2d(3, 5)};

        MapDirection[] animalOneDirections = {MapDirection.NORTH, MapDirection.EAST, MapDirection.EAST, MapDirection.SOUTH, MapDirection.SOUTH, MapDirection.SOUTH, MapDirection.SOUTH, MapDirection.SOUTH};
        MapDirection[] animalTwoDirections = {MapDirection.NORTH, MapDirection.WEST, MapDirection.WEST, MapDirection.NORTH, MapDirection.NORTH, MapDirection.NORTH, MapDirection.NORTH, MapDirection.NORTH};

        for (int i = 0; i < 8; i++) {
            map.run(directions.subList(i * 2, (i + 1) * 2));

            assertEquals(animalOnePositions[i], animalOne.getPosition());
            assertEquals(animalOneDirections[i], animalOne.getMapDirection());

            assertEquals(animalTwoPositions[i], animalTwo.getPosition());
            assertEquals(animalTwoDirections[i], animalTwo.getMapDirection());

        }


    }
}
