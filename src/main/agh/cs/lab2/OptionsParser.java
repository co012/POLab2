package agh.cs.lab2;

import java.util.LinkedList;


public class OptionsParser {

    public static LinkedList<MoveDirection> parse(String[] dir) {
        LinkedList<MoveDirection> moveDirections = new LinkedList<>();

        for (String d : dir) {
            switch (d) {
                case "f", "forward" -> moveDirections.add(MoveDirection.FORWARD);
                case "b", "backward" -> moveDirections.add(MoveDirection.BACKWARD);
                case "l", "left" -> moveDirections.add(MoveDirection.LEFT);
                case "r", "right" -> moveDirections.add(MoveDirection.RIGHT);
            }
        }

        return moveDirections;
    }

}
