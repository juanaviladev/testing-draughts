package usantatecla.draughts.views;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import usantatecla.draughts.controllers.StartController;

import static org.mockito.Mockito.verify;

public class StartViewTest extends SubViewTest {

    @InjectMocks
    private StartView startView;

    @Mock
    private StartController controller;

    @Override
    public void setUp() {
        super.setUp();
    }

    @Test
    public void shouldShowTitleMessageWhenInteract() {
        this.startView.interact(controller);
        verify(this.console).writeln("Draughts");
    }

    //TODO: ¿Cómo probamos GameView sin cambiar cod de produccion?

}
