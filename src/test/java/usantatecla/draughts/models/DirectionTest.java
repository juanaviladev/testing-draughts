package usantatecla.draughts.models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DirectionTest {

    @Test
    public void shouldReturnCorrectCoordinateWithDistance() {
        //given
        Direction direction = Direction.NE;

        //when
        Coordinate actualCoordinate = direction.getDistanceCoordinate(2);

        //then
        Coordinate expectedCoordinate = new Coordinate(2,2);
        assertEquals(expectedCoordinate,actualCoordinate);
    }

}
