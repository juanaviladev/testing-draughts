package usantatecla.draughts.models;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.runners.Parameterized.Parameter;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ColorTestInitialColor {

    @Parameter(0) public Coordinate coordinate;
    @Parameter(1) public Color expectedInitialColor;

    @Parameters(name = "At coordinate {0} should be color {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { firstRowBlackCell(), Color.BLACK },
                { lastRowBlackCell(), Color.WHITE },
                { firstRowBlankCell(), null },
                { invalidCoordinate(), null },
                { inTheMiddleBlackCell(), null },
        });
    }

    private static Coordinate inTheMiddleBlackCell() {
        return new Coordinate(4,4);
    }

    private static Coordinate firstRowBlankCell() {
        return new Coordinate(0,0);
    }

    @Test
    public void testGetInitialColor() {
        //when
        Color actualResult = Color.getInitialColor(coordinate);

        //then
        assertEquals(expectedInitialColor, actualResult);
    }

    private static Coordinate firstRowBlackCell() {
        return new Coordinate(0, 1);
    }

    private static Coordinate lastRowBlackCell() {
        return new Coordinate(7, 0);
    }

    private static Coordinate invalidCoordinate() {
        return new Coordinate(-1,-1);
    }

}
