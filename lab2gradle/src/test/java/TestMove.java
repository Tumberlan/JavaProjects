import com.company.RunLogGame;

import java.io.IOException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TestMove{
    private final static Logger logger = Logger.getLogger(TestMove.class);
    @Test
    void CommandStringCheck() throws IOException {
        BasicConfigurator.configure();
        RunLogGame RLG = new RunLogGame();
        RLG.TakeCommRes("INIT 10 10 2 2");
        String comm1 = "move U 1";
        String comm2 = "MOVE U 3";
        String comm3 = "MOVE U 100";
        String comm4 = "MOVE UP 3";
        String comm5 = "MOVE D";
        String comm6 = "MOVE D 3 2";

        assertFalse(RLG.TakeCommRes(comm1));
        assertTrue(RLG.TakeCommRes(comm2));
        assertTrue(RLG.TakeCommRes(comm3));
        assertFalse(RLG.TakeCommRes(comm4));
        assertFalse(RLG.TakeCommRes(comm5));
        assertFalse(RLG.TakeCommRes(comm6));
        logger.info("TestMove Completed");
        RLG.TakeCommRes("end");
    }
}