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
public class ChalmersRiskTest {

    @Test
    public void testTerritoriesAreConnected(){
        ArrayList<Territory> territories = new ArrayList<Territory>();
        Continent testContinent = new Continent("test",1,territories);
        Player testPlayer1 = new Player("1");
        Player testPlayer2 = new Player("2");


        Territory testTer1 = new Territory("Test1",testContinent,testPlayer1);
        Territory testTer2 = new Territory("Test1",testContinent,testPlayer1);

        //Test when no territories are connected.
        assertFalse(ChalmersRisk.territoriesAreConnected(testTer1, testTer2, testPlayer1));

        ArrayList<Territory> adjTers = new ArrayList<Territory>();
        adjTers.add(testTer2);
        testTer1.addAdjacent(adjTers);

        //Test when two territories are connected.
        assertTrue(ChalmersRisk.territoriesAreConnected(testTer1, testTer2, testPlayer1));

        //Test with a chain of connected territories.

        //Test with a chain that includes a loop. 
    }
}
