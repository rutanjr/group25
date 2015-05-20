package edu.chl.ChalmersRisk.controller;

import edu.chl.ChalmersRisk.model.Continent;
import edu.chl.ChalmersRisk.model.Player;
import edu.chl.ChalmersRisk.model.Territory;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Created by Björn Bergqvist on 28/04/15.
 */
public class ChalmersRiskTestCombat {

    @Test (expected = IllegalArgumentException.class)
    public void testCombatThrowsExceptionWhenTooFewTroops(){

       /* ChalmersRisk testRisk2 = new ChalmersRisk();
        ArrayList<Territory> territories = new ArrayList<Territory>();
        Continent testContinent = new Continent("test",1,territories);
        Player testPlayer1 = new Player("1");
        Player testPlayer2 = new Player("2");

        Territory testTer1 = new Territory("Test1",testContinent,testPlayer1);
        Territory testTer2 = new Territory("Test2",testContinent,testPlayer2);
        Territory testTer3 = new Territory("Test3",testContinent,testPlayer2);

        testTer1.addTroops(1);
        testTer2.addTroops(1);

        testRisk2.combat(testTer1,testTer2);
        testRisk2.combat(testTer3,testTer2);
        */

    }
}
