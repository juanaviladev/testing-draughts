package usantatecla.draughts.views;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import usantatecla.draughts.controllers.ResumeController;
import usantatecla.draughts.controllers.StartController;
import usantatecla.draughts.utils.YesNoDialog;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ResumeViewTest extends SubViewTest {

    @InjectMocks
    private ResumeView resumeView;

    @Mock
    private ResumeController controller;

    @Mock
    private YesNoDialog yesNoDialog;

    @Override
    public void setUp() {
        super.setUp();
    }

    @Test
    public void shouldResetGameIfUserWantToResume() {
        when(this.yesNoDialog.read("¿Queréis jugar otra")).thenReturn(true);
        this.resumeView.interact(controller);
        verify(this.controller).reset();
    }

    @Test
    public void shouldCallNextIfUserDontWantToResume() {
        when(this.yesNoDialog.read("¿Queréis jugar otra")).thenReturn(false);
        this.resumeView.interact(controller);
        verify(this.controller).next();
    }

    @Test(expected = AssertionError.class)
    public void shouldThrowErrorWhenControllerIsNull() {
        this.resumeView.interact(null);
    }
}
