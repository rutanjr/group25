package edu.chl.ChalmersRisk.model;

import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertFalse;

/**
 * Created by viking on 28/05/15.
 */
public class PlayerTest {
    @Test
    public void testMoveTroops() throws Exception {

        ArrayList<Territory> territories = new ArrayList<Territory>();
        Continent testContinent = new Continent("test",1,territories);
        Player testPlayer1 = new Player("1", "red");
        Player testPlayer2 = new Player("2", "blue");

        Territory testTer1 = new Territory("Test1",testContinent,testPlayer1);
        Territory testTer2 = new Territory("Test2",testContinent,testPlayer1);
        Territory testTer3 = new Territory("Test3",testContinent,testPlayer2);
        Territory testTer4 = new Territory("Test4",testContinent,testPlayer2);


        ArrayList<Territory> adjTers1 = new ArrayList<Territory>();
        adjTers1.add(testTer2);
        adjTers1.add(testTer3);
        testTer1.addAdjacent(adjTers1);

        ArrayList<Territory> adjTers2 = new ArrayList<Territory>();
        adjTers2.add(testTer4);
        testTer3.addAdjacent(adjTers2);

        //Test moving 1 troop when there are no troops available to move.
        testTer1.addTroops(1);
        assertFalse(testPlayer1.moveTroops(testTer1, testTer2, 1));

        //Test moving all troops when there are troops available to move.
        testTer1.addTroops(2);
        assertTrue(testPlayer1.moveTroops(testTer1,testTer2));
        assertTrue(testTer2.getAmountOfTroops()==2);

        //Test moving to troops to a territory owned by another player.
        testTer1.addTroops(2);
        int oldAmt = testTer3.getAmountOfTroops();
        assertFalse(testPlayer1.moveTroops(testTer1, testTer3));
        assertTrue(testTer3.getAmountOfTroops() == oldAmt);

        //Test moving a negative amount of troops
        testTer1.addTroops(2);
        assertFalse(testPlayer1.moveTroops(testTer1, testTer2, -3));

        //Test moving another player's troops.
        testTer3.addTroops(4);
        assertFalse(testPlayer1.moveTroops(testTer3, testTer4, 3));

    }

    @Test
    public void testCombat() throws Exception {

    }
}
