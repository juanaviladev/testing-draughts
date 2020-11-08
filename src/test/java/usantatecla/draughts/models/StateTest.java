package usantatecla.draughts.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class StateTest {

    private StateBuilder stateBuilder;

    @Before
    public void setUp() {
        this.stateBuilder = new StateBuilder();
    }

    @Test
    public void shouldStartWithInitialState() {
        State state = stateBuilder.build();

        assertEquals(StateValue.INITIAL, state.getValueState());
    }

    @Test
    public void shouldReturnCorrectStateWhenNextState() {
        State state = stateBuilder.build();

        state.next();
        assertEquals(StateValue.IN_GAME, state.getValueState());

        state.next();
        assertEquals(StateValue.FINAL, state.getValueState());

        state.next();
        assertEquals(StateValue.EXIT, state.getValueState());
    }

    @Test(expected = AssertionError.class)
    public void shouldFailWhenNextInExitState() {
        State state = stateBuilder
                .withStateValue(StateValue.EXIT)
                .build();

        state.next();
    }

    @Test
    public void shouldReturnToInitialWhenReset() {
        State state = stateBuilder
                .withStateValue(StateValue.IN_GAME)
                .build();

        state.reset();

        assertEquals(StateValue.INITIAL, state.getValueState());
    }

}
