package agh.cs.lab2;

import java.util.Objects;

public final class Vector2d {

    public final int x;
    public final int y;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public String toString() {
        return "(" + x + ',' + y + ')';
    }


    public boolean precedes(Vector2d other) {
        if (other == null) throw new NullPointerException();
        return x <= other.x && y <= other.y;
    }


    public boolean follows(Vector2d other) {
        if (other == null) return false;
        return x >= other.x && y >= other.y;
    }


    public Vector2d upperRight(Vector2d other) {
        if (other == null) return null;

        int nx = Math.max(other.x, this.x);
        int ny = Math.max(other.y, this.y);

        return new Vector2d(nx, ny);
    }


    public Vector2d lowerLeft(Vector2d other) {
        if (other == null) return null;

        int nx = Math.min(other.x, this.x);
        int ny = Math.min(other.y, this.y);

        return new Vector2d(nx, ny);
    }


    public Vector2d add(Vector2d other) {
        if (other == null) return null;

        int nx = other.x + this.x;
        int ny = other.y + this.y;

        return new Vector2d(nx, ny);
    }


    public Vector2d subtract(Vector2d other) {
        if (other == null) return null;

        int nx = -other.x + this.x;
        int ny = -other.y + this.y;

        return new Vector2d(nx, ny);
    }


    public boolean equals(Object other) {
        if (this == other) return true;

        if (!(other instanceof Vector2d)) return false;

        Vector2d that = (Vector2d) other;

        return that.x == x && that.y == y;
    }


    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }


    public Vector2d opposite() {
        return new Vector2d(-x, -y);
    }



}
