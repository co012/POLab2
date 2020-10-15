package agh.cs.lab2;

public class World {

    public static void main(String[] args) {
        Vector2d position1 = new Vector2d(1, 2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2, 1);
        System.out.println(position2);
        System.out.println(position1.add(position2));
        System.out.println();

        for(MapDirection d : MapDirection.values()){
            System.out.println(d);
            System.out.println(d.next());
            System.out.println(d.previous());
            System.out.println(d.toUnitVector());
            System.out.println();
        }




    }
}
