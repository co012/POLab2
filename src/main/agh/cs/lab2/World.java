package agh.cs.lab2;

import java.util.LinkedList;

public class World {

    public static void main(String[] args) {

        LinkedList<MoveDirection> dir = OptionsParser.parse(args);
        Animal animal = new Animal();

        while (!dir.isEmpty()) {
            System.out.println(animal);
            animal.move(dir.remove());
        }


    }
}
