package usantatecla.draughts.controllers;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import usantatecla.draughts.models.ColorTestInitialRow;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        StartControllerTest.class,
        ResumeControllerTest.class,
        CancelControllerTest.class,
        MoveControllerTest.class,
        PlayControllerTest.class,
})
public class AllTest { }
