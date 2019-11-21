import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class HoldemTest {

    @Test
    public void getWinner() {
        Map<String, Integer> playerScores
                = Holdem.getWinner(Lists.newArrayList("AD KS 3D 3C AD", "AC KH 2C 3C 8C"), "8D 8S");
        assertTrue(playerScores.get("AC KH 2C 3C 8C") > playerScores.get("AD KS 3D 3C AD"));
    }
}