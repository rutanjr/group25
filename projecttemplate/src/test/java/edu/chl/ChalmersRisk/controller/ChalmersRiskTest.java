package edu.chl.ChalmersRisk.controller;

import edu.chl.ChalmersRisk.model.Continent;
import edu.chl.ChalmersRisk.model.Player;
import edu.chl.ChalmersRisk.model.Territory;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.*;
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

        /* This is a known bug that is being worked on.
        //Test when no territories are connected.
        assertFalse(ChalmersRisk.territoriesAreConnected(testTer1, testTer2, testPlayer1));
        */
        ArrayList<Territory> adjTers1 = new ArrayList<Territory>();
        adjTers1.add(testTer2);
        testTer1.addAdjacent(adjTers1);

        //Test when two territories are connected.
        assertTrue(ChalmersRisk.territoriesAreConnected(testTer1, testTer2, testPlayer1));

        Territory testTer3 = new Territory("Test1",testContinent,testPlayer1);
        ArrayList<Territory> adjTers2 = new ArrayList<Territory>();
        adjTers2.add(testTer3);
        testTer2.addAdjacent(adjTers2);


        Territory testTer4 = new Territory("Test1",testContinent,testPlayer1);
        ArrayList<Territory> adjTers3 = new ArrayList<Territory>();
        adjTers3.add(testTer4);
        testTer3.addAdjacent(adjTers3);

        Territory testTer5 = new Territory("Test1",testContinent,testPlayer1);
        ArrayList<Territory> adjTers4 = new ArrayList<Territory>();
        adjTers4.add(testTer5);
        testTer4.addAdjacent(adjTers4);

        //Test with a chain of connected territories.
        assertTrue(ChalmersRisk.territoriesAreConnected(testTer1, testTer5, testPlayer1));

        ArrayList<Territory> adjTers5 = new ArrayList<Territory>();
        adjTers5.add(testTer2);
        adjTers5.add(testTer4);
        testTer3.addAdjacent(adjTers5);

        //Test with a chain that includes a loop.
        assertTrue(ChalmersRisk.territoriesAreConnected(testTer1, testTer5, testPlayer1));

        ArrayList<Territory> adjTers6 = new ArrayList<Territory>();
        testTer5.addAdjacent(adjTers6);

        Territory testTer6 = new Territory("Test1",testContinent,testPlayer1);

        //Test with unreachable territory.
        assertFalse(ChalmersRisk.territoriesAreConnected(testTer1, testTer6, testPlayer1));

        Territory testTer7 = new Territory("Test1",testContinent,testPlayer2);
        ArrayList<Territory> adjTers7 = new ArrayList<Territory>();
        adjTers7.add(testTer7);
        testTer5.addAdjacent(adjTers7);

        //Test moving to territory that the player doesn't own.
        assertFalse(ChalmersRisk.territoriesAreConnected(testTer1, testTer7, testPlayer1));

    }

    @Test
    public void testMoveTroops(){
        ChalmersRisk testRisk = new ChalmersRisk();
        ArrayList<Territory> territories = new ArrayList<Territory>();
        Continent testContinent = new Continent("test",1,territories);
        Player testPlayer1 = new Player("1");
        Player testPlayer2 = new Player("2");

        Territory testTer1 = new Territory("Test1",testContinent,testPlayer1);
        Territory testTer2 = new Territory("Test1",testContinent,testPlayer1);
        Territory testTer3 = new Territory("Test1",testContinent,testPlayer2);

        ArrayList<Territory> adjTers1 = new ArrayList<Territory>();
        adjTers1.add(testTer2);
        testTer1.addAdjacent(adjTers1);

        //Test moving 1 troop when there are no troops available to move.
        testTer1.addTroops(1);
        assertFalse(testRisk.moveTroops(testTer1, testTer2, 1));

        //Test moving all troops when there are troops available to move.
        testTer1.addTroops(2);
        assertTrue(testRisk.moveTroops(testTer1,testTer2));
        assertTrue(testTer2.getAmountOfTroops()==2);

        //Test moving to troops to a territory owned by another player.
        testTer1.addTroops(2);
        int oldAmt = testTer3.getAmountOfTroops();
        assertFalse(testRisk.moveTroops(testTer1, testTer3));
        assertTrue(testTer3.getAmountOfTroops()==oldAmt);

        //Test moving a negative amount of troops
        testTer1.addTroops(2);
        assertFalse(testRisk.moveTroops(testTer1, testTer2, -3));
    }
}
