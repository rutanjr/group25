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
 * Created by Björn Bergqvist on 22/05/15.
 */
public class LoseTerritoryCardTest {
    @Test
    public void testTurnCard() throws Exception {
        ArrayList<Territory> testList = new ArrayList<Territory>();
        Continent testCont = new Continent("Test Continent.", -1, testList);
        Territory testTer1 = new Territory("Test Territory 1.", testCont);
        Territory testTer2 = new Territory("Test Territory 2.", testCont);
        Player testPlayer1 = new Player("Test Name","red");
        Player testPlayer2 = new Player("Test Name2","red");

        testPlayer1.addTerritory(testTer1);
        testPlayer2.addTerritory(testTer2);


        ICard testCard = new LoseTerritoryCard(testPlayer1, testPlayer2);
        testCard.turnCard();

        assertTrue(true);
        assertFalse(testTer1.getOwner()==testPlayer1 && testTer2.getOwner()==testPlayer2);
    }
}
