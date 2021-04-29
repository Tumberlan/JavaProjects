import com.company.CommandFabric;
import com.company.GameDraw;
import com.company.RunLogGame;

import java.io.IOException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TestInit {
    private final static Logger logger = Logger.getLogger(TestInit.class);
    @Test
    void CommandStringCheck() throws IOException {
        BasicConfigurator.configure();
        RunLogGame RLG = new RunLogGame();
        String comm1 = "INIT 10 10 2 3";
        String comm2 = "INIT 10 10 12 2";
        String comm3 = "INIT -5 5 2 2";
        String comm4 = "INIT 10 10 -1 2";
        String comm5 = "INIT 0 0 2 2";
        String comm6 = "init 10 10 2 3";
        String comm7 = "INIT";

        assertTrue(RLG.TakeCommRes(comm1));
        assertTrue(RLG.TakeCommRes(comm2));
        assertFalse(RLG.TakeCommRes(comm3));
        assertFalse(RLG.TakeCommRes(comm4));
        assertFalse(RLG.TakeCommRes(comm5));
        assertFalse(RLG.TakeCommRes(comm6));
        assertFalse(RLG.TakeCommRes(comm7));
        logger.info("TestInit Completed");
        RLG.TakeCommRes("end");
    }
}
