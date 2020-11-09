package usantatecla.draughts.models;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class GameTest {

    private Game game;

    @Before
    public void setUp() {
        this.game = new Game();
    }

    @Test
    public void shouldReturnErrorWhenMovingPiecesOfOpponent() {
        Coordinate blacksPiece = new Coordinate(2, 1);
        Coordinate emptyCoordinate = new Coordinate(3, 2);

        Error actualError = this.game.move(blacksPiece, emptyCoordinate);
        Error expectedError = Error.OPPOSITE_PIECE;

        assertThat(actualError, is(equalTo(expectedError)));
    }

    @Test
    public void shouldReturnErrorWhenExceedingMovements() {
        Coordinate origin = new Coordinate(5, 0);
        Coordinate firstTarget = new Coordinate(4, 1);
        Coordinate secondTarget = new Coordinate(3, 2);

        Error actualError = this.game.move(origin, firstTarget, secondTarget);
        Error expectedError = Error.TOO_MUCH_JUMPS;

        assertThat(actualError, is(equalTo(expectedError)));
    }

    @Test
    public void shouldReturnErrorWhenJumpingToOccupiedCoordinate() {
        Coordinate origin = new Coordinate(7, 0);
        Coordinate firstTarget = new Coordinate(6, 1);

        Error actualError = this.game.move(origin, firstTarget);
        Error expectedError = Error.NOT_EMPTY_TARGET;

        assertThat(actualError, is(equalTo(expectedError)));
    }

    @Test
    public void shouldReturnErrorWhenOriginIsEmpty() {
        Coordinate origin = new Coordinate(7, 1);
        Coordinate firstTarget = new Coordinate(6, 2);

        Error actualError = this.game.move(origin, firstTarget);
        Error expectedError = Error.EMPTY_ORIGIN;

        assertThat(actualError, is(equalTo(expectedError)));
    }

    @Test
    public void shouldConvertPawnInDraughtWhenReachesTheLimit() {
        Board board = new BoardBuilder()
                .row("        ")
                .row("  b     ")
                .row(" n      ")
                .row("n b     ")
                .row("        ")
                .row("        ")
                .row("        ")
                .row("        ")
                .build();

        Game gameScenario = new Game(board);

        Coordinate origin = new Coordinate(1, 2);
        Coordinate target = new Coordinate(0, 1);

        Error actualError = gameScenario.move(origin, target);
        Piece actualPiece = gameScenario.getPiece(target);
        Piece expectedPiece = new Draught(Color.WHITE);

        assertThat(actualError, is(nullValue()));
        assertThat(actualPiece, is(equalTo(expectedPiece)));
    }

    @Test
    public void shouldBeBlockedWhenHasNoMorePieces() {
        Board board = new BoardBuilder()
                .row("        ")
                .row("  n     ")
                .row(" n      ")
                .row("n n     ")
                .row("        ")
                .row("        ")
                .row("        ")
                .row("        ")
                .build();

        Game gameScenario = new Game(board);
        boolean actualIsBlocked = gameScenario.isBlocked();

        assertTrue(actualIsBlocked);
    }

    @Test
    public void shouldJumpWhenHasTwoDiagonalOppositePieces() {
        Board board = new BoardBuilder()
                .row("        ")
                .row("    n   ")
                .row("        ")
                .row("  n     ")
                .row(" b      ")
                .row("  n     ")
                .row("        ")
                .row("        ")
                .build();

        Game gameScenario = new Game(board);

        Coordinate origin = new Coordinate(4, 1);
        Coordinate target = new Coordinate(2, 3);
        Coordinate secondTarget = new Coordinate(0, 5);

        Error actualError = gameScenario.move(origin, target, secondTarget);

        assertThat(actualError, is(nullValue()));
    }

    @Test
    public void shouldMoveToDiagonalAdjacentCellWhenIsEmpty() {
        Coordinate origin = new Coordinate(5, 0);
        Coordinate target = new Coordinate(4, 1);

        Error actualError = this.game.move(origin, target);

        assertThat(actualError, is(nullValue()));
    }

    @Test
    public void shouldChangeTurnWhenGameIsCancelled() {
        this.game.cancel();

        Color actualColor = this.game.getTurnColor();
        Color expectedColor = Color.BLACK;

        assertThat(actualColor, is(equalTo(expectedColor)));
    }

}
