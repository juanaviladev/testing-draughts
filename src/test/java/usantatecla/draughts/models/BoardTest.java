package usantatecla.draughts.models;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

public class BoardTest {

    private BoardBuilder boardBuilder;
    private Board board;

    @Before
    public void setUp() {
        this.boardBuilder = new BoardBuilder();
        this.board = this.boardBuilder
                .row(" n n n n")
                .row("n n n n ")
                .row(" n n n n")
                .row("        ")
                .row("        ")
                .row("b b b b ")
                .row(" b b b b")
                .row("b b b b ")
                .build();
    }

    @Test
    public void shouldReturnPieceCorrespondingToCoordinate() {
        Piece actualPiece = this.board.getPiece(new Coordinate(0,1));
        Piece expectedPiece = new Pawn(Color.BLACK);

        assertThat(actualPiece, is(equalTo(expectedPiece)));
    }

    @Test
    public void shouldReturnNullWhenThereIsNotPiece() {
        Piece actualPiece = this.board.getPiece(new Coordinate(0,0));

        assertThat(actualPiece, is(nullValue()));
    }

    @Test
    public void shouldReturnNullWhenPieceIsRemoved() {
        this.board.remove(new Coordinate(0,1));
        Piece actualPiece = this.board.getPiece(new Coordinate(0,1));

        assertThat(actualPiece, is(nullValue()));
    }

    @Test
    public void shouldMoveAPieceToEmptyCoordinate() {
        Coordinate origin = new Coordinate(0,1);
        Coordinate target = new Coordinate(0,2);

        this.board.move(origin, target);

        Piece actualPiece = this.board.getPiece(target);
        Piece expectedPiece = new Pawn(Color.BLACK);

        assertThat(actualPiece, is(equalTo(expectedPiece)));
    }

    @Test
    public void shouldReturnPieceColorOfAnOccupiedCoordinate() {
        Coordinate occupiedCoordinate = new Coordinate(0,1);

        Color actualColor = this.board.getColor(occupiedCoordinate);
        Color expectedColor = Color.BLACK;

        assertThat(actualColor, is(equalTo(expectedColor)));
    }

    @Test
    public void shouldReturnNullColorWhenCoordinateIsEmpty() {
        Coordinate emptyCoordinate = new Coordinate(0,0);

        Color actualColor = this.board.getColor(emptyCoordinate);

        assertThat(actualColor, is(nullValue()));
    }

    @Test
    public void shouldReturnPiecesBetweenDiagonalCoordinates() {
        Coordinate origin = new Coordinate(0,1);
        Coordinate target = new Coordinate(3,4);

        List<Piece> actualPieces = this.board.getBetweenDiagonalPieces(origin,target);
        Piece expectedPiece = new Pawn(Color.BLACK);

        assertThat(actualPieces, containsInAnyOrder(expectedPiece,expectedPiece));
    }


    @Test(expected = AssertionError.class)
    public void shouldNotMoveAnEmptyCoordinate() {
        Coordinate origin = new Coordinate(0,0);
        Coordinate target = new Coordinate(1,0);

        this.board.move(origin, target);
    }

    @Test(expected = AssertionError.class)
    public void shouldThrowErrorWhenCoordinateIsOutOfBounds() {
        this.board.remove(new Coordinate(-1,-1));
    }

    @Test(expected = AssertionError.class)
    public void shouldThrowErrorWhenNotPieceToRemove() {
        this.board.remove(new Coordinate(0,0));
    }

}
