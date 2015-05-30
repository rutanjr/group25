package edu.chl.ChalmersRisk.cardModels;

import edu.chl.ChalmersRisk.model.Continent;
import edu.chl.ChalmersRisk.model.Territory;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertFalse;

/**
 * Created by Björn Bergqvist on 21/05/15.
 */
public class TerritoryTroopCardTest {
    ArrayList<Territory> testList = new ArrayList<Territory>();
    Continent testCont = new Continent("Test Continent.", -1, testList);
    Territory testTer = new Territory("Test Territory.", testCont);
    TerritoryTroopCard testCard = new TerritoryTroopCard(testTer,5);

    @Test
    public void testGetTitle() throws Exception {
        assertTrue(testCard.getTitle().equals("Reinforcements"));
    }

    @Test
    public void testGetMessage() throws Exception {
        assertTrue(testCard.getMessage().equals("More reinforcements has arrived and territory " + testTer.getName() + " will receive " + 5 + " additional troops"));
    }

    @Test
    public void testTurnCard() throws Exception {
        int troopsBefore = testTer.getAmountOfTroops();
        testCard.turnCard();
        int troopsAfter = testTer.getAmountOfTroops();

        assertFalse(troopsAfter==troopsBefore);
        assertTrue(troopsAfter==troopsBefore+5);



    }
}
