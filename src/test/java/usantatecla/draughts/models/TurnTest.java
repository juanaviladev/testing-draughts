package usantatecla.draughts.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TurnTest {

    private Turn turn;

    @Before
    public void setUp() {
        this.turn = new Turn();
    }

    @Test
    public void shouldStartWithWhiteColor() {
        assertEquals(Color.WHITE, this.turn.getColor());
    }

    @Test
    public void shouldReturnOppositeWhenTurnChanges() {
        this.turn.change();
        assertEquals(Color.BLACK, this.turn.getColor());
    }

    @Test
    public void shouldReturnOppositeColorToTheActual() {
        Color actualColor = this.turn.getColor();
        Color oppositeColor = this.turn.getOppositeColor();

        assertNotEquals(actualColor, oppositeColor);
    }

    @Test
    public void shouldReturnToStartWithTwoChanges() {
        this.turn.change();
        this.turn.change();

        assertEquals(Color.WHITE, this.turn.getColor());
    }
}
