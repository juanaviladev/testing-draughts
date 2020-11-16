package usantatecla.draughts.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import usantatecla.draughts.models.Coordinate;
import usantatecla.draughts.models.Game;
import usantatecla.draughts.models.State;
import usantatecla.draughts.models.StateValue;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class MoveControllerTest extends ControllerTest {

    private MoveController controller;

    @Mock
    private Game game;

    private State state;

    @Before
    public void setUp() {
        initMocks(this);
        this.state = new State();
        this.controller = new MoveController(game, state);
    }

    @Override
    protected Controller createSUT(Game game, State state) {
        return new MoveController(game, state);
    }

    @Test(expected = AssertionError.class)
    public void shouldThrowErrorIfMoveCoordinatesAreBelowMinimum() {
        Coordinate coordinate = mock(Coordinate.class);

        this.controller.move(coordinate);
    }

    @Test(expected = AssertionError.class)
    public void shouldThrowErrorIfMoveCoordinatesAreNull() {
        this.controller.move(null);
    }

    @Test
    public void shouldMoveIfCoordinatesAreValid() {
        Coordinate firstCoordinate = mock(Coordinate.class);
        Coordinate secondCoordinate = mock(Coordinate.class);

        this.controller.move(firstCoordinate, secondCoordinate);

        verify(game).move(firstCoordinate, secondCoordinate);
    }

    @Test
    public void shouldPassToNextStateWhenGameIsBlocked() {
       when(game.isBlocked()).thenReturn(true);

        Coordinate firstCoordinate = mock(Coordinate.class);
        Coordinate secondCoordinate = mock(Coordinate.class);

        this.controller.move(firstCoordinate, secondCoordinate);

        StateValue actualState = this.state.getValueState();

        assertThat(actualState, is(equalTo(StateValue.IN_GAME)));
    }

}
