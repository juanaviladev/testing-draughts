package usantatecla.draughts.models;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class CoordinateTest {

    private Coordinate coordinate;

    @Before
    public void setUp() {
        this.coordinate = new Coordinate(3, 3);
    }

    @Test(expected = AssertionError.class)
    public void shouldFailWhenGetDirectionWithNull() {
        //when
        this.coordinate.getDirection(null);
    }

    @Test
    public void shouldGetCorrectDirectionWithOtherCoordinate() {
        Coordinate otherCoordinate = new Coordinate(4,4);
        Direction expectedDirection = Direction.NE;

        Direction actualDirection = this.coordinate.getDirection(otherCoordinate);

        assertThat(actualDirection, is(equalTo(expectedDirection)));
    }

    @Test
    public void shouldGetNullDirectionWhenCoordinatesAreEqual() {
        Direction actualDirection = this.coordinate.getDirection(this.coordinate);

        assertThat(actualDirection, is(nullValue()));
    }

    @Test
    public void shouldBeBlackWhenSumIsEven() {
        Coordinate evenSumCoordinate = new Coordinate(1,0);

        assertTrue(evenSumCoordinate.isBlack());
    }

    @Test
    public void shouldBeLastWhenRowIsOnUpperLimit() {
        Coordinate lastRowCoordinate = new Coordinate(7,0);

        assertTrue(lastRowCoordinate.isLast());
    }

    @Test
    public void shouldBeFirstWhenRowIsOnLowerLimit() {
        Coordinate firstRowCoordinate = new Coordinate(0,0);

        assertTrue(firstRowCoordinate.isFirst());
    }

    @Test
    public void shouldReturnDiagonalCoordinatesInAllDirections() {
        Coordinate middleCoordinate = new Coordinate(3,3);

        List<Coordinate> expectedCoordinates = Arrays.asList(
                new Coordinate(2,2),
                new Coordinate(4,4),
                new Coordinate(2,4),
                new Coordinate(4,2)
        );
        List<Coordinate> coordinates = middleCoordinate.getDiagonalCoordinates(1);

        assertThat(coordinates, containsInAnyOrder(expectedCoordinates.toArray()));
    }

    @Test
    public void shouldReturnDiagonalCoordinatesAtBoardLowerLimit() {
        Coordinate middleCoordinate = new Coordinate(0,0);

        List<Coordinate> expectedCoordinates = Collections.singletonList(
                new Coordinate(1, 1)
        );
        List<Coordinate> coordinates = middleCoordinate.getDiagonalCoordinates(1);

        assertThat(coordinates, containsInAnyOrder(expectedCoordinates.toArray()));
    }

    @Test
    public void shouldReturnDiagonalCoordinatesAtBoardUpperLimit() {
        Coordinate middleCoordinate = new Coordinate(8,8);

        List<Coordinate> expectedCoordinates = Collections.singletonList(
                new Coordinate(7, 7)
        );
        List<Coordinate> coordinates = middleCoordinate.getDiagonalCoordinates(1);

        assertThat(coordinates, containsInAnyOrder(expectedCoordinates.toArray()));
    }

    @Test
    public void shouldReturnTrueWhenTwoCoordinatesAreTheSame() {
        Coordinate first = new Coordinate(0,0);
        Coordinate second = new Coordinate(0,0);

        assertEquals(first,second);
    }

    @Test(expected = AssertionError.class)
    public void shouldFailGettingDiagonalDistanceWhenAreNotOnDiagonal() {
        Coordinate first = new Coordinate(0,0);
        Coordinate second = new Coordinate(1,0);

        first.getDiagonalDistance(second);
    }

    @Test
    public void shouldReturnDiagonalDistance() {
        Coordinate first = new Coordinate(0,0);
        Coordinate second = new Coordinate(1,1);

        int expectedDistance = 1;
        int actualDistance = first.getDiagonalDistance(second);

        assertThat(actualDistance, is(equalTo(expectedDistance)));
    }

    @Test(expected = AssertionError.class)
    public void shouldFailWhenGettingBetweenDiagonalBetweenCoordinatesNotDiagonal() {
        Coordinate first = new Coordinate(0,0);
        Coordinate second = new Coordinate(1,0);

        first.getBetweenDiagonalCoordinates(second);
    }

    @Test
    public void shouldReturnAllCoordinatesOnDiagonal() {
        Coordinate first = new Coordinate(0,0);
        Coordinate second = new Coordinate(3,3);

        List<Coordinate> expectedCoordinates = Arrays.asList(
                new Coordinate(1, 1),
                new Coordinate(2, 2)
        );
        List<Coordinate> actualCoordinates = first.getBetweenDiagonalCoordinates(second);

        assertThat(actualCoordinates, containsInAnyOrder(expectedCoordinates.toArray()));
    }

}
