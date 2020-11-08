package usantatecla.draughts.models;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ColorTestInitialRow.class,
        ColorTestInitialColor.class,
        StateTest.class,
        DirectionTest.class,
        CoordinateTest.class,
        TurnTest.class,
        GetCoordinateFormatTest.class,
        PawnTest.class,
        DraughtTest.class,
})
public class AllTest { }
