package edu.chl.ChalmersRisk.cardModels;

import edu.chl.ChalmersRisk.model.Continent;
import edu.chl.ChalmersRisk.model.Player;
import edu.chl.ChalmersRisk.model.Territory;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertTrue;

/**
 * Created by viking on 22/05/15.
 */
public class AllChangeTroopCardTest {
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
        testTer1.addTroops(5);
        testTer2.addTroops(5);

        ICard testCard = new AllChangeTroopCard(testList,2);
        testCard.turnCard();

        assertTrue(testTer1.getAmountOfTroops()==7);
        assertTrue(testTer2.getAmountOfTroops()==7);

        ICard testCard2 = new AllChangeTroopCard(testList,-3);
        testCard2.turnCard();

        assertTrue(testTer1.getAmountOfTroops()==4);
        assertTrue(testTer2.getAmountOfTroops()==4);
    }
}
