package usantatecla.draughts.models;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class GetCoordinateFormatTest {

    @Parameterized.Parameter(0) public String format;
    @Parameterized.Parameter(1) public Coordinate expectedCoordinate;

    @Parameterized.Parameters(name = "With format \"{0}\", coordinate should be {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "12", new Coordinate(0,1) }, //limite inicio filas
                { "21", new Coordinate(1,0) }, //limite inicio columnas
                { "11", new Coordinate(0,0) }, //limite inicios
                { "82", new Coordinate(7,1) }, //limite final filas
                { "28", new Coordinate(1,7) }, //limite final columnas
                { "88", new Coordinate(7,7) }, //limite finales
                { "92", null }, //superior filas
                { "29", null }, //superior columnas
                { "02", null }, //inferior filas
                { "20", null }, //inferior columnas
                { "99", null }, //superior ambos
                { "00", null }, //inferior ambos
                { inValidFormat(), null },
        });
    }

    private static String inValidFormat() {
        return "%d$ada";
    }

    @Test
    public void testGetCoordinate() {
        //when
        Coordinate actualResult = Coordinate.getInstance(format);

        //then
        assertEquals(expectedCoordinate, actualResult);
    }
}
