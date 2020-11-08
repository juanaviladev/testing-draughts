package usantatecla.draughts.models;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.junit.runners.Parameterized.*;

@RunWith(Parameterized.class)
public class ColorTestInitialRow {

    @Parameter(0) public Color color;
    @Parameter(1) public int row;
    @Parameter(2) public boolean expectedIsInitialRow;

    @Parameters(name = "Color {0} with initial row {1} should be {2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { Color.WHITE, outOfBoundsNegativeRow(), false },
                { Color.BLACK, outOfBoundsNegativeRow(), false },

                { Color.WHITE, outOfBoundsPositiveRow(), false },
                { Color.BLACK, outOfBoundsPositiveRow(), false },

                { Color.WHITE, rowInsideLimitsOf(Color.WHITE), true },
                { Color.BLACK, rowInsideLimitsOf(Color.BLACK), true },

                { Color.WHITE, rowOutsideLeftLimitOf(Color.WHITE), false },
                { Color.BLACK, rowOutsideLeftLimitOf(Color.BLACK), false },

                { Color.WHITE, rowOutsideRightLimitOf(Color.WHITE), false },
                { Color.BLACK, rowOutsideRightLimitOf(Color.BLACK), false },
        });
    }

    @Test
    public void testIsInitialRow() {
        //when
        boolean actualResult = color.isInitialRow(row);

        //then
        assertEquals(expectedIsInitialRow, actualResult);
    }

    private static int rowInsideLimitsOf(Color color) {
        if(color == Color.WHITE) return 5;
        if(color == Color.BLACK) return 2;
        throw new IllegalArgumentException("Not expected color: "+color);
    }

    private static int rowOutsideLeftLimitOf(Color color) {
        if(color == Color.WHITE) return 4;
        if(color == Color.BLACK) return -1;
        throw new IllegalArgumentException("Not expected color: "+color);
    }

    private static int rowOutsideRightLimitOf(Color color) {
        if(color == Color.WHITE) return 8;
        if(color == Color.BLACK) return 4;
        throw new IllegalArgumentException("Not expected color: "+color);
    }

    private static int outOfBoundsPositiveRow() {
        //TODO: ¿Mejor atarlo a Coordinate.getDimension() para hacer menos
        // frágil el test? Al fin y al cabo no depende de ese cambio
        return 8;
    }

    private static int outOfBoundsNegativeRow() {
        return -1;
    }

}
