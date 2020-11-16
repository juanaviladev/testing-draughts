package usantatecla.draughts.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import usantatecla.draughts.models.Game;
import usantatecla.draughts.models.State;

import static org.mockito.Mockito.mock;
import static org.mockito.MockitoAnnotations.initMocks;

public abstract class ControllerTest {

    @Before
    public void setUp() {
        initMocks(this);
    }

    protected abstract Controller createSUT(Game game, State state);

    @Test(expected = AssertionError.class)
    public void shouldThrowErrorIfGameIsNull() {
        this.createSUT(null, mock(State.class));
    }

    @Test(expected = AssertionError.class)
    public void shouldThrowErrorIfStateIsNull() {
        this.createSUT(mock(Game.class), null);
    }

}
