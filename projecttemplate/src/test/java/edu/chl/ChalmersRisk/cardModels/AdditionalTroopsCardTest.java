package edu.chl.ChalmersRisk.cardModels;

import edu.chl.ChalmersRisk.model.Player;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Björn Bergqvist on 22/05/15.
 */
public class AdditionalTroopsCardTest {
    @Test
    public void testTurnCard() throws Exception {
        Player testPlayer = new Player("name","red");
        int troopsBefore = testPlayer.getTroopsToPlace().size();

        ICard testCard = new AdditionalTroopsCard(testPlayer,5);
        testCard.turnCard();

        assertFalse(troopsBefore==testPlayer.getTroopsToPlace().size());
        assertTrue(troopsBefore==testPlayer.getTroopsToPlace().size()-5);
    }
}
