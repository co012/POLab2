package agh.cs.lab2;

public class Grass {

    private Vector2d position;


    public Grass(Vector2d position) {
        if (position == null) throw new NullPointerException("position can't be null");
        this.position = position;
    }


    public Vector2d getPosition() {
        return position;
    }


    @Override
    public String toString() {
        return "*";
    }
}
