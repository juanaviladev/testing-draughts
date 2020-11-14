package usantatecla.draughts.views;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import usantatecla.draughts.utils.Console;

import static org.mockito.MockitoAnnotations.initMocks;

public abstract class SubViewTest {

    @Mock
    protected Console console;

    @Before
    public void setUp(){
        initMocks(this);
    }

}
