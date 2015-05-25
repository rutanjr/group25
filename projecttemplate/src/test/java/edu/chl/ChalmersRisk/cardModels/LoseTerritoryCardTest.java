package edu.chl.ChalmersRisk.cardModels;

import edu.chl.ChalmersRisk.model.Continent;
import edu.chl.ChalmersRisk.model.Player;
import edu.chl.ChalmersRisk.model.Territory;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Tests LostTerritoryCard.
 * Created by viking on 22/05/15.
 */
public class LoseTerritoryCardTest {
    @Test
    public void testTurnCard() throws Exception {
        ArrayList<Territory> testList = new ArrayList<Territory>();
        Continent testCont = new Continent("Test Continent.", -1, testList);
        Territory testTer = new Territory("Test Territory 1.", testCont);
        Player testPlayer = new Player("Test Name","red");
        testTer.setnewOwner(testPlayer);

        ICard testCard = new LoseTerritoryCard(testPlayer);
        testCard.turnCard();

        assertTrue(true);
        assertFalse(testTer.getOwner()==testPlayer);
    }
}
