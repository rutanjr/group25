package edu.chl.ChalmersRisk.cardModels;

import edu.chl.ChalmersRisk.model.Continent;
import edu.chl.ChalmersRisk.model.Player;
import edu.chl.ChalmersRisk.model.Territory;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by viking on 22/05/15.
 */
public class TerritoryChangeCardTest {
    @Test
    public void testTurnCard() throws Exception {
        ArrayList<Territory> testList = new ArrayList<Territory>();
        Continent testCont = new Continent("Test Continent.", -1, testList);
        Territory testTer1 = new Territory("Test Territory 1.", testCont);
        Territory testTer2 = new Territory("Test Territory 2.", testCont);

        Player testPlayer1 = new Player("name1","red");
        Player testPlayer2 = new Player("name2","blue");

        testTer1.setnewOwner(testPlayer1);
        testTer2.setnewOwner(testPlayer2);

        TerritoryChangeCard testCard = new TerritoryChangeCard(testPlayer1, testPlayer2);
        testCard.turnCard();
        assertTrue(testTer1.getOwner()==testPlayer2);
        assertTrue(testTer2.getOwner()==testPlayer1);
        assertFalse(testTer1.getOwner()==testTer2.getOwner());
    }
}
