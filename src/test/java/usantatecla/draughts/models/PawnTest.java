package usantatecla.draughts.models;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class PawnTest {

    private Pawn whitePawn;
    private Pawn blackPawn;

    @Before
    public void setUp() {
        this.whitePawn = new Pawn(Color.WHITE);
        this.blackPawn = new Pawn(Color.BLACK);
    }

    @Test
    public void shouldAdvanceIfWhiteAndTargetIsUnderOrigin() {
        Coordinate origin = new Coordinate(1, 0);
        Coordinate target = new Coordinate(0, 0);

        boolean actualIsAdvanced = this.whitePawn.isAdvanced(origin, target);

        assertTrue(actualIsAdvanced);
    }

    @Test
    public void shouldAdvanceIfBlackAndTargetIsOverOrigin() {
        Coordinate origin = new Coordinate(0, 0);
        Coordinate target = new Coordinate(1, 0);

        boolean actualIsAdvanced = this.blackPawn.isAdvanced(origin, target);

        assertTrue(actualIsAdvanced);
    }

    @Test
    public void shouldReturnErrorWhenCoordinatesAreNotInDiagonal() {
        Coordinate origin = new Coordinate(0, 0);
        Coordinate target = new Coordinate(1, 0);

        Error actualError = this.blackPawn.isCorrectMovement(emptyList(), 0, origin, target);

        assertThat(actualError, is(equalTo(Error.NOT_DIAGONAL)));
    }

    @Test
    public void shouldReturnErrorWhenSameColorPieceIsBetween() {
        Coordinate origin = new Coordinate(0, 0);
        Coordinate target = new Coordinate(1, 1);

        Error actualError = this.blackPawn.isCorrectMovement(
                Collections.singletonList(this.blackPawn), 0, origin, target
        );

        assertThat(actualError, is(equalTo(Error.COLLEAGUE_EATING)));
    }

    @Test
    public void shouldReturnErrorWhenDistanceIsOverMax() {
        Coordinate origin = new Coordinate(0, 0);
        Coordinate target = new Coordinate(3, 3);

        Error actualError = this.blackPawn.isCorrectMovement(
                emptyList(), 0, origin, target
        );

        assertThat(actualError, is(equalTo(Error.TOO_MUCH_ADVANCED)));
    }

    @Test
    public void shouldReturnErrorWhenMaxDistanceMovementWithoutEating() {
        Coordinate origin = new Coordinate(0, 0);
        Coordinate target = new Coordinate(2, 2);

        Error actualError = this.blackPawn.isCorrectMovement(
                emptyList(), 0, origin, target
        );

        assertThat(actualError, is(equalTo(Error.WITHOUT_EATING)));
    }

    @Test
    public void shouldReturnErrorIfAdvancingOnReverse() {
        Coordinate origin = new Coordinate(0, 0);
        Coordinate target = new Coordinate(2, 2);

        Error actualError = this.whitePawn.isCorrectMovement(
                emptyList(), 0, origin, target
        );

        assertThat(actualError, is(equalTo(Error.NOT_ADVANCED)));
    }

    @Test
    public void shouldReturnNullWhenMovementIsCorrect() {
        Coordinate origin = new Coordinate(0, 0);
        Coordinate target = new Coordinate(1, 1);

        Error actualError = this.blackPawn.isCorrectMovement(
                emptyList(), 0, origin, target
        );

        assertThat(actualError, is(nullValue()));
    }

    @Test
    public void shouldReturnNullWhenEatingMovementIsCorrect() {
        Coordinate origin = new Coordinate(0, 0);
        Coordinate target = new Coordinate(2, 2);

        Error actualError = this.blackPawn.isCorrectMovement(
                singletonList(this.whitePawn), 0, origin, target
        );

        assertThat(actualError, is(nullValue()));
    }


    @Test
    public void shouldBeOnLimitIfIsWhiteAndIsOnFirstRow() {
        Coordinate firstRow = new Coordinate(0, 0);

        boolean actualIsLimit = this.whitePawn.isLimit(firstRow);

        assertTrue(actualIsLimit);
    }

    @Test
    public void shouldBeOnLimitIfIsBlackAndIsOnLastRow() {
        Coordinate lastRow = new Coordinate(7, 0);

        boolean actualIsLimit = this.blackPawn.isLimit(lastRow);

        assertTrue(actualIsLimit);
    }

}
