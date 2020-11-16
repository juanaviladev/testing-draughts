package usantatecla.draughts.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import usantatecla.draughts.models.Coordinate;
import usantatecla.draughts.models.Error;
import usantatecla.draughts.models.Game;
import usantatecla.draughts.models.State;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class PlayControllerTest extends ControllerTest {

    @InjectMocks
    private PlayController controller;

    @Mock
    private Game game;

    @Mock
    private State state;

    @Mock
    private InteractorControllersVisitor visitor;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Override
    protected Controller createSUT(Game game, State state) {
        return new PlayController(game, state);
    }

    @Test(expected = AssertionError.class)
    public void shouldThrowErrorWhenWhenMoveFails() {
        this.controller.move();
    }

    @Test
    public void shouldReturnErrorWhenWhenMoveFails() {
        Coordinate firstCoordinate = mock(Coordinate.class);
        Coordinate secondCoordinate = mock(Coordinate.class);

        when(game.move(firstCoordinate,secondCoordinate)).thenReturn(Error.NOT_EMPTY_TARGET);

        Error actualValue = this.controller.move(firstCoordinate, secondCoordinate);

        assertThat(actualValue, is(equalTo(Error.NOT_EMPTY_TARGET)));
    }

    @Test
    public void shouldCancelGameWhenCancel() {
        this.controller.cancel();
        verify(game).cancel();
    }

    @Test
    public void shouldAcceptVisitWhenIsInvoked() {
        this.controller.accept(visitor);
        verify(visitor).visit(controller);
    }

    @Test(expected = AssertionError.class)
    public void shouldThrowErrorWhenAcceptingVisitorIsNull() {
        this.controller.accept(null);
    }

}
