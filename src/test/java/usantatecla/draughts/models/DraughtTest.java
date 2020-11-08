package usantatecla.draughts.models;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class DraughtTest {

    private Draught whiteDraught;

    @Before
    public void setUp() {
        this.whiteDraught = new Draught(Color.WHITE);
    }

    @Test
    public void shouldReturnErrorIfMoreThanOnePieceInDiagonal() {
        Coordinate origin = new Coordinate(0, 0);
        Coordinate target = new Coordinate(1, 1);

        Error actualError = this.whiteDraught.isCorrectDiagonalMovement(2,0, origin, target);

        assertThat(actualError, is(equalTo(Error.TOO_MUCH_EATINGS)));
    }

}
