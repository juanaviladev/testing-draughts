package usantatecla.draughts.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import usantatecla.draughts.models.Game;
import usantatecla.draughts.models.State;
import usantatecla.draughts.models.StateValue;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class CancelControllerTest extends ControllerTest {

    private CancelController controller;

    @Mock
    private Game game;

    private State state;

    @Before
    public void setUp() {
        initMocks(this);
        this.state = new State();
        this.controller = new CancelController(game, state);
    }


    @Override
    protected Controller createSUT(Game game, State state) {
        return new CancelController(game, state);
    }

    @Test
    public void shouldCancelGameWhenCancel() {
        this.controller.cancel();

        verify(game).cancel();
    }

    @Test
    public void shouldPassToNextStateWhenCancel() {
        this.controller.cancel();

        StateValue actualValue = state.getValueState();
        StateValue expectedValue = StateValue.IN_GAME;

        assertThat(actualValue,is(equalTo(expectedValue)));
    }

}
