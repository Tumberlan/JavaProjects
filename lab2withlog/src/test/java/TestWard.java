import com.company.RunLogGame;

import java.io.IOException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TestWard{
    private final static Logger logger = Logger.getLogger(TestWard.class);
    @Test
    void CommandStringCheck() throws IOException {
        BasicConfigurator.configure();
        RunLogGame RLG = new RunLogGame();
        RLG.TakeCommRes("INIT 10 10 2 2");
        String comm1 = "WARD CIRCLE";
        String comm2 = "WARD 1 2";
        String comm3 = "ward";
        String comm4 = "WARD";

        assertFalse(RLG.TakeCommRes(comm1));
        assertFalse(RLG.TakeCommRes(comm2));
        assertFalse(RLG.TakeCommRes(comm3));
        assertTrue(RLG.TakeCommRes(comm4));
        logger.info("TestWard Completed");
        RLG.TakeCommRes("end");
    }
}
