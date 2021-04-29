import com.company.RunLogGame;

import java.io.IOException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TestDraw{
    private final static Logger logger = Logger.getLogger(TestDraw.class);
    @Test
    void CommandStringCheck() throws IOException {
        BasicConfigurator.configure();
        RunLogGame RLG = new RunLogGame();
        RLG.TakeCommRes("INIT 10 10 2 2");
        String comm1 = "DRAW CIRCLE";
        String comm2 = "DRAW 1 2";
        String comm3 = "draw";
        String comm4 = "DRAW";

        assertFalse(RLG.TakeCommRes(comm1));
        assertFalse(RLG.TakeCommRes(comm2));
        assertFalse(RLG.TakeCommRes(comm3));
        assertTrue(RLG.TakeCommRes(comm4));
        logger.info("TestDraw Completed");
        RLG.TakeCommRes("end");
    }
}
