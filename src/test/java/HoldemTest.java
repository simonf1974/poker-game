import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class HoldemTest {

    @Test
    public void getScores() {
        Map<String, Integer> playerScores
                = Holdem.getScores("8D 8S", Lists.newArrayList("AD KS 3D 3C AD", "AC KH 2C 3C 8C"));
        assertTrue(playerScores.get("AC KH 2C 3C 8C") > playerScores.get("AD KS 3D 3C AD"));

        playerScores
                = Holdem.getScores("8D 8S", "AD KS 3D 3C AD", "AC KH 2C 3C 8C");
        assertTrue(playerScores.get("AC KH 2C 3C 8C") > playerScores.get("AD KS 3D 3C AD"));

        String formattedScores
                = Holdem.getFormattedScores("8D 8S", "AD KS 3D 3C AD", "AC KH 2C 3C 8C");
    }
}