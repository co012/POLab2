package agh.cs.lab2;


import java.util.List;

public class World {

    public static void main(String[] args) {

        List<MoveDirection> directions = null;
        try {
             directions = OptionsParser.parse(args);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            System.exit(-1);
        }

        IWorldMap map = new GrassField(10);

        try {
            map.place(new Animal(map));
            map.place(new Animal(map, new Vector2d(3, 4)));
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            System.exit(1);
        }

        map.run(directions);


    }
}
