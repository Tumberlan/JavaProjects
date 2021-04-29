import com.company.RunLogGame;

import java.io.IOException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TestTeleport {
    private final static Logger logger = Logger.getLogger(TestTeleport.class);
    @Test
    void CommandStringCheck() throws IOException {
        BasicConfigurator.configure();
        RunLogGame RLG = new RunLogGame();
        RLG.TakeCommRes("INIT 10 10 2 2");
        String comm1 = "TELEPORT 10 10 2";
        String comm2 = "TELEPORT 5 -1";
        String comm3 = "teleport 4 4";
        String comm4 = "TELEPORT";
        String comm5 = "TELEPORT 4 4";
        String comm6 = "TELEPORT 100 23";

        assertFalse(RLG.TakeCommRes(comm1));
        assertFalse(RLG.TakeCommRes(comm2));
        assertFalse(RLG.TakeCommRes(comm3));
        assertFalse(RLG.TakeCommRes(comm4));
        assertTrue(RLG.TakeCommRes(comm5));
        assertTrue(RLG.TakeCommRes(comm6));
        logger.info("TestTeleport Completed");
        RLG.TakeCommRes("end");
    }
}