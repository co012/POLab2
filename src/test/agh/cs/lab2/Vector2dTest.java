package agh.cs.lab2;

import org.junit.Test;

import static org.junit.Assert.*;

public class Vector2dTest {

    @Test
    public void shouldBeEqual() {
        Vector2d a = new Vector2d(1, 2);
        Vector2d b = new Vector2d(1, 2);

        assertEquals(a, b);
    }

    @Test
    public void shouldReturnString() {
        assertEquals("(1,2)", new Vector2d(1, 2).toString());
    }

    @Test
    public void shouldCheckIfBothAreSmaller() {
        Vector2d a = new Vector2d(1, 2);
        Vector2d b = new Vector2d(2, 3);

        assertTrue(a.precedes(b));
        assertFalse(b.precedes(a));
    }

    @Test
    public void shouldCheckIfBothAreBigger() {
        Vector2d a = new Vector2d(3, 4);
        Vector2d b = new Vector2d(2, 3);

        assertTrue(a.follows(b));
        assertFalse(b.follows(a));

    }

    @Test
    public void shouldReturnNewVectorWithMaxValues() {
        Vector2d a = new Vector2d(0, 0);

        assertEquals(new Vector2d(1, 1), a.upperRight(new Vector2d(1, 1)));
        assertEquals(new Vector2d(0, 1), a.upperRight(new Vector2d(-1, 1)));
        assertEquals(new Vector2d(1, 0), a.upperRight(new Vector2d(1, -1)));
        assertEquals(new Vector2d(0, 0), a.upperRight(new Vector2d(-1, -1)));
    }

    @Test
    public void shouldReturnNewVectorWithMinValues() {
        Vector2d a = new Vector2d(0, 0);

        assertEquals(new Vector2d(-1, -1), a.lowerLeft(new Vector2d(-1, -1)));
        assertEquals(new Vector2d(-1, 0), a.lowerLeft(new Vector2d(-1, 1)));
        assertEquals(new Vector2d(0, -1), a.lowerLeft(new Vector2d(1, -1)));
        assertEquals(new Vector2d(0, 0), a.lowerLeft(new Vector2d(1, 1)));
    }

    @Test
    public void shouldAddVectors() {
        Vector2d a = new Vector2d(1, 2);
        Vector2d b = new Vector2d(4, 9);

        assertEquals(new Vector2d(5, 11), a.add(b));
    }

    @Test
    public void shouldSubtractVectorFromVector() {
        Vector2d a = new Vector2d(1, 9);
        Vector2d b = new Vector2d(2, 1);

        assertEquals(new Vector2d(-1, 8), a.subtract(b));
    }

    @Test
    public void shouldBeOpposite() {
        Vector2d a = new Vector2d(-2, 4);

        assertEquals(new Vector2d(2, -4), a.opposite());
    }


}